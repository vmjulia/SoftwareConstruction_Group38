package ch.uzh.group38;


public abstract class  Field {

    abstract boolean isWhite();
    abstract boolean isRed();
    abstract boolean isKing();
    abstract boolean isEmpty();
    abstract String getLabel();
    abstract void convertToKing();

}
