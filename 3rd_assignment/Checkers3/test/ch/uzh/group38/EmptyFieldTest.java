package ch.uzh.group38;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmptyFieldTest {
    EmptyField f;
    @Before
    public void createField(){
        f = new EmptyField(1, 2 );
    }



    @Test
    public void testCoordinates(){
        assertEquals(1, f.getX());
        assertEquals(2, f.getY());
         f.updatePosition(2, 1);
        assertEquals(2, f.getX());
        assertEquals(1, f.getY());
    }
    @Test
    public void testIsSomething(){
        assertFalse(f.isKing());
        assertFalse(f.isWhite());
        assertFalse(f.isRed());
        assertTrue(f.isEmpty());
        f.convertToKing();
        assertFalse(f.isKing());

    }

    @Test
    public void testMoveStorage(){
        RuleEvaluator.resetCurrentPlayer();
        Move m;
       Board b  = new Board();
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(b.getField(i, j).isEmpty()){
                    f.update();
                    assertFalse(f.isAnyMovePossible());
                    m = new Move (i, j, i+1, j+1);
                    assertFalse(f.isMoveStored(m));
                    assertFalse(f.isJumpMoveStored(m));
                    m = new Move (i, j, i-1, j-1);
                    assertFalse(f.isMoveStored(m));
                    assertFalse(f.isJumpMoveStored(m));
                    m = new Move (i, j, i+1, j-1);
                    assertFalse(f.isMoveStored(m));
                    assertFalse(f.isJumpMoveStored(m));
                    m = new Move (i, j, i-1, j+1);
                    assertFalse(f.isMoveStored(m));
                    assertFalse(f.isJumpMoveStored(m));
                    m = new Move (i, j, i+2, j+2);
                    assertFalse(f.isMoveStored(m));
                    assertFalse(f.isJumpMoveStored(m));
                    m = new Move (i, j, i-2, j-2);
                    assertFalse(f.isMoveStored(m));
                    assertFalse(f.isJumpMoveStored(m));
                    m = new Move (i, j, i+2, j-2);
                    assertFalse(f.isMoveStored(m));
                    assertFalse(f.isJumpMoveStored(m));
                    m = new Move (i, j, i-2, j+2);
                    assertFalse(f.isMoveStored(m));
                    assertFalse(f.isJumpMoveStored(m));

                }

            }

        }

    }


}