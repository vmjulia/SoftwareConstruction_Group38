package ch.uzh.group38;


import java.util.Scanner;


public class Game {

    private  Move currentMove;
    private final Board board;

    private Game(){
        this.board = new Board();
    }

    private void askForInput(){
        System.out.print("Player ");
        RuleEvaluator.printCurrentPlayer();
        System.out.print(" please enter your next move in this format [a3]X[b4]:\n");
    }

    private String readInput(){
        Scanner s = new Scanner(System.in);
        return s.nextLine().toLowerCase();
    }

    /*
    Calls askForInput and readInput.
    Checks the format of the input.
    Calls createMove with the input.
     */
    private void getInput(){
        askForInput();
        String input = readInput();

        //checks that the input is of the correct format using regex
        if(!input.matches("^\\[[a-h][1-8]\\]x\\[[a-h][1-8]\\]$")){
            System.out.println("Sorry. That is not a valid input.");
            getInput();
            return;
        }

        createMove(input);

        //checks that the input is a valid move
        if (!RuleEvaluator.checkValidity(currentMove, board)) {
            System.out.println("Sorry. That is not a valid move.");
            getInput();
        }
    }

    /*
    Converts String input into four variables {x1, y1, x2, y2}
    ([a3]x[b4] for example will become: x1=0, y1=2 and x2=1, y2=3)
    Sets currentMove to a Move with those coordinates.
    */
    private void createMove(String input){
        int x1 = input.charAt(1) - 'a';
        int y1 = Character.getNumericValue(input.charAt(2)) - 1;

        int x2 = input.charAt(6) - 'a';
        int y2 = Character.getNumericValue(input.charAt(7)) - 1;

        currentMove = new Move(x1, y1, x2, y2);
    }

    /*
    Prints the board. gets the input. executes the move.
    */
    private void nextMove(){
        this.board.printBoard();
        getInput();
        currentMove.move(board);
    }

    public static void main(String[] args) {
        Game game = new Game();
        RuleEvaluator.resetCurrentPlayer();
        while (true) game.nextMove();
    }

}
