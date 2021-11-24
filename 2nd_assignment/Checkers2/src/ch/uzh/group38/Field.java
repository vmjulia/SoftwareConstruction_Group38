package ch.uzh.group38;

interface Observer{
    void update();
}


public abstract class  Field implements Observer {

    abstract boolean isWhite();
    abstract boolean isRed();
    abstract boolean isKing();
    abstract boolean isEmpty();

    abstract void convertToKing();
    abstract void updatePosition(int X, int Y);
    abstract boolean isMoveStored(Move move);
    abstract boolean isJumpMoveStored(Move move);
    abstract boolean isAnyMovePossible();

}

class EmptyField extends Field {

    public boolean isWhite(){return (false);}

    public boolean isRed(){ return (false);}

    public boolean isKing(){return (false);}

    public boolean isEmpty(){return (true);}

    public void convertToKing(){
        GUI.setMessage("Cannot convert empty space to King ");
    }


    public void update() {}
    public void updatePosition(int X, int Y){}
    public boolean isMoveStored(Move move){return false;}
    public boolean isJumpMoveStored(Move move){return false;}
    public boolean isAnyMovePossible(){return false;}
}

class PieceField extends Field {

    public enum Color {WHITE, RED}
    public enum Type {PAWN, KING}

    //own position
    private int x0;
    private int y0;

    //possible moves
    private Move[] possibleSimpleMoves;
    private Move[] possibleJumpMoves ;

    //color and type
    private final Color color;
    private Type type;


    public PieceField(Color c, Type t){
        this.color = c;
        this.type = t;
    }

    public void update() {
        int k = 0;
        int l = 0;
        possibleSimpleMoves = new Move[4];
        possibleJumpMoves = new Move[4];

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
            Move evaluatedMove = new Move(x0, y0, i, j);
            if (RuleEvaluator.checkValidity(evaluatedMove)){
                if ((x0-i)==1 ||(y0-j) ==1 || (x0-i)==-1 ||(y0-j)==-1){
                    possibleSimpleMoves[k] = evaluatedMove;
                k++;}
                else { possibleJumpMoves[l] = evaluatedMove;
                l++;}
                }
            }
        }
    }




    public void updatePosition(int X, int Y) {
        x0 = X;
        y0 = Y;
    }

    private boolean isSimpleMoveStored(Move move){
        for (Move possibleSimpleMove : possibleSimpleMoves) {
            if (possibleSimpleMove != null && (possibleSimpleMove.toX() == move.toX() && possibleSimpleMove.toY() == move.toY())) {
                return true;
            }
        }

        return false;

    }

    public boolean isJumpMoveStored(Move move){
        for (Move possibleJumpMove : possibleJumpMoves) {
            if (possibleJumpMove != null && (possibleJumpMove.toX() == move.toX() && possibleJumpMove.toY() == move.toY())) {
                return true;
            }
        }

        return false;

    }

    public boolean isMoveStored(Move move){
        return (isJumpMoveStored(move)||isSimpleMoveStored(move));

    }

    public boolean isAnyMovePossible(){
        return (!(possibleSimpleMoves[0] == null && possibleJumpMoves[0] == null));

    }

    public void convertToKing(){
        if (this.type != Type.KING){
            GUI.setMessage("Well done Player " + RuleEvaluator.getCurrentPlayer() + "! Your pawn is now a king!");
            this.type = Type.KING;
        }
    }

    public boolean isWhite(){
        return (this.color == Color.WHITE);
    }

    public boolean isRed(){
        return (this.color == Color.RED);
    }

    public boolean isKing(){
        return (this.type == Type.KING);
    }

    public boolean isEmpty(){ return (false);}

}
