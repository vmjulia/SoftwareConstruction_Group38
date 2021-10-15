package ch.uzh.group38;

//import ch.uzh.group38.Game;
//import ch.uzh.group38.Board;

public class Move {
    
    public static void move(int[] move){
        if (RuleEvaluator.isJumpMove(move)){
            jumpMove(move);
        }
        else{
            simpleMove(move);
        }
    }
    /*
    executing a simple move
    */
    public static void simpleMove(int[] move){
        Board.movePiece(move);

        //case other side of board is reached
        if (((Board.isRed(move[2], move[3]) && move[3] == 0) || (Board.isRed(move[2], move[3]) && move[3] == 7)) && !Board.isKing(move[2], move[3])){
            Board.changeType(move[2], move[3]);
        }

        RuleEvaluator.updateTurn();
        Game.nextMove();

    }

    /*
    executing a jump move
    */
    public static void jumpMove(int[] move){
        Board.movePiece(move);
        Board.removePiece((move[0]+move[2])/2,(move[1]+move[3])/2);

        //case other side of board is reached
        if (((Board.isRed(move[0], move[2]) && move[3] == 0) || (Board.isRed(move[1], move[3]) && move[3] == 7)) && !Board.isKing(move[1], move[3])){
            Board.changeType(move[2], move[3]);
            RuleEvaluator.updateTurn();
        }
        //case no other jumpmove is possible
        else if (!RuleEvaluator.checkForJumpmoves(move[2], move[3])){
            RuleEvaluator.updateTurn();
        }
        Game.nextMove();
    }
}
