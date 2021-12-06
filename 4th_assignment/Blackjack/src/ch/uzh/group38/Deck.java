package ch.uzh.group38;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    
    private static Stack<Card> cards = new Stack<Card>();

    public Deck(){
        for (Suit s : Suit.values()){
            for (Rank r : Rank.values()){
                cards.push(new Card(r, s));
            }
        }         
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card draw(){
        if (!cards.empty()){
            return cards.pop();
        }
        else {
            System.out.println("No cards left in the deck!");
            return null;}
    }
    
}
