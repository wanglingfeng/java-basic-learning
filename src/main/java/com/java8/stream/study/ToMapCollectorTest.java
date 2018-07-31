package com.java8.stream.study;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author keleguo
 * @date Created in 2018/7/3
 */
public class ToMapCollectorTest {

    public static List<Person> personList = Arrays.asList(
            new Person("a", "1"),
            new Person("b", "2"),
            new Person("v", "3"),
            new Person("a", "4"),
            new Person("d", "5"),
            new Person("s", "6"),
            new Person("b", "7"),
            new Person("a", "8"),
            new Person("s", "9"),
            new Person("d", "0")
    );

    public static void main(String[] args) {
        Map<String, String> phoneBook = personList.stream()
                .collect(Collectors.toMap(Person::getName, Person::getAddress, (p1, p2) -> p1 + ", " + p2));

        System.out.println(phoneBook);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Person {
        private String name;
        private String address;
    }
}
