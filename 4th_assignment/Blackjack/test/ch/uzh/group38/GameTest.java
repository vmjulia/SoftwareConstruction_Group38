package ch.uzh.group38;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game game;

    Field playerField;
    Field dealerField;
    Field deckField;

    @Before
    public void setUpGame() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Constructor<Game> constructor = Game.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        this.game = constructor.newInstance();

        this.playerField = this.game.getClass().getDeclaredField("player");
        assertTrue(Modifier.isPrivate(this.playerField.getModifiers()));
        this.playerField.setAccessible(true);

        this.dealerField = this.game.getClass().getDeclaredField("dealer");
        assertTrue(Modifier.isPrivate(this.dealerField.getModifiers()));
        this.dealerField.setAccessible(true);

        this.deckField = this.game.getClass().getDeclaredField("deck");
        assertTrue(Modifier.isPrivate(this.deckField.getModifiers()));
        this.deckField.setAccessible(true);
    }

    @After
    public void resetDeck() throws IllegalAccessException {
        Deck deck = (Deck) deckField.get(this.game);
        deck.putDiscardBack();
    }

    @Test
    public void testReset() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Player player = (Player) playerField.get(this.game);
        Dealer dealer = (Dealer) dealerField.get(this.game);
        Deck deck = (Deck) deckField.get(this.game);

        ArrayList<Card> testCards = new ArrayList<>();
        testCards.add(deck.draw());
        testCards.add(deck.draw());

        player.takeCards(new CardIterator(testCards));
        dealer.takeCards(new CardIterator(testCards));

        assertEquals(50, deck.size());

        Method reset = Game.class.getDeclaredMethod("reset");
        reset.setAccessible(true);
        reset.invoke(this.game);

        assertEquals(0, player.countScore());
        assertEquals(0, dealer.countScore());
        assertEquals(52, deck.size());
    }
}
