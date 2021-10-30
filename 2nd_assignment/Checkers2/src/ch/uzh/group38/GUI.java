package ch.uzh.group38;// from: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class GUI implements ActionListener {

    private int x1;
    private int y1;
    private boolean pawnActive;
    private final JLabel label;
    private JFrame frame;
    private JPanel panel;
    private final Icon redKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_king.png");
    private final Icon whiteKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_king.png");
    private final Icon redPawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_pawn.png");
    private final Icon whitePawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_pawn.png");

    public GUI(Board board) {
        /*
        basically the window
         */
        frame = new JFrame();

        /*
        actions for the buttons
         */

        label = new JLabel("Your add here!");

        /*
        layout to put stuff in the window
         */
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 200, 100));
        panel.setLayout(new GridLayout(9, 8));

        boolean timeForWhite = false;
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                if (timeForWhite) {
                    JButton button = new JButton();
                    button.setBackground(Color.white);
                    button.setBorder(BorderFactory.createEmptyBorder(25, 25, 25,
                            25));
                    panel.add(button);
                }
                else {
                    JButton button;
                    if (board.isWhite(i, j)) {
                        if (board.isKing(i, j)) {
                            button = new JButton(whiteKingIcon);
                        }
                        else {
                            button = new JButton(whitePawnIcon);
                        }
                    }
                    else if (board.isRed(i, j)) {
                        if (board.isKing(i, j)){
                            button = new JButton(redKingIcon);
                        }
                        else {
                            button = new JButton(redPawnIcon);
                        }
                    }
                    else {
                        button = new JButton();
                    }
                    button.setBackground(Color.black);
                    button.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
                    button.addActionListener(new GoodAction(i, j, button, board));
                    panel.add(button);
                }
                timeForWhite = !timeForWhite;
            }
            timeForWhite = !timeForWhite;
        }
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
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
            label.setText(x.toString() + y.toString());

            //i.e. the first chosen pawn is valid input
            if (pawnActive) {
                //press the same button again to cancel
                if (associatedButton.getBackground() == Color.green) {
                    associatedButton.setBackground(Color.black);
                    pawnActive = false;
                }
                //i.e. the move is valid
                else if (RuleEvaluator.checkValidity(x1, y1, x, y, localBoard)) {
                    label.setText("Good Stuff!");
                    Move move = new Move(x1, y1, x, y);
                    move.move(localBoard);
                    new GUI(localBoard);
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
                    label.setText("Please touch your pawns only");
                }
            }

        }
    }

}
