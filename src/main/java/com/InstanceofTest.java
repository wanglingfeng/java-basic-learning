package com;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class InstanceofTest {

    public static void main(String... args) {
        Father father = new Father();
        father.setId(1);
        father.setName("joseph");

        if (father instanceof Son) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    @Data
    static class Father {

        private int id;
        private String name;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    static class Son extends Father {

        private long birthday;
    }
}
