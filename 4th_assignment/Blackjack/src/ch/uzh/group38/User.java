package ch.uzh.group38;

import java.util.ArrayList;

public abstract class User {
    public Strategy strategy;

    private final ArrayList<Card> cards = new ArrayList<Card>();

    public void reset() {
        this.cards.clear();
    }

    public Iterator createIterator(){
        return new CardIterator(cards);
    }

    protected int countScore() {
        int score = 0;
        int aces = 0;
        for (Card card : this.cards) {
            score += card.getValue();
            if (card.getRank() == Rank.ACE){
                aces ++;
            }
        }
        while (aces > 0 && score > 21){
            score -= 10;
            aces--;
        }
        return score;
    }

    public void takeCards(Iterator iterator) {
        while (iterator.hasNext()) {
            this.cards.add(iterator.next());
        }
    }

    public boolean bust(){
        if (this.countScore() > 21){
            return true;
        }
        else{
            return false;
        }
    }
}
