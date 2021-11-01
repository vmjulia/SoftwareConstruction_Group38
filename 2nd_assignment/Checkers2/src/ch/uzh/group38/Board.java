package ch.uzh.group38;


public class Board {


    private Field[][] board;


    /*
    Board constructor: resets the board to default state
    */
    public Board(){
        RuleEvaluator.resetCurrentPlayer();
        this.board = new Field[8][8];

        for (int i = 0; i < 8; i++){
            for (int j = 7; j > 4; j--){
                if ((i+j) %2 == 1){
                this.board[j][i] = new PieceField(PieceField.Color.WHITE, PieceField.Type.PAWN);
                }
                else{
                    this.board[j][i] = new EmptyField();
                }
            }
            for (int j = 3; j < 5; j++){
                this.board[j][i] = new EmptyField();
            }
            for (int j = 0; j < 3; j++){
                if ((i+j) %2 == 1){
                    this.board[j][i] = new PieceField(PieceField.Color.RED, PieceField.Type.PAWN);
                }
                else{
                    this.board[j][i] = new EmptyField();
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
                    System.out.print(board[j][i].getLabel());
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
        Field temp = board[x1][y1];
        board[x1][y1] = new EmptyField();
        board[x2][y2] = temp;
    }

    /*
    removes a piece from the board 
    */
    public void removePiece(int x, int y){
        board[x][y] = new EmptyField();
    }


    public Field getField(int x, int y){
        return(board[x][y]);
    }
}




