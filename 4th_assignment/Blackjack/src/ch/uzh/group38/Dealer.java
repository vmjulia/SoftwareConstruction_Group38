package ch.uzh.group38;

import java.util.Scanner;

public class Dealer {

    private static Scanner scanner = CustomScanner.getInstance();

    private static Player player = new Player();
    static Table table;
    private static int bet;

    private static void playGame(){
        do {
            bet = player.makeBet();
        } while (bet > player.getCashAmount());
        table = new Table();
        table.firstRound();
        playersTurn();
        dealersTurn();
    }

    private static void dealersTurn(){
        table.flipCard();
        while (table.dealerScore() < 17){
            System.out.println("Dealer draws a card");
            table.hitDealerCard();
            table.print();
        }
        System.out.println("Dealer stays");
        table.print();
        endOfGame();
    }

    private static void playersTurn(){
        String input;
        do {
            System.out.println("hit or stay? [H/S] ");
            input = scanner.nextLine();
            if (input == "s"){
                return;
            }
            else if (input == "h"){
                table.hitPlayerCard();
                table.print();
                if (table.playerScore() > 21){
                    endOfGame();
                    return;
                }
            }
        } while (input == "h");
        return;
    }

    private static void endOfGame(){
        if (table.playerScore() > 21){
            System.out.println("you bust!");
            player.pay(bet);
        }
        else if (table.dealerScore() > 21){
            System.out.println("Dealer busts!");
            player.increaseCash(bet);
        }
        else if (table.dealerScore() < table.playerScore()){
            System.out.println("you win!");
            player.increaseCash(bet);
        }
        else if (table.dealerScore() > table.playerScore()){
            System.out.println("Dealer wins!");
            player.pay(bet);
        }
        else if (table.dealerScore() == table.playerScore()){
            System.out.println("nobody wins");
        }
        if (player.getCashAmount() <= 0){
            System.out.println("Your are broke and you get kicked out of the casion!");
        }
        else{
            System.out.println("lets play again");
            playGame();
        }
    }

    public static void main(String[] args){
        playGame();
    }
}
