package ch.uzh.group38;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

interface Iterator {
    boolean hasNext();
    Card next();
}

public class CardIterator implements Iterator {
    Queue<Card> myQueue = new LinkedList<>();

    public CardIterator(ArrayList<Card> cards) {
        for (Card card : cards) myQueue.add(card);
    }

    public boolean hasNext() {
        return !myQueue.isEmpty();
    }

    public Card next() {
        return myQueue.remove();
    }
}
