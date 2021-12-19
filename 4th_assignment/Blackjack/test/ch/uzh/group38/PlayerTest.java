package ch.uzh.group38;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    Player p;
    Player pl;
    Player player;

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testMakeBet() {

        p = new Player();
        final String testString = "100";
        provideInput(testString);

        p.makeBet();
        p.looseBet();
        assertTrue(p.isOutOfMoney());
    }

    @Test
    public void testWinBet() {
        pl = new Player();
        String testString1 = "50";
        provideInput(testString1);

        pl.makeBet();
        pl.winBet();
        String testString2 = "150";
        provideInput(testString2);
        pl.makeBet();
        pl.looseBet();

        assertTrue(pl.isOutOfMoney());
    }

    @Test
    public void testCountScore(){
        player = new Player();
        player.cards.add(new Card(Rank.ACE, Suit.CLUBS));
        player.cards.add(new Card(Rank.NINE, Suit.CLUBS));
        assertEquals(player.countScore(),20);
        player.cards.add(new Card(Rank.FIVE, Suit.CLUBS));
        assertEquals(player.countScore(),15);
    }

    @Test
    public void testBust(){
        player = new Player();
        player.cards.add(new Card(Rank.KING, Suit.CLUBS));
        player.cards.add(new Card(Rank.NINE, Suit.CLUBS));
        player.cards.add(new Card(Rank.FIVE, Suit.CLUBS));
        assertTrue(player.bust());
    }
}
