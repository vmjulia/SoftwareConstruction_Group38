package ch.uzh.group38;


import org.junit.Test;

import static org.junit.Assert.*;

public class RuleEvaluatorTest {


    @Test
    public void testPlayersUpdate(){
        RuleEvaluator.resetCurrentPlayer();
        assertEquals(1, RuleEvaluator.getCurrentPlayer());
        RuleEvaluator.updateTurn();
        assertEquals(2, RuleEvaluator.getCurrentPlayer());

    }

    @Test
    public void testMoveValidation(){
        RuleEvaluator.resetCurrentPlayer();
        Board b = new Board();
        Move m = new Move (1, 0, 2, 1);
        assertFalse(RuleEvaluator.checkValidity(m));
        b.removePiece(2, 1);
        assertTrue(RuleEvaluator.checkValidity(m));

    }


    @Test
    public void testCheckInput(){
        RuleEvaluator.resetCurrentPlayer();
        assertFalse(RuleEvaluator.checkInput(5,0));
        assertTrue(RuleEvaluator.checkInput(0,1));
        RuleEvaluator.updateTurn();
        assertTrue(RuleEvaluator.checkInput(5,0));
        assertFalse(RuleEvaluator.checkInput(0,1));
    }
    @Test
    public void testCheckWinner(){
        RuleEvaluator.resetCurrentPlayer();
        Board b = new Board();
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


}

