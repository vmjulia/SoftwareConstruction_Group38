package ch.uzh.group38;


public class Board {

    private Piece[][] board;
    /*
    Board constructor: resets the board to default state
    */
    public Board(){
        this.board = new Piece[8][8];

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 3; j++){
                if ((i+j) %2 ==0){
                this.board[i][j] = new Piece(Piece.Color.WHITE, Piece.Type.PAWN);
                }
            }
            for (int j = 5; j < 8; j++){
                if ((i+j) %2 ==0){
                    this.board[i][j] = new Piece(Piece.Color.RED, Piece.Type.PAWN);
                }
            }
        }
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
                if (board[j][i]!=null){
                    System.out.print(board[j][i].getLabel());
                }
                else {
                    System.out.print("[   ] ");
                }
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
    public void movePiece(int x1, int y1, int x2, int y2){
        Piece temp = board[x1][y1];
        board[x1][y1] = null;
        board[x2][y2] = temp;
    }

    /*
    removes a piece from the board 
    */
    public void removePiece(int x, int y){
        board[x][y] = null;
    }

    /*
    changes type of a piece from pawn to king
    */
    public void convertToKing(int x, int y){
        board[x][y].convertToKing();
    }

    /*
    checks if a piece is a king
    */
    public boolean isKing(int x, int y){
        return((board[x][y]!=null) && (board[x][y].isKing()));
    }

    /*
    checks if a piece is red
    */
    public boolean isRed(int x, int y){
        return ((board[x][y]!=null) && (board[x][y].isRed()));
    }

    /*
    checks if a piece is white
    */
    public boolean isWhite(int x, int y){
        return ((board[x][y]!=null) && (board[x][y].isWhite()));
    }    

    /*
    checks if a field of the board is empty
    */
    public boolean isEmpty(int x, int y){
        return (board[x][y] == null);
    }

}
