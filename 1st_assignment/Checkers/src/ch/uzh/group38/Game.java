package ch.uzh.group38;


import java.util.Locale;
import java.util.Scanner;

public class Game {

    private static Move currentMove;
    private static Board board;

    /*
    asks for input 
    */

    public static void askForInput(){
        System.out.println("Player " + RuleEvaluator.getCurrentPlayer() + " please enter your next move in this format [a3]X[b4]:");
    }

    /*
    reads input from console and checks its format
    */
    public static String ReadInput(){
        Scanner s = new Scanner(System.in);
        return s.nextLine().toLowerCase();
    }

    public static void getInput(){
        askForInput();
        String input = ReadInput();

        //checks that the input is of the correct format using regex
        if(!input.matches("^\\[[a-h][1-8]\\]x\\[[a-h][1-8]\\]$")){
            System.out.println("Sorry. That is not a valid input format.");
            getInput();
            return;
        }

        convertInput(input);

        //checks that the input is a valid move
        if (!RuleEvaluator.checkValidity(currentMove.coordinates, board)) {
            System.out.println("Sorry. That is not a valid move.");
            getInput();
        }
    }

    /*
    converts String input into four variables {x1, y1, x2, y2} and stores them in coordinates
    [a3]x[b4] for example will become: x1=0, y1=2 and x2=1, y2=3
    */
    private static void convertInput(String input){
        int x1 = input.charAt(1) - 'a';
        int y1 = Character.getNumericValue(input.charAt(2)) - 1;

        int x2 = input.charAt(6) - 'a';
        int y2 = Character.getNumericValue(input.charAt(7)) - 1;
        int[]array = {x1, y1, x2, y2};
        currentMove = new Move(array);
    }

    public static void nextMove(){
        board.printBoard();
        getInput();
        currentMove.move(board);
    }

    public static void main(String[] args) {
        board = new Board();
        RuleEvaluator.resetCurrentPlayer();
        while (true) nextMove();
    }
}