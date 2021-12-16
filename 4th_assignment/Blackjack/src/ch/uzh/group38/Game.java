package ch.uzh.group38;


public class Game {

    private final Player player;
    private final Dealer dealer;

    private Game() {
        this.player = new Player();
        this.dealer = new Dealer();
        dealer.registerObserver(player);
        playRound();
    }

    private void playRound() {
        player.makeBet();
        dealer.giveCards(player);
        dealer.takeCards();
        //show dealer's cards to player

        // Game.print() or its equivalent should be called inside takeTurn
        player.takeTurn();
        dealer.takeTurn();
    }

    public static void main(String[] args) {
        new Game();
    }
}
