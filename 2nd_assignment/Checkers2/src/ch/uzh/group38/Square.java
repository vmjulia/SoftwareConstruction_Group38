package ch.uzh.group38;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public abstract class Square extends JButton {}

class EmptySquare extends Square {
    public EmptySquare() {
        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.setBackground(Color.white);
        this.setOpaque(true);
    }
}

class BlackSquare extends Square {
    public BlackSquare(ActionListener passedAction) {
        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.setBackground(Color.black);
        this.setOpaque(true);
        this.addActionListener(passedAction);
    }
}

class BlackSquareWithPawn extends BlackSquare {
    public BlackSquareWithPawn(ActionListener passedAction, Icon passedIcon) {
        super(passedAction);
        this.setIcon(passedIcon);
    }
}