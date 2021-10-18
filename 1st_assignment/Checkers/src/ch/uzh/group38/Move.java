package ch.uzh.group38;


public class Move {

    public int[] coordinates;

    public Move(int[] Coordinates){
        this.coordinates = Coordinates;
    }
    
    public void move(Board board){
        if (RuleEvaluator.isJumpMove(coordinates, board)){
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
        board.movePiece(coordinates);

        //case other side of board is reached -> king
        if ((coordinates[3] == 0 || coordinates[3] == 7) && !board.isKing(coordinates[2], coordinates[3])){
            System.out.println("Well done Player " + RuleEvaluator.getCurrentPlayer() + "! Your pawn is now a king!");
            board.changeType(coordinates[2], coordinates[3]);
        }

        RuleEvaluator.updateTurn(board);

    }

    /*
    executing a jump move
    */
    public void jumpMove(Board board){
        board.movePiece(coordinates);
        board.removePiece((coordinates[0]+coordinates[2])/2,(coordinates[1]+coordinates[3])/2);

        //case other side of board is reached -> king
        if ((coordinates[3] == 0 || coordinates[3] == 7) && !board.isKing(coordinates[1], coordinates[3])){
            System.out.println("Well done Player " + RuleEvaluator.getCurrentPlayer() + "! Your pawn is now a king!");
            board.changeType(coordinates[2], coordinates[3]);
            RuleEvaluator.updateTurn(board);
        }

        //case no other jumpmove is possible
        else if (!RuleEvaluator.checkForJumpmoves(coordinates[2], coordinates[3], board)){
            RuleEvaluator.updateTurn(board);
        }


    }
}
