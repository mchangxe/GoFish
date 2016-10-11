import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 * Created by Museum2015 on 10/10/2016.
 */
public class PlayerTest {

    /**
     * Testing the overriden function equals for player objects
     * @return true since the two player objects have the same player numbers and points
     */
    @Test
    public void equals() throws Exception {
        Player x = new Player(0, 0);
        Player y = new Player(0, 0);
        assertTrue(x.equals(y));
    }

    /**
     * Testing the overriden function equals for player objects
     * @return true since the two player objects have the same player numbers and points
     */
    @Test
    public void equals2() throws Exception {
        Player x = new Player(0, 1);
        Player y = new Player(0, 2);
        assertFalse(x.equals(y));
    }

    /**
     * Testing the receiveCard function that allows a player obejct to add a certain number of cards of a rank to
     * their card collection
     * @return true since the player object successfully added 4 cards of card rank 4 to their collection
     */
    @Test
    public void receiveCard() throws Exception {
        Player x = new Player(0, 0);
        x.receiveCard(4, 4);
        assertTrue(x.getCards().get(4) == 4);
    }

    /**
     * Testing the receiveCard function that allows a player obejct to add a certain number of cards of a rank to
     * their card collection
     * @return true since the method receiveCard will throw an exception since there are no 5 cards of the same rank
     * in a card deck
     */
    @Test(expected = Exception.class)
    public void receiveCard2() throws Exception {
        Player x = new Player(0, 0);
        x.receiveCard(4, 5);
    }

    /**
     * Testing the giveCard function that allows a player obejct to give a card of a certain rank
     * @return true since the player does have cards of the given rank
     */
    @Test
    public void giveCard() throws Exception {
        Player x = new Player(0,0);
        x.setCards(5,3);
        int numberOfCardsToGive = x.giveCard(5);
        assertTrue(numberOfCardsToGive == 3);
    }

    /**
     * Testing the giveCard function that allows a player obejct to give a card of a certain rank
     * @return true since the player does not have any cards of the given rank
     */
    @Test
    public void giveCard2() throws Exception {
        Player x = new Player(0,0);
        int numberOfCardsToGive = x.giveCard(5);
        assertTrue(numberOfCardsToGive == 0);
    }

    /**
     * Testing the askForCard function that generates randomly a card rank to ask for
     * @return true since the generated card rank should never be less or equal to 0 or more or equal to 13
     */
    @Test
    public void askForCard() throws Exception {
        Player x = new Player(0,1);
        Player y = new Player(0,2);
        int rankToAsk = x.askForCard(2);
        assertTrue(rankToAsk<=13 && rankToAsk>= 0);
    }

    /**
     * Testing the checkBook function that returns true if the player has 4 cards of a single rank.
     * @return true since the player does have 4 cards of rank 3
     */
    @Test
    public void checkBook() throws Exception {
        Player x = new Player(0,0);
        x.setCards(3,4);
        boolean gotBook = x.checkBook();
        assertTrue(gotBook);
    }

    /**
     * Testing the checkBook function that returns true if the player has 4 cards of a single rank.
     * @return true since the player has an empty collection (does not have any cards at the very start of te game)
     */
    @Test
    public void checkBook2() throws Exception {
        Player x = new Player(0,0);
        boolean gotBook = x.checkBook();
        assertTrue(!gotBook);
    }

    /**
     * Testing the checkBook function that returns true if the player has 4 cards of a single rank.
     * @return true since the player has an empty collection (does not have any cards at the very start of te game)
     */
    @Test
    public void picksOpponent() throws Exception {
        Player x = new Player(0,0);
        //suppose this game has 5 total players including the player x
        int opponent = x.picksOpponent(5);
        //the opponent cannot be himself or a player with a player number outside the total number of players in game
        assertTrue(opponent!=0 && opponent<5 && opponent>0);
    }

}