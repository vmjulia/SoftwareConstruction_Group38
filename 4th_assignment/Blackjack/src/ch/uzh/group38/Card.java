package ch.uzh.group38;

enum Rank {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
enum Suit {CLUBS, DIAMONDS, SPADES, HEARTS}

public class Card {
    private Rank aRank;
    private Suit aSuit;
    private int value;

    public Card (Rank pRank, Suit pSuit){
        aRank = pRank;
        aSuit = pSuit;
        switch(aRank){
            case ACE: value = 11; break;
            case TWO: value = 2; break;
            case THREE: value = 3; break;
            case FOUR: value = 4; break;
            case FIVE: value = 5; break;
            case SIX: value = 6; break;
            case SEVEN: value = 7; break;
            case EIGHT: value = 8; break;
            case NINE: value = 9; break;
            default: value = 10; break;
        }
    }

    public void display(){
        System.out.println("[" + this.aRank + " of " + this.aSuit + "]");
    }

    public int getValue() {
        return this.value;
    }
}
