package ch.uzh.group38;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game game;

    @Before
    public void setUpGame() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        /*
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
        */
        Constructor<Game> constructor = Game.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        this.game = constructor.newInstance();
    }

    @Test
    public void aaahTest() {
    }
}
