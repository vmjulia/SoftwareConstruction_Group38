package ch.uzh.group38;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    public static final ClassLoader loader = GUI.class.getClassLoader();
    //the argument might be null, but intellij's suggestion does not solve that issue
    private final Icon whitePawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_pawn.png");
    private final Icon whiteKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_king.png");
    private final Icon redPawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_pawn.png");
    private final Icon redKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_king.png");

    private Move currentMove;
    private Board board;


    private int x1;
    private int y1;
    private boolean pawnActive = false;
    private JFrame frame;
    private final JPanel gui = new JPanel();
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

        reset();
        frame.pack();
    }

    private void refresh(){
        gui.removeAll();
        message.setText("Player " + RuleEvaluator.getCurrentPlayer() + " please enter your move");
                
        //creating toolbar
        JButton rb = new JButton("Reset");
        rb.addActionListener(new ResetButton());
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(rb); 
        toolbar.addSeparator();
        toolbar.add(message);
        toolbar.setOpaque(false);
        
        //creating the board
        JPanel playBoard = new JPanel(new GridLayout(0, 10));
        playBoard.setBorder(new LineBorder(Color.black));

        for (int i = 0; i< playBoardSquares.length; i++) {
            for (int j = 0; j< playBoardSquares[i].length; j++) {
                //the square is black
                if ((i+j) %2 == 1) {
                    if (board.getField(i, j).isRed() && board.getField(i, j).isKing()){
                        playBoardSquares[i][j].setInactiveAction();
                        playBoardSquares[i][j].applyIcon(redKingIcon);
                    }
                    else if (board.getField(i, j).isRed() && !board.getField(i, j).isKing()){
                        playBoardSquares[i][j].setInactiveAction();
                        playBoardSquares[i][j].applyIcon(redPawnIcon);
                    }
                    else if (board.getField(i, j).isWhite() && board.getField(i, j).isKing()){
                        playBoardSquares[i][j].setInactiveAction();
                        playBoardSquares[i][j].applyIcon(whiteKingIcon);
                    }
                    else if (board.getField(i, j).isWhite() && !board.getField(i, j).isKing()){
                        playBoardSquares[i][j].setInactiveAction();
                        playBoardSquares[i][j].applyIcon(whitePawnIcon);
                    }
                    else {
                        playBoardSquares[i][j].setEmptyAction();
                        //removes any icon
                        playBoardSquares[i][j].applyIcon(null);
                    }
                }
                else playBoardSquares[i][j].setVoidAction();
            }
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


        //set up GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        gui.setLayout(new BoxLayout(gui, BoxLayout.Y_AXIS));
        gui.add(toolbar);
        gui.add(playBoard);
        frame.add(gui);
        frame.setVisible(true);
    }

    private void reset(){
        RuleEvaluator.resetCurrentPlayer();
        board = new Board();
        refresh();
    }

    public static void setMessage(String msg){
        message.setText(msg);
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

        @Override
        public void actionPerformed(ActionEvent e) {
            message.setText("Player" + RuleEvaluator.getCurrentPlayer() + " please touch your pawns only!");
        }
    }

    //empty square pressed
    class EmptyState implements State {
        private final ButtonPressed buttonPressed;

        public EmptyState(ButtonPressed actionListener) {
            this.buttonPressed = actionListener;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (pawnActive) {
                currentMove = new Move(x1, y1, buttonPressed.x, buttonPressed.y);
                if (board.getField(x1, y1).isMoveStored(currentMove)) {
                    currentMove.move(board);
                    playBoardSquares[x1][y1].activate();
                    pawnActive = false;
                    refresh();
                    if (RuleEvaluator.checkWinner(board)) {
                        JOptionPane.showMessageDialog(frame, "Player " +
                                RuleEvaluator.getCurrentPlayer() + " wins!!");
                        reset();
                    }
                }
            }
            else {
                message.setText("Player" + RuleEvaluator.getCurrentPlayer() + " please touch your pawns only!");
            }
        }
    }

    class InactiveState implements State {
        private final ButtonPressed buttonPressed;

        public InactiveState(ButtonPressed buttonPressed) {
            this.buttonPressed = buttonPressed;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(RuleEvaluator.checkInput(buttonPressed.x, buttonPressed.y)) {
                buttonPressed.setState(buttonPressed.getActiveState());
                playBoardSquares[buttonPressed.x][buttonPressed.y].activate();
                x1 = buttonPressed.x;
                y1 = buttonPressed.y;
                pawnActive = true;
                message.setText("Player " + RuleEvaluator.getCurrentPlayer() + " please select target field!");
            }
            else {
                message.setText("Player " + RuleEvaluator.getCurrentPlayer() + " please touch your pawns only!");
            }
        }
    }

    class ActiveState implements State{
        private final ButtonPressed buttonPressed;

        public ActiveState(ButtonPressed buttonPressed) {
            this.buttonPressed = buttonPressed;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            buttonPressed.setState(buttonPressed.getInactiveState());
            playBoardSquares[buttonPressed.x][buttonPressed.y].activate();
            pawnActive = false;
            message.setText("Player " + RuleEvaluator.getCurrentPlayer() + " please enter your move");
        }
    }

    class ResetButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            reset();
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}