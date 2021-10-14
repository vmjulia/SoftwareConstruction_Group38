package ch.uzh.group38;

import ch.uzh.group38.Board;
import ch.uzh.group38.Move;

public class RuleEvaluator {

    /*
    variable to keep track of who's turn it is
    */
    private static int currentPlayer = 1;

    public static int getCurrentPlayer(){
        return currentPlayer;
    }

    public static void updateTurn(){
        if (currentPlayer == 1){
            currentPlayer = 2;
        }
        if (currentPlayer == 2){
            currentPlayer = 1;
        }
    }

    /*
    checks if an input is overall valid
    */
    public static boolean checkValidity(String input){
        if (!checkInputValidity(input)) {
            System.out.println("Sorry. That is not a valid input.");
            return false;
        }
        if (!checkMoveValidity(input)) {
            System.out.println("Sorry. That is not a valid move.");
            return false;
        }
        return true;
    }

    /*
    checks if an input is valid
    */
    private static boolean checkInputValidity(String input) {
        return input.matches("(?i)^\\[[a-h][1-8]\\]x\\[[a-h][1-8]\\]$");
    }

    /*
    checks if a move is valid
    */
    private static boolean checkMoveValidity(String input) {
        return true;
    }

    /*
    checks if there are possible jumpmoves from the current position
    */
    public static boolean checkForJumpmoves(){  
        return true;
    }

    /*
    checks if a player has won the game
    */
    public static void checkWinner(){  
    }
}
