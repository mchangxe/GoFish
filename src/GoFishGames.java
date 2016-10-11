import java.util.HashMap;
import java.util.Map;

/**
 * Created by Museum2015 on 8/10/2016.
 */
public class GoFishGames {
    private Map<Integer, Integer> cardDeck;
    private int numOfCardsInDeck;
    private int numOfPlayersInGame;
    public Player[] allPlayers;

    public Map<Integer, Integer>getCardDeck(){
        return cardDeck;
    }

    public Player[] getAllPlayers(){
        return allPlayers;
    }

    public int getNumOfCardsInDeck(){
        return numOfCardsInDeck;
    }

    public int getNumOfPlayersInGame(){
        return numOfPlayersInGame;
    }

    public void setNumOfCardsInDeck(int x){ numOfCardsInDeck = x;}

    public void setCardDeck(Map<Integer, Integer> newDeck){ cardDeck = newDeck;}

    /**
     * GoFishGames constructor that creates a new GoFishGames object, sets the numOfPlayersInGame variable to num
     * ,creates the according player objects and creates a new card deck of 52 cards.
     * @param num number of players that is in this game
     */
    public GoFishGames(int num){
        numOfPlayersInGame = num;
        createPlayers(num);
        getNewDeck();
    }

    /**
     * Creates a number of player objects according to the parameter numOfPlayers.
     * Sets each player's score to 0 and player number to their respective number
     * @param numOfPlayers number of players needed to be created
     */
    public void createPlayers(int numOfPlayers){
        allPlayers = new Player[numOfPlayers];
        for(int i=0; i<numOfPlayers; i++){
            allPlayers[i] = new Player(0, i+1);
        }
    }

    /**
     * Creates a new deck for the game. A deck has card rank 1 to 13 and each rank has 4 cards.
     */
    public void getNewDeck(){
        cardDeck = new HashMap<Integer, Integer>();
        for (int i=1; i<14; i++){
            cardDeck.put(i, 4);
        }
        numOfCardsInDeck = 52;
    }

    /**
     * Checks if there is any cards left in the deck
     * @return true if the deck is empty and the game has ended. False if otherwise
     */
    public boolean checkDeckEmpty(){
        if (this.getNumOfCardsInDeck() == 0){
            return true;
        }else return false;
    }

    /**
     * A Player adds a card from the card deck to his own collection of cards.
     * @param x the player that is drawing a card from the card deck
     */
    public void drawCard(Player x){
        int randomRank = (int) ((Math.random()*13) + 1);
        while (cardDeck.get(randomRank) == 0){
            randomRank = (int)(Math.random()*13) + 1;
        }
        x.getCards().put(randomRank, x.getCards().get(randomRank)+1);
        cardDeck.put(randomRank, cardDeck.get(randomRank)-1);
        System.out.println("Player"+x.getPlayerNumber()+" draws a card from deck.");
        numOfCardsInDeck--;
    }



}
