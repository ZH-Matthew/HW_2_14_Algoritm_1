package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        IntegerListImpl data = new IntegerListImpl();

        System.out.println(data.checkBubble());
        System.out.println(data.checkSelection());
        System.out.println(data.checkInsertion());
    }
}