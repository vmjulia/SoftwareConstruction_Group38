package ch.uzh.group38;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Square extends JButton {
    public Square() {

    }
}

class EmptySquare extends Square {
    public EmptySquare() {
        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.setBackground(Color.white);
        this.setOpaque(true);
    }
}

class BlackSquare extends Square implements ActionListener{
    private int x;
    private int y;

    public BlackSquare(int passedX, int passedY) {
        x = passedX;
        y = passedY;

        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.setBackground(Color.black);
        this.setOpaque(true);
        this.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getBackground() == Color.green) {
            this.setBackground(Color.black);
            GUI.buttonUnpressed();
        }
        else {
            GUI.buttonPressed(x, y);
        }
    }
}

class RedPawnSquare extends BlackSquare {
    private final Icon redPawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_pawn.png");

    public RedPawnSquare(int x, int y) {
        super(x, y);
        this.setIcon(redPawnIcon);
    }
}

class WhitePawnSquare extends BlackSquare {
    private final Icon whitePawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_pawn.png");

    public WhitePawnSquare(int x, int y) {
        super(x, y);
        this.setIcon(whitePawnIcon);
    }
}

class RedKingSquare extends BlackSquare {
    private final Icon redKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_king.png");

    public RedKingSquare(int x, int y) {
        super(x, y);
        this.setIcon(redKingIcon);
    }
}

class WhiteKingSquare extends BlackSquare {
    private final Icon whiteKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_king.png");

    public WhiteKingSquare(int x, int y){
        super(x, y);
        this.setIcon(whiteKingIcon);
    }
}