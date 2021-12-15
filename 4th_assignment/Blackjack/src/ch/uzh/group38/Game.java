package ch.uzh.group38;


interface Subject {
    void registerObserver();
    void removeObserver();

    // can be split into different notifyObserver methods
    void notifyObserver();
}

public class Game implements Subject{

    private final Player player;
    private final Dealer dealer;

    private Game() {
        this.player = new Player();
        this.dealer = new Dealer();
        playRound();
    }

    private void playRound() {
        player.makeBet();
        dealer.giveCards(player);
        dealer.takeCards();
        //show dealer's cards to player
        print();

        // Game.print() or its equivalent should be called inside takeTurn
        player.takeTurn();
        dealer.takeTurn();

        // pass give player dealer's score for player to compare
        notifyObserver();
    }

    private void print() {

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
        player.update();
    }
}
