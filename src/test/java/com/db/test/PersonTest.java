package com.db.test;

import lombok.SneakyThrows;
import org.junit.Test;

public class PersonTest {

    @Test(expected = Exception.class)
    @SneakyThrows
    public void testThatSalaryIsMandatory() {
        Person person = Person.getBuilder().build();
    }
}
