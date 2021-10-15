package ch.uzh.group38;

/* import ch.uzh.group38.Board;
import ch.uzh.group38.RuleEvaluator; */

import java.util.Locale;
import java.util.Scanner;

public class Game {

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

        if(!input.matches("^\\[[a-h][1-8]\\]x\\[[a-h][1-8]\\]$")){
            System.out.println("Sorry. That is not a valid input.");
            askForInput();
            return;
        }

        int[] coordinates = convertInput(input);
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
    [a3]x[b4] for example will become: x1=5, y1=0 and x2=4, y2=1
    since a3 is board[5][0] and b4 is board [4][1]
    */
    private static int[] convertInput(String input){
        int x1 = 8 - Character.getNumericValue(input.charAt(2));
        int y1 = input.charAt(1) - 'a';

        int x2 = 8 - Character.getNumericValue(input.charAt(7));
        int y2 = input.charAt(6) - 'a';

        int[] coordinates = {x1, y1, x2, y2};
        return coordinates;
    }

    public static void nextMove(){
        Board.printBoard();
        //askForInput();
        //to be implemented furhter
    }

    public static void main(String[] args) {
        Board.resetBoard();
        nextMove();
    }
}
