package edu.sjsu.cs.cs151.Controller;
import edu.sjsu.cs.cs151.Message.*;
import edu.sjsu.cs.cs151.Model.Model;
import edu.sjsu.cs.cs151.View.*;

import java.util.*;
import java.util.concurrent.*;

public class Controller {
    private BlockingQueue<Message> queue;
    private View view;
    private Model model;
    private List<Valve> valves = new LinkedList<>();
    private GameInfo gameInfo;

    public Controller(View view, Model model, BlockingQueue<Message> queue) {
        this.view = view;
        this.model = model;
        this.queue = queue;
        addAllValves();
    }

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

    public View getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }

    private GameInfo updateGameInfo() {
        gameInfo = new GameInfo(model);
        return gameInfo;
    }

    private void addAllValves() {
        valves.add(new StartNewGameValve());
        valves.add(new DoActionCheckValve());
        valves.add(new DoActionCallValve());
        valves.add(new DoActionBetValve());
        valves.add(new DoActionRaiseValve());
        valves.add(new DoActionFoldValve());
    }

    private void updateGame(String action) {
        view.setGamePanel(updateGameInfo());
        view.setInfoPannel(updateGameInfo());
        view.setPlayerPannel(updateGameInfo());
        view.setControlPannel(updateGameInfo(), action);
    }

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

    public class DoActionBetValve implements Valve {

        public ValveResponse execute(Message message) {

            if (message.getClass() != ActionBetMessage.class) {
                return ValveResponse.MISS;
            }

            model.bet(message.getAmount());
            view.setMessage(gameInfo, "Bet " + message.getAmount());
            model.nextPlayerToAct();
            updateGame("BET");

            return ValveResponse.EXECUTED;
        }
    }

    public class DoActionCallValve implements Valve {
        
        public ValveResponse execute(Message message) {

            if (message.getClass() != ActionCallMessage.class) {
                return ValveResponse.MISS;
            }

            model.call();

            model.dealCardByStage();

            if (model.isStarted() && !model.isOver()) {
                model.dealPreFlop();
                view.setMessage(updateGameInfo(), updateGameInfo().getCurrentPlayer().getName() + " win a hand for " + updateGameInfo().getPotTotal());
                model.nextPlayerToAct();
                updateGame("CALL");
            } else if (model.isOver()) {
                view.setMessage(updateGameInfo(), updateGameInfo().getCurrentPlayer().getName() + " is a Winner");
                model.resetHand();
                view = View.init(queue);
            } else {
                view.setMessage(updateGameInfo(), updateGameInfo().getCurrentPlayer().getName() + " just Call!");
                model.nextPlayerToAct();
                updateGame("CALL");
            }

            return ValveResponse.EXECUTED;
        }
    }

    public class DoActionCheckValve implements Valve {

        public ValveResponse execute(Message message) {
            if (message.getClass() != ActionCheckMessage.class) {
                return ValveResponse.MISS;
            }

            model.check();

            model.dealCardByStage();

            if (model.isStarted() && !model.isOver()) {
                model.dealPreFlop();
                view.setMessage(updateGameInfo(), updateGameInfo().getCurrentPlayer().getName() + " win a hand for " + updateGameInfo().getPotTotal());
                updateGame("CHECK");
                model.nextPlayerToAct();
            } else if (model.isOver()) {
                view.setMessage(updateGameInfo(), updateGameInfo().getCurrentPlayer().getName() + " is a Winner");
                model.resetHand();
                view = View.init(queue);
            } else {
                view.setMessage(updateGameInfo(), updateGameInfo().getCurrentPlayer().getName() + " just Check!");

                updateGame("CHECK");
                model.nextPlayerToAct();
            }

            return ValveResponse.EXECUTED;
        }
    }

    public class DoActionRaiseValve implements Valve {

        public ValveResponse execute(Message message) {
            //if(!(message instanceof NewGameMessage))
            if (message.getClass() != ActionRaiseMessage.class) {
                return ValveResponse.MISS;
            }

            model.raise(message.getAmount());

            view.setMessage(updateGameInfo(), updateGameInfo().getCurrentPlayer().getName() + " Raise " + message.getAmount());

            updateGame("RAISE");

            model.nextPlayerToAct();


            return ValveResponse.EXECUTED;
        }
    }

    public class DoActionFoldValve implements Valve {

        public ValveResponse execute(Message message) {
            //if(!(message instanceof NewGameMessage))
            if (message.getClass() != ActionFoldMessage.class) {
                return ValveResponse.MISS;
            }

            model.fold();

            model.nextPlayerToAct();

            model.dealPreFlop();

            updateGame("CHECK");

            return ValveResponse.EXECUTED;
        }
    }
}
