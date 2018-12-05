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


    public GameInfo updateGameInfo() {
        gameInfo = new GameInfo(model);
        return gameInfo;
    }
    public View getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }

    public BlockingQueue<Message> getQueue() {
        return queue;
    }

    public List<Valve> getValves() {
        return valves;
    }

    public void addAllValves() {
        valves.add(new StartNewGameValve());
        valves.add(new DoActionCheckValve());
        valves.add(new DoActionCallValve());
        valves.add(new DoActionBetValve());
        valves.add(new DoActionRaiseValve());
        valves.add(new DoActionFoldValve());
//        valves.add(new DoDealValve());

    }

    private class StartNewGameValve implements Valve {
        public ValveResponse execute(Message message) {
            if (message.getClass() != NewGameMessage.class) {
                return ValveResponse.MISS;
            }

            model.start();

//            model.resetHand();
//            model.nextPlayerToAct();
            model.setIsOver(false);
            model.dealPreFlop();



            view.setGamePanel(updateGameInfo());
            view.setInfoPannel(updateGameInfo());
            view.setPlayerPannel(updateGameInfo());
            view.setControlPannel(gameInfo, "NEW_GAME");

            model.nextPlayerToAct();

            return ValveResponse.EXECUTED;
        }
    }

    public class DoActionBetValve implements Valve {

        public ValveResponse execute(Message message) {

            if (message.getClass() != ActionBetMessage.class) {
                return ValveResponse.MISS;
            }

            model.bet(message.getAmount());

            view.setInfoPannel(updateGameInfo());
            view.setPlayerPannel(updateGameInfo());
            view.setControlPannel(gameInfo, "BET");
            view.setMessage(gameInfo, "Bet " + message.getAmount());

            model.nextPlayerToAct();

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

            view.setGamePanel(updateGameInfo());
            view.setInfoPannel(updateGameInfo());
            view.setPlayerPannel(updateGameInfo());
            view.setControlPannel(gameInfo, "CALL");
            view.setMessage(gameInfo, "Call ");

            model.nextPlayerToAct();

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

                view.setControlPannel(updateGameInfo(), "CHECK");
                view.setInfoPannel(updateGameInfo());
                view.setGamePanel(updateGameInfo());
                view.setPlayerPannel(updateGameInfo());
//                view.setMessage(gameInfo, "win a hand for " + updateGameInfo().getPotTotal());
                model.nextPlayerToAct();
            } else if (model.isOver()) {

                System.out.println("GAME OVER -> Start a new Game");
                view = new View();
                model = new Model();


//                model.start();
//                model.resetHand();
//                model.dealPreFlop();

//                view.setControlPannel(updateGameInfo(), "NEW_GAME");
//                view.setInfoPannel(updateGameInfo());
//                view.setGamePanel(updateGameInfo());
//                view.setPlayerPannel(updateGameInfo());
            } else {
                view.setInfoPannel(updateGameInfo());
                view.setGamePanel(updateGameInfo());
                view.setPlayerPannel(updateGameInfo());
                view.setControlPannel(updateGameInfo(), "CHECK");
//                view.setMessage(gameInfo, "Check ");
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

            view.setInfoPannel(updateGameInfo());
            view.setPlayerPannel(updateGameInfo());
            view.setControlPannel(gameInfo, "RAISE");
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

            view.setGamePanel(updateGameInfo());
            view.setInfoPannel(updateGameInfo());
            view.setPlayerPannel(updateGameInfo());
            view.setControlPannel(updateGameInfo(), "CHECK");



            return ValveResponse.EXECUTED;
        }
    }
//    public class DoActionAllInValve implements Valve {
//
//        public ValveResponse execute(Message message) {
//            //if(!(message instanceof NewGameMessage))
//            if (message.getClass() != AllInactionMessage.class) {
//                return ValveResponse.MISS;
//            }
//
//            model.bet(message.getAmount());
//            model.nextPlayerToAct();
//
//            view.setInfoPannel(updateGameInfo());
//            view.setPlayerPannel(updateGameInfo());
//            view.setControlPannel(gameInfo, "ALL_IN");
//
//            return ValveResponse.EXECUTED;
//        }
//    }
}
