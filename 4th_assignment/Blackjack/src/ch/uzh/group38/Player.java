package ch.uzh.group38;

import ch.uzh.group38.exceptions.NeedCardException;
import ch.uzh.group38.exceptions.PlayerBustException;
import ch.uzh.group38.exceptions.PlayerOutOfMoneyException;

import java.util.ArrayList;
import java.util.Scanner;


interface Observer {
    void update(Iterator iterator);
}

public class Player implements Observer {

    private int cash = 100;
    private int bet;
    private ArrayList<Card> cards = new ArrayList<Card>();

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

    public void takeTurn() throws NeedCardException, PlayerBustException {
        if (countScore(cards) > 21) {throw (new PlayerBustException());}

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

    public void checkScoreAndCash() throws PlayerOutOfMoneyException {
        int score = countScore(cards);
        int dealersScore = countScore(dealersCards);
        if (score > dealersScore && score <= 21) {
            this.cash += 2*bet;
        } else if (score == dealersScore && score <= 21) {
            this.cash += bet;
        } else if (this.cash == 0){
            throw (new PlayerOutOfMoneyException());
        }
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
        while (iterator.hasNext()) {
            dealersCards.add(iterator.next());
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