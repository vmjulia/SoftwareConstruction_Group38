package ch.uzh.group38;


public class Board {


    private Field[][] board;


    /*
    Board constructor: resets the board to default state
    */
    public Board(){
        this.board = new Field[8][8];

        for (int i = 0; i < 8; i++){
            for (int j = 7; j > 4; j--){
                if ((i+j) %2 == 1){
                this.board[j][i] = new PieceField(PieceField.Color.WHITE, PieceField.Type.PAWN);


                    // this.board[j][i].updateField();
                }
                else{
                    this.board[j][i] = new EmptyField();
                }
                this.board[j][i].updatePosition(j, i);
            }
            for (int j = 3; j < 5; j++){
                this.board[j][i] = new EmptyField();
                this.board[j][i].updatePosition(j, i);
            }
            for (int j = 0; j < 3; j++){
                if ((i+j) %2 == 1){
                    this.board[j][i] = new PieceField(PieceField.Color.RED, PieceField.Type.PAWN);


                    // this.board[j][i].updateField();
                }
                else{
                    this.board[j][i] = new EmptyField();
                }
                this.board[j][i].updatePosition(j, i);

            }

        }
        updatePieces();
    }

    /*
    moves a piece by removing it at the actual location
    and creating a new piece at the new location
    */
    public void movePiece(int x1, int y1, int x2, int y2, boolean Check, boolean Convert) {

        Field temp = board[x1][y1];
        removePiece(x1, y1);
        board[x2][y2] = temp;
        board[x2][y2].updatePosition(x2, y2);

        if (Convert) { board[x2][y2].convertToKing();}

        if (Check){
            if (!RuleEvaluator.checkForJumpMoves(x2, y2, this))
            {
                RuleEvaluator.updateTurn(this);}
            }
        else RuleEvaluator.updateTurn(this);
        this.updatePieces();
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

    private void updatePieces(){
        RuleEvaluator.update(this);
        // condition could be changed as we don't need white squares

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[i].length; j++) {
                if ((i + j) % 2 == 1)
                {
                    board[j][i].updateField();
                }
            }
        }
    }

}




