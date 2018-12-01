import java.util.List;

/**
 * An observer showing the table information and acting of players
 * @author ADNguyen
 *
 */
public interface Observer {
	
	//rotate player's turn in a game
	void rotatePosition(Player actor);
	
	//Update information on the table
	void tableUpdated(List<Card> cards, int bet, int pot);
	
	//Handle the event of a player acting
	void playerActed(Player player);
	
	

}
