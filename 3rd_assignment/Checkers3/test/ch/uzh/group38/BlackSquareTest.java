package ch.uzh.group38;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class BlackSquareTest extends TestCase {
    GUI gui;
    Square[][] playBoardSquares;

    @Before
    public void setUp() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Constructor<GUI> constructor = GUI.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        GUI gui = constructor.newInstance();

        Field pBS = gui.getClass().getDeclaredField("playBoardSquares");
        pBS.setAccessible(true);
        this.playBoardSquares = (Square[][]) pBS.get(gui);
    }

    @Test
    public void testSetAction() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        GUI.ButtonPressed buttonPressed = constructor.newInstance();

        GUI.State voidState = buttonPressed.getVoidState();
        GUI.State emptyState = buttonPressed.getEmptyState();
        GUI.State inactiveState = buttonPressed.getInactiveState();
        GUI.State activeState = buttonPressed.getActiveState();

        Field cS = buttonPressed.getClass().getDeclaredField("currentState");
        cS.setAccessible(true);
        GUI.State currentState = (GUI.State) cS.get(buttonPressed);

        BlackSquare bs =  new BlackSquare(buttonPressed);

        bs.setVoidAction();
        assertEquals(voidState, currentState);

        bs.setEmptyAction();
        assertEquals(emptyState, currentState);

        bs.setInactiveAction();
        assertEquals(inactiveState, currentState);

        bs.setActiveAction();
        assertEquals(activeState, currentState);


    }
}