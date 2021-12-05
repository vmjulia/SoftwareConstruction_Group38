package ch.uzh.group38;

import java.util.Stack;

public class Deck {
    
    Stack<Card> cards = new Stack<Card>();

    public Deck(){
        //To be changed
        cards.push(new Card(Rank.ACE, Suit.CLUBS)); 
    }

    public static void shuffle(){

    }

    public Card draw(){
        return null;
    }
    
}
