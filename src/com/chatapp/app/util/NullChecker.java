package com.chatapp.app.util;

public class NullChecker {
    public static void nullCheck(Object ...objects) throws NullPointerException {
        for (Object o : objects) {
            if (o == null) {
                throw new NullPointerException();
            }
        }
    }
    public static void main(String[] args) {
        try {
            NullChecker.nullCheck(new Object(), null, new Object());
            System.out.println("Failed");
        } catch (NullPointerException e) {
            System.out.println("Success");
        }
        try {
            NullChecker.nullCheck(new Object(), new int[4], new Object());
            System.out.println("Success");
        } catch (NullPointerException e) {
            System.out.println("Failed");
        }
        try {
            NullChecker.nullCheck();
            System.out.println("Success");
        } catch (NullPointerException e) {
            System.out.println("Failed");
        }
    }
}
