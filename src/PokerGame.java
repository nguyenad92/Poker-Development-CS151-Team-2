import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * This PokerGame class will be the main loop to generate the game
 */
public class PokerGame {

    private ArrayList<Player> playerList = new ArrayList<>();
    private Dealer dealer = new Dealer();
    private DeckOfCard deckOfCard = new DeckOfCard();
    private Table table = new Table();

    /**
     * Main Function will run the whole program
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

    public void addPlayer(Player p) {

    }

    public void addDealer(Dealer d) {
    }

    public void resetGame() {
    }
}