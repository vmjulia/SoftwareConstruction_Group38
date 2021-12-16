package ch.uzh.group38;

import java.util.Scanner;


interface Observer {
    void update(Iterator iterator);
}

interface Aggregate {
    Iterator createIterator();
}

public class Player implements Observer, Aggregate{

    private int cash = 100;
    private int bet;
    private int score;
    private Card[] cards;

    public Player() {

    }

    public int makeBet() {
        System.out.println("Your current cash: " + this.cash + "\nHow much would you like to bet? ");
        int bet =  this.readInput();
        if (bet > this.cash || bet <= 0){
            makeBet();
        }
        this.bet = bet;
        return this.bet;
    }

    public void takeTurn() {

    }

    private int readInput(){
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

    public int countScore() {
        return this.score;
    }

    @Override
    public void update(Iterator iterator) {
        // will be updated from Dealer
        Card[] dealersCards;
        int dealersScore;

        // cash and score are updated,
        // exceptions can be sent to dealer and then to game in case player runs out of money
        this.score = score;
        this.cash = cash;
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