package ch.uzh.group38;

import java.io.IOException;
import java.util.ArrayList;

public abstract class User {
    public HitBehaviour hitBehaviour;
    public InputBehaviour inputBehaviour;
    protected final String NAME;

    protected User(String name) {
        this.NAME = name;
    }

    private final ArrayList<Card> cards = new ArrayList<Card>();

    public void reset() {
        this.cards.clear();
    }

    public Iterator createIterator() {
        return new CardIterator(cards);
    }

    public void chooseInputBehaviour(String type) {
        if (type.equals("Dealer")) {
            this.hitBehaviour = new DealerStrategy();
        } else if (type.equals("Player")) {
            this.hitBehaviour = new PlayerStrategy();
        } else {
            try {
                this.hitBehaviour = new PlayerVoiceStrategy();
            } catch (IOException e) {
                this.hitBehaviour = new PlayerStrategy();
            }
        }

    }

    protected int countScore() {
        int score = 0;
        int aces = 0;
        for (Card card : this.cards) {
            score += card.getValue();
            if (card.getRank() == Rank.ACE) {
                aces++;
            }
        }
        while (aces > 0 && score > 21) {
            score -= 10;
            aces--;
        }
        return score;
    }

    public void showCards() {
        Iterator cards = createIterator();
        System.out.println(this.NAME + " cards:   (score: " + countScore() + ")");
        while (cards.hasNext()) {
            System.out.print(cards.next().display() + " ");
        }
        System.out.println("\n");
    }

    public void takeCards(Iterator iterator) {
        while (iterator.hasNext()) {
            this.cards.add(iterator.next());
        }
    }

    public boolean bust() {
        if (this.countScore() > 21) {
            return true;
        } else {
            return false;
        }
    }
}
