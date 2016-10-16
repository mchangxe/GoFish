import java.text.MessageFormat;
import java.util.*;

/**
 * Created by Museum2015 on 8/10/2016.
 */
public class GoFishGames {
    private Map<Integer, Integer> cardDeck;
    private int numOfCardsInDeck;
    private int numOfPlayersInGame;
    private Map<Integer, Integer> allMoves;
    public cpu[] allPlayers;

    public Map<Integer, Integer>getCardDeck(){
        return cardDeck;
    }
    public cpu[] getAllPlayers(){
        return allPlayers;
    }
    public int getNumOfCardsInDeck(){
        return numOfCardsInDeck;
    }
    public void setNumOfCardsInDeck(int x){ numOfCardsInDeck = x;}
    public void setCardDeck(Map<Integer, Integer> newDeck){ cardDeck = newDeck;}
    public Map<Integer, Integer> getAllMoves() {return allMoves;}

    /**
     * GoFishGames constructor that creates a new GoFishGames object, sets the numOfPlayersInGame variable to num
     * ,creates the according player objects and creates a new card deck of 52 cards.
     * @param stupid number of stupid players that is in this game
     * @param smart number of smart players that is in this game
     */
    public GoFishGames(int stupid, int smart){
        numOfPlayersInGame = stupid+smart;
        createPlayers(stupid, smart);
        getNewDeck();
        resetLastMoves();
    }

    /**
     * Sets the record of all moves made by players in the game to empty.
     */
    public void resetLastMoves(){
        allMoves = new HashMap<Integer,Integer>();
    }

    /**
     * Creates a number of player objects according to the parameter numOfPlayers.
     * Sets each player's score to 0 and player number to their respective number
     * @param stupid number of stupid players that is in this game
     * @param smart number of smart players that is in this game
     */
    public void createPlayers(int stupid, int smart){
        allPlayers = new cpu[stupid+smart];
        for(int i=0; i<stupid; i++){
            allPlayers[i] = new StupidPlayer(0, i+1);
        }
        for (int u=stupid; u<smart+stupid; u++){
            allPlayers[u] = new SmartPlayer(0, u+1);
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
     * A SmartPlayer adds a card from the card deck to his own collection of cards.
     * @param x the player that is drawing a card from the card deck
     */
    public void drawCard(cpu x){
        int randomRank = (int) ((Math.random()*13) + 1);
        while (cardDeck.get(randomRank) == 0){
            randomRank = (int)(Math.random()*13) + 1;
        }
        x.getCards().put(randomRank, x.getCards().get(randomRank)+1);
        cardDeck.put(randomRank, cardDeck.get(randomRank)-1);
        numOfCardsInDeck--;
    }

    public static ResourceBundle setLocation(String userInput){

        String language;
        String country;

        if (userInput.equals("Chinese")) {
            language = new String("zh");
            country = new String("CN");
        } else {
            language = new String("en");
            country = new String("US");
        }

        Locale currentLocale;

        currentLocale = new Locale(language, country);

        return ResourceBundle.getBundle("MessagesBundle", currentLocale);

    }

    public static void mainGame(int numberOfStupid, int numberOfSmart, ResourceBundle messages) throws Exception {

        int numberOfPlayers = numberOfSmart+numberOfStupid;
        GoFishGames newGame = new GoFishGames(numberOfStupid, numberOfSmart);
        cpu[] player = newGame.getAllPlayers();

        //game starts by everyone drawing a card
        for(cpu curr: player){
            newGame.drawCard(curr);
            newGame.drawCard(curr);
            newGame.drawCard(curr);
            System.out.println(MessageFormat.format(messages.getString("player.draws.card"), curr.getPlayerNumber()));
        }

        int whosTurn = 0;

        while(!newGame.checkDeckEmpty()){

            //the current player picks an opponent
            int opponent = player[whosTurn].picksOpponent(numberOfPlayers);

            int cardRank = player[whosTurn].askForCard(opponent);
            System.out.print(MessageFormat.format(messages.getString("player"), whosTurn+1));
            System.out.print(MessageFormat.format(messages.getString("player.asks.card"), opponent));
            System.out.println(cardRank);

            int numberOfCardsToGive = player[opponent-1].giveCard(cardRank);

            if(numberOfCardsToGive != 0){
                System.out.println(MessageFormat.format(messages.getString("player.gives.card"), cardRank));
                //the player gets some cards from his opponent
                player[whosTurn].receiveCard(cardRank, numberOfCardsToGive);
                player[whosTurn].checkBook();
                newGame.getAllMoves().put(whosTurn+1, cardRank);

            }else{
                //GO FISH!!!
                System.out.println(messages.getString("go.fish"));
                newGame.drawCard(player[whosTurn]);

                whosTurn++;
                if (whosTurn >= numberOfPlayers){
                    whosTurn = 0;
                }
            }
        }

        System.out.println(messages.getString("game.ended"));

        //decide winner
        int winner = 1;
        for (cpu p: player){
            if (p.getPoints() > winner){
                winner = p.getPlayerNumber();
            }
        }
        System.out.println(MessageFormat.format(messages.getString("winner"), winner));

    }

}
