package ch.uzh.group38;

public class RuleEvaluator {

    /*
    variable to keep track of whose turn it is
    */
    private static Board currentBoard;
    private static int currentRound = 0;
    private static int currentPlayer;
    private static int lastX = -1;
    private static int lastY = -1;

    public static void updateBoard(Board board)
    {
       currentBoard = board;
    }

    //sets first Player to 1
    public static void resetCurrentPlayer(){
        currentPlayer = 1;
    }

    public static int getCurrentPlayer(){
        return currentPlayer;
    }

    public static void updateTurn(){
        //PLayer 1 is red, Player 2 is white
        if (currentPlayer == 1){
            currentPlayer = 2;
        }
        else{
            currentPlayer = 1;
        }
        lastX = -1;
        lastY = -1;
    }

    public static void resetCurrentRound(){
        currentRound = 1;
    }

    public static void updateCurrentRound(){
        currentRound += 1;
    }

    public static int getCurrentRound(){
        return currentRound;
    }

    /*
    checks if the piece can be accessed by current player
     */
    public static boolean checkInput(int x, int y) {
        if (currentPlayer == 1) {
            return currentBoard.getField(x, y).isRed();
        }
        else if (currentPlayer == 2) {
            return currentBoard.getField(x, y).isWhite();
        }
        return false;
    }

    /*
    checks if input is a valid move
    */
    public static boolean checkValidity(Move move){

        int x1 = move.fromX();
        int y1 = move.fromY();
        int x2 = move.toX();

        //check if move is going in the right direction
        //red pawns can only move negative y
        if (currentBoard.getField(x1,y1).isRed() && !currentBoard.getField(x1,y1).isKing()
            && x2 - x1 <= 0 ){
            //GUI.setMessage("This move is not valid");
                return false;
            }

        //white pawns can only move positive y
        if (currentBoard.getField(x1,y1).isWhite() && !currentBoard.getField(x1,y1).isKing()
            && x2 - x1 >= 0 ){
            //GUI.setMessage("This move is not valid");
                return false;
            }

        //check if it is players color  
        if (((currentBoard.getField(x1,y1).isRed() && currentPlayer == 1) || (currentBoard.getField(x1,y1).isWhite() && currentPlayer == 2))){
            if (isJumpMove(move) || isSimpleMove(move)){
                
                if ((lastX != -1 && lastY != -1) && (x1 != lastX && y1 != lastY)){
                    //GUI.setMessage("Continue your move with same piece");
                    return false;
                }

                    //check if a jump move was possible
                if (isSimpleMove(move)){
                    Iterator currentIterator = currentBoard.createIterator();
                    while (currentIterator.hasNext()){
                        Field currentField = currentIterator.next();
                        if (currentPlayer == 1 && currentField.isRed() || currentPlayer == 2 && currentField.isWhite()){
                            if (checkForJumpMoves(currentField.getX(), currentField.getY())){
                                //GUI.setMessage("There is a possible jump move");
                                return false;

                    } } }

                }
            return true;
            }
        }
        // GUI.setMessage("This move is not valid");
        return false;
    }

    /*
    checks if there are possible jumpMoves from the current position
    */
    public static boolean checkForJumpMoves(int x, int y){

        if (currentBoard.getField(x, y).isKing() || currentBoard.getField(x, y).isRed()){
            if (x+2 < 8 && y+2 < 8){
                Move potentialMove = new Move(x, y, x+2, y+2);
                if (isJumpMove(potentialMove)) {
                    return true;
                }
            }
        }

        if (currentBoard.getField(x, y).isKing() || currentBoard.getField(x, y).isWhite()){
            if (x-2 >= 0 && y+2 < 8) {
                Move potentialMove = new Move(x, y, x-2, y+2);
                if (isJumpMove(potentialMove)) {
                    return true;
                }
            }
        }

        if (currentBoard.getField(x, y).isKing() || currentBoard.getField(x, y).isWhite()){
            if (x-2 >= 0 && y-2 >= 0){
                Move potentialMove = new Move(x, y, x-2, y-2);
                if (isJumpMove(potentialMove)) {
                    return true;
                }
            }
        }

        if (currentBoard.getField(x, y).isKing() || currentBoard.getField(x, y).isRed()){
            if (x+2 < 8 && y-2 >= 0){
                Move potentialMove = new Move(x, y, x+2, y-2);
                return isJumpMove(potentialMove);
            }
        }

        return false;
    }

    private static boolean isJumpMove(Move move){

        int x1 = move.fromX();
        int y1 = move.fromY();
        int x2 = move.toX();
        int y2 = move.toY();


        if ((x1 - x2 == 2 || x1 - x2 == -2)
            && (y1 - y2 == 2 || y1 - y2 == -2) 
            && currentBoard.getField(x2,y2).isEmpty()){

            //check if jump is over opponent pin
            return (currentBoard.getField(x1, y1).isWhite() && (currentBoard.getField((x1 + x2) / 2, (y1 + y2) / 2)).isRed())
                    || (currentBoard.getField(x1, y1).isRed() && (currentBoard.getField((x1 + x2) / 2, (y1 + y2) / 2)).isWhite());
            }
        return false;
    }

    private static boolean isSimpleMove(Move move){

        int x1 = move.fromX();
        int y1 = move.fromY();
        int x2 = move.toX();
        int y2 = move.toY();


        return (x1 - x2 == 1 || x1 - x2 == -1)
                && (y1 - y2 == 1 || y1 - y2 == -1)
                && currentBoard.getField(x2, y2).isEmpty();
    }

    /*
    checks if a player has won the game
    */
    public static boolean checkWinner(Board board){
        //check if there are pins left and if they can move
        Iterator currentIterator = board.createIterator();
        while (currentIterator.hasNext()){
            Field currentField = currentIterator.next();
            if (currentField.isAnyMovePossible()){
                return false;
            }
        }

        updateTurn();
        return true;        
    }


    public static void storeLastMove(Move move){
            lastX = move.toX();
            lastY = move.toY();

    }
}
