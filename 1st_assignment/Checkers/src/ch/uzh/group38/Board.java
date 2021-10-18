package ch.uzh.group38;




public class Board {

    public Piece[][] board;


    /*
    Board constructor: resets the board to default state
    */
    public Board(){
        this.board = new Piece[8][8];

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 3; j++){
                if ((i+j) %2 ==0){
                this.board[i][j] = new Piece('W', 'P');}

                }
            for (int j = 5; j < 8; j++){
                if ((i+j) %2 ==0){
                    this.board[i][j] = new Piece('R', 'P');}}}

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
                if (board[j][i]!=null){ System.out.print(board[j][i].getLabel());}
                else {System.out.print("[   ] ");}}
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
    public void movePiece(int [] move){
        Piece temp = board[move[0]][move[1]];
        board[move[0]][move[1]] = null;
        board[move[2]][move[3]] = temp;
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
    public void changeType(int x, int y){
        board[x][y].becomeKing();
        }


    /*
    checks if a piece is a king
    */
    public boolean isKing(int x, int y){
        return(board[x][y]!=null &&board[x][y].type == 'K');
    }

    /*
    checks if a piece is red
    */
    public boolean isRed(int x, int y){
        return (board[x][y]!=null && board[x][y].colour == 'R');
    }

    /*
    checks if a piece is white
    */
    public boolean isWhite(int x, int y){
        return (board[x][y]!=null &&board[x][y].colour == 'W');
    }    

    /*
    checks if a field of the board is empty
    */
    public boolean isEmpty(int x, int y){
        return (board[x][y] == null);
    }
}
