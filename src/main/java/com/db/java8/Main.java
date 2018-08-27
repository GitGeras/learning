package com.db.java8;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        /*List<String> strings = Arrays.asList("Java", "java", "JaVa", "scala");
        System.out.println(ListUtil.countDuplicates(strings, "java", (s, t1) -> s.length() == t1.length()));
        ListUtil.forEachWithDelay(strings, 1000, System.out::println);*/

        /*BufferedReader br = new BufferedReader(new FileReader("test"));
        System.out.println(
                br.lines().
                        flatMap(s -> Arrays.stream(s.split("\\W+")))
                        .mapToInt(String::length).average().orElse(0));*/
//                .peek(System.out::println)
//                        .count());

        String str = joinNamesToString(Arrays.asList(
                new Employee("Jack", 500),
                new Employee("Andrew", 100),
                new Employee("John", 300),
                new Employee("Alice", 400),
                new Employee("Sam", 600),
                new Employee("Bred", 800)));
        System.out.println(str);
    }

    private static int calculateSalary(List<Employee> employees) {
        return employees.stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }
    private static String joinNamesToString(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
    }
}
