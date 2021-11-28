package ch.uzh.group38;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.*;
import java.lang.reflect.Field;

public class GUITest {
    GUI gui;
    Method refresh;
    Square[][] playBoardSquares;

    @Before
    public void createGUI() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Constructor<GUI> constructor = GUI.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        this.gui = constructor.newInstance();

        this.refresh = GUI.class.getDeclaredMethod("refresh");
        refresh.setAccessible(true);
        refresh.invoke(gui);

        Field pBS = gui.getClass().getDeclaredField("playBoardSquares");
        pBS.setAccessible(true);
        this.playBoardSquares = (Square[][]) pBS.get(gui);
    }

    @After
    public void cleanUp() throws NoSuchFieldException, IllegalAccessException {
        Field frm = gui.getClass().getDeclaredField("frame");
        frm.setAccessible(true);
        JFrame frame = (JFrame) frm.get(gui);
        frame.dispose();
    }

    @Test
    public void refreshActionStatesTest() throws NoSuchFieldException, IllegalAccessException{

        for (int i = 0; i< playBoardSquares.length; i++) {
            for (int j = 0; j< playBoardSquares[i].length; j++) {
                ActionListener[] aLs = playBoardSquares[i][j].getActionListeners();
                int expectedLength = 1;
                int actualLength = aLs.length;

                assertEquals(expectedLength, actualLength);

                for (ActionListener aL : aLs) {
                    Field cS = aL.getClass().getDeclaredField("currentState");
                    cS.setAccessible(true);
                    GUI.State currentState = (GUI.State) cS.get(aL);
                    Class actualState = currentState.getClass();

                    if ((i + j) % 2 == 1) {
                        Class expectedState1 = GUI.InactiveState.class;
                        Class expectedState2 = GUI.EmptyState.class;
                        assertTrue(expectedState1 == actualState || expectedState2 == actualState);
                    } else {
                        Class expectedState = GUI.VoidState.class;
                        assertTrue(expectedState == actualState);
                    }
                }
            }
        }
    }

    @Test
    public void endOfGameTest() throws IllegalAccessException, InvocationTargetException, NoSuchFieldException {

        Field brd = gui.getClass().getDeclaredField("board");
        brd.setAccessible(true);
        Board board = (Board) brd.get(gui);

        int x1 = 2;
        int y1 = 1;
        int x2 = 3;
        int y2 = 0;

        int x3 = 5;
        int y3 = 2;
        int x4 = 4;
        int y4 = 1;

        Move m1 = new Move(x1, y1, x2, y2);
        m1.move(board);
        Move m2 = new Move(x3, y3, x4, y4);
        m2.move(board);

        //removing all other pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                board.removePiece(j, i);
            }


            for (int j = 5; j < 8; j++) {
                board.removePiece(j, i);
            }
        }
        refresh.invoke(gui);

        //executing last move
        playBoardSquares[3][0].doClick();

        Field pA = gui.getClass().getDeclaredField("pawnActive");
        pA.setAccessible(true);
        boolean pawnActive = (boolean) pA.get(gui);
        assertTrue(pawnActive);

        playBoardSquares[5][2].doClick();
        assertTrue(RuleEvaluator.checkWinner(board));
    }

    @Test
    public void testRandomPresser(){
        int numberOfMoves = 100;
        for (int i = 0; i < numberOfMoves; i++) {
            int m = (int) (8*Math.random());
            int n = (int) (8*Math.random());
            playBoardSquares[m][n].doClick();
        }
    }
}
