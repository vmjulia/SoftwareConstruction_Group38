package ch.uzh.group38;

public abstract class  Field {

    abstract boolean isWhite();
    abstract boolean isRed();
    abstract boolean isKing();
    abstract boolean isEmpty();

    abstract void convertToKing();
    abstract void updateField();
    abstract void updatePosition(int X, int Y);
    abstract boolean isMoveStored(Move move);
    abstract boolean isJumpMoveStored(Move move);
    abstract boolean isAnyMovePossible();

}

class EmptyField extends Field{

    public boolean isWhite(){return (false);}

    public boolean isRed(){ return (false);}

    public boolean isKing(){return (false);}

    public boolean isEmpty(){return (true);}

    public void convertToKing(){
        GUI.setMessage("Cannot convert empty space to King ");
    }


    void updateField() {}
    void updatePosition(int X, int Y){}
    boolean isMoveStored(Move move){return false;}
    boolean isJumpMoveStored(Move move){return false;}
    boolean isAnyMovePossible(){return false;}
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

    public void updateField() {
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
        } } }



/*//
        // jump moves
        for (int i = y0-2; i < 8; i += 4 ) {
            for (int j = x0-2; j < 8; j += 4) {
                if (i >= 0 && j >= 0){

                 Move evaluatedMove = new Move(x0, y0, j, i);
                if (RuleEvaluator.checkValidity(evaluatedMove)){
                    possibleMoves[k] = evaluatedMove;
                    k++;
                }
                }
            }
        }

        // simple moves
        if (k==0){
            for (int i = y0-1; i < 8; i += 2 ) {
                for (int j = x0-1; j < 8; j += 2) {
                    if (i >= 0 &&j >= 0){
                    Move evaluatedMove = new Move(x0, y0, j, i);
                    if (RuleEvaluator.checkValidity(evaluatedMove)){
                        possibleMoves[k] = evaluatedMove;
                        // k++;
                    }
                    }
                }
            }
        }

 */
    }

    public void updatePosition(int X, int Y) {
        x0 = X;
        y0 = Y;
    }

    private boolean isSimpleMoveStored(Move move){
        for (Move possibleSimpleMove : possibleSimpleMoves) {
            if (possibleSimpleMove != null && (possibleSimpleMove.ToX() == move.ToX() && possibleSimpleMove.ToY() == move.ToY())) {
                return true;
            }
        }

        return false;

    }

    public boolean isJumpMoveStored(Move move){
        for (Move possibleJumpMove : possibleJumpMoves) {
            if (possibleJumpMove != null && (possibleJumpMove.ToX() == move.ToX() && possibleJumpMove.ToY() == move.ToY())) {
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
