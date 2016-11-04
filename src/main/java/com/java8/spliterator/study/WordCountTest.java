package com.java8.spliterator.study;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Lingfeng on 2016/7/28.
 */
public class WordCountTest {

    public static final String SENTENCE = " Nel  mezzo del cammin  di nostra  vita " +
            "mi ritrovai in una  selva oscura che la  dritta via era  smarrita ";

    public static void main(String... args) {
        System.out.println("Found " + countWordsIneratively(SENTENCE) + " words");

        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);

        System.out.println("Found " + countWords(stream) + " words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream1 = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(stream1) + " words");
    }

    public static int countWordsIneratively(String s) {
        int counter = 0;
        boolean lastSpace = true;

        for (char c : s.toCharArray()) { // 逐个遍历String中的所有字符
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter ++; // 上一个字符是空格，而当前遍历的字符不是空格时，将单词计数器加一
                lastSpace = false;
            }
        }

        return counter;
    }

    public static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                                                WordCounter::accumulate,
                                                WordCounter::combine);

        return wordCounter.getCounter();
    }
}
