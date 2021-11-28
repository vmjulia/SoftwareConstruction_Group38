package ch.uzh.group38;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;

public class BlackSquareTest extends TestCase {
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
@Test
    public void SetActionTest(){

    GUI.ButtonPressed buttonPressed;
    GUI.VoidState voidState;
    GUI.EmptyState emptyState;
    GUI.InactiveState inactiveState;
    GUI.ActiveState activeState;

    BlackSquare bs =  new BlackSquare(buttonPressed);

    bs.setVoidAction();
    assertEquals(voidState, buttonPressed.currentState);

    bs.setEmptyAction();
    assertEquals(emptyState, buttonPressed.currentState);

    bs.setInactiveAction();
    assertEquals(inactiveState, buttonPressed.currentState);

    bs.setActiveAction();
    assertEquals(activeState, buttonPressed.currentState);

    

}
}