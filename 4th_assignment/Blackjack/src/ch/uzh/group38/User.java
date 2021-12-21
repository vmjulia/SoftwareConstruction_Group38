package ch.uzh.group38;

import java.io.IOException;
import java.util.ArrayList;
interface Aggregate{
    Iterator createIterator();
}
public abstract class User implements Aggregate {
    private HitBehaviour hitBehaviour;
    private InputBehaviour inputBehaviour;

    private final String NAME;
    private final ArrayList<Card> cards = new ArrayList<>();

    protected User(String name) {
        this.NAME = name;
        if (this.NAME.equals("Player")) {
            this.hitBehaviour = new PlayerHitBehaviour();
            this.inputBehaviour = new TerminalInputBehaviour();
        } else if (this.NAME.equals("Dealer")) {
            this.hitBehaviour = new DealerHitBehaviour();
            this.inputBehaviour = new DummyInputBehaviour();
        }
    }

    public void activateVoiceInput() {
        try {
            this.inputBehaviour = new VoiceInputBehaviour();
        } catch (IOException e) {
            this.inputBehaviour = new TerminalInputBehaviour();
            throw (new RuntimeException("Failed to assign voice input\nSelecting terminal input"));
        }
    }

    public void reset() {
        this.cards.clear();
    }

    public boolean hit(int score) {
        return this.hitBehaviour.hit(this.inputBehaviour.readHitOrStayInput(), score);
    }

    public boolean bust() {
        if (this.countScore() > 21) {
            return true;
        } else {
            return false;
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

    public void takeCards(Iterator iterator) {
        while (iterator.hasNext()) {
            this.cards.add(iterator.next());
        }
    }

    public void showCards() {
        Iterator cards = createIterator();
        System.out.println(this.NAME + " cards:   (score: " + countScore() + ")");
        while (cards.hasNext()) {
            System.out.print(cards.next().display() + " ");
        }
        System.out.println("\n");
    }

    public Iterator createIterator() {
        return new CardIterator(cards);
    }
}
