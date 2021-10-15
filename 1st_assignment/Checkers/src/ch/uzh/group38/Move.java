package ch.uzh.group38;

//import ch.uzh.group38.Game;
//import ch.uzh.group38.Board;

public class Move {
    /*
    executing a simple move
    */
    public void simpleMove(int x1, int y1, int x2, int y2){
        if (((Board.isRed(x2, y2) && y2 == 0) || (Board.isRed(x2, y2) && y2 == 7)) && !Board.isKing(x2, y2)){
            Board.changeType(x2, y2);
            RuleEvaluator.updateTurn();
            Game.nextMove();
            }
        else{
            Board.movePiece(x1, y1, x2, y2);
            RuleEvaluator.updateTurn();
        }
    }

    /*
    executing a jump move
    */
    public static void jumpMove(int x1, int y1, int x2, int y2){
        Board.movePiece(x1, y1, x2, y2);
        Board.removePiece((x1+x2)/2,(y1+y2)/2);

        //case other side of board is reached
        if (((Board.isRed(x2, y2) && y2 == 0) || (Board.isRed(x2, y2) && y2 == 7)) && !Board.isKing(x2, y2)){
            Board.changeType(x2, y2);
            RuleEvaluator.updateTurn();
            Game.nextMove();
            }
        //case another jumpmove is possible
        else if (RuleEvaluator.checkForJumpmoves(x2, y2)){
            Game.nextMove();
        }
        else{
            RuleEvaluator.updateTurn();
            Game.nextMove();
        }
    }
}
