package ch.uzh.group38;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    String name;
    User u;
    @Before
    public void CreateUser(){
        name = "a";
        u = new User(name);
    }
    @Test
    public void UserStorageTest(){
        assertEquals(name, u.getName());
        assertEquals(0, u.getScore());
    }

    @Test public void ScoreTest(){
        u.increaseScore();
        assertEquals(1, u.getScore());

    }



}