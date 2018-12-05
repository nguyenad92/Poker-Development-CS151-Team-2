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
            model.dealPreFlop();

            System.out.println("This is the current Bet: " + updateGameInfo().getCurrentBet());

            view.setInfoPannel(updateGameInfo());
            view.setPlayerPannel(updateGameInfo());
            view.setControlPannel("CHECK");

            return ValveResponse.EXECUTED;
        }
    }

    public class DoActionBetValve implements Valve {

        public ValveResponse execute(Message message) {

            if (message.getClass() != ActionBetMessage.class) {
                return ValveResponse.MISS;
            }

            model.bet(message.getAmount());
            model.nextPlayerToAct();

            view.setInfoPannel(updateGameInfo());
            view.setPlayerPannel(updateGameInfo());
            view.setControlPannel("BET");


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

            view.setInfoPannel(updateGameInfo());
            view.setPlayerPannel(updateGameInfo());
            view.setControlPannel("CALL");

            return ValveResponse.EXECUTED;
        }
    }

    public class DoActionCheckValve implements Valve {

        public ValveResponse execute(Message message) {
            if (message.getClass() != ActionCheckMessage.class) {
                return ValveResponse.MISS;
            }

            System.out.println("Player " + model.getCurrentActor().getName() + " just check");
            model.check();

            model.dealCardByStage();

            view.setPlayerPannel(updateGameInfo());
            view.setControlPannel("CHECK");

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
            model.nextPlayerToAct();

            view.setInfoPannel(updateGameInfo());
            view.setPlayerPannel(updateGameInfo());
            view.setControlPannel("RAISE");

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
            model.resetHand();
            model.dealPreFlop();

            view.setInfoPannel(updateGameInfo());
            view.setPlayerPannel(updateGameInfo());
            view.setControlPannel("CHECK");

            return ValveResponse.EXECUTED;
        }
    }

//    public class DoDealValve  implements Valve {
//
//        public ValveResponse execute(Message message) {
//            if (message.getClass() != DealCardMessage.class) {
//                return ValveResponse.MISS;
//            }
//
//            if (model.isFlop()) model.dealFlop();
//            else if (model.isTurn()) model.dealTurn();
//            else if (model.isRiver()) {
//                model.dealRiver();
//            }
//
//            view.setGamePanel(updateGameInfo());
//            view.setControlPannel("CHECK");
//
//            return ValveResponse.EXECUTED;
//        }
//    }
}
