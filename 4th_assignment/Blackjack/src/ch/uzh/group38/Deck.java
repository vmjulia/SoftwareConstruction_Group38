package ch.uzh.group38;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private static Deck instance;
    private static Stack<Card> cards = new Stack<Card>();

    private Deck(){
        for (Suit s : Suit.values()){
            for (Rank r : Rank.values()){
                cards.push(new Card(r, s));
            }
        }         
    }

    public static Deck getInstance(){
        if (instance == null){
            instance = new Deck();
        }
        return instance;
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
