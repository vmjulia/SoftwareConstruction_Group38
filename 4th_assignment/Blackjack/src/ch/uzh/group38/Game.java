package ch.uzh.group38;

import java.util.ArrayList;


public class Game {

    private final Player player;
    private final Dealer dealer;
    private User currentPlayer;
    private Deck deck;

    private Game() {
        this.player = new Player();
        this.dealer = new Dealer();
        this.deck = Deck.getInstance();
    }

    public void playRound() {
        this.reset();
        this.firstRound();

        //Players turn
        player.strategy = new PlayerStrategy();
        currentPlayer = player;
        turn();
        
        //Dealers turn
        dealer.cards.get(1).flip();
        dealer.strategy = new DealerStrategy();
        currentPlayer = dealer;
        turn();

        endOfGame();
    }

    private void giveCards(User player, int numberOfCards) {
        ArrayList<Card> playersCards = new ArrayList<Card>();
        for (int i = 0; i < numberOfCards; i++){
            playersCards.add(deck.draw());
        }
        player.takeCards(new CardIterator(playersCards));
    }


    private void turn(){
        printTable();
        while (currentPlayer.strategy.hit(currentPlayer.countScore())){
            giveCards(currentPlayer, 1);
            if (currentPlayer.bust()){
                printTable();
                endOfGame();
            }
            printTable();
        }
    }

    private void printTable(){
        System.out.println("\n-----------------------------------------");
        dealer.showCards();
        player.showCards();
    }

    private void reset(){
        dealer.reset();
        player.reset();
        this.deck.putDiscardBack();
        this.deck.shuffle();
    }

    private void firstRound(){
        player.makeBet();
        giveCards(player, 2);
        giveCards(dealer, 2);
        dealer.cards.get(1).flip();
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
        // player isn't passed, as all players win, so no need to distinguish
        System.out.println("\nDealerBusts, you win by default\n");
        player.winBet();
    }

    private void handlePlayerBlackjack() {
        // it might not be the case in real game, but eeeeeeeeeeeeeeeeeeeeh
        // the hidden dealer's card is not shown
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

    private void handlePlayerWin(){
        System.out.println("\nYou win\n");
        player.winBet();
    }

    private void handleDealerWin(){
        System.out.println("\nDealer wins\n");
        player.looseBet();
        if (player.isOutOfMoney()) {
            handlePlayerOutOfMoney();
        }
    }

    private void handleDraw(){
        System.out.println("\nDraw\n");
    }

    private void endOfGame() {
        if (player.bust()){
            handlePlayerBust();
        }
        else if (dealer.bust()){
            handleDealerBust();
        }
        else if (player.countScore() == 21){
            handlePlayerBlackjack();
        }
        else if (dealer.countScore() == 21){
            handleDealerBlackjack();
        }
        else if (player.countScore() > dealer.countScore()){
            handlePlayerWin();
        }
        else if (player.countScore() < dealer.countScore()){
            handleDealerWin();
        }
        else if (player.countScore() == dealer.countScore()){
            handleDraw();
        }
        playRound();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playRound();
    }
}
