package com;

public class BinaryMathTest {

    public static void main(String... args) {
        String binaryString = Integer.toBinaryString(32);
        System.out.println(binaryString);
        System.out.println(Short.valueOf(binaryString, 2));
        System.out.println(Integer.valueOf("100000", 2));

        System.out.println(Integer.toBinaryString(17));

        System.out.println("--------------------");

        BinaryMathTest test = new BinaryMathTest();

        System.out.println(Integer.toBinaryString(test.setCookStatus(Short.valueOf("1", 2), true)));
        System.out.println(Integer.toBinaryString(test.setCookStatus(Short.valueOf("11", 2), true)));
        System.out.println(Integer.toBinaryString(test.setCookStatus(Short.valueOf("111", 2), true)));
        System.out.println(Integer.toBinaryString(test.setCookStatus(Short.valueOf("1111", 2), true)));
        System.out.println(Integer.toBinaryString(test.setCookStatus(Short.valueOf("1111111", 2), true)));
        System.out.println(Integer.toBinaryString(test.setCookStatus(Short.valueOf("1111111", 2), false)));
    }

    private static final short COOK_TARGET_NUM = 32;

    private short setCookStatus(short status, boolean cookStatus) {
        if (cookStatus) {
            return (short) (status | COOK_TARGET_NUM);
        } else {
            return (short) (status & ~COOK_TARGET_NUM);
        }
    }
}
