package ch.uzh.group38;

import java.util.Scanner;
import java.util.stream.StreamSupport;

public class CustomScanner{
    private static Scanner instance = new Scanner(System.in);

    public static Scanner getInstance() {
        return instance;
    }
}
