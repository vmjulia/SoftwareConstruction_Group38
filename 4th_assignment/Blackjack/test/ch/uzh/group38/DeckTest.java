package ch.uzh.group38;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class DeckTest {
    Deck d;

    @Before
    public void createDeck() {
        d = Deck.getInstance();
    }

    @After
    public void resetDeck() {
        // since Deck is a Singleton, not resetting it might affect other tests
        d.putDiscardBack();
    }


    @Test
    public void testTwoInstances() {
        Deck dd;
        dd = Deck.getInstance();
        assertEquals(d, dd);
    }

    @Test
    public void testDrawCard() {
        for (int i = 0; i <= 51; i++) {
            Card c = d.draw();
            assertNotNull(c);
        }
        Card c = d.draw();
        assertNull(c);
    }
}