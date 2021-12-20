package ch.uzh.group38;

public class Dealer extends User{

    public Dealer(){
        super("Dealer");
    }

    public void flipCard(){
        Iterator cards =  createIterator();
        cards.next().flip();
    }
}
