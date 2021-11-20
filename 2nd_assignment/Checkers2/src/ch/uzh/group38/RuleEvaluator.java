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

    public static void printCurrentPlayer(){
        System.out.print(currentPlayer);
    }

    public static void updateTurn(Board board){
        checkWinner(board);
        //Player 1 is red, Player 2 is white
        if (currentPlayer == 1){
            currentPlayer = 2;
        }
        else{
            currentPlayer = 1;
        }
    }

    /*
    checks if the pawn can be accessed by current player
     */
    public static boolean checkInput(int x, int y, Board board) {
        if (currentPlayer == 1) {
            return board.isRed(x, y);
        }
        else if (currentPlayer == 2) {
            return board.isWhite(x, y);
        }
        return false;
    }

    /*
    checks whether a given Move is a valid
    */
    public static boolean checkValidity(Move move, Board board){
        int x1 = move.FromX();
        int y1 = move.FromY();
        int x2 = move.ToX();
        int y2 = move.ToY();

        //check if move is going in the right direction
        //red pawns can only move negative y
        if (board.isRed(x1, y1) && !board.isKing(x1, y1)
            && y2 - y1 >= 0 ){
                return false;
            }

        //white pawns can only move positive y
        if (board.isWhite(x1, y1) && !board.isKing(x1, y1)
            && y2 - y1 <= 0 ){
                return false;
            }

        //check if it is players color  
        if (((board.isRed(x1, y1) && currentPlayer == 1)
            || (board.isWhite(x1, y1) && currentPlayer == 2))){
                
            if (isJumpMove(move, board) || isSimpleMove(move, board)){
                //check if a jump move was possible
                if (isSimpleMove(move, board)){
                    for (int i = 0; i < 8; i++){
                        for (int j = 0; j < 8; j++){
                            if (currentPlayer == 1 && board.isRed(i, j) || (currentPlayer == 2 && board.isWhite(i, j))){
                                if (checkForJumpMoves(i, j, board)){
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
    checks if there are possible jumpMoves from a position
    */
    public static boolean checkForJumpMoves(int x, int y, Board board){

        if (board.isKing(x, y) || board.isWhite(x, y)){
            if (x+2 < 8 && y+2 < 8){
                Move potentialMove = new Move(x, y, x+2, y+2);
                if (isJumpMove(potentialMove, board)) {
                    return true;
                }
            }
        }

        if (board.isKing(x, y) || board.isWhite(x, y)){
            if (x-2 >= 0 && y+2 < 8) {
                Move potentialMove = new Move(x, y, x-2, y+2);
                if (isJumpMove(potentialMove, board)) {
                    return true;
                }
            }
        }

        if (board.isKing(x, y) || board.isRed(x, y)){
            if (x-2 >= 0 && y-2 >= 0){
                Move potentialMove = new Move(x, y, x-2, y-2);
                if (isJumpMove(potentialMove, board)) {
                    return true;
                }
            }
        }

        if (board.isKing(x, y) || board.isRed(x, y)){
            if (x+2 < 8 && y-2 >= 0){
                Move potentialMove = new Move(x, y, x+2, y-2);
                if (isJumpMove(potentialMove, board)) {
                    return true;
                }
            }
        }

        return false;
    }

    /*
    checks if there are possible simpleMoves from a position
    */
    private static boolean checkForSimpleMoves(int x, int y, Board board){

        if (board.isKing(x, y) || board.isWhite(x, y)){
            if (x+1 < 8 && y+1 < 8) {
                Move potentialMove = new Move(x, y, x+1, y+1);
                if (isSimpleMove(potentialMove, board)) {
                    return true;
                }
            }
        }

        if (board.isKing(x, y) || board.isWhite(x, y)){
            if (x-1 >= 0 && y+1 < 8){
                Move potentialMove = new Move(x, y, x-1, y+1);
                if (isSimpleMove(potentialMove, board)) {
                    return true;
                }
            }
        }

        if (board.isKing(x, y) || board.isRed(x, y)){
            if (x-1 >= 0 && y-1 >= 0){
                Move potentialMove = new Move(x, y, x-1, y-1);
                if (isSimpleMove(potentialMove, board)) {
                    return true;
                }
            }
        }

        if (board.isKing(x, y) || board.isRed(x, y)){
            if (x+1 < 8 && y-1 >= 0){
                Move potentialMove = new Move(x, y, x+1, y-1);
                if (isSimpleMove(potentialMove, board)) {
                    return true;
                }
            }
        }

        return false;
    }

    /*
    checks whether a given Move is a jumpMove
    */
    public static boolean isJumpMove(Move move, Board board){
        int x1 = move.FromX();
        int y1 = move.FromY();
        int x2 = move.ToX();
        int y2 = move.ToY();

        if ((x1 - x2 == 2 || x1 - x2 == -2)
            && (y1 - y2 == 2 || y1 - y2 == -2) 
            && board.isEmpty(x2, y2)){

            //check if jump is over opponent pin
            return (board.isWhite(x1, y1) && board.isRed((x1 + x2) / 2, (y1 + y2) / 2))
                    || (board.isRed(x1, y1) && board.isWhite((x1 + x2) / 2, (y1 + y2) / 2));
        }
        return false;
    }

    /*
    checks whether a given Move is a simpleMove
    */
    private static boolean isSimpleMove(Move move, Board board){
        int x1 = move.FromX();
        int y1 = move.FromY();
        int x2 = move.ToX();
        int y2 = move.ToY();

        return (x1 - x2 == 1 || x1 - x2 == -1)
                && (y1 - y2 == 1 || y1 - y2 == -1)
                && board.isEmpty(x2, y2);
    }

    /*
    checks if a player has won the game
    */
    private static void checkWinner(Board board){
        //check if there are pins left and if they can move
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (currentPlayer == 1 && board.isWhite(i, j) || (currentPlayer == 2 && board.isRed(i, j))){
                    if (checkForJumpMoves(i, j,board) || checkForSimpleMoves(i, j,board)){
                        return;
                    }
                }
            }
        }
        System.out.println("Player " + currentPlayer + " wins!!");
        System.exit(0);
    }

}
