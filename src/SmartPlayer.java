import java.util.HashMap;
import java.util.Map;

/**
 * Created by Museum2015 on 6/10/2016.
 */
public class SmartPlayer extends cpu{

    private Map<Integer, Integer> movesMadeByOtherPlayers;
    public void setMovesMadeByOtherPlayers(Map<Integer, Integer> movesMadeByOtherPlayers) {
        this.movesMadeByOtherPlayers = movesMadeByOtherPlayers;
    }

    @Override
    public boolean equals(Object other){
        SmartPlayer otherPlayer = (SmartPlayer) other;
        if (this.getPoints()==otherPlayer.getPoints()
                && this.getPlayerNumber() == otherPlayer.getPlayerNumber()
                && this.getCards().equals(otherPlayer.getCards())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * The smart player can remember what happened in the game so far and act accordingly to moves made by
     * other players.
     * @param games
     */
    public void getLastMoves(GoFishGames games){
        movesMadeByOtherPlayers = games.getAllMoves();
    }

    /**
     * SmartPlayer class constructor that creates a player object, sets his points, playerNumber and gives him an empty
     * card collection
     * @param numberOfPoints the amount of points the player has
     * @param number the player's number
     */
    public SmartPlayer(int numberOfPoints, int number){
        this.points = numberOfPoints;
        this.playerNumber = number;
        this.cards = new HashMap<Integer, Integer>();
        for (int i=1; i<14; i++){
            this.cards.put(i, 0);
        }
        movesMadeByOtherPlayers = new HashMap<Integer,Integer>();
    }

    /**
     * A player receives a card of a given rank in a given quantity. The function adds those cards to the player's
     * collection
     * @param rank the rank of the card
     * @param numberOfCards the number of cards in that rank the player is receiving
     */
    public void receiveCard(int rank, int numberOfCards) throws Exception {
        if (rank > 13 || numberOfCards > 4){
            throw new Exception("Either the rank doesn't exist or there exists more than 4 four cards of that rank");
        }

        int before = cards.get(rank);
        int after = before + numberOfCards;
        cards.put(rank, after);

    }

    /**
     * Checks to see if a player has cards of a certain rank.
     * @param rank the card rank that another player is asking for
     * @return an int of how many cards the player needs to give to the player asking
     */
    public int giveCard(int rank){
        int numberOfCardsToGive = 0;
        if (cards.get(rank) != 0){
            numberOfCardsToGive = cards.get(rank);
            cards.put(rank, 0);

        }else{
            //Go FISH!
        }
        return numberOfCardsToGive;
    }

    /**
     * Decide for a player what card rank to ask
     * @param opponent the opponent of the current player
     * @return the card rank that the player is going to ask for
     */
    public int askForCard(int opponent){
        int rank = 999;

        //check is other players have received cards that he could use for a book. If so ask for those cards
        for (int playerNumber: movesMadeByOtherPlayers.keySet()){
            if (getCards().get(movesMadeByOtherPlayers.get(playerNumber)) != 0 ){
                rank = movesMadeByOtherPlayers.get(playerNumber);
            }
        }

        if(rank==999) {
            rank = (int) (Math.random() * 12) + 1;
            while (this.getCards().get(rank) == 4 || this.getCards().get(rank)==0) {
                rank = (int) (Math.random() * 12) + 1;
            }
        }

        return rank;
    }

    /**
     * Picks an opponent randomly for a player
     * @param totalNumOfPlayer the total number of player in the current game
     * @return the number of the player who is the new opponent of the current player
     */
    public int picksOpponent(int totalNumOfPlayer){
        int randomOpponent = 999;

        //checks if other players have received cards that he could use for a book. If so, ask that player for cards
        for (int playerNumber: movesMadeByOtherPlayers.keySet()){
            if (getCards().get(movesMadeByOtherPlayers.get(playerNumber)) != 0 ){
                randomOpponent = playerNumber;
            }
        }

        //else just pick a random opponent
        if (randomOpponent==999) {
            randomOpponent = (int) (Math.random() * (totalNumOfPlayer - 1)) + 1;
            while (randomOpponent == playerNumber) {
                randomOpponent = (int) (Math.random() * (totalNumOfPlayer - 1)) + 1;
            }
        }

        return randomOpponent;
    }
}
