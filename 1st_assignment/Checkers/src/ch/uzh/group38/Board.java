package ch.uzh.group38;

public class Board {

    private static String[][] board;

    //Not sure if this is needed yet
    public static String emptyField = "[   ]";
    public static String redPawn = "[R_P]";
    public static String redKing = "[R_K]";
    public static String whitePawn = "[W_P]";
    public static String whiteKing = "[W_K]";

    private static final String[][] defaultBoard =
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
    resets the board to default state
    */
    public void resetBoard(){
        board = defaultBoard.clone();
    }

    /*
    prints actual state of the board 
    */
    public void printBoard(){
        System.out.println("      a     b     c     d     e     f     g     h");
        System.out.println("  +-------------------------------------------------+");
        for (int i = 7; i >= 0; i--){
            System.out.print(i+1 + " | ");
            for (int j = 0; j < 8; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.print("| " + (i+1));
            System.out.print("\n");
        }
        System.out.println("  +-------------------------------------------------+");
        System.out.println("      a     b     c     d     e     f     g     h");
    }

    /*
    moves a piece by removing it at the actual location
    and creating a new piece at the new location
    */
    public static void movePiece(int x1, int y1, int x2, int y2){
        String temp = board[x1][y1];
        board[x1][y1] = emptyField;
        board[x2][y2] = temp;
    }

    /*
    removes a piece from the board 
    */
    public static void removePiece(int x, int y){
        board[x][y] = emptyField;
    }

    /*
    changes type of a piece from pawn to king
    */
    public static void changeType(int x, int y){
        if (board[x][y] == redPawn){
            board[x][y] = redKing;
        }
        else if (board[x][y] == whitePawn){
            board[x][y] = whiteKing;
        }
    }

    /*
    checks if a piece is a king or not
    */
    public static boolean isKing(int x, int y){
        if (board[x][y].charAt(3) == 'K'){
            return true;
        }
        else{
            return false;
        }
    }
}
