package ch.uzh.group38;

import java.util.Scanner;

public class Player {
    private int cash = 100;

    public int makeBet(){
        System.out.println("current chash: " + this.cash + "\nHow much would you like to bet? ");
        return this.readInput();
    }

    public int readInput(){
        while(true){
            try (Scanner s = new Scanner(System.in)) {
                return s.nextInt();
            }
            catch (Exception InputMismatchException) {
                System.out.println("Invalid input! Please give an input of type integer");
            }
        }
    }

    public int getCashAmount(){
        return cash;
    }

    
}
