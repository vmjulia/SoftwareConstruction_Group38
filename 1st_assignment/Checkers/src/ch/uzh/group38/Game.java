package ch.uzh.group38;

/* import ch.uzh.group38.Board;
import ch.uzh.group38.RuleEvaluator; */

import java.util.Locale;
import java.util.Scanner;

public class Game {

    private static int[] coordinates;
    /*
    asks for input 
    */
    public static void askForInput(){
        System.out.println("Player " + RuleEvaluator.getCurrentPlayer() + " please enter your next move in this format [a3]X[b4]:");
        readInput();
    }

    /*
    reads input from console and checks its format
    */
    public static void readInput(){
        Scanner s = new Scanner(System.in);
        String input = s.nextLine().toLowerCase();
        s.close();

        if(!input.matches("^\\[[a-h][1-8]\\]x\\[[a-h][1-8]\\]$")){
            System.out.println("Sorry. That is not a valid input.");
            askForInput();
            return;
        }

        coordinates = convertInput(input);
        for(int i = 0; i<4; i++) {
            System.out.println(coordinates[i]);
        }

        if (!RuleEvaluator.checkValidity(coordinates)) {
            System.out.println("Sorry. That is not a valid move.");
            askForInput();
            return;
        }
        //to be implemented further
    }

    /*
    converts String input into four variables {x1, y1, x2, y2}
    [a3]x[b4] for example will become: x1=0, y1=2 and x2=1, y2=3
    */
    private static int[] convertInput(String input){
        int x1 = input.charAt(1) - 'a';
        int y1 = Character.getNumericValue(input.charAt(2)) - 1;

        int x2 = input.charAt(6) - 'a';
        int y2 = Character.getNumericValue(input.charAt(7)) - 1;

        int[] coordinates = {x1, y1, x2, y2};
        return coordinates;
    }

    public static void nextMove(){
        Board.printBoard();
        askForInput();
        Move.move(coordinates);
        //to be implemented furhter
    }

    public static void main(String[] args) {
        Board.resetBoard();
        nextMove();
    }
}
