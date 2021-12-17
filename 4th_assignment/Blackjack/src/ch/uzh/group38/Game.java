package ch.uzh.group38;

import ch.uzh.group38.exceptions.NeedCardException;
import ch.uzh.group38.exceptions.BustException;

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
            } catch (BustException e) {
                player.checkScoreAndCash();
                if (player.isOutOfMoney()) {
                    handlePlayerOutOfMoney(player);
                } else {
                    handlePlayerBust(player);
                }
            }
        }

        try {
            dealer.takeTurn();
        } catch (BustException e) {
            dealer.notifyObservers();
            player.checkScoreAndCash();
            handleDealerBust();
        }

        dealer.notifyObservers();
        player.checkScoreAndCash();
        if (player.isOutOfMoney()) {
            handlePlayerOutOfMoney(player);
        }
        handleOrdinaryOutcome();
        playRound();
    }

    private void handlePlayerOutOfMoney(Player player) {
        dealer.removeObserver(player);
        System.out.println("\nYou are broke and you get kicked out of the casino\n");
        System.exit(0);
    }

    private void handlePlayerBust(Player player) {
        // cash has been withdrawn upon making bet, no need to do anything to player yet
        System.out.println("\nYou bust, dealer wins by default\n");
        playRound();
    }

    private void handleDealerBust() {
        // player isn't passed, as all players win, so no need to distinguish
        System.out.println("\nDealerBusts, you win by default\n");
        playRound();
    }

    private void handlePlayerBlackjack(Player player) {
        // it might not be the case in real game, but eeeeeeeeeeeeeeeeeeeeh
        System.out.println("\nYou have a blackjack, you win by default\n");
        playRound();
    }

    private void handleDealerBlackjack() {
        player.checkScoreAndCash();
        if (player.isOutOfMoney()) {
            handlePlayerOutOfMoney(player);
        } else {
            System.out.println("\nDealer has a blackjack, you loose by default\n");
            playRound();
        }
    }

    private void handleOrdinaryOutcome() {
        int playerIsWinner = player.checkWinner();
        if (playerIsWinner == 1) {
            System.out.println("\nYou win\n");
        } else if (playerIsWinner == 0) {
            System.out.println("\nDealer wins\n");
        } else if (playerIsWinner == 2){
            System.out.println("\nDraw\n");
        }
        playRound();
    }

    public static void main(String[] args) {
        new Game();
    }
}
