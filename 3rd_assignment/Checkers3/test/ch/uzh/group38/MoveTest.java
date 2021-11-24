package ch.uzh.group38;

import static org.junit.Assert.*;
import org.junit.Test;


public class MoveTest {

    @Test
    public void testGetters(){
        Move m = new Move(5, 3, 6, 2 );
        int expectedFromx = 5;
        int expectedFromy = 3;
        int expectedTox = 6;
        int expectedToy = 2;
        int actualFromx = m.fromX();
        int actualFromy = m.fromY();
        int actualTox = m.toX();
        int actualToy = m.toY();
        assertEquals(expectedFromx,actualFromx );
        assertEquals(expectedFromy,actualFromy );
        assertEquals(expectedTox,actualTox );
        assertEquals(expectedToy,actualToy );

    }

    @Test
    public void testSimpleMove(){
        Board b = new Board();
        Move m = new Move(2, 3, 3, 4 );
        m.move(b);

        boolean actualFrom =  b.getField(2, 3).isEmpty();
        boolean actualTo =  b.getField(3, 4).isEmpty();
        assertTrue(actualFrom);
        assertFalse(actualTo);

    }

    @Test
    public void testJumpMove(){
        Board b = new Board();
        Move m1 = new Move(2, 1, 3, 0 );
        m1.move(b);
        Move m2 = new Move(5, 2, 4, 1 );
        m2.move(b);
        Move jumpMove = new Move(3, 0, 5, 2 );
        jumpMove.move(b);

        boolean actualfrom =  b.getField(2, 1).isEmpty();
        boolean actualbetween =  b.getField(4, 1).isEmpty();
        boolean actualTo =  b.getField(5, 2).isEmpty();
        boolean actualToCol =  b.getField(5, 2).isRed();
        assertTrue(actualfrom);
        assertTrue(actualbetween);
        assertFalse(actualTo);
        assertTrue(actualToCol);

    }

}