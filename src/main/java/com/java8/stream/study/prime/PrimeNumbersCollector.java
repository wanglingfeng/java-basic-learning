package com.java8.stream.study.prime;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * Created by Lingfeng on 2016/7/27.
 */
public class PrimeNumbersCollector implements Collector<Integer, // 流中元素的类型
                                                        Map<Boolean, List<Integer>>, // 累加器类型
                                                        Map<Boolean, List<Integer>>> { // collect操作的结果类型

    // 从一个有两个空List的Map开始收集过程
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    // 将已经找到的质数列表传递给isPrime方法，根据isPrime方法的返回值，从Map中取质数或非质数列表，把当前的被测数加进去
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) ->
                acc.get(isPrime(acc.get(true), candidate)) // 根据isPrime的结果，获取质数或非质数列表
                        .add(candidate); // 将被测数添加到相应的列表中
    }

    // 将第二个Map合并到第一个
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));

            return map1;
        };
    }

    // 收集过程最后无需转换，因此用identity函数收尾
    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    // 这个收集器是IDENTITY_FINISH,既不是UNORDERED也不是CONCURRENT,是因为质数是按顺序发现的
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);

        return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(p -> candidate % p == 0);
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;

        for (A item : list) {
            if (! p.test(item)) { // 检查列表中的当前项目是否满足条件
                return list.subList(0, i); // 如果不满足，返回该项目之前的前缀子列表
            }

            i++;
        }

        return list; // 列表中的所有项目都满足条件，因此返回列表本身
    }
}
