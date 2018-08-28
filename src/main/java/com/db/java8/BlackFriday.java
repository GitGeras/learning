package com.db.java8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class BlackFriday {
    public static void main(String[] args) {
        BlackFriday blackFriday = new BlackFriday();
        Map<Integer, Long> blackFridayMap = blackFriday.findBlackFriday(2000, 2018);
        blackFridayMap.entrySet().stream()
                .sorted((o1, o2) -> Long.compare(o2.getValue(), o1.getValue()))
                .forEachOrdered(System.out::println);
    }

    public Map<Integer, Long> findBlackFriday(int startYear, int endYear) {
        LocalDate startLocalDate = LocalDate.ofYearDay(startYear, 13);
        LocalDate endLocalDate = LocalDate.of(endYear, Month.DECEMBER, 13);
        Map<Integer, Long> blackFridayMap = Stream.iterate(startLocalDate, localDate -> localDate.plusMonths(1))
                .limit(ChronoUnit.MONTHS.between(startLocalDate, endLocalDate))
                .filter(localDate -> localDate.getDayOfWeek() == DayOfWeek.FRIDAY)
                .collect(groupingBy(LocalDate::getYear, counting()));
        return blackFridayMap;
    }
}
