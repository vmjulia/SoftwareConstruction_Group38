package ch.uzh.group38;

import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CardIteratorTest {
    @Test
    public void testCardIterator(){
        ArrayList<Card> cards = new ArrayList<>();
        Card card = new Card(Rank.ACE, Suit.HEARTS);
        for(int i = 0; i < 10; ++i){
            cards.add(card);
        }
        CardIterator ci = new CardIterator(cards);
        for(int i = 0; i < 10; ++i){
            assert(ci.hasNext());
            assertEquals(ci.next(), card);
        }
        assertFalse(ci.hasNext());
        assertThrows(NoSuchElementException.class, ci::next);
    }
}