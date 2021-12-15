package ch.uzh.group38;


interface Subject {
    void registerObserver();
    void removeObserver();

    // can be split into different notifyObserver methods
    void notifyObserver();
}

public class Game implements Subject{

    private Player player;
    private Dealer dealer;

    private Game() {

    }

    private void playRound() {

    }

    public static void main(String[] args) {

    }

    @Override
    public void registerObserver() {

    }

    @Override
    public void removeObserver() {

    }

    @Override
    public void notifyObserver() {

    }
}
