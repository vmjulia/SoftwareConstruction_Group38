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
    checks if a move is valid
    returns true if a move is valid, false if not 
    */
    public static boolean checkValidity(){  
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
