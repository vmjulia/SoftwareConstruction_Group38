package ch.uzh.group38;

public abstract class  Field {

    abstract boolean isWhite();
    abstract boolean isRed();
    abstract boolean isKing();
    abstract boolean isEmpty();
    abstract String getLabel();
    abstract void convertToKing();

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
}

class PieceField extends Field {

    public enum Color {WHITE, RED}
    public enum Type {PAWN, KING}

    private final Color color;
    private Type type;

    public PieceField(Color c, Type t){
        this.color = c;
        this.type = t;
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
            GUI.message.setText("Well done Player " + RuleEvaluator.getCurrentPlayer() + "! Your pawn is now a king!");
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
