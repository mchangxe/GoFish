import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Museum2015 on 6/10/2016.
 */
public class Player {

    private Map<Integer, Integer> cards;
    private int points;
    private int playerNumber;

    public Map<Integer, Integer>getCards(){return cards;}
    public int getPoints(){return points;}
    public int getPlayerNumber(){return playerNumber;}
    public void setCards(int rank, int numberOfCards){
        cards.put(rank, numberOfCards);
    }

    @Override
    public boolean equals(Object other){
        Player otherPlayer = (Player) other;
        if (this.getPoints()==otherPlayer.getPoints()
                && this.getPlayerNumber() == otherPlayer.getPlayerNumber()
                && this.getCards().equals(otherPlayer.getCards())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Player class constructor that creates a player object, sets his points, playerNumber and gives him an empty
     * card collection
     * @param numberOfPoints the amount of points the player has
     * @param number the player's number
     */
    public Player(int numberOfPoints, int number){
        this.points = numberOfPoints;
        this.playerNumber = number;
        this.cards = new HashMap<Integer, Integer>();
        for (int i=1; i<14; i++){
            this.cards.put(i, 0);
        }
    }

    /**
     * A player receives a card of a given rank in a given quantity. The function adds those cards to the player's
     * collection
     * @param rank the rank of the card
     * @param numberOfCards the number of cards in that rank the player is receiving
     * @return all neighbors of the city in a list
     */
    public void receiveCard(int rank, int numberOfCards) throws Exception {
        if (rank > 13 || numberOfCards > 4){
            throw new Exception("Either the rank doesn't exist or there exists more than 4 four cards of that rank");
        }

        int before = cards.get(rank);
        int after = before + numberOfCards;
        cards.put(rank, after);
        System.out.println("Player" + playerNumber + " received " + numberOfCards + " " + rank + " cards");
        System.out.println("He/She now has " + after + " " + rank + "cards");
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
            System.out.println("I have " + numberOfCardsToGive + " " + rank + ", here.");
        }else{
            System.out.println("HAHA, GO FISH!");
        }
        return numberOfCardsToGive;
    }

    /**
     * Decide for a player what card rank to ask
     * @param opponent the opponent of the current player
     * @return the card rank that the player is going to ask for
     */
    public int askForCard(int opponent){
        int rank = (int) (Math.random() * 12) + 1;
        while (this.getCards().get(rank) == 4){
            rank = (int) (Math.random() * 12) + 1;
        }

        System.out.println(this.getPlayerNumber() + " asks Player" + opponent + " for any " + rank);
        return rank;
    }

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

    /**
     * Picks an opponent randomly for a player
     * @param totalNumOfPlayer the total number of player in the current game
     * @return the number of the player who is the new opponent of the current player
     */
    public int picksOpponent(int totalNumOfPlayer){
        int randomOpponent = (int) (Math.random() * (totalNumOfPlayer-1)) + 1;
        while (randomOpponent == playerNumber){
            randomOpponent = (int) (Math.random() * (totalNumOfPlayer-1)) + 1;
        }
        return randomOpponent;
    }
}
