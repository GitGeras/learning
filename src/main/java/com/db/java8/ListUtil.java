package com.db.java8;

import lombok.SneakyThrows;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ListUtil {
    public static <T> int countDuplicates(List<T> list, T t, Equalator<T> equalator) {
        int counter = 0;

        for (T t1 : list) {
            if (equalator.equals(t, t1)) {
                counter++;
            }
        }

        return counter;
    }

    public static <T> void forEachWithDelay(List<T> list, int delay, Consumer<? super T> action) {
        list.forEach(t -> {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            action.accept(t);
        });
    }
}
