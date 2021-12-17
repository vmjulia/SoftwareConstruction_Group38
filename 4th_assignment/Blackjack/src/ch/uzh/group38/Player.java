package ch.uzh.group38;

import ch.uzh.group38.exceptions.BlackjackException;
import ch.uzh.group38.exceptions.NeedCardException;
import ch.uzh.group38.exceptions.BustException;

import java.util.ArrayList;
import java.util.Scanner;


interface Observer {
    void update(Iterator iterator);
}

public class Player implements Observer {

    private int cash = 100;
    private int bet;
    private final ArrayList<Card> cards = new ArrayList<Card>();
    private final ArrayList<Card> dealersCards = new ArrayList<Card>();

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

    // player bust exception could be replaced with a simple check here
    public void takeTurn() throws NeedCardException, BustException, BlackjackException {
        if (countScore(cards) == 21) {throw (new BlackjackException());}
        if (countScore(cards) > 21) {throw (new BustException());}

        print();
        System.out.println("hit or stay? [H/S] ");
        String input = readHitOrStayInput();
        switch (input) {
            case "s": break;
            case "h": throw (new NeedCardException());
        }
    }

    public void takeCards(Iterator iterator) {
        while (iterator.hasNext()) {
            cards.add(iterator.next());
        }
    }

    public void checkScoreAndCash() {
        print();
        int score = countScore(cards);
        int dealersScore = countScore(dealersCards);
        // if player busts first, player looses regardless of dealerScore
        if (score <= 21) {
            if (dealersScore <= 21) {
                if (score > dealersScore) {
                    this.cash += 2*bet;
                } else if (score == dealersScore) {
                    this.cash += bet;
                }
                //bet has already been withdrawn from cash
            } else {
                // dealer busts
                this.cash += 2*bet;
            }
            // so that cash is not updated if checkScoreAndCash is called more than once
            bet = 0;
        }
    }

    // haven't found a better way, except two booleans maybe
    public int checkWinner() {
        int score = countScore(cards);
        int dealersScore = countScore(dealersCards);
        if (score > dealersScore) {
            return 1;
        } else if (score < dealersScore) {
            return 0;
        }
        return 2;
    }

    public boolean isOutOfMoney() {
        return this.cash <= 0;
    }

    public void reset() {
        this.dealersCards.clear();
        this.cards.clear();
    }

    private int countScore(ArrayList<Card> cards) {
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

    private void print(){
        System.out.println("Dealers cards:   (score: " + countScore(dealersCards) + ")");
        for (Card c : dealersCards) {
            System.out.print(c.display() + " ");
        }
        System.out.println("\n");
        System.out.println("Players cards:   (score: " + countScore(cards) + ")");
        for (Card c : cards) {
            System.out.print(c.display() + " ");
        }
        System.out.println("\n");
    }

    @Override
    public void update(Iterator iterator) {
        this.dealersCards.clear();
        while (iterator.hasNext()) {
            this.dealersCards.add(iterator.next());
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