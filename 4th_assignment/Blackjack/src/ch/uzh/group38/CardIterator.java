package ch.uzh.group38;


interface Iterator {
    boolean hasNext();
    Card next();

    // no idea what this should actually be
    void remove();

}
public class CardIterator implements Iterator{

    public CardIterator(Card[] cards) {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Card next() {
        return null;
    }

    @Override
    public void remove() {

    }
}
