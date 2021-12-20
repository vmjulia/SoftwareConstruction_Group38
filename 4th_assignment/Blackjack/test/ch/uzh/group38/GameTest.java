package ch.uzh.group38;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
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

        provideInput("no");
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

    private void provideInput(String inputString) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(testIn);
    }

    @After
    public void resetDeck() throws IllegalAccessException {
        Deck deck = (Deck) deckField.get(this.game);
        deck.putDiscardBack();
    }

    @Test
    public void testEndOfGame() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Method eog = Game.class.getDeclaredMethod("endOfGame");
        eog.setAccessible(true);

        System.setIn(new ByteArrayInputStream("".getBytes()));
        OutputStream os;

        Player player = (Player) playerField.get(game);
        Dealer dealer = (Dealer) dealerField.get(game);

        ArrayList<Card> playerCards = new ArrayList<>();
        ArrayList<Card> dealerCards = new ArrayList<>();


        // player bust
        os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        playerCards.add(new Card(Rank.KING, Suit.HEARTS));
        playerCards.add(new Card(Rank.QUEEN, Suit.HEARTS));
        playerCards.add(new Card(Rank.TEN, Suit.HEARTS));
        player.takeCards(new CardIterator(playerCards));
        try{
            eog.invoke(game);
        } catch (InvocationTargetException e){
            // happens because of empty input for next round
        }
        assertTrue(os.toString().contains("You bust, dealer wins by default"));
        playerCards.clear();
        player.reset();

        // dealer bust
        os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        dealerCards.add(new Card(Rank.KING, Suit.HEARTS));
        dealerCards.add(new Card(Rank.QUEEN, Suit.HEARTS));
        dealerCards.add(new Card(Rank.TEN, Suit.HEARTS));
        dealer.takeCards(new CardIterator(dealerCards));
        try{
            eog.invoke(game);
        } catch (InvocationTargetException e){
            // happens because of empty input for next round
        }
        assertTrue(os.toString().contains("Dealer busts, you win by default"));
        dealerCards.clear();
        dealer.reset();

        // player blackjack
        os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        playerCards.add(new Card(Rank.KING, Suit.HEARTS));
        playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
        player.takeCards(new CardIterator(playerCards));

        try{
            eog.invoke(game);
        } catch (InvocationTargetException e){
            // happens because of empty input for next round
        }
        assertTrue(os.toString().contains("You have a blackjack, you win by default"));
        playerCards.clear();
        player.reset();


        // dealer blackjack
        os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        dealerCards.add(new Card(Rank.KING, Suit.HEARTS));
        dealerCards.add(new Card(Rank.ACE, Suit.HEARTS));
        dealer.takeCards(new CardIterator(dealerCards));

        try{
            eog.invoke(game);
        } catch (InvocationTargetException e){
            // happens because of empty input for next round
        }
        assertTrue(os.toString().contains("Dealer has a blackjack, you loose by default"));
        dealerCards.clear();
        dealer.reset();


        // player wins
        os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        playerCards.add(new Card(Rank.KING, Suit.HEARTS));
        playerCards.add(new Card(Rank.SEVEN, Suit.HEARTS));
        player.takeCards(new CardIterator(playerCards));

        dealerCards.add(new Card(Rank.SIX, Suit.HEARTS));
        dealerCards.add(new Card(Rank.QUEEN, Suit.HEARTS));
        dealer.takeCards(new CardIterator(dealerCards));

        try{
            eog.invoke(game);
        } catch (InvocationTargetException e){
            // happens because of empty input for next round
        }
        assertTrue(os.toString().contains("You win"));
        playerCards.clear();
        player.reset();
        dealerCards.clear();
        dealer.reset();


        // dealer wins
        os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        playerCards.add(new Card(Rank.KING, Suit.HEARTS));
        playerCards.add(new Card(Rank.SIX, Suit.HEARTS));
        player.takeCards(new CardIterator(playerCards));

        dealerCards.add(new Card(Rank.SEVEN, Suit.HEARTS));
        dealerCards.add(new Card(Rank.QUEEN, Suit.HEARTS));
        dealer.takeCards(new CardIterator(dealerCards));

        try{
            eog.invoke(game);
        } catch (InvocationTargetException e){
            // happens because of empty input for next round
        }
        assertTrue(os.toString().contains("Dealer wins"));
        playerCards.clear();
        player.reset();
        dealerCards.clear();
        dealer.reset();


        // draw
        os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        playerCards.add(new Card(Rank.KING, Suit.HEARTS));
        playerCards.add(new Card(Rank.SIX, Suit.HEARTS));
        player.takeCards(new CardIterator(playerCards));

        dealerCards.add(new Card(Rank.SEVEN, Suit.HEARTS));
        dealerCards.add(new Card(Rank.NINE, Suit.HEARTS));
        dealer.takeCards(new CardIterator(dealerCards));

        try{
            eog.invoke(game);
        } catch (InvocationTargetException e){
            // happens because of empty input for next round
        }
        assertTrue(os.toString().contains("Draw"));
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
