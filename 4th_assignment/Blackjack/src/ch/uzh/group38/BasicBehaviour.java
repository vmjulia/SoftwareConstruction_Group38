package ch.uzh.group38;

import java.util.ArrayList;

public class BasicBehaviour {
    ArrayList<Card> cards = new ArrayList<Card>();
    int score;

    TakeTurnBehaviour takeTurnBehaviour;
    TakeCardBehaviour takeCardBehaviour;

    // maybe no need to pass
    public void showCards(ArrayList<Card> cards){
        System.out.println("Players cards:   (score: " + countScore(cards) + ")");
        for (Card c : cards) {
            System.out.print(c.display() + " ");
        }
        System.out.println("\n");
    }

    public int countScore(ArrayList<Card> cards) {
        int score = 0;
        for (Card card : cards) {
            score += card.getValue();
        }
        return score;
    }

    public void blackjackCheck() {

    }
}


interface TakeTurnBehaviour {
    void takeTurn();
}

class TakeTurnPlayer implements TakeTurnBehaviour {
    @Override
    public void takeTurn() {

    }
}

class TakeTurnDealer implements TakeTurnBehaviour {
    @Override
    public void takeTurn() {
        //this.cards;
        //this.countScore();
    }
}

interface TakeCardBehaviour {
    void takeCard();
}

class TakeCardPlayer implements TakeCardBehaviour {
    @Override
    public void takeCard() {

    }
}

class TakeCardDealer implements TakeCardBehaviour {
    @Override
    public void takeCard() {

    }
}