package ch.uzh.group38;// from: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class GUI implements ActionListener {

    private  Move currentMove;
    private final Board board;

    private int x1;
    private int y1;
    private boolean pawnActive;
    //private final JLabel label;
    private JFrame frame;
    private final JPanel gui = new JPanel();
    private JPanel playboard;
    private JButton[][] playboardsquares = new JButton [8][8];
    private static final String COLS = "ABCDEFGH";
    private final JLabel message = new JLabel("Your add here!");
    private final Icon redKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_king.png");
    private final Icon whiteKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_king.png");
    private final Icon redPawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_pawn.png");
    private final Icon whitePawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_pawn.png");

    //constructor
    private GUI() {
            
        this.board = new Board();
        /*
        basically the window
         */
        frame = new JFrame();

        /*
        layout to put stuff in the window
         */
        gui.setBorder(new EmptyBorder(10, 10, 10, 10));
        gui.setLayout(new BoxLayout(gui, BoxLayout.Y_AXIS));
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        gui.add(toolbar);
        toolbar.add(new JButton("Reset"));
        toolbar.add(new JButton("Save"));
        toolbar.addSeparator();
        toolbar.add(message);
        
        playboard = new JPanel(new GridLayout(0, 10));
        playboard.setBorder(new LineBorder(Color.black));
        gui.add(playboard);

        for (int i = 0; i<playboardsquares.length; i++) {
            for (int j = 0; j<playboardsquares[i].length; j++) {
                JButton button = new JButton();
                button.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
                
                if ((i+j) %2 == 1) {
                    if (board.getField(i, j).isRed() && board.getField(i, j).isKing()){
                        button.setIcon(redKingIcon);
                    }
                    if (board.getField(i, j).isRed() && !board.getField(i, j).isKing()){
                        button.setIcon(redPawnIcon);
                    }
                    if (board.getField(i, j).isWhite() && board.getField(i, j).isKing()){
                        button.setIcon(whiteKingIcon);
                    }
                    if (board.getField(i, j).isWhite() && !board.getField(i, j).isKing()){
                        button.setIcon(whitePawnIcon);
                    }
                    button.addActionListener(new GoodAction(i, j, button, board));
                    button.setBackground(Color.black);
                }
                else {
                    button.setBackground(Color.white);
                }
                playboardsquares[i][j] = button;
            }
        }

        //fill in top row
        playboard.add(new JLabel("+",SwingConstants.CENTER));
        for (int i = 0; i < 8; i++) {
            playboard.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
        }
        playboard.add(new JLabel("+",SwingConstants.CENTER));
        
        //fill in playboard
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0 || j==9) {
                        playboard.add(new JLabel("" + (8-i), SwingConstants.CENTER));
                }
                else {
                        playboard.add(playboardsquares[i][j-1]);
                }
            }
        }
        //fill in bottom row
        playboard.add(new JLabel("+",SwingConstants.CENTER));
        for (int i = 0; i < 8; i++) {
            playboard.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
        } 
        playboard.add(new JLabel("+", SwingConstants.CENTER)); 

        //playboard.add(label);
        gui.add(playboard);
        frame.add(gui);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    class GoodAction implements ActionListener {
        private JButton associatedButton;
        private Board localBoard;
        private Integer x;
        private Integer y;

        public GoodAction(int i, int j, JButton button, Board board) {
            associatedButton = button;
            localBoard = board;
            x = i;
            y = j;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //see which button is pressed
            message.setText(x.toString() + y.toString());

            //i.e. the first chosen pawn is valid input
            if (pawnActive) {
                Move move = new Move(x1, y1, x, y);
                //press the same button again to cancel
                if (associatedButton.getBackground() == Color.green) {
                    associatedButton.setBackground(Color.black);
                    pawnActive = false;
                }
                //i.e. the move is valid
                else if (RuleEvaluator.checkValidity(move, localBoard)) {
                    message.setText("Good Stuff!");
                    //Move move = new Move(x1, y1, x, y);
                    move.move(localBoard);
                    new GUI();
                    frame.dispose();
                }
            }
            //no valid pawn has been chosen yet
            else {
                if (RuleEvaluator.checkInput(x, y, localBoard)) {
                    associatedButton.setBackground(Color.green);
                    x1 = x;
                    y1 = y;
                    pawnActive = true;
                } else {
                    message.setText("Please touch your pawns only");
                }
            }

        }
    }

/*     private void nextMove(){
        this.board.printBoard();
        getInput();
        currentMove.move(board);
    } */

    public static void main(String[] args) {
        RuleEvaluator.resetCurrentPlayer();
        GUI gui = new GUI();
        //while (true) gui.nextMove();
    } 

}