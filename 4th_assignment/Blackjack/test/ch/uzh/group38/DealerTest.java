package ch.uzh.group38;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import ch.uzh.group38.CardIterator;

public class DealerTest {
    @Test
    public void flipCardTest() throws NoSuchFieldException, IllegalAccessException {
        Dealer dealer = new Dealer();
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, Suit.HEARTS));
        cards.add(new Card(Rank.KING, Suit.HEARTS));

        dealer.takeCards(new CardIterator(cards));

        Field cardsField = User.class.getDeclaredField("cards");
        cardsField.setAccessible(true);
        ArrayList<Card> dealerCards = (ArrayList<Card>) cardsField.get(dealer);

        assertEquals("[ACE of HEARTS]", dealerCards.get(0).display());

        dealer.flipCard();

        assertEquals("[XXXXX]", dealerCards.get(0).display());

        dealer.flipCard();

        assertEquals("[ACE of HEARTS]", dealerCards.get(0).display());
    }
}
