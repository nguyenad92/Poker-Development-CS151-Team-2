/**
 * A class to create a dealer, access table, 
 * and deal card to player and on table.
 * Dealer can also be used to reset the game
 */
public class Dealer {
    private int ID;
    public String name;
    private DeckOfCard deck;
    private Table table;

    /**
     * Construct a dealer object
     */
    public Dealer(){

        deck = new DeckOfCard();
    }

    /**
     * set the ID for dealer
     * @param id
     */
    public void setID(int id){

        ID = id;
    }

    /**
     * Set the name for dealer
     * @param name
     */
    public void setName(String name){

        this.name = name;
    }

    /**
     * Get a deck of card from dealer
     * @return deck
     */
    public DeckOfCard getDeck(){

        return deck;
    }

    /**
     * Set a table for dealer
     * @param table
     */
    public void setTable(Table table){

        this.table = table;
    }

}
