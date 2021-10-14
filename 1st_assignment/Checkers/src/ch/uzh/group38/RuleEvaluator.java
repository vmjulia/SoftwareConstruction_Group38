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
        //PLayer 1 is red, Player 2 is white
        if (currentPlayer == 1){
            currentPlayer = 2;
        }
        if (currentPlayer == 2){
            currentPlayer = 1;
        }
    }

    /*
    checks if input is a valid move
    */
    public static boolean checkValidity(int[] coordinates){
        //check if it is players color and if target field is empty
        if ((Board.isRed(coordinates[0], coordinates[1]) && getCurrentPlayer() == 1 
            || !Board.isRed(coordinates[0], coordinates[1]) && getCurrentPlayer() == 2)){

                if (isJumpMove(coordinates) || isSimpleMove(coordinates)){
                    return true;
                }
        }
        return false;
    }

    /*
    checks if there are possible jumpmoves from the current position
    */
    public static boolean checkForJumpmoves(int x, int y){  
        int [] target = {x, y, x+2, y+2}; 
        if (isJumpMove(target)){
            return true;
        }

        target[2] = x-2;
        if (isJumpMove(target)){
            return true;
        }

        target[3] = y-2;
        if (isJumpMove(target)){
            return true;
        }

        target[2] = x+2;
        if (isJumpMove(target)){
            return true;
        }

        return false;
    }

    public static boolean isJumpMove(int[] coordinates){  
        if ((coordinates[0] - coordinates[2] == 2 || coordinates[0] - coordinates[2] == -2)
            && (coordinates[1] - coordinates[3] == 2 || coordinates[1] - coordinates[3] == -2)
            && Board.isEmpty(coordinates[2], coordinates[3])){

                if (!Board.isRed(coordinates[0], coordinates[1]) == Board.isRed((coordinates[0] + coordinates[2])/2, (coordinates[1] + coordinates[3])/2)){
                    return true;
                }
            }
        return false;
    }

    public static boolean isSimpleMove(int[] coordinates){  
        if ((coordinates[0] - coordinates[2] == 1 || coordinates[0] - coordinates[2] == -1)
            && (coordinates[1] - coordinates[3] == 1 || coordinates[1] - coordinates[3] == -1)
            && Board.isEmpty(coordinates[2], coordinates[3])){
                return true;                
            }
        return false;
    }

    /*
    checks if a player has won the game
    */
    public static void checkWinner(){  
    }
}
