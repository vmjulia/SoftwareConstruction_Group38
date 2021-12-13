package ch.uzh.group38;

import java.util.Scanner;

public class Player {
    private int cash = 100;
    private Scanner scanner = CustomScanner.getInstance();

    public int makeBet(){
        System.out.println("Your current cash: " + this.cash + "\nHow much would you like to bet? ");
        return this.readInput();
    }

    public int readInput(){
        while (true) {
            String input = scanner.nextLine();
            try {
                int i = Integer.parseInt(input);
                return i;
            } catch (Exception NumberFormatException) {
                System.out.println("Invalid input! Please give an input of type integer");
            }
        }
    }

    public int getCashAmount(){
        return cash;
    }

    public void winMoney(int c){
        this.cash += c;
    }

    public void loseMoney(int c){
        this.cash -= c;
    }
}
