package edu.sjsu.cs.cs151.Controller;
import edu.sjsu.cs.cs151.Message.*;
import edu.sjsu.cs.cs151.Model.Model;
import edu.sjsu.cs.cs151.View.*;

import java.util.*;
import java.util.concurrent.*;

/**
 * Controller: will be controlling the game and have all actions of View and Model
 */
public class Controller {
    private BlockingQueue<Message> queue;
    private View view;
    private Model model;
    private List<Valve> valves = new LinkedList<>();
    private GameInfo gameInfo;

    /**
     * Constructor: Connect the Model, View, and message Queue
     * @param view: GUI of the app
     * @param model: model of the app
     * @param queue: queue connecting View and Model
     */
    public Controller(View view, Model model, BlockingQueue<Message> queue) {
        this.view = view;
        this.model = model;
        this.queue = queue;
        addAllValves();
    }

    /**
     * Main Loop to Execute the message from the View
     * @throws Exception
     */
    public void mainLoop() throws Exception {
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;

        while (response != ValveResponse.FINISH) {
            try {
                message = (Message) queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (Valve valve : valves) {
                response = valve.execute(message);
                if (response != ValveResponse.MISS) break;
            }
        }
    }

    /**
     *
     * @return the View of the App
     */
    public View getView() {
        return view;
    }

    /**
     *
     * @return the model of the App
     */
    public Model getModel() {
        return model;
    }

    /**
     * Update the new Model
     * @return an updated Model wih changes
     */
    private GameInfo updateGameInfo() {
        gameInfo = new GameInfo(model);
        return gameInfo;
    }

    /**
     * Add all the Valve so the mainloop can execute the message
     */
    private void addAllValves() {
        valves.add(new StartNewGameValve());
        valves.add(new newHandValve());
        valves.add(new DoActionCheckValve());
        valves.add(new DoActionCallValve());
        valves.add(new DoActionBetValve());
        valves.add(new DoActionRaiseValve());
        valves.add(new DoActionFoldValve());

    }

    /**
     * Update all the View Panel
     * @param action: Current action of
     */
    private void updateGame(String action) {
        view.setGamePanel(updateGameInfo());
        view.setInfoPannel(updateGameInfo());
        view.setPlayerPanel(updateGameInfo());
        view.setControlPannel(updateGameInfo(), action);
    }

    /**
     * Start a new Game
     */
    private class StartNewGameValve implements Valve {
        public ValveResponse execute(Message message) {
            if (message.getClass() != NewGameMessage.class) {
                return ValveResponse.MISS;
            }

            model.start();
            model.resetHand();

            model.setIsOver(false);
            model.dealPreFlop();
            model.nextPlayerToAct();
            view.setMessage(updateGameInfo(), "The game begins: " + updateGameInfo().getCurrentPlayer().getName() + " is the first player!");

            updateGame("NEW_GAME");

            return ValveResponse.EXECUTED;
        }
    }

    /**
     * Call Action
     */
    public class DoActionCallValve implements Valve {
        
        public ValveResponse execute(Message message) {

            if (message.getClass() != ActionCallMessage.class) {
                return ValveResponse.MISS;
            }

            model.call();
            view.setMessage(updateGameInfo(), updateGameInfo().getCurrentPlayer().getName() + " just Call!");
            model.dealCardByStage();

            if (model.isStarted() && !model.isOver()) {
                view.setMessage(updateGameInfo(), updateGameInfo().getWinner().getName() + " win a hand for " + updateGameInfo().getPotTotal());
                updateGame("NEW_HAND");
            } else {
                updateGame("CALL");
            }

            return ValveResponse.EXECUTED;
        }
    }

    public class newHandValve implements Valve {

        public ValveResponse execute(Message message) {
            if (message.getClass() != newHandActionMessage.class) {
                return ValveResponse.MISS;
            }

            model.resetHand();

            if (model.isOver()) {
                view.setMessage(updateGameInfo(), updateGameInfo().getCurrentPlayer().getName() + " is a Winner");
                updateGame("");
            } else {
                model.setIsOver(false);
                model.dealPreFlop();
                model.nextPlayerToAct();
                view.setMessage(updateGameInfo(), "The game begins: " + updateGameInfo().getCurrentPlayer().getName() + " is the first player!");

                updateGame("NEW_GAME");
            }



            return ValveResponse.EXECUTED;
        }
    }

    /**
     * Check Action
     */
    public class DoActionCheckValve implements Valve {

        public ValveResponse execute(Message message) {
            if (message.getClass() != ActionCheckMessage.class) {
                return ValveResponse.MISS;
            }

            model.check();
            view.setMessage(updateGameInfo(), updateGameInfo().getCurrentPlayer().getName() + " just Check!");
            model.dealCardByStage();

            if (model.isStarted() && !model.isOver()) {
                view.setMessage(updateGameInfo(), updateGameInfo().getWinner().getName() + " win a hand for " + updateGameInfo().getPotTotal() + " with a " + updateGameInfo().getWinner().getRankedHand().getRankedHandType().getHandType());
                updateGame("NEW_HAND");

            } else {
                updateGame("CHECK");

            }

            return ValveResponse.EXECUTED;
        }
    }

    /**
     * Raise Action
     */
    public class DoActionRaiseValve implements Valve {

        public ValveResponse execute(Message message) {
            //if(!(message instanceof NewGameMessage))
            if (message.getClass() != ActionRaiseMessage.class) {
                return ValveResponse.MISS;
            }

            model.raise(message.getAmount());

            view.setMessage(updateGameInfo(), updateGameInfo().getCurrentPlayer().getName() + " Raise " + message.getAmount());

            model.nextPlayerToAct();
            updateGame("RAISE");

            return ValveResponse.EXECUTED;
        }
    }

    /**
     * Bet Action
     */
    public class DoActionBetValve implements Valve {

        public ValveResponse execute(Message message) {

            if (message.getClass() != ActionBetMessage.class) {
                return ValveResponse.MISS;
            }

            model.bet(message.getAmount());
            view.setMessage(updateGameInfo(), updateGameInfo().getCurrentPlayer().getName() + " just Bet $" + message.getAmount());
            model.nextPlayerToAct();
            updateGame("BET");

            return ValveResponse.EXECUTED;
        }
    }

    /**
     * Fold Action
     */
    public class DoActionFoldValve implements Valve {

        public ValveResponse execute(Message message) {
            //if(!(message instanceof NewGameMessage))
            if (message.getClass() != ActionFoldMessage.class) {
                return ValveResponse.MISS;
            }

            model.fold();

            view.setMessage(updateGameInfo(), updateGameInfo().getWinner().getName() + " win a hand for " + updateGameInfo().getPotTotal());
            model.nextPlayerToAct();

            System.out.println(model.getCurrentActor().getName());
            updateGame("NEW_HAND");

            return ValveResponse.EXECUTED;
        }
    }
}
