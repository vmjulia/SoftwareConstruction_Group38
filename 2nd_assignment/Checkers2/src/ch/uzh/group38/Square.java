package ch.uzh.group38;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Square extends JButton {
    private State currentState;

    private final State whiteState;
    private final State blackEmptyState;
    private final State blackInactiveState;
    private final State blackActiveState;

    public Square(ActionListener voidAction, ActionListener emptyAction,
                  ActionListener inactiveAction, ActionListener activeAction) {

        this.whiteState = new WhiteState(this, voidAction);
        this.blackEmptyState = new BlackEmptyState(this, emptyAction);
        this.blackInactiveState = new BlackInactiveState(this, inactiveAction);
        this.blackActiveState = new BlackActiveState(this, activeAction);

        this.currentState = this.whiteState;
        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.redraw();
    }

    public State getWhiteState() {return this.whiteState;}
    public State getBlackEmptyState() {return this.blackEmptyState;}
    public State getBlackInactiveState() {return this.blackInactiveState;}
    public State getBlackActiveState() {return this.blackActiveState;}

    public void setState(State passedState) {
        this.currentState = passedState;
        this.redraw();
    }

    //implementation in states is likely to change in future
    public void applyIcon(Icon passedIcon) {
        currentState.applyIcon(passedIcon);
    }

    public void redraw() {
        currentState.redraw();
    }
}

interface State {
    void applyIcon(Icon passedIcon);
    void redraw();
}

class WhiteState implements State {
    private final Square square;
    private final Color bgColor = Color.white;

    public WhiteState(Square passedSquare, ActionListener passedAction) {
        this.square = passedSquare;
        this.square.addActionListener(passedAction);
    }

    public void applyIcon(Icon passedIcon) {
        throw new java.lang.Error("White Squares should not have an icon");
    }

    public void redraw() {
        this.square.setBackground(bgColor);
        this.square.setOpaque(true);
    }
}

class BlackEmptyState implements State {
    private final Square square;
    private final Color bgColor = Color.black;

    public BlackEmptyState(Square passedSquare, ActionListener passedAction) {
        this.square = passedSquare;
        this.square.addActionListener(passedAction);
    }

    public void applyIcon(Icon passedIcon) {
        this.square.setIcon(passedIcon);
    }

    public void redraw() {
        this.square.setBackground(bgColor);
        this.square.setOpaque(true);
    }
}

class BlackInactiveState implements State {
    private final Square square;
    private final Color bgColor = Color.black;

    public BlackInactiveState(Square passedSquare, ActionListener passedAction) {
        this.square = passedSquare;
        this.square.addActionListener(passedAction);
    }

    public void applyIcon(Icon passedIcon) {
        this.square.setIcon(passedIcon);
    }

    public void redraw() {
        this.square.setBackground(bgColor);
        this.square.setOpaque(true);
    }
}

class BlackActiveState implements State {
    private final Square square;
    private final Color bgColor = Color.green;

    public BlackActiveState(Square passedSquare, ActionListener passedAction) {
        this.square = passedSquare;
        this.square.addActionListener(passedAction);
    }

    public void applyIcon(Icon passedIcon) {
        this.square.setIcon(passedIcon);
    }

    public void redraw() {
        this.square.setBackground(bgColor);
        this.square.setOpaque(true);
    }
}