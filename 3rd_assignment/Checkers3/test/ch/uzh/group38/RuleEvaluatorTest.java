package ch.uzh.group38;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RuleEvaluatorTest {
    Board b;

    @Before
    public void setup() {
        RuleEvaluator.resetCurrentPlayer();
        this.b = new Board();
    }

    @Test
    public void testPlayersUpdate(){
        assertEquals(1, RuleEvaluator.getCurrentPlayer());
        RuleEvaluator.updateTurn();
        assertEquals(2, RuleEvaluator.getCurrentPlayer());

    }

    @Test
    public void testMoveValidation(){
        Move m = new Move (1, 0, 2, 1);
        assertFalse(RuleEvaluator.checkValidity(m));
        b.removePiece(2, 1);
        assertTrue(RuleEvaluator.checkValidity(m));

    }


    @Test
    public void testCheckInput(){
        assertFalse(RuleEvaluator.checkInput(5,0));
        assertTrue(RuleEvaluator.checkInput(0,1));
        RuleEvaluator.updateTurn();
        assertTrue(RuleEvaluator.checkInput(5,0));
        assertFalse(RuleEvaluator.checkInput(0,1));
    }
    @Test
    public void testCheckWinner(){
        assertFalse(RuleEvaluator.checkWinner(b));
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if((i!=7)&&(j!=0)){
                b.removePiece(i, j);
                }
            }
        }

        RuleEvaluator.resetCurrentPlayer();
        b.notifyObservers();
        assertFalse(RuleEvaluator.checkWinner(b));

        RuleEvaluator.resetCurrentPlayer();
        RuleEvaluator.updateTurn();
        b.notifyObservers();
        assertFalse(RuleEvaluator.checkWinner(b));



    }

    /*
  checks if RE does not allow to make a move anybody if last move is stord. ( so can only move from that cell on)
   */

    @Test
    public void testMultipleJump(){
        Move m = new Move (0,0,2,1);
        RuleEvaluator.storeLastMove(m);



        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Move m1 = new Move(i, j, i+1, j+1);
                if(j+1<8){
                if((i==2) &&(j==1)){
                   assertTrue(RuleEvaluator.checkValidity(m1));
                }
                   else {
                    assertFalse(RuleEvaluator.checkValidity(m1));
                }
                }

            }

        }


    }


}

