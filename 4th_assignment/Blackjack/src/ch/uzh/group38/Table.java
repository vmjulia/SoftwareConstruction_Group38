package ch.uzh.group38;

import java.util.ArrayList;

public class Table {
    private ArrayList<Card> playerCards = new ArrayList<Card>();
    private ArrayList<Card> dealerCards = new ArrayList<Card>();
    
    public void printTable(){
        System.out.println("Dealers cards:   (score: " + score(dealerCards) + ")");
        for (Card c : dealerCards) {            
            System.out.print(c.display() + " ");
        }
        System.out.println("\n");        
        System.out.println("Players cards:   (score: " + score(playerCards) + ")");
        for (Card c : playerCards) {            
            System.out.print(c.display() + " ");
        }
    }

    public void firstRound(){
        Deck deck = new Deck();
        deck.shuffle();
        playerCards.add(deck.draw());
        playerCards.add(deck.draw());
        dealerCards.add(deck.draw());
        this.printTable();
        dealerCards.add(deck.draw());
    }

    private int score(ArrayList<Card> cards){
        int score = 0;
        for (Card c : cards){
            score += c.getValue();
        }
        return score;
    }
}
