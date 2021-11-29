package ch.uzh.group38;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private final Icon whitePawnIcon = new WhitePawn();
    private final Icon whiteKingIcon = new WhiteKing();
    private final Icon redPawnIcon = new RedPawn();
    private final Icon redKingIcon = new RedKing();

    private Move currentMove;
    private Board board;


    private int x1;
    private int y1;
    private static int currentRound = 0;
    private boolean pawnActive = false;
    private JFrame frame;
    private static User player1;
    private static User player2;
    private final guiDisplay gui = new guiDisplay();
    private final HistoryDisplay history = new HistoryDisplay();
    private final JToolBar toolbar = new JToolBar();
    private final Square[][] playBoardSquares = new Square[8][8];
    private final String COLS = "ABCDEFGH";
    private static final JLabel message = new JLabel("Your add here!");

    private GUI() {

        //creating new window
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Checkers");

        //creating action listeners
        for (int i = 0; i< playBoardSquares.length; i++) {
            for (int j = 0; j< playBoardSquares[i].length; j++) {
                if ((i+j) %2 == 1){
                    playBoardSquares[i][j] = new BlackSquare(new ButtonPressed(i, j));
                }
                else {
                    playBoardSquares[i][j] = new EmptySquare(new ButtonPressed(i, j));
                }
            }
        }
        resetCurrentRound();
        setPlayers();
        reset();
        frame.pack();
    }


    private void refresh(){
        history.removeAll();
        history.setVisible(false);
        gui.update();
        frame.add(gui);
        gui.setVisible(true);
        frame.setVisible(true);
    }

    private void setPlayers(){
        RuleEvaluator.resetCurrentPlayer();
        player1 = new User(askPlayerName());
        RuleEvaluator.updateTurn();
        player2 = new User(askPlayerName());
        RuleEvaluator.resetCurrentPlayer();
    }

    private void reset(){        
        board = new Board();
        if (pawnActive) {
            playBoardSquares[x1][y1].deactivate();
        }
        refresh();
    }

    private String askPlayerName(){
        String username = JOptionPane.showInputDialog(frame, "Player " + RuleEvaluator.getCurrentPlayer() + ", please enter your name", "Player " + RuleEvaluator.getCurrentPlayer());
        return username;
    }
    public static void resetCurrentRound(){
        currentRound = 1;
    }
    public static void updateCurrentRound(){
        currentRound += 1;
    }

    private static User currentPlayer(){
        if (RuleEvaluator.getCurrentPlayer() == 1){
            return (player1);}
        return(player2);
    }

    public static String currentPlayerName(){
        return(GUI.currentPlayer().getName());
    }

    public void displayHistory(boolean roundEnd){
        gui.removeAll();
        gui.setVisible(false);
        history.update(roundEnd);
        frame.add(history);
        history.setVisible(true);
        frame.setVisible(true);
    }


    class guiDisplay extends JPanel{

        public void update(){
            this.removeAll();
            toolbar.removeAll();
            message.setText(currentPlayerName() + " please enter your move");
            createToolbar();
            JPanel playBoard = createBoard();
            this.setBorder(new EmptyBorder(5, 5, 5, 5));
            this.setLayout(new BoxLayout(gui, BoxLayout.Y_AXIS));
            this.add(toolbar);
            this.add (playBoard);
        }

        private void createToolbar() {

            //creating toolbar
            JButton rb = new JButton("Reset");
            rb.addActionListener(new ResetButton());
            JButton hb = new JButton("Game history");
            hb.addActionListener(new ScoreTableButton());
            toolbar.setFloatable(false);
            toolbar.add(rb);
            toolbar.addSeparator();
            toolbar.add(hb);
            toolbar.addSeparator();
            toolbar.add(message);
            toolbar.setOpaque(false);
        }

        public JPanel  createBoard()  {

            //creating the board
            JPanel playBoard = new JPanel(new GridLayout(0, 10));
            playBoard.setBorder(new LineBorder(Color.black));

            Iterator currentIterator = board.createIterator();
            while (currentIterator.hasNext()){
                Field currentField = currentIterator.next();
                int i =currentField.getX();
                int j = currentField.getY();
                if ((i+ j)%2 ==1){
                    if (currentField.isRed() && currentField.isKing()){
                        playBoardSquares[i][j].setInactiveAction();
                        playBoardSquares[i][j].setIcon(redKingIcon);
                    }
                    else if (currentField.isRed() && !currentField.isKing()){
                        playBoardSquares[i][j].setInactiveAction();
                        playBoardSquares[i][j].setIcon(redPawnIcon);
                    }
                    else if (currentField.isWhite() && currentField.isKing()){
                        playBoardSquares[i][j].setInactiveAction();
                        playBoardSquares[i][j].setIcon(whiteKingIcon);
                    }
                    else if (currentField.isWhite() && !currentField.isKing()){
                        playBoardSquares[i][j].setInactiveAction();
                        playBoardSquares[i][j].setIcon(whitePawnIcon);
                    }
                    else {
                        playBoardSquares[i][j].setEmptyAction();
                        //removes any icon
                        playBoardSquares[i][j].setIcon(null);
                    }

                }
                else playBoardSquares[i][j].setVoidAction();
            }

            //fill in top row
            playBoard.add(new JLabel("+",SwingConstants.CENTER));
            for (int i = 0; i < 8; i++) {
                playBoard.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
            }
            playBoard.add(new JLabel("+",SwingConstants.CENTER));

            //fill in playboard
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 10; j++) {
                    if (j == 0 || j==9) {
                        playBoard.add(new JLabel("" + (8-i), SwingConstants.CENTER));
                    }
                    else {
                        playBoard.add(playBoardSquares[i][j-1]);
                    }
                }
            }
            //fill in bottom row
            playBoard.add(new JLabel("+",SwingConstants.CENTER));
            for (int i = 0; i < 8; i++) {
                playBoard.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
            }
            playBoard.add(new JLabel("+", SwingConstants.CENTER));
            return playBoard;
        }

    }

    class HistoryDisplay extends JPanel{
        public void update(boolean RoundEnd){
            this.removeAll();
            toolbar.removeAll();
            toolbar.setFloatable(false);

            if (RoundEnd){
                RoundEndToolbar();
            }

            else{
                WithinRoundToolbar();
            }

            toolbar.addSeparator();
            toolbar.add(message);
            toolbar.setOpaque(false);
            this.add(toolbar);
            this.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Score table", TitledBorder.CENTER, TitledBorder.TOP));
            String[][] rec = {
                    { player1.getName(), String.valueOf(player1.getScore())},
                    { player2.getName(), String.valueOf(player2.getScore()) },

            };
            String[] header = { "Player", "Score"};
            JTable table = new JTable(rec, header);
            this.add(new JScrollPane(table));

        }

        private void RoundEndToolbar(){
        message.setText("Player " + GUI.currentPlayerName() + " wins this round!! Do you want to play one more?");
        JButton resb = new JButton("One more round");
        resb.addActionListener(new NextRoundButton());
        JButton rb1 = new JButton("New Game");
        rb1.addActionListener(new ResetButton());
        toolbar.add(resb);
        toolbar.addSeparator();
        toolbar.add(rb1);
        }

        private void WithinRoundToolbar(){
            message.setText("Round " + currentRound);
            JButton rb3 = new JButton("back to game");
            rb3.addActionListener(new BackButton());
            toolbar.add(rb3);

        }

    }
    
    class ButtonPressed implements ActionListener{
        private final int x;
        private final int y;

        private State currentState;

        private final VoidState voidState;
        private final EmptyState emptyState;
        private final InactiveState inactiveState;
        private final ActiveState activeState;

        public ButtonPressed(int x, int y) {
            this.x = x;
            this.y = y;
            //initialise states
            this.voidState = new VoidState();
            this.emptyState = new EmptyState(this);
            this.inactiveState = new InactiveState(this);
            this.activeState = new ActiveState(this);

            this.currentState = voidState;
        }

        public void setState(State passedState) {
            this.currentState = passedState;
        }

        public State getVoidState() {return this.voidState;}
        public State getEmptyState() {return this.emptyState;}
        public State getInactiveState() {return this.inactiveState;}
        public State getActiveState() {return this.activeState;}

        @Override
        public void actionPerformed(ActionEvent e) {
            this.currentState.actionPerformed(e);
        }
    }

    interface State {
        void actionPerformed(ActionEvent e);
    }

    //white square pressed, mustn't be static
    class VoidState implements State {
        public VoidState() {}

        public void actionPerformed(ActionEvent e) {
            message.setText(GUI.currentPlayerName() + " please touch your pawns only!");
        }
    }

    //empty square pressed
    class EmptyState implements State {
        private final ButtonPressed buttonPressed;

        public EmptyState(ButtonPressed actionListener) {
            this.buttonPressed = actionListener;
        }

        public void actionPerformed(ActionEvent e) {
            if (pawnActive) {
                currentMove = new Move(x1, y1, buttonPressed.x, buttonPressed.y);
                if (board.getField(x1, y1).isMoveStored(currentMove)) {
                    currentMove.move(board);
                    playBoardSquares[x1][y1].deactivate();
                    pawnActive = false;
                    refresh();
                    if (RuleEvaluator.checkWinner(board)) {
                        currentPlayer().increaseScore();
                        displayHistory(true);
                    }
                }
                else {
                    message.setText(GUI.currentPlayerName() + " this is not a valid move");
                }
            }
            else {
                message.setText(GUI.currentPlayerName() + " please touch your pawns only!");
            }
        }
    }

    class InactiveState implements State {
        private final ButtonPressed buttonPressed;

        public InactiveState(ButtonPressed buttonPressed) {
            this.buttonPressed = buttonPressed;
        }

        public void actionPerformed(ActionEvent e) {
            if(RuleEvaluator.checkInput(buttonPressed.x, buttonPressed.y)) {
                //deactivate the previously pressed button
                if (pawnActive) {
                    playBoardSquares[x1][y1].setInactiveAction();
                    playBoardSquares[x1][y1].deactivate();
                }
                //activate this button
                buttonPressed.setState(buttonPressed.getActiveState());
                playBoardSquares[buttonPressed.x][buttonPressed.y].activate();
                x1 = buttonPressed.x;
                y1 = buttonPressed.y;
                pawnActive = true;
                message.setText(GUI.currentPlayerName() + " please select target field!");
            }
            else {
                message.setText(GUI.currentPlayerName() + " please touch your pawns only!");
            }
        }
    }

    class ActiveState implements State{
        private final ButtonPressed buttonPressed;

        public ActiveState(ButtonPressed buttonPressed) {
            this.buttonPressed = buttonPressed;
        }

        public void actionPerformed(ActionEvent e) {
            buttonPressed.setState(buttonPressed.getInactiveState());
            playBoardSquares[buttonPressed.x][buttonPressed.y].deactivate();
            pawnActive = false;
            message.setText(GUI.currentPlayerName() + " please enter your move");
        }
    }

    class ResetButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            resetCurrentRound();
            setPlayers();
            reset();
        }
    }

    class ScoreTableButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            displayHistory(false);
        }
    }

    class BackButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            refresh();
        }
    }

    class NextRoundButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            updateCurrentRound();
            reset();
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}