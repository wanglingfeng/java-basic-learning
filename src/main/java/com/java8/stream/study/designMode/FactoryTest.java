package com.java8.stream.study.designMode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 工厂模式
 * Created by Lingfeng on 2016/7/28.
 */
public class FactoryTest {

    public static class ProductFactory {
        public static Product createProduct(String name) {
            switch (name) {
                case "loan" : return new Loan();
                case "stock" : return new Stock();
                case "bond" : return new Bond();
                default: throw new IllegalArgumentException("No such product " + name);
            }
        }

        // 使用Lambda表达式实现
        final static Map<String, Supplier<Product>> map = new HashMap<>();

        static {
            map.put("load", Loan::new);
            map.put("stock", Stock::new);
            map.put("bond", Bond::new);
        }

        public static Product createProductWithLambda(String name) {
            Supplier<Product> p = map.get(name);
            if (p != null) return p.get();

            throw new IllegalArgumentException("No such product " + name);
        }
    }

    public interface Product {

    }

    public static class Loan implements Product {

    }

    public static class Stock implements Product {

    }

    public static class Bond implements Product {

    }
}
