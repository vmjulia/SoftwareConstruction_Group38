package ch.uzh.group38;

public class Board {

    public String emptyField = "[   ]";
    public String redPawn = "[R_P]";
    public String redKing = "[R_K]";
    public String whitePawn = "[W_P]";
    public String whiteKing = "[W_K]";

    public static String[][] defaultBoard = 
        {{"[W_P]","[   ]","[W_P]","[   ]","[W_P]","[   ]","[W_P]","[   ]"}
        ,{"[   ]","[W_P]","[   ]","[W_P]","[   ]","[W_P]","[   ]","[W_P]"}
        ,{"[W_P]","[   ]","[W_P]","[   ]","[W_P]","[   ]","[W_P]","[   ]"}
        ,{"[   ]","[   ]","[   ]","[   ]","[   ]","[   ]","[   ]","[   ]"}
        ,{"[   ]","[   ]","[   ]","[   ]","[   ]","[   ]","[   ]","[   ]"}
        ,{"[   ]","[R_P]","[   ]","[R_P]","[   ]","[R_P]","[   ]","[R_P]"}
        ,{"[R_P]","[   ]","[R_P]","[   ]","[R_P]","[   ]","[R_P]","[   ]"}
        ,{"[   ]","[R_P]","[   ]","[R_P]","[   ]","[R_P]","[   ]","[R_P]"}
    };

    /*
    resets the board to deafault state 
    */
    public void resetBoard(String[][] board){
        board = Board.defaultBoard;
    }

    /*
    prints actual state of the board 
    */
    public static void printBoardState(){

    }

    /*
    moves a piece by removing it at the actual location 
    and creating a new piece at the new location
    */
    public static void movePiece(){

    }

    /*
    removes a piece from the board 
    */
    public static void removePiece(){

    }

    /*
    changes type of a piece from pawn to king
    */
    public static void changeType(){

    }
}
