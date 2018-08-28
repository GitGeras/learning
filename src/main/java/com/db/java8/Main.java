package com.db.java8;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

        Map<Seniority, List<Employee>> seniorityListMap = EmployeeUtils.categorizeBySalary(Arrays.asList(
                new Employee("Jack", 5000),
                new Employee("Andrew", 1000),
                new Employee("John", 3000),
                new Employee("Alice", 4000),
                new Employee("Sam", 6000),
                new Employee("Bred", 8000)));
        System.out.println(seniorityListMap);
    }
}
