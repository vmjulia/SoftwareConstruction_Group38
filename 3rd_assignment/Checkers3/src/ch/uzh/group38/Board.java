package ch.uzh.group38;
import java.util.ArrayList;
import java.util.List;

interface Subject{
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}




    public class Board implements Subject{

    private final Field[][] board;
    private List<Observer> observers = new ArrayList<>();

    public Iterator createIterator(){
        return new BoardIterator(board);
    }




    /*
    Board constructor: resets the board to default state
    */
    public Board(){
        this.board = new Field[8][8];

        for (int i = 0; i < 8; i++){
            for (int j = 7; j > 4; j--){
                if ((i+j) %2 == 1){
                this.board[j][i] = new PieceField(PieceField.Color.WHITE, PieceField.Type.PAWN,j, i);

                }
                else{
                    this.board[j][i] = new EmptyField(j, i);
                }
                this.board[j][i].updatePosition(j, i);
            }
            for (int j = 3; j < 5; j++){
                this.board[j][i] = new EmptyField(j, i);
                this.board[j][i].updatePosition(j, i);
            }
            for (int j = 0; j < 3; j++){
                if ((i+j) %2 == 1){
                    this.board[j][i] = new PieceField(PieceField.Color.RED, PieceField.Type.PAWN, j, i);

                }
                else{
                    this.board[j][i] = new EmptyField(j, i);
                }
                this.board[j][i].updatePosition(j, i);

            }

        }


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i + j) % 2 == 1)
                {
                    registerObserver(board[j][i]);
                }
            }
        }

        RuleEvaluator.updateBoard(this);
        notifyObservers();
    }

    /*
    moves a piece by removing it at the actual location
    and creating a new piece at the new location
    */
    public void movePiece(Move move, boolean Check, boolean Convert) {

        int x1 = move.fromX();
        int y1 = move.fromY();
        int x2 = move.toX();
        int y2 = move.toY();

        Field temp = board[x1][y1];
        removePiece(x1, y1);
        board[x2][y2] = temp;
        board[x2][y2].updatePosition(x2, y2);

        if (Convert) { board[x2][y2].convertToKing();}
        RuleEvaluator.updateBoard(this);

        if (Check){
            if (RuleEvaluator.checkForJumpMoves(x2, y2))
            {
                RuleEvaluator.storeLastMove(move);}
            else
            {RuleEvaluator.updateTurn();}
            }
        else {RuleEvaluator.updateTurn();}
        this.notifyObservers();
    }

    /*
    removes a piece from the board 
    */
    public void removePiece(int x, int y){
        board[x][y] = new EmptyField(x, y);
    }

    public Field getField(int x, int y){
        return(board[x][y]);
    }

    public void registerObserver(Observer observer){
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }

    public void removeObserver(Observer observer){
            observers.remove(observer);
            }

    public void notifyObservers(){
        observers.forEach(Observer::update);

            }
}




