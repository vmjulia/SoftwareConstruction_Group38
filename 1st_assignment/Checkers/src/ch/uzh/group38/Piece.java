package ch.uzh.group38;

public class Piece {
    public char colour;
    public char type;

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

    public void becomeKing(){
        type = 'K';
    }


}
