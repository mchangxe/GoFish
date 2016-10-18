import java.util.Map;

/**
 * Created by Museum2015 on 15/10/2016.
 */
public abstract class cpu {

    protected Map<Integer, Integer> cards;
    protected int points;
    protected int playerNumber;

    public Map<Integer, Integer>getCards(){return cards;}
    public int getPoints(){return points;}
    public int getPlayerNumber(){return playerNumber;}
    public void setCards(int rank, int numberOfCards){
        cards.put(rank, numberOfCards);
    }


    /**
     * A player receives a card of a given rank in a given quantity. The function adds those cards to the player's
     * collection
     * @param rank the rank of the card
     * @param numberOfCards the number of cards in that rank the player is receiving
     */
    public abstract void receiveCard(int rank, int numberOfCards) throws Exception;

    /**
     * Checks to see if a player has cards of a certain rank.
     * @param rank the card rank that another player is asking for
     * @return an int of how many cards the player needs to give to the player asking
     */
    public abstract int giveCard(int rank);

    /**
     * Decide for a player what card rank to ask
     * @param opponent the opponent of the current player
     * @return the card rank that the player is going to ask for
     */
    public abstract int askForCard(int opponent);

    /**
     * Picks an opponent randomly for a player
     * @param totalNumOfPlayer the total number of player in the current game
     * @return the number of the player who is the new opponent of the current player
     */
    public abstract int picksOpponent(int totalNumOfPlayer);

    /**
     * Checks if a player has a book and should receive a point
     * @return true if a player has 4 cards of the same rank. False if otherwise
     */
    public boolean checkBook(){
        boolean gotCheck = false;
        for (Integer key: cards.keySet()){
            if (cards.get(key) == 4){
                gotCheck = true;
                points++;
            }
        }
        return gotCheck;
    }
}
