import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * This PokerGame class will be the main loop to generate the game
 */
public class PokerGame {

    private ArrayList<Player> playerList;
    private ArrayList<Card> communityCardList;
    private DeckOfCard deckOfCard;
    private Table table;
    private int dealerPosition, currentPlayerPosition, bigBlind;
    private RankedHand handComparison;


    public PokerGame(Player p1, Player p2) {
        this.table = new Table();
        this.deckOfCard = new DeckOfCard();
        this.playerList = new ArrayList<>();
        this.handComparison = new RankedHand();

        // Add Player
        addPlayer(p1);
        addPlayer(p2);
    }


    /**
     * Main Function will run the whole program
     *
     * @param args
     */
    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//
//        JTextArea area = new JTextArea(20, 40); // 20 rows, 40 columns
//        JScrollPane scroller = new JScrollPane(area);
//        frame.add(scroller, BorderLayout.CENTER);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(200, 200);
//        frame.setVisible(true);

//        PokerGame main = new PokerGame();

    }

    private void start() {
        dealerPosition = -1;
        currentPlayerPosition = -1;

        while (true) {
            int noOfActivePlayers = 0;
            for (Player player : playerList) {
                if (player.getMoney() >= 0) {
                    noOfActivePlayers++;
                }
            }
            if (noOfActivePlayers > 1) {
                playHand();
            } else {
                break;
            }
        }

        // Reset Everything when oneHand is finished or the other loses all Money
        resetGame();
        for (Player player : playerList) {
            player.resetHand();
        }
        System.out.println("No one is at the table OR others don't have enough money to play");
    }


    private void playHand() {


        // Set BigBlind and Small Blind



        // Deck Shuffle




        // Deck deal Preflop
        // Player starts betting

        while ()
        // Deck deal Flop
        // Player starts betting


        // Deck deal Turn
        // Player starts betting


        // Deck deal River
        // Player starts betting


        // ShowDown



        // Done




    }


    public void betting() {

    }

    /**
     * Adds a player.
     * @param player
     *            The player.
     */
    public void addPlayer(Player player) {
        playerList.add(player);
    }


    public void resetGame() {
        // Clear the Pot Contribution

        // reset Deck


        // clear the card in the communityCardList
    }
}