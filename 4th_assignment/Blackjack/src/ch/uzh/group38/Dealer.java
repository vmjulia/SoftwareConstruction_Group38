package ch.uzh.group38;

public class Dealer extends User{

    public void showCards(){
        System.out.println("Dealer cards:   (score: " + countScore() + ")");
        for (Card c : this.cards) {
            System.out.print(c.display() + " ");
        }
        System.out.println("\n");
    }        
}
