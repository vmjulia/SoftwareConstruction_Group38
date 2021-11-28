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
    public void createGUI() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

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
}
