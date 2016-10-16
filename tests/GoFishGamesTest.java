import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Museum2015 on 9/10/2016.
 */
public class GoFishGamesTest {


    GoFishGames newGameTest = new GoFishGames(2,2);


    /**
     * Testing createPlayers function
     * @return true if the two arrays of players are equal
     */
    @Test
    public void createPlayers() throws Exception {
        newGameTest.createPlayers(2,2);
        cpu[] answer = new cpu[4];
        for (int i=0; i<2; i++){
            answer[i] = new StupidPlayer(0, i+1);
        }
        for (int u=2; u<4; u++){
            answer[u] = new SmartPlayer(0, u+1);
        }

        assertArrayEquals(answer, newGameTest.getAllPlayers());
    }

    /**
     * Testing createPlayers function
     * @return true if the two arrays of players are equal
     */
    @Test
    public void createPlayers2() throws Exception {
        //even though Main() will never allow number of players to be 0
        newGameTest.createPlayers(0,0);
        SmartPlayer[] answer = new SmartPlayer[0];
        assertArrayEquals(newGameTest.getAllPlayers(), answer);
    }

    /**
     * Testing getNewDeck methods that creates a new standard deck
     * @return true if the two decks are equal
     */
    @Test
    public void getNewDeck() throws Exception {
        newGameTest.getNewDeck();
        Map<Integer,Integer> standardDeck = new HashMap<>();
        for (int i=1; i<14; i++){
            standardDeck.put(i, 4);
        }
        assertTrue(standardDeck.equals(newGameTest.getCardDeck()));

    }

    /**
     * Testing the checkDeckEmpty function that returns true if the card deck used in a game is empty
     * @return true if the deck is not empty
     */
    @Test
    public void checkDeckEmpty() throws Exception {
        assertTrue(!newGameTest.checkDeckEmpty());
    }

    /**
     * Testing the checkDeckEmpty function that returns true if the card deck used in a game is empty
     * @return true since the deck is now empty (number of cards set to 0)
     */
    @Test
    public void checkDeckEmpty2() throws Exception {
        newGameTest.setNumOfCardsInDeck(0);
        assertTrue(newGameTest.checkDeckEmpty());
    }


    /**
     * Testing the drawCard method that determines what card is drawn from the deck by a certain player
     * @return true since the deck is now empty (number of cards set to 0)
     */
    @Test
    public void drawCard() throws Exception {
        newGameTest.drawCard(newGameTest.getAllPlayers()[0]);
        assertTrue(newGameTest.getNumOfCardsInDeck() == 51);
    }

    /**
     * Testing the drawCard method that determines what card is drawn from the deck by a certain player
     * @return true since the deck is now empty (number of cards set to 0)
     */
    @Test
    public void drawCard2() throws Exception {
        //this deck only has one card left, which is an ace
        Map<Integer, Integer> almostEmptyDeck = new HashMap<>();
        almostEmptyDeck.put(13,1);
        for (int i=1; i<13; i++){
            almostEmptyDeck.put(i, 0);
        }
        newGameTest.setCardDeck(almostEmptyDeck);
        newGameTest.setNumOfCardsInDeck(1);
        newGameTest.drawCard(newGameTest.getAllPlayers()[0]);

        assertTrue(newGameTest.getNumOfCardsInDeck() == 0 && newGameTest.getAllPlayers()[0].getCards().get(13) == 1);
    }

}