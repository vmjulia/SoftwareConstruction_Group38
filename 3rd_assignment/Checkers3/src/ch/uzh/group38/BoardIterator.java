package ch.uzh.group38;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

interface Iterator {
    boolean hasNext();
    Field next();
}


public class BoardIterator implements Iterator{

    Queue<Field> myQueue = new LinkedList<>();

    public BoardIterator(Field[][] arr) {
        for (Field[] fields : arr) myQueue.addAll(Arrays.asList(fields));
    }


    public boolean hasNext() {
        return !myQueue.isEmpty();
    }

    public Field next() {
        return myQueue.remove();
    }

}
