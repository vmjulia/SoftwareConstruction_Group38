package ch.uzh.group38;

import ch.uzh.group38.exceptions.NeedCardException;
import ch.uzh.group38.exceptions.PlayerBustException;
import ch.uzh.group38.exceptions.PlayerOutOfMoneyException;

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
        dealer.reset();
        player.reset();

        player.makeBet();
        dealer.giveCards(player, 2);
        dealer.takeCards();
        // showing player dealer's cards
        dealer.notifyObservers();

        while (true) {
            try {
                player.takeTurn();
                break;
            }  catch (NeedCardException e) {
                dealer.giveCards(player, 1);
            } catch (PlayerBustException e) {
                try {
                    player.checkScoreAndCash();
                    playRound();
                } catch (PlayerOutOfMoneyException ex) {
                    dealer.removeObserver(player);
                    System.out.println("\nYou are broke and you get kicked out of the casino!");
                    System.exit(0);
                }
            }
        }
        System.out.println("\n start of dealer.takeTurn \n");
        dealer.takeTurn();
        try {
            player.checkScoreAndCash();
        } catch (PlayerOutOfMoneyException e) {
            dealer.removeObserver(player);
            System.out.println("\nYou are broke and you get kicked out of the casino!");
            System.exit(0);
        }

        playRound();
    }

    public static void main(String[] args) {
        new Game();
    }
}
