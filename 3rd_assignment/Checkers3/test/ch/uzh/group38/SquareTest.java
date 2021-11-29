package ch.uzh.group38;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class SquareTest extends TestCase {
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
    public void testBlackSquare() throws IllegalAccessException, NoSuchFieldException {
        
        Square blackSquare = playBoardSquares[0][1];

        Field bP = blackSquare.getClass().getDeclaredField("buttonPressed");
        bP.setAccessible(true);
        GUI.ButtonPressed buttonPressed = (GUI.ButtonPressed) bP.get(blackSquare);

        GUI.State voidState = buttonPressed.getVoidState();
        GUI.State emptyState = buttonPressed.getEmptyState();
        GUI.State inactiveState = buttonPressed.getInactiveState();
        GUI.State activeState = buttonPressed.getActiveState();

        Field cS = buttonPressed.getClass().getDeclaredField("currentState");
        cS.setAccessible(true);

        blackSquare.setVoidAction();
        assertEquals(voidState, cS.get(buttonPressed));

        blackSquare.setEmptyAction();
        assertEquals(emptyState, cS.get(buttonPressed));

        blackSquare.setInactiveAction();
        assertEquals(inactiveState, cS.get(buttonPressed));

        blackSquare.setActiveAction();
        assertEquals(activeState, cS.get(buttonPressed));
    }

    @Test
    public void testEmptySquare() throws IllegalAccessException, NoSuchFieldException {

        Square emptySquare = playBoardSquares[0][0];

        Field bP = emptySquare.getClass().getDeclaredField("buttonPressed");
        bP.setAccessible(true);
        GUI.ButtonPressed buttonPressed = (GUI.ButtonPressed) bP.get(emptySquare);

        GUI.State voidState = buttonPressed.getVoidState();
        GUI.State emptyState = buttonPressed.getEmptyState();
        GUI.State inactiveState = buttonPressed.getInactiveState();
        GUI.State activeState = buttonPressed.getActiveState();

        Field cS = buttonPressed.getClass().getDeclaredField("currentState");
        cS.setAccessible(true);

        emptySquare.setVoidAction();
        assertEquals(voidState, cS.get(buttonPressed));

        emptySquare.setEmptyAction();
        assertEquals(emptyState, cS.get(buttonPressed));

        emptySquare.setInactiveAction();
        assertEquals(inactiveState, cS.get(buttonPressed));

        emptySquare.setActiveAction();
        assertEquals(activeState, cS.get(buttonPressed));
    }
}