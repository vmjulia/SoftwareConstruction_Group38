package ch.uzh.group38;

enum Rank {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
enum Suit {CLUBS, DIAMONDS, SPADES, HEARTS}

public class Card {
    private Rank aRank;
    private Suit aSuit;
    private boolean facingUp;
    private int value;

    public Card (Rank pRank, Suit pSuit){
        aRank = pRank;
        aSuit = pSuit;
        facingUp = true;
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

    public String display(){
        if (!this.facingUp) {
            return "[XXXXX]";
        }
        return "[" + this.aRank + " of " + this.aSuit + "]";
    }

    public int getValue() {
        if (!this.facingUp) {
            // so that player does not see value of hidden cards
            return 0;
        }
        return this.value;
    }

    public Rank getRank(){
        return this.aRank;
    }

    public void flip() {
        this.facingUp = !this.facingUp;
    }
}
