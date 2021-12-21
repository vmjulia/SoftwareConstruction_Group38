package ch.uzh.group38;

import java.util.ArrayList;
import java.util.Scanner;

public class Game implements Aggregate {

    private final Player player;
    private final Dealer dealer;
    private User currentPlayer;
    private final Deck deck;
    ArrayList<Card> cardsToGive;

    private Game() {
        this.player = new Player();
        selectInputBehaviour();
        this.dealer = new Dealer();
        this.deck = Deck.getInstance();
    }

    private void playRound() {
        this.reset();
        selectLeaveOrStay();
        this.firstRound();

        //Players turn
        currentPlayer = player;
        turn();

        //Dealers turn
        dealer.flipCard();
        currentPlayer = dealer;
        turn();

        endOfGame();
    }

    private void giveCards(User player, int numberOfCards) {
        cardsToGive = new ArrayList<Card>();
        for (int i = 0; i < numberOfCards; i++) {
            cardsToGive.add(deck.draw());
        }
        player.takeCards(createIterator());
    }

    private void turn() {
        printTable();
        while (currentPlayer.hit(currentPlayer.countScore())) {
            giveCards(currentPlayer, 1);
            if (currentPlayer.bust()) {
                printTable();
                endOfGame();
            }
            printTable();
        }
    }

    private void printTable() {
        System.out.println("\n-----------------------------------------");
        dealer.showCards();
        player.showCards();
    }

    private void reset() {
        dealer.reset();
        player.reset();
        this.deck.putDiscardBack();
        this.deck.shuffle();
    }

    private void firstRound() {
        player.makeBet();
        giveCards(player, 2);
        giveCards(dealer, 2);
        dealer.flipCard();
    }

    private void handlePlayerOutOfMoney() {
        System.out.println("\nYou are broke and you get kicked out of the casino\n");
        System.exit(0);
    }

    private void handlePlayerBust() {
        // cash has been withdrawn upon making bet, no need to do anything to player yet
        System.out.println("\nYou bust, dealer wins by default\n");
        player.looseBet();
        if (player.isOutOfMoney()) {
            handlePlayerOutOfMoney();
        }
    }

    private void handleDealerBust() {
        System.out.println("\nDealer busts, you win by default\n");
        player.winBet();
    }

    private void handlePlayerBlackjack() {
        System.out.println("\nYou have a blackjack, you win by default\n");
        player.winBet();
    }

    private void handleDealerBlackjack() {
        System.out.println("\nDealer has a blackjack, you loose by default\n");
        player.looseBet();
        if (player.isOutOfMoney()) {
            handlePlayerOutOfMoney();
        }
    }

    private void handlePlayerWin() {
        System.out.println("\nYou win\n");
        player.winBet();
    }

    private void handleDealerWin() {
        System.out.println("\nDealer wins\n");
        player.looseBet();
        if (player.isOutOfMoney()) {
            handlePlayerOutOfMoney();
        }
    }

    private void handleDraw() {
        System.out.println("\nDraw\n");
    }

    private void endOfGame() {
        if (player.bust()) {
            handlePlayerBust();
        } else if (dealer.bust()) {
            handleDealerBust();
        } else if (player.countScore() == 21) {
            handlePlayerBlackjack();
        } else if (dealer.countScore() == 21) {
            handleDealerBlackjack();
        } else if (player.countScore() > dealer.countScore()) {
            handlePlayerWin();
        } else if (player.countScore() < dealer.countScore()) {
            handleDealerWin();
        } else if (player.countScore() == dealer.countScore()) {
            handleDraw();
        }
        playRound();
    }

    private void selectInputBehaviour() {
        System.out.println("Do you want to have voice input [yes] or [no]?");
        String ans = this.readInput();
        if (ans.equals("yes")) {
            this.player.activateVoiceInput();
        }
    }

    private void selectLeaveOrStay() {
        this.player.displayCash();
        System.out.println("Would you like to leave the casino [yes] or [no]?");
        String ans = this.readInput();
        if (ans.equals("yes")) {
            System.out.println("Good bye!");
            System.exit(0);
        }
    }

    private String readInput() {
        while (true) {
            String input = new Scanner(System.in).nextLine().toLowerCase();
            if (input.equals("yes") || input.equals("no")) {
                return input;
            }
            System.out.println("Invalid input! Please choose [yes] or [no]");
        }
    }

    public Iterator createIterator() {
        return new CardIterator(cardsToGive);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playRound();
    }
}
