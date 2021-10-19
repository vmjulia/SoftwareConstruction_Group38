package ch.uzh.group38;


public class Piece {
    private char colour;
    private char type;

    public Piece (char Colour, char Type){
        this.colour = Colour;
        this.type = Type;
    }

    public String getLabel (){
        if ((colour=='R')&& (type=='K')){
            return("[R_K] "); }
        if ((colour=='R')&& (type=='P')){
            return("[R_P] "); }
        if ((colour=='W')&& (type=='K')){
            return("[W_K] "); }
        if ((colour=='W')&& (type=='P')){
            return("[W_P] "); }

        return("[  ] ");
    }

    public void setType(char Type){ this.type = Type;}
    public char getColour(){return (this.colour);}
    public char getType(){return (this.type);}


}
