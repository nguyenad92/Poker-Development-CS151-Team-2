import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * This PokerGame class will be the main loop to generate the game
 */
public class PokerGame {

    private ArrayList<Player> playerList;
    private Dealer dealer;
    private DeckOfCard deckOfCard;
    private Table table;
    private Pot pot;
    private int dealerPosition, currentPlayerPosition, bigBlind;

    public PokerGame(int bigBlind) {
        this.table = new Table();
        this.dealer = new Dealer();
        this.deckOfCard = new DeckOfCard();
        this.playerList = new ArrayList<>();
        this.pot = new Pot();
        this.bigBlind = bigBlind;
    }


    /**
     * Main Function will run the whole program
     *
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        JTextArea area = new JTextArea(20, 40); // 20 rows, 40 columns
        JScrollPane scroller = new JScrollPane(area);
        frame.add(scroller, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }

    private void run() {
        for (Player player : playerList) {
            addPlayer(player);
        }
        dealerPosition = -1;
        currentPlayerPosition = -1;
        while (true) {
            int noOfActivePlayers = 0;
            for (Player player : playerList) {
                if (player.getMoney() >= bigBlind) {
                    noOfActivePlayers++;
                }
            }
            if (noOfActivePlayers > 1) {
                playHand();
            } else {
                break;
            }
        }

        // Reset Everything when oneHand is finished
//        table.clear();
//        pots.clear();
        table.setTotalMoney(0);
        for (Player player : playerList) {
            player.resetHand();
        }
        System.out.println("No one is at the table OR others don't have enough money to play");
    }


    private void playHand() {
        // Dealer
    }

    /**
     * Adds a player.
     * @param player
     *            The player.
     */
    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public void addDealer(Dealer d) {

    }

    public void resetGame() {
    }
}