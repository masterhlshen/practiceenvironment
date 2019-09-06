package com.shl.test;

import java.awt.*;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamLearn {
    public static void main(String[] args) {
        String str = " abcd ";
        Stream<String> stream = Stream.of(str);
        Object[] aim = stream.filter("a"::equals).toArray();
        System.out.println(">>>>>>>>>>aim=" + aim[0].toString());
    }

    public static void print(String text) {
        // Java 8
        Optional.ofNullable(text).ifPresent(System.out::println);
        // Pre-Java 8
        if (text != null) {
            System.out.println(text);
        }
    }
    public static int getLength(String text) {
        // Java 8
        return Optional.ofNullable(text).map(String::length).orElse(-1);
        // Pre-Java 8
        // return if (text != null) ? text.length() : -1;
    };
}
