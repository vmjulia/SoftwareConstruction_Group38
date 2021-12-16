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
        dealer.giveCards(player, 2);
        dealer.takeCards();
        //show dealer's cards to player

        // Game.print() or its equivalent should be called inside takeTurn
        player.takeTurn();
        dealer.takeTurn();

        try {
            player.checkScore();
        } catch (PlayerBustException e) {
            dealer.removeObserver(player);
            System.out.println("\nYou are broke and you get kicked out of the casino!");
            System.exit(0);
        }

        // call some reset here to clear cards, shuffle the deck
    }

    public static void main(String[] args) {
        new Game();
    }
}
