package ch.uzh.group38;

import ch.uzh.group38.Game;
import ch.uzh.group38.Board;

public class Move {
    /*
    prints actual state of the board 
    */
    public void simpleMove(int x1, int y1, int x2, int y2){
        Board.movePiece(x1, y1, x2, y2);
        RuleEvaluator.updateTurn();
    }

    /*
    prints actual state of the board 
    */
    public static void jumpMove(int x1, int y1, int x2, int y2){
        Board.movePiece(x1, y1, x2, y2);
        Board.removePiece((x1+x2)/2,(y1+y2)/2);
        if (RuleEvaluator.checkForJumpmoves(x2, y2)){
            //ask for input again
        }
        else{
            RuleEvaluator.updateTurn();
        }
    }
}
