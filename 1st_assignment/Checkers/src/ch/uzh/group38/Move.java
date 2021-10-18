package ch.uzh.group38;


public class Move {

    private int x1, y1, x2, y2;

    public Move(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public void move(Board board){
        if (RuleEvaluator.isJumpMove(x1, y1, x2, y2, board)){
            jumpMove(board);
        }
        else{
            simpleMove(board);
        }
    }

    /*
    executing a simple move
    */
    public void simpleMove(Board board){
        board.movePiece(x1, y1, x2, y2);

        //case other side of board is reached -> king
        if ((y2 == 0 || y2 == 7) && !board.isKing(x2, y2)){
            System.out.println("Well done Player " + RuleEvaluator.getCurrentPlayer() + "! Your pawn is now a king!");
            board.changeType(x2, y2);
        }

        RuleEvaluator.updateTurn(board);

    }

    /*
    executing a jump move
    */
    public void jumpMove(Board board){
        board.movePiece(x1, y1, x2, y2);
        board.removePiece((x1+x2)/2,(y1+y2)/2);

        //case other side of board is reached -> king
        if ((y2 == 0 || y2 == 7) && !board.isKing(x2, y2)){
            System.out.println("Well done Player " + RuleEvaluator.getCurrentPlayer() + "! Your pawn is now a king!");
            board.changeType(x2, y2);
            RuleEvaluator.updateTurn(board);
        }

        //case no other jumpmove is possible
        else if (!RuleEvaluator.checkForJumpmoves(x2, y2, board)){
            RuleEvaluator.updateTurn(board);
        }


    }
}
