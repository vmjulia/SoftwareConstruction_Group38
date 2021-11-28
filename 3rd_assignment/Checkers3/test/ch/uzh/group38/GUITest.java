package ch.uzh.group38;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionListener;
import java.lang.reflect.*;
import java.lang.reflect.Field;

public class GUITest {
    GUI gui;
    Method refresh;

    @Before
    public void createGUI() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Constructor<GUI> constructor = GUI.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        this.gui = constructor.newInstance();

        this.refresh = GUI.class.getDeclaredMethod("refresh");
        refresh.setAccessible(true);
        refresh.invoke(gui);
    }

    @Test
    public void refreshActionStatesTest() throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        refresh.invoke(gui);
        Field pBS = gui.getClass().getDeclaredField("playBoardSquares");
        pBS.setAccessible(true);
        Square[][] playBoardSquares = (Square[][]) pBS.get(gui);

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
    public void endOfGameTest() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        int x1 = 2;
        int y1 = 1;
        int x2 = 3;
        int y2 = 0;

        int x3 = 5;
        int y3 = 2;
        int x4 = 4;
        int y4 = 1;

        Move m1 = new Move(x1, y1, x2, y2);
        m1.move(this.gui.board);
        Move m2 = new Move(x3, y3, x4, y4);
        m2.move(this.gui.board);

        //removing all other pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                this.gui.board.removePiece(j, i);
            }


            for (int j = 5; j < 8; j++) {
                this.gui.board.removePiece(j, i);
            }
        }
        refresh.invoke(gui);

        //executing last move
        gui.playBoardSquares[3][0].doClick();
        assertTrue(this.gui.pawnActive);
        gui.playBoardSquares[5][2].doClick();
        assertTrue(RuleEvaluator.checkWinner(this.gui.board));
    }
}
