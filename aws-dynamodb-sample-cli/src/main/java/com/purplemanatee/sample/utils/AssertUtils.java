package com.purplemanatee.sample.utils;

public class AssertUtils {
    public static void notNull(Object in, String message) {
        if (in == null)
            throw new RuntimeException(message);
    }
}
