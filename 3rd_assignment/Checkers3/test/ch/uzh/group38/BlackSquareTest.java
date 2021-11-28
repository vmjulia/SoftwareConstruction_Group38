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