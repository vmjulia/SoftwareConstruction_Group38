package ch.uzh.group38;


interface Observer {
    void update();
}

interface Aggregate {
    Iterator createIterator();
}

public class Player implements Observer, Aggregate{

    private int bet;
    private int score;
    private Card[] cards;

    public Player() {

    }

    public int makeBet() {
        return this.bet;
    }

    public void takeTurn() {

    }

    public int countScore() {
        return this.score;
    }

    @Override
    public void update() {

    }

    @Override
    public Iterator createIterator() {
        return null;
    }
}
/*
import java.util.Scanner;

public class Player {
    private int cash = 100;
    private Scanner scanner = CustomScanner.getInstance();
    private int bet;

    public void makeBet(){
        System.out.println("Your current cash: " + this.cash + "\nHow much would you like to bet? ");
        int bet =  this.readInput();
        if (bet > this.cash || bet <= 0){
            makeBet();
        }
        this.bet = bet;
    }

    public int readInput(){
        while (true) {
            String input = scanner.nextLine();
            try {
                int i = Integer.parseInt(input);
                return i;
            } catch (Exception NumberFormatException) {
                System.out.println("Invalid input! Please give an input of type integer");
            }
        }
    }

    public void winMoney(){
        this.cash += this.bet;
    }

    public void loseMoney(){
        this.cash -= this.bet;
    }

    public boolean isCashLeft(){
        return (this.cash > 0);
    }
}
*/