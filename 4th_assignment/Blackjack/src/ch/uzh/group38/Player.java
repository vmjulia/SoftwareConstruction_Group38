package ch.uzh.group38;

import java.util.Scanner;

public class Player extends User {

    private int cash = 100;
    private int bet;

    public Player(){
        super("Player");
    }

    public int makeBet() {
        int b;
        do {
            System.out.println("How much would you like to bet? ");
            b =  this.readIntInput();
        } while(b > this.cash || b <= 0);
        this.bet = b;
        return this.bet;
    }

    private int readIntInput(){
        while (true) {
            String input = new Scanner(System.in).nextLine();
            try {
                int i = Integer.parseInt(input);
                return i;
            } catch (Exception NumberFormatException) {
                System.out.println("Invalid input! Please give an input of type integer");
            }
        }
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
