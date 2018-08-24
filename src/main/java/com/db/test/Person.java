package com.db.test;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Person {

    private String name;
    private int age;
    private int salary;

    private Person(){}

    /*public Person(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }*/

    public static PersonBuilder getBuilder(){
        return new PersonBuilder();
    }

    static class PersonBuilder{
        private String name;
        private int age;
        private Integer salary;

        public PersonBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder age(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder salary(int salary) {
            this.salary = salary;
            return this;
        }

        public Person build () throws Exception {
            Person person = new Person();
            person.setName(this.name);
            person.setAge(this.age);
            person.setSalary(this.salary);

            validate();
            clear();
            return person;
        }

        private void validate() throws Exception {
            if (salary == null){
                throw new Exception();
            }
        }

        private void clear() {
            this.name = null;
            this.age = 0;
            this.salary = null;
        }
    }
}
