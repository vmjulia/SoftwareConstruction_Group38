package ch.uzh.group38;

import java.util.Scanner;

public class Dealer {

    private static Scanner scanner = CustomScanner.getInstance();

    private static Player player = new Player();
    static Table table = Table.getInstance();
    private static int bet;

    private static void playRound(){
        do {
            bet = player.makeBet();
        } while (bet > player.getCashAmount() || bet <= 0);
        table.reset();
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
        endOfRound();
    }

    private static void playersTurn(){
        String input;
        while(true){
            System.out.println("hit or stay? [H/S] ");
            input = scanner.nextLine().toLowerCase();
            if (input.equals("s")){
                return;
            }
            else if (input.equals("h")){
                table.hitPlayerCard();
                table.print();
                if (table.playerScore() > 21){
                    endOfRound();
                    return;
                }
            }
        }
    }

    private static void endOfRound(){
        if (table.playerScore() > 21){
            System.out.println("you bust!");
            player.loseMoney(bet);
        }
        else if (table.dealerScore() > 21){
            System.out.println("Dealer busts!");
            player.winMoney(bet);
        }
        else if (table.dealerScore() < table.playerScore()){
            System.out.println("you win!");
            player.winMoney(bet);
        }
        else if (table.dealerScore() > table.playerScore()){
            System.out.println("Dealer wins!");
            player.loseMoney(bet);
        }
        else if (table.dealerScore() == table.playerScore()){
            System.out.println("nobody wins");
        }
        if (player.getCashAmount() <= 0){
            System.out.println("Your are broke and you get kicked out of the casino!");
        }
        else{
            System.out.println("lets play again");
            playRound();
        }
    }

    public static void main(String[] args){
        playRound();
    }
}
