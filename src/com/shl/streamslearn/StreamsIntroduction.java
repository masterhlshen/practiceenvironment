package com.shl.streamslearn;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsIntroduction {

    public static void main(String[] args) {
        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String[] strArray = new String[]{"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();


        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);


        List<String> wordList = new LinkedList<>();
        wordList.add("helo");
        wordList.add("heloddd");
        wordList.add("helossss");
        List<String> output = wordList.stream().
                map(String::toUpperCase).
                collect(Collectors.toList());
        Stream.of(output).forEach(System.out::println);


        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().
                map(n -> n * n).
                collect(Collectors.toList());
        Stream.of(squareNums).forEach(System.out::println);


        List<Double> score = new LinkedList<>();
        score.add(12.0);
        score.add(-12.0);
        Double total = score.stream().reduce(Double::sum).get();
        System.out.println(">>>>>>>>total = " + total);
        score = new LinkedList<>();
        List<Double> sortList = score.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        Stream.of(sortList).forEach(System.out::println);

        Map<String, String> map = new HashMap<>(16);
        map.put("11", "11");
        String s = "11";
        boolean flag;
        flag = map.entrySet().stream().anyMatch(p -> {
            if (s.equals(p.getKey())) {
                return true;
            }
            return false;
        });
        System.out.println(">>>>>>>>>flag = " + flag);
    }
}
