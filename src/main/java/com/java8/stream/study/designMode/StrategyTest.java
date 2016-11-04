package com.java8.stream.study.designMode;

/**
 * 策略模式
 * Created by Lingfeng on 2016/7/28.
 */
public class StrategyTest {

    public static void main(String... args) {
        // 传统实现
        Validator numericValidator = new Validator(new IsNumeric());
        boolean isNumeric = numericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean isLowerCase = lowerCaseValidator.validate("bbbb");

        System.out.println("isNumeric is: " + isNumeric + " , isLowerCase is: " + isLowerCase);

        System.out.println("--------------");

        // Lambda表达式
        Validator numericValidator1 = new Validator((String s) -> s.matches("[a-z]+"));
        boolean isNumeric1 = numericValidator1.validate("aaaa");
        Validator lowerCaseValidator1 = new Validator((String s) -> s.matches("\\d+"));
        boolean isLowerCase1 = lowerCaseValidator1.validate("bbbb");

        System.out.println("isNumeric1 is: " + isNumeric1 + " , isLowerCase1 is: " + isLowerCase1);
    }

    public static class Validator {
        private final ValidationStrategy strategy;

        public Validator(ValidationStrategy strategy) {
            this.strategy = strategy;
        }

        public boolean validate(String s) {
            return this.strategy.execute(s);
        }
    }

    public interface ValidationStrategy {
        boolean execute(String s);
    }

    public static class IsAllLowerCase implements ValidationStrategy {

        @Override
        public boolean execute(String s) {
            return s.matches("[a-z]+");
        }
    }

    public static class IsNumeric implements ValidationStrategy {

        @Override
        public boolean execute(String s) {
            return s.matches("\\d+");
        }
    }
}
