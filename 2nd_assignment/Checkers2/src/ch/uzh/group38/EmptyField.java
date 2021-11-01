package ch.uzh.group38;

public class EmptyField extends Field{

    public boolean isWhite(){
        return (false);
    }

    public boolean isRed(){
        return (false);
    }

    public boolean isKing(){
        return (false);
    }

    public boolean isEmpty(){
        return (true);
    }

    public String getLabel(){
        String label = "[   ] ";
        return label;
    }

    public void convertToKing(){
            System.out.println("Cannot convert empty space to King ");
    }

}
