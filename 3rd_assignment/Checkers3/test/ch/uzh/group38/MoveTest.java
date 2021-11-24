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


    /*
   make 1 simple move. compare all cells with default board except for 2 cells involved into move
   move from should be empty, move To should be equal to default from
   */

    @Test
    public void testSimpleMove(){
        RuleEvaluator.resetCurrentPlayer();
        Board b = new Board();
        Board c = new Board();
        int x1 = 2;
        int y1 = 1;
        int x2 = 3;
        int y2 = 0;

        Move m = new Move(x1, y1, x2, y2);
        m.move(b);

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (i == x1 && j == y1){
                    assertTrue( b.getField(i, j).isEmpty());
                }
                else if (i == x2 && j ==y2){
                    assertEquals(c.getField(x1,y1).isEmpty(), b.getField(i,j).isEmpty());

                }
                else {
                    assertEquals(c.getField(i,j).isEmpty(), b.getField(i,j).isEmpty());
                    assertEquals(c.getField(i,j).isRed(), b.getField(i,j).isRed());
                    assertEquals(c.getField(i,j).isKing(), b.getField(i,j).isKing());

                }
            }
        }
    }

    /*
     make two simple moves first in order to make jump after.
     reset player - start with red
     after - check if all positions remained unchanged except for those involved in moves
     */
    @Test
    public void testJumpMove(){
        RuleEvaluator.resetCurrentPlayer();
        Board b = new Board();
        Board c = new Board();
        int x1 = 2;
        int y1 = 1;
        int x2 = 3;
        int y2 = 0;

        int x3 = 5;
        int y3 = 2;
        int x4 = 4;
        int y4 = 1;



        Move m1 = new Move(x1, y1, x2, y2);
        m1.move(b);
        Move m2 = new Move(x3, y3, x4, y4);
        m2.move(b);
        Move jumpMove = new Move(x2, y2, x3, y3);
        jumpMove.move(b);




        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if ((i == x1 && j == y1) || (i == x2 && j == y2)||(i == x4 && j == y4)) {
                    assertTrue( b.getField(i, j).isEmpty());
                }
                else if (i == x3 && j ==y3){
                    assertTrue( b.getField(i, j).isRed());

                }
                else {
                    assertEquals(c.getField(i,j).isEmpty(), b.getField(i,j).isEmpty());
                    assertEquals(c.getField(i,j).isRed(), b.getField(i,j).isRed());
                    assertEquals(c.getField(i,j).isKing(), b.getField(i,j).isKing());

                }
            }
        }
    }
}