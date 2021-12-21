package ch.uzh.group38;

public class Player extends User {

    private int cash = 100;
    private int bet;

    public Player(){
        super("Player");
    }

    public void makeBet() {
        this.bet = super.makeBet(cash);
    }


    public void displayCash() {
        System.out.println("Your current cash: " + this.cash);
    }

    public boolean isOutOfMoney() {
        return this.cash <= 0;
    }

    public void winBet(){
        this.cash += this.bet;
        this.bet = 0;
    }
    
    public void looseBet(){
        this.cash -= this.bet;
        this.bet = 0;
    }
}
