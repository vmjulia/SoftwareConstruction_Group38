package ch.uzh.group38;

public abstract class  Field {

    abstract boolean isWhite();
    abstract boolean isRed();
    abstract boolean isKing();
    abstract boolean isEmpty();
    abstract String getLabel();
    abstract void convertToKing();
    abstract void updateField();
    abstract void updatePosition(int X, int Y);
    abstract boolean isMoveStored(Move move);

}

class EmptyField extends Field{

    public boolean isWhite(){return (false);}

    public boolean isRed(){ return (false);}

    public boolean isKing(){return (false);}

    public boolean isEmpty(){return (true);}

    public String getLabel(){
        String label = "[   ] ";
        return label;
    }

    public void convertToKing(){
        GUI.setMessage("Cannot convert empty space to King ");
    }

    // I guess this is fine, it has to be here, but if it is called it will not do anything
    void updateField() {}
    void updatePosition(int X, int Y){}
    boolean isMoveStored(Move move){return false;}
}

class PieceField extends Field {

    public enum Color {WHITE, RED}
    public enum Type {PAWN, KING}

    //own position
    private int x0;
    private int y0;

    private Move[] possibleMoves = new Move[8];

    private final Color color;
    private Type type;

    public PieceField(Color c, Type t){
        this.color = c;
        this.type = t;
    }

    public void updateField() {
        int k = 0;
        //redundant
        /*
        for (int i = 0; i < 8; i++){
            possibleMoves[i] = null;
        }
         */

        // jump moves
        for (int i = y0-2; i >= 0 && i < 8; i += 4 ) {
            for (int j = x0-2; j >= 0 && j < 8; j += 4) {
                Move evaluatedMove = new Move(x0, y0, j, i);
                if (RuleEvaluator.checkValidity(evaluatedMove)){
                    possibleMoves[k] = evaluatedMove;
                    k++;
                }
            }
        }

        // simple moves
        if (k==0){
            for (int i = y0-1; i >= 0 && i < 8; i += 2 ) {
                for (int j = x0-1; j >= 0 && j < 8; j += 2) {
                    Move evaluatedMove = new Move(x0, y0, j, i);
                    if (RuleEvaluator.checkValidity(evaluatedMove)){
                        possibleMoves[k] = evaluatedMove;
                        // k++;
                    }
                }
            }
        }
    }

    public void updatePosition(int X, int Y) {
        x0 = X;
        y0 = Y;
    }

    public boolean isMoveStored(Move move){
        for (int i = 0; i < possibleMoves.length; i++){
            if (possibleMoves[i] != null && (possibleMoves[i].ToX() == move.ToX() && possibleMoves[i].ToY() == move.ToY())){
                return true;
            }
        }
        return false;
    }
 

    public String getLabel (){
        String label = "[";


        if (this.color==Color.RED){
            label += "R_";
        }
        else if (this.color==Color.WHITE){
            label += "W_";
        }

        if (this.type==Type.KING){
            label += "K] ";
        }
        else if (this.type==Type.PAWN){
            label += "P] ";
        }

        return label;
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
