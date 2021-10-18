package ch.uzh.group38;


public class RuleEvaluator {

    /*
    variable to keep track of whose turn it is
    */
    private static int currentPlayer;

    //sets first Player to 1
    public static void resetCurrentPlayer(){
        currentPlayer = 1;
    }

    public static int getCurrentPlayer(){
        return currentPlayer;
    }


    public static void updateTurn(Board board){
        checkWinner(board);
        //PLayer 1 is red, Player 2 is white
        if (currentPlayer == 1){
            currentPlayer = 2;
        }
        else{
            currentPlayer = 1;
        }
    }

    /*
    checks if input is a valid move
    */
    public static boolean checkValidity(int[] coordinates, Board board){

        //check if move is going in the right direction
        //red pawns can only move negative y
        if (board.isRed(coordinates[0], coordinates[1]) && !board.isKing(coordinates[0], coordinates[1])
            && coordinates[3] - coordinates[1] >= 0 ){
                return false;
            }

        //white pawns can only move positive y
        if (board.isWhite(coordinates[0], coordinates[1]) && !board.isKing(coordinates[0], coordinates[1])
            && coordinates[3] - coordinates[1] <= 0 ){
                return false;
            }

        //check if it is players color  
        if (((board.isRed(coordinates[0], coordinates[1]) && getCurrentPlayer() == 1)
            || (board.isWhite(coordinates[0], coordinates[1]) && getCurrentPlayer() == 2))){
                
                if (isJumpMove(coordinates, board) || isSimpleMove(coordinates, board)){
                    //check if a jump move was possible
                    if (isSimpleMove(coordinates, board)){
                        for (int i = 0; i < 8; i++){
                            for (int j = 0; j < 8; j++){
                                if (getCurrentPlayer() == 1 && board.isRed(i, j) || (getCurrentPlayer() == 2 && board.isWhite(i, j))){
                                    if (checkForJumpmoves(i, j, board)){
                                        System.out.println("There is a possible jump move");
                                        return false;
                                    }
                                }
                            }
                        }
                        
                    }
                    return true;
                }
        }
        return false;
    }

    /*
    checks if there are possible jumpmoves from the current position
    */
    public static boolean checkForJumpmoves(int x, int y, Board board){
        int [] target = {x, y, x+2, y+2}; 
        if (board.isKing(x, y) || board.isWhite(x, y)){
            if (target[2] < 8 && target[3] < 8)
                if (isJumpMove(target, board)){
                    return true;
            }
        }

        if (board.isKing(x, y) || board.isWhite(x, y)){
            target[2] = x-2;
            target[3] = y+2;
            if (target[2] >= 0 && target[3] < 8) 
                if (isJumpMove(target, board)){
                    return true;
            }
        }

        if (board.isKing(x, y) || board.isRed(x, y)){
            target[2] = x-2;
            target[3] = y-2;
            if (target[2] >= 0 && target[3] >= 0)
                if (isJumpMove(target, board)){
                    return true;
            }
        }

        if (board.isKing(x, y) || board.isRed(x, y)){
            target[2] = x+2;
            target[3] = y-2;
            if (target[2] < 8 && target[3] >= 0)
                if (isJumpMove(target, board)){
                    return true;
            }
        }
        return false;
    }

    public static boolean checkForSimpleMoves(int x, int y, Board board){
        int [] target = {x, y, x+1, y+1}; 
        if (board.isKing(x, y) || board.isWhite(x, y)){
            if (target[2] < 8 && target[3] < 8) 
                if (isSimpleMove(target,board)){
                    return true;
            }
        }

        if (board.isKing(x, y) || board.isWhite(x, y)){
            target[2] = x-1;
            target[3] = y+1;
            if (target[2] >= 0 && target[3] < 8)
                if (isSimpleMove(target,board)){
                    return true;
            }
        }

        if (board.isKing(x, y) || board.isRed(x, y)){
            target[2] = x-1;
            target[3] = y-1;
            if (target[2] >= 0 && target[3] >= 0)
                if (isSimpleMove(target, board)){
                    return true;
            }
        }

        if (board.isKing(x, y) || board.isRed(x, y)){
            target[2] = x+1;
            target[3] = y-1;
            if (target[2] < 8 && target[3] >= 0)
                if (isSimpleMove(target,board)){
                    return true;
            }
        }
        return false;
    }

    public static boolean isJumpMove(int[] coordinates, Board board){
        if ((coordinates[0] - coordinates[2] == 2 || coordinates[0] - coordinates[2] == -2)
            && (coordinates[1] - coordinates[3] == 2 || coordinates[1] - coordinates[3] == -2) 
            && board.isEmpty(coordinates[2], coordinates[3])){

                //check if jump is over opponent pin
            return (board.isWhite(coordinates[0], coordinates[1]) && board.isRed((coordinates[0] + coordinates[2]) / 2, (coordinates[1] + coordinates[3]) / 2))
                    || (board.isRed(coordinates[0], coordinates[1]) && board.isWhite((coordinates[0] + coordinates[2]) / 2, (coordinates[1] + coordinates[3]) / 2));
            }
        return false;
    }

    public static boolean isSimpleMove(int[] coordinates, Board board){
        return (coordinates[0] - coordinates[2] == 1 || coordinates[0] - coordinates[2] == -1)
                && (coordinates[1] - coordinates[3] == 1 || coordinates[1] - coordinates[3] == -1)
                && board.isEmpty(coordinates[2], coordinates[3]);
    }

    /*
    checks if a player has won the game
    */
    public static void checkWinner(Board board){
        
        //check if there are pins left and if they can move
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (getCurrentPlayer() == 1 && board.isWhite(i, j) || (getCurrentPlayer() == 2 && board.isRed(i, j))){
                    if (checkForJumpmoves(i, j,board) || checkForSimpleMoves(i, j,board)){
                        return;
                    }
                }
            }
        }
        System.out.println("Player " + RuleEvaluator.getCurrentPlayer() + " wins!!");
        System.exit(0);
    }
}
