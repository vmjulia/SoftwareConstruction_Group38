package ch.uzh.group38;

import java.util.ArrayList;
import java.util.Scanner;

public class Table {
    private ArrayList<Card> playerCards = new ArrayList<Card>();
    private ArrayList<Card> dealerCards = new ArrayList<Card>();
    private Card coveredCard;
    Deck deck;
    
    public void print(){
        System.out.println("Dealers cards:   (score: " + score(dealerCards) + ")");
        for (Card c : dealerCards) {            
            System.out.print(c.display() + " ");
        }
        System.out.println("\n");        
        System.out.println("Players cards:   (score: " + score(playerCards) + ")");
        for (Card c : playerCards) {            
            System.out.print(c.display() + " ");
        }
        System.out.println("\n");
    }

    public void firstRound(){
        deck = new Deck();
        deck.shuffle();
        playerCards.add(deck.draw());
        playerCards.add(deck.draw());
        dealerCards.add(deck.draw());
        this.coveredCard = deck.draw();
        this.print();
    }

    public void playersTurn(){
        String input;
        do {
            System.out.println("hit or stay? [H/S] ");
            input = System.console().readLine();
            if (input.charAt(0) == 's'){
                return;
            }
            else if (input.charAt(0) == 'h'){
                playerCards.add(deck.draw());
                this.print();
            }
        } while (input.charAt(0) == 'h');
        return;
    }

    private int score(ArrayList<Card> cards){
        int score = 0;
        int aces = 0;
        for (Card c : cards){
            score += c.getValue();
            if (c.getRank() == Rank.ACE){
                aces ++;
            }
        }
        while (aces > 0 && score > 21){
            score -= 10;
            aces--;
        }
        return score;
    }
}
