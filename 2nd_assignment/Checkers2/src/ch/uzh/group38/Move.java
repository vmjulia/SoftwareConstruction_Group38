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

    public int FromX(){
        return (this.from[0]);
    }

    public int FromY(){
        return (this.from[1]);
    }

    public int ToX(){
        return (this.to[0]);
    }

    public int ToY(){
        return (this.to[1]);
    }


    public void move(Board board){
        if (RuleEvaluator.isItJump(this)){
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
            board.movePiece(from[0], from[1], to[0] , to[1], false, true);

        }
        //else move the piece without conversion

        else{ board.movePiece(from[0], from[1], to[0] , to[1], false, false);
        }
    }

    /*
    executing a jump move
    */
    private void jumpMove(Board board){
        // remove the piece in between
        board.removePiece((from[0]+ to[0])/2,(from[1]+ to[1])/2);

        //case other side of board is reached -> king, dont need to check for further jump moves
        if ((to[0] == 0 || to[0] == 7)){
            board.movePiece(from[0], from[1], to[0] , to[1], false, true);
        }

        // move the piece and check for further jump moves
        else{ board.movePiece(from[0], from[1], to[0] , to[1], true, false); }

        }
    }

