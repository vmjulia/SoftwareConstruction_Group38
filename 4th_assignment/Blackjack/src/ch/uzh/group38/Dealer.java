package ch.uzh.group38;


interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);

    // can be split into different notifyObserver methods
    void notifyObserver();
}

public class Dealer implements Subject{

    private final Deck deck;
    private int score;
    private Card[] cards;

    private Observer observer;

    public Dealer() {
        this.deck = Deck.getInstance();
    }

    public void giveCards(Player player) {

    }

    // probably shuffles the deck
    public void reset() {

    }

    // called from game to tell dealer to take his cards
    public void takeCards() {

    }

    public void takeTurn() {


        notifyObserver();
    }

    public int countScore() {
        return this.score;
    }

    private Iterator getCards() {
        return new CardIterator(this.cards);
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void removeObserver(Observer observer) {

    }

    // get cardIterator from dealer and pass it to all observers
    @Override
    public void notifyObserver() {
        observer.update(getCards());
    }
}

/*
import java.util.Scanner;

public class Dealer {

    private static final NewRoundState newRoundState = new NewRoundState();
    private static final PlayerBustState playerBustState = new PlayerBustState();
    private static final PlayerNotBustState playerNotBustState = new PlayerNotBustState();
    private static final DealerBustState dealerBustState = new DealerBustState();
    private static final DealerNotBustState dealerNotBustState = new DealerNotBustState();

    private static State currentState = newRoundState;

    private static Scanner scanner = CustomScanner.getInstance();

    private static Player player = new Player();
    private static Table table = Table.getInstance();



    private static void playRound(){
        player.makeBet();
        table.resetTable();
        table.firstRound();
        playersTurn();
    }

    private static void dealersTurn(){
        table.flipCard();
        table.print();
        while (table.dealerScore() < 17){
            System.out.println("Dealer draws a card");
            table.hitDealerCard();
            table.print();
        }
        if (table.dealerScore() > 21){
            currentState = dealerBustState;
        }
        else {
            System.out.println("Dealer stays");
            currentState = dealerNotBustState;
        }
        currentState.nextAction();
    }

    private static void playersTurn(){
        String input;
        while (true){
            System.out.println("hit or stay? [H/S] ");
            input = scanner.nextLine().toLowerCase();
            if (input.equals("s")){
                currentState = playerNotBustState;
                break;
            }
            else if (input.equals("h")){
                table.hitPlayerCard();
                table.print();
                if (table.playerScore() > 21) {
                    currentState = playerBustState;
                    break;
                }
            }
        }
        currentState.nextAction();
    }

    private interface State {
        void nextAction();
    }

    private static class NewRoundState implements State {
        public void nextAction(){
            playRound();
        }
    }

    private static class PlayerBustState implements State {
        public void nextAction(){
            System.out.println("You bust! Dealer wins");
            player.loseMoney();
            currentState = newRoundState;
        }
    }

    private static class PlayerNotBustState implements State {
        public void nextAction(){
            dealersTurn();
        }
    }

    private static class DealerBustState implements State {
        public void nextAction(){
            System.out.println("Dealer busts! You win!");
            player.winMoney();
            currentState = newRoundState;
        }
    }

    private static class DealerNotBustState implements State {
        public void nextAction(){
            checkWinner();
            currentState = newRoundState;
        }
    }

    private static void checkWinner(){
        if (table.dealerScore() < table.playerScore()){
            System.out.println("You win!");
            player.winMoney();
        }
        else if (table.dealerScore() > table.playerScore()){
            System.out.println("Dealer wins!");
            player.loseMoney();
        }
        else if (table.dealerScore() == table.playerScore()){
            System.out.println("Nobody wins");
        }
    }

    private static boolean thereIsANextRound(){
        if (!player.isCashLeft()){
            System.out.println("\nYou are broke and you get kicked out of the casino!");
            return false;
        }
        else{
            System.out.println("\nLet's play a round of Blackjack!");
            return true;
        }
    }

    public static void main(String[] args){
        Dealer dealer = new Dealer();
        while(dealer.thereIsANextRound()){
            dealer.currentState.nextAction();
        }
    }
}
*/