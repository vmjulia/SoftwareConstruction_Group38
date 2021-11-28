package ch.uzh.group38;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {
    Board b;
    Field[][] defaultBoard;


    @Before
    public void CreateBoard(){
        RuleEvaluator.resetCurrentPlayer();
        this.b  = new Board();
        this.defaultBoard = new Field[8][8];
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                defaultBoard[i][j] = b.getField(i, j);
            }
        }
    }
    @Test
    public void testMovePiece(){

                  /*
  case 1: move without checking for further moves & without conversion to King
   */
        int x1 = 2;
        int y1 = 1;
        int x2 = 3;
        int y2 = 0;
        Move m = new Move(x1, y1, x2, y2);
        Field pieceMoved =  b.getField(x1,y1);
        b.movePiece(m, false, false);


        assertTrue(b.getField(x1, y1).isEmpty());
        assertEquals( defaultBoard[x1][y1], b.getField(x2,y2));
        assertEquals(x2, b.getField(x2, y2).getX());
        assertEquals(y2, b.getField(x2, y2).getY());
        assertTrue(b.getField(x2, y2).isRed());
        assertFalse(b.getField(x2, y2).isKing());

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (((i !=x2) || (j != y2)) && ((i !=x1) || (j != y1))){
                    assertEquals(defaultBoard[i][j].isEmpty(), b.getField(i,j).isEmpty());
                    assertEquals(defaultBoard[i][j].isRed(), b.getField(i,j).isRed());
                    assertEquals(defaultBoard[i][j].isKing(), b.getField(i,j).isKing());
                }
            }
        }
    }


    @Test
    public void testKindConversion(){


        /*
  case 1: test how conversion is handled ( do not need to check for moving of pieces as it is done in test 1
   */
        int x1 = 2;
        int y1 = 1;
        int x2 = 3;
        int y2 = 0;
        Move m = new Move(x1, y1, x2, y2);
        b.movePiece(m, false, true);
        assertTrue(b.getField(m.toX(),m.toY()).isKing());
    }


    @Test
    public void testRemovePiece(){
        int x1 = 7;
        int y1 = 4;
        b.removePiece(x1, y1);
        assertTrue( b.getField(x1, y1).isEmpty());
    }


    @Test
    public void testMultipleJumps(){
          /*
  case 3 : checks if board performs necessary calls to ensure multi jumps correctness

   1. create special version of board ( to faster get to multiple jump move)
   2. play 3 moves - 2 simple and 1 jump which has potential for a further jump move
   3. if board properly handles this - check validity should be correct*/


        int x1 = 7;
        int y1 = 4;
        int x2 = 5;
        int y2 = 2;
        int x3 = 2;
        int y3 = 1;
        int x4 = 3;
        int y4 = 0;
        int x5 = 5;
        int y5 = 0;
        int x6 = 4;
        int y6 = 1;

        b.removePiece(x1, y1);
        b.removePiece(x2, y2);

        Move m1 = new Move(x3, y3, x4, y4);
        m1.move(b);
        Move m2 = new Move(x5, y5, x6, y6);
        m2.move(b);
        Move m = new Move(x4, y4, x2, y2);
        b.movePiece(m, true, false);

        Move potentialMove = new Move (x2, y2, x1, y1);
        assertTrue(RuleEvaluator.checkValidity(potentialMove));


        }

    @Test
    public void testGetField(){
        Field f = b.getField(0,3);
        assertEquals(0, f.getX());
        assertEquals(3, f.getY());
        assertTrue(f.isRed());

    }

    /*
  remove all observers, add just one, and try to update it
  test if that observer has list of valid moves ( when it is registered and npt*/

    @Test
    public void testObserver(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1)
                {
                    b.removeObserver(b.getField(i, j));
                }
            }
    }
        b.removePiece(2, 1);
        RuleEvaluator.updateBoard(b);
        b.notifyObservers();
        assertFalse(b.getField(1, 0).isAnyMovePossible());

        b.registerObserver(b.getField(1, 0));
        b.notifyObservers();
        assertTrue(b.getField(1, 0).isAnyMovePossible());

    }


}