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
        if (board.getField(this.fromX(), this.fromY()).isJumpMoveStored(this)){
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

        //case other side of board is reached -> king
        if (to[0] == 0 || to[0] == 7){
            board.movePiece(this, false, true);

        }
        //else move the piece without conversion

        else{ board.movePiece(this, false, false);
        }
    }

    /*
    executing a jump move
    */
    private void jumpMove(Board board){
        // remove the piece in between
        board.removePiece((from[0]+ to[0])/2,(from[1]+ to[1])/2);

        //case other side of board is reached -> king, dont need to check for further jump moves
        if ((to[0] == 0 || to[0] == 7)&& !board.getField(to[0], to[1]).isKing()){
            board.movePiece(this, false, true);
        }

        // move the piece and check for further jump moves
        else{ board.movePiece(this, true, false); }

        }
    }

