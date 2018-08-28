package com.db.java8;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeUtils {
    public static int calculateSalary(List<Employee> employees) {
        return employees.stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public static String joinNamesToString(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
    }

    public static Map<Seniority, List<Employee>> categorizeBySalary(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(t -> Seniority.findBySalary(t.getSalary())));
    }
}
