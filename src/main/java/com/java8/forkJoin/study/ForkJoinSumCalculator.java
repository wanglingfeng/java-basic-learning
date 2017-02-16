package com.java8.forkJoin.study;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Created by Lingfeng on 2016/7/28.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers; // 要求和的数组
    private final int start; // 子任务处理的数组的起始位置
    private final int end; // 子任务处理的数组的结束位置

    public static final long THRESHOLD = 10_000; // 不再将任务分解为子任务的数组大小

    // 公共构造函数用于创建主任务
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    // 私有构造函数用于以递归方式为主任务创建子任务
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();

        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);

        // 如果任务被取消或者抛出异常
        if (task.isCompletedAbnormally()) {
            System.out.println(task.getException());
            return 0l;
        }
        return new ForkJoinPool().invoke(task);
    }

    @Override
    protected Long compute() {
        int length = end - start; // 该任务负责求和部分的大小

        if (length <= THRESHOLD) {
            return computeSequentially(); // 如果大小小于或等于阈值，顺序计算结果
        }

        // 创建子任务来为数组的前一半求和
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length/2);
        leftTask.fork(); // 利用另一个ForkJoinPool线程异步执行新创建的子任务

        // 创建一个任务为数组的后一半求和
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length/2, end);

        Long rightResult = rightTask.compute(); // 同步执行第二个子任务，有可能允许进一步递归划分
        Long leftResult = leftTask.join(); // 读取第一个子任务的结果，如果尚未完成就等待

        return leftResult + rightResult; // 该任务的结果是两个子任务结果的组合
    }

    // 在子任务不再可分时计算结果的简单算法
    private long computeSequentially() {
        long sum = 0;

        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }

        return sum;
    }
}
