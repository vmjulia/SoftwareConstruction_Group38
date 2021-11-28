package ch.uzh.group38;

import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class BlackSquareTest extends TestCase {

    @Test
    public void setActionTest() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

    Constructor<GUI.ButtonPressed> constructor = GUI.ButtonPressed.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    constructor.setAccessible(true);
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