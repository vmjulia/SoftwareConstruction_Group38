package ch.uzh.group38;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StrategyTest {
    PlayerStrategy ps;
    DealerStrategy ds;

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
    public void PlayerStrategyTest(){
        ps = new PlayerStrategy();

        final String testString = "h";
        provideInput(testString);

        assertTrue(ps.hit(10));
    }

    @Test
    public void DealerStrategyTest(){
        ds = new DealerStrategy();

        assertTrue(ds.hit(10));
        assertFalse(ds.hit(17));
    }
}
