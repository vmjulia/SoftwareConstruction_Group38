package ch.uzh.group38;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class InputBehaviourTest {
    TerminalInputBehaviour t;
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before


    public void setUpEnvironment() {
        t = new TerminalInputBehaviour();
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    public void provideInput(String inputString) {
        testIn = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(testIn);
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testDummyInput(){
        DummyInputBehaviour d = new DummyInputBehaviour();
        String s = d.readHitOrStayInput();
        assertNull(s);

        int i = d.makeBet(100);
        assertEquals(0, i);
    }

    @Test
    public void testTerminalInput(){

        // check that invalid input leads to exception
        TerminalInputBehaviour t = new TerminalInputBehaviour();
        provideInput("hit");
        boolean thrown  = false;
        try {
            assertEquals("h", t.readHitOrStayInput());
        } catch (Exception e){ thrown = true; }
        assertTrue(thrown);
        //check valid inputs
        provideInput("h");
        assertEquals("h", t.readHitOrStayInput());
        provideInput("H");
        assertEquals("h", t.readHitOrStayInput());
        provideInput("s");
        assertEquals("s", t.readHitOrStayInput());
        provideInput("S");
        assertEquals("s", t.readHitOrStayInput());

       
    }



    @Test
    public void testTerminalBetInput(){
        // try correct bet
        TerminalInputBehaviour t = new TerminalInputBehaviour();
        provideInput("50");
        assertEquals(50, t.makeBet(100));

        // try too high bet/ negative bet
        provideInput("150");
        String e = "How much would you like to bet?";
        assertEquals(e, testOut.toString().trim());
        provideInput("-150");
        assertEquals(e, testOut.toString().trim());



    }

    @Test
    public void testVoice()  {

        // check that mic and config are built without throwing exceptions
        boolean thrown  = false;
        try {
            VoiceInputBehaviour v = new VoiceInputBehaviour();
        } catch (Exception e){ thrown = true; }
        assertFalse(thrown);
    }



    @Test
    public void testVoiceInterpreter()  {

        // check that inout is correctly interpreted

        boolean thrown  = false;
        try {
            VoiceInputBehaviour v = new VoiceInputBehaviour();
           int num =  v.interpretInput ("ten");
           assertEquals(10, num);
            num =  v.interpretInput ("twenty");
            assertEquals(20, num);
            num =  v.interpretInput ("thirty");
            assertEquals(30, num);
            num =  v.interpretInput ("forty");
            assertEquals(40, num);
            num =  v.interpretInput ("fifty");
            assertEquals(50, num);
            num =  v.interpretInput ("sixty");
            assertEquals(60, num);
            num =  v.interpretInput ("seventy");
            assertEquals(70, num);
            num =  v.interpretInput ("hundred");
            assertEquals(100, num);
            num =  v.interpretInput ("something");
            assertEquals(0, num);
        } catch (Exception e){ thrown = true; }
        assertFalse(thrown);
    }

}