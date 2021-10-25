package ch.uzh.group38;


public class Piece {

    public enum Color {WHITE, RED}
    public enum Type {PAWN, KING}

    private final Color colour;
    private Type type;

    public Piece (Color c, Type t){
        this.colour = c;
        this.type = t;
    }

    public String getLabel (){
        if ((colour==Color.RED)&& (type==Type.KING)){
            return("[R_K] "); }
        if ((colour==Color.RED)&& (type==Type.PAWN)){
            return("[R_P] "); }
        if ((colour==Color.WHITE)&& (type==Type.KING)){
            return("[W_K] "); }
        if ((colour==Color.WHITE)&& (type==Type.PAWN)){
            return("[W_P] "); }

        return("[  ] ");
    }

    public void setType(Type t){
        this.type = t;
    }

    public Color getColour(){
        return (this.colour);
    }

    public Type getType(){
        return (this.type);
    }

}
