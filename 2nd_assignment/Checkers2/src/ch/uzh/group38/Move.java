package ch.uzh.group38;


public class Move {

    private int[] from;
    private int[] to;

    public Move(int x1, int y1, int x2, int y2){

        this.from = new int[2];
        this.to = new int[2];
        this.from[0] = x1;
        this.from[1] = y1;
        this.to[0] = x2;
        this.to[1] = y2;
    }

    public int fromX(){
        return (this.from[0]);
    }

    public int fromY(){
        return (this.from[1]);
    }

    public int toX(){
        return (this.to[0]);
    }

    public int toY(){
        return (this.to[1]);
    }


    public void move(Board board){
        if (RuleEvaluator.isJumpMove(this, board)){
            jumpMove(board);
        }
        else{
            simpleMove(board);
        }
    }

    /*
    executing a simple move
    */
    private void simpleMove(Board board){
        board.movePiece(from[0], from[1], to[0] , to[1]);

        //case other side of board is reached -> king
        if (to[0] == 0 || to[0] == 7){
            board.getField(to[0], to[1]).convertToKing();
        }

        RuleEvaluator.updateTurn(board);
    }

    /*
    executing a jump move
    */
    private void jumpMove(Board board){
        board.movePiece(from[0], from[1], to[0] , to[1]);
        board.removePiece((from[0]+ to[0])/2,(from[1]+ to[1])/2);

        //case other side of board is reached -> king
        if ((to[0] == 0 || to[0] == 7) && !board.getField(to[0], to[1]).isKing()){
            board.getField(to[0], to[1]).convertToKing();
            RuleEvaluator.updateTurn(board);
        }

        //case no other jumpMove is possible
        else if (!RuleEvaluator.checkForJumpMoves(to[0], to[1], board)){
            RuleEvaluator.updateTurn(board);
        }
    }
}
