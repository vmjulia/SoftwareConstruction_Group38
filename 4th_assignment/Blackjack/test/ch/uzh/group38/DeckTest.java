package ch.uzh.group38;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Stack;

import static org.junit.Assert.*;


public class DeckTest {
    Deck deck;

    @Before
    public void createDeck() {
        deck = Deck.getInstance();
    }

    @After
    public void resetDeck() {
        // since Deck is a Singleton, not resetting it might affect other tests
        deck.putDiscardBack();
    }


    @Test
    public void testTwoInstances() {
        Deck dd;
        dd = Deck.getInstance();
        assertEquals(deck, dd);
    }

    @Test
    public void testDrawCard() {
        for (int i = 0; i <= 51; i++) {
            Card c = deck.draw();
            assertNotNull(c);
        }
        Card c = deck.draw();
        assertNull(c);
    }

    // should have a 1 in 10^67 chance to fail
    @Test
    public void testShuffle() throws NoSuchFieldException, IllegalAccessException {
        Field cardsField = deck.getClass().getDeclaredField("cards");
        cardsField.setAccessible(true);
        Stack<Card> cardsAfter = (Stack) cardsField.get(deck);
        Stack<Card> cardsBefore = new Stack<>();
        for (int i = 0; i < 52; i++) {
            cardsBefore.add(cardsAfter.get(i));
        }

        // check that contents are identical
        for (int i = 0; i < 52; i++) {
            if (!cardsAfter.get(i).equals(cardsBefore.get(i))) {
                fail();
            }
        }

        deck.shuffle();

        // check that contents are no longer identical
        for (int i = 0; i < 52; i++) {
            if (!cardsAfter.get(i).equals(cardsBefore.get(i))) {
                return;
            }
        }
        fail();
    }
}