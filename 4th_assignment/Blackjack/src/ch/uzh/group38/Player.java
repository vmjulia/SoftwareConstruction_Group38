package ch.uzh.group38;

import java.util.ArrayList;
import java.util.Scanner;


interface Observer {
    void update(Iterator iterator);
}

public class Player implements Observer {

    private int cash = 100;
    private int bet;
    private ArrayList<Card> cards = new ArrayList<Card>();

    private int dealersScore = 0;
    private ArrayList<Card> dealersCards = new ArrayList<Card>();

    public Player() {

    }

    public int makeBet() {
        System.out.println("Your current cash: " + this.cash + "\nHow much would you like to bet? ");
        int bet =  this.readIntInput();
        if (bet > this.cash || bet <= 0){
            makeBet();
        }
        this.bet = bet;
        this.cash -= bet;
        return this.bet;
    }

    public void takeTurn() {
        // print cards here
        System.out.println("hit or stay? [H/S] ");
        String input = readHitOrStayInput();

    }

    public void takeCards(Iterator iterator) {
        while (iterator.hasNext()) {
            cards.add(iterator.next());
        }
    }

    public void checkScore() throws PlayerBustException {
        int score = countScore();
        if (score > dealersScore) {
            this.cash += 2*bet;
        } else if (score == dealersScore) {
            this.cash += bet;
        } else if (this.cash == 0){
            throw (new PlayerBustException());
        }
    }

    private int countScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getValue();
        }
        return score;
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

    private String readHitOrStayInput() {
        while (true) {
            String input = new Scanner(System.in).nextLine().toLowerCase();
            if (input.equals("h") || input.equals("s")){
                return input;
            }
            System.out.println("Invalid input! Please choose [H] or [S]");
        }
    }

    @Override
    public void update(Iterator iterator) {
        while (iterator.hasNext()) {
            Card card = iterator.next();
            dealersScore += card.getValue();
            // should be empty at this point!
            this.dealersCards.add(card);
        }
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