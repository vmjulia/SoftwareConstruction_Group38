package ch.uzh.group38;

public class Board {

    private String[][] board;

    //Not sure if this is needed yet
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
    public void resetBoard(){
        board = defaultBoard;
    }

    /*
    prints actual state of the board 
    */
    public void printBoard(){
        System.out.println("      a    b    c    d    e    f    g    h");
        System.out.println("  +------------------------------------------+");
        for (int i = 7; i >= 0; i--){
            System.out.print(i+1 + " | ");
            for (int j = 0; j < 8; j++){
                System.out.print(board[i][j]);
            }
            System.out.print(" | " + (i+1));
            System.out.print("\n");
        }
        System.out.println("  +------------------------------------------+");
        System.out.println("      a    b    c    d    e    f    g    h");        
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

    /*
    checks if a piece is a king or not
    */
    public boolean isKing(int x, int y){
        if (this.board[x][y].charAt(3) == 'K'){
            return true;
        }
        else{
            return false;
        }
    }
}
