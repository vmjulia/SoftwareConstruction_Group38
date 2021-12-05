package ch.uzh.group38;

enum Suit {CLUBS, DIAMONDS, SPADES, HEARTS}
enum Rank {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}

public class Card {
    private Rank aRank;
    private Suit aSuit;
    private int value;

    public Card (Rank pRank, Suit pSuit){
        aRank = pRank;
        aSuit = pSuit;
        value = 1;
    }
}
