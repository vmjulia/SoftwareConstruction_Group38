package ch.uzh.group38;

public class Dealer extends User{

    public void showCards(){
        Iterator cards =  createIterator();
        System.out.println("Dealer cards:   (score: " + countScore() + ")");
        while (cards.hasNext()){
            System.out.print(cards.next().display()+ " ");
        }
        System.out.println("\n");
    }

    public void flipCard(){
        Iterator cards =  createIterator();
        cards.next().flip();
    }
}
