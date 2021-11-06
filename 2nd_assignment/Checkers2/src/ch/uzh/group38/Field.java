package ch.uzh.group38;

public abstract class  Field {

    abstract boolean isWhite();
    abstract boolean isRed();
    abstract boolean isKing();
    abstract boolean isEmpty();
    abstract String getLabel();
    abstract void convertToKing();
    abstract void update();
    abstract void updatePosition(int X, int Y);
    abstract void isMoveStored(int X, int Y);

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
        System.out.println("Cannot convert empty space to King ");
    }

    // I guess this is fine, it has to be here, but if it is called it will not do anything
    void update() {}
    void updatePosition(int X, int Y){}
    void isMoveStored(int X, int Y){}
}

class PieceField extends Field {

    public enum Color {WHITE, RED}
    public enum Type {PAWN, KING}

    private int x;
    private int y;
    // 2D structure of array makes getMove() simplier
    private Move[][] simpleMoves = new Move[2][2];
    private Move[][] jumpMoves = new Move[2][2];

    private final Color color;
    private Type type;

    public PieceField(Color c, Type t){
        this.color = c;
        this.type = t;
    }

    public void update() {
        // simple moves
        for (int i = y-1; i >= 0 && i < 8; i += 2 ) {
            for (int j = x-1; j >= 0 && j < 8; j += 2) {
                Move evaluatedMove = new Move(x, y, j, i);
                if (RuleEvaluator.checkValidity(evaluatedMove)){
                    simpleMoves[((i -y) +1) /2][((j -x) +1) /2] = evaluatedMove;
                }
            }
        }

        // jump moves
        for (int i = y-2; i >= 0 && i < 8; i += 4 ) {
            for (int j = x-2; j >= 0 && j < 8; j += 4) {
                Move evaluatedMove = new Move(x, y, j, i);
                if (RuleEvaluator.checkValidity(evaluatedMove)){
                    jumpMoves[((i -y) +2) /4][((j -x) +2) /4] = evaluatedMove;
                }
            }
        }
    }

    public void updatePosition(int X, int Y) {
        x = X;
        y = Y;
    }

    public void isMoveStored(int X, int Y) {
        if (X>x && Y>y) {
            if (X-x == 1){
                System.out.println("simple, top right");
                System.out.println(simpleMoves[1][1]);
            }
            else {
                System.out.println("jump, top right");
                System.out.println(jumpMoves[1][1]);
            }
        }
        if (X>x && Y<y) {
            if (X-x == 1){
                System.out.println("simple, bottom right");
                System.out.println(simpleMoves[0][1]);
            }
            else {
                System.out.println("jump, top right");
                System.out.println(jumpMoves[0][1]);
            }
        }
        if (X<x && Y>y) {
            if (x-X == 1){
                System.out.println("simple, top left");
                System.out.println(simpleMoves[1][0]);
            }
            else {
                System.out.println("jump, top left");
                System.out.println(jumpMoves[1][0]);
            }
        }
        if (X<x && Y<y) {
            if (x-X == 1){
                System.out.println("simple, bottom left");
                System.out.println(simpleMoves[0][0]);
            }
            else {
                System.out.println("jump, bottom left");
                System.out.println(jumpMoves[0][0]);
            }
        }
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
