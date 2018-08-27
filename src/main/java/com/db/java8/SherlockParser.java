package com.db.java8;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SherlockParser {
    @SneakyThrows
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new FileReader("test"));
        Map<String, Long> map = br.lines()
                .flatMap(s -> Arrays.stream(s.split("\\W+")))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(t -> t, Collectors.counting()));

        List<Map.Entry<String, Long>> first10 = map.entrySet().stream()
                .sorted((o1, o2) -> Long.compare(o2.getValue(), o1.getValue()))
                .limit(10)
                .collect(Collectors.toList());

        for (Map.Entry<String, Long> stringLongEntry : first10) {
            System.out.println(stringLongEntry);
        }
    }
}
