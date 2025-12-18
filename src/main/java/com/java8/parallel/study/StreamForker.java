package com.java8.parallel.study;

import com.google.common.collect.Maps;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Lingfeng on 2016/8/1.
 */
public class StreamForker<T> {

    private final Stream<T> stream;
    private final Map<Object, Function<Stream<T>, ?>> forks = Maps.newHashMap();

    public StreamForker(Stream<T> stream) {
        this.stream = stream;
    }

    public StreamForker<T> fork(Object key, Function<Stream<T>, ?> f) {
        forks.put(key, f); // 使用一个键对流上的函数进行索引

        return this; // 返回this从而保证多次流畅地调用fork方法
    }

    public Results getResults() {
        ForkingStreamConsumer<T> consumer = build();

        try {
            this.stream.sequential().forEach(consumer);
        } finally {
            consumer.finish();
        }

        return consumer;
    }

    private ForkingStreamConsumer<T> build() {
        List<BlockingQueue<T>> queues = new ArrayList<>(); // 创建由队列组成的列表，每一个队列对应一个操作

        // 建立用于标识操作的键与包含操作结果的Future之间的映射关系
        Map<Object, Future<?>> actions = forks.entrySet().stream().reduce(
                new HashMap<>(),
                (map, e) -> {
                    map.put(e.getKey(), getOperationResult(queues, e.getValue()));

                    return map;
                },
                (m1, m2) -> {
                    m1.putAll(m2);
                    return m1;
                }
        );

        return new ForkingStreamConsumer<>(queues, actions);
    }

    private Future<?> getOperationResult(List<BlockingQueue<T>> queues, Function<Stream<T>, ?> f) {
        BlockingQueue<T> queue = new LinkedBlockingDeque<>();
        queues.add(queue); // 创建一个队列，并将其添加到队列的列表中

        Spliterator<T> spliterator = new BlockingQueueSpliterator<>(queue); // 创建一个Spliterator，遍历队列中的元素
        Stream<T> source = StreamSupport.stream(spliterator, false); // 创建一个流，将Spliterator作为数据源
        return CompletableFuture.supplyAsync(() -> f.apply(source)); // 创建一个Future对象，
                                                                     // 以异步方式计算在流上执行特定函数的结果
    }
}
