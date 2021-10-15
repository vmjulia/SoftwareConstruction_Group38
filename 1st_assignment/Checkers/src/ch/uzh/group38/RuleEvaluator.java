package ch.uzh.group38;

/* import ch.uzh.group38.Board;
import ch.uzh.group38.Move; */

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

        //check if move is going in the right direction
        //red pawns can only move negative y
        if (Board.isRed(coordinates[0], coordinates[1]) && !Board.isKing(coordinates[0], coordinates[1]) 
            && coordinates[3] - coordinates[1] >= 0 ){
                return false;
            }

        //white pawns can only move positive y
        if (!Board.isRed(coordinates[0], coordinates[1]) && !Board.isKing(coordinates[0], coordinates[1]) 
            && coordinates[3] - coordinates[1] <= 0 ){
                return false;
            }

        //check if it is players color and if target field is empty
        if (((Board.isRed(coordinates[0], coordinates[1]) && getCurrentPlayer() == 1) 
            || (!Board.isRed(coordinates[0], coordinates[1]) && getCurrentPlayer() == 2))
            && Board.isEmpty(coordinates[2], coordinates[3])){
                
                if (isJumpMove(coordinates) || isSimpleMove(coordinates)){
                    //check if a jump move was possible
                    if (isSimpleMove(coordinates) && checkForJumpmoves(coordinates[0], coordinates[1])){
                        System.out.println("Move not valid, there is a possible jump move");
                        return false;
                    }
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
        if (target[2] < 8 && target[3] < 8 && isJumpMove(target)){
            return true;
        }

        target[2] = x-2;
        if (target[2] >= 0 && target[3] < 8 && isJumpMove(target)){
            return true;
        }

        target[3] = y-2;
        if (target[2] < 8 && target[3] >= 0 && isJumpMove(target)){
            return true;
        }

        target[2] = x+2;
        if (target[2] < 8 && target[3] >= 0 && isJumpMove(target)){
            return true;
        }
        return false;
    }

    public static boolean isJumpMove(int[] coordinates){  
        if ((coordinates[0] - coordinates[2] == 2 || coordinates[0] - coordinates[2] == -2)
            && (coordinates[1] - coordinates[3] == 2 || coordinates[1] - coordinates[3] == -2)){

                //check if jump is over opponent pin
                if (!Board.isRed(coordinates[0], coordinates[1]) == Board.isRed((coordinates[0] + coordinates[2])/2, (coordinates[1] + coordinates[3])/2)){
                    return true;
                }
            }
        return false;
    }

    public static boolean isSimpleMove(int[] coordinates){  
        if ((coordinates[0] - coordinates[2] == 1 || coordinates[0] - coordinates[2] == -1)
            && (coordinates[1] - coordinates[3] == 1 || coordinates[1] - coordinates[3] == -1)){
                return true;                
            }
        return false;
    }

    /*
    checks if a player has won the game
    return 0 if no one has won, 1 if User1 won and 2 if User2 won
    */
    public static int checkWinner(){
        return 0;
    }
}
