package com.java8.stream.study.designMode;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * 责任链模式
 * Created by Lingfeng on 2016/7/28.
 */
public class ConcreteTest {

    public static void main(String... args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCHeckerProcessing();

        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);

        System.out.println("--------------");

        // Lambda表达式实现
        UnaryOperator<String> headerProcessing = (String input) -> "From Raoul, Mario and Alan: " + input;
        UnaryOperator<String> spellcheckerProcessing = (String input) -> input.replaceAll("labda", "lambda");

        // 将两个方法结合起来，结果就是一个操作链
        Function<String, String> pipeline = headerProcessing.andThen(spellcheckerProcessing);
        String reuslt1 = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println(reuslt1);
    }

    public static abstract class ProcessingObject<T> {

        protected ProcessingObject<T> successor;

        public void setSuccessor(ProcessingObject<T> successor) {
            this.successor = successor;
        }

        public T handle(T input) {
            T r = handleWork(input);

            if (this.successor != null) {
                return this.successor.handle(r);
            }

            return r;
        }

        abstract protected T handleWork(T input);
    }

    public static class HeaderTextProcessing extends ProcessingObject<String> {

        @Override
        protected String handleWork(String input) {
            return "From Raoul, Mario and Alan: " + input;
        }
    }

    public static class SpellCHeckerProcessing extends ProcessingObject<String> {

        @Override
        protected String handleWork(String input) {
            return input.replaceAll("labda", "lambda");
        }
    }
}
