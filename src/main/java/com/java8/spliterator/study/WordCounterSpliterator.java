package com.java8.spliterator.study;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by Lingfeng on 2016/7/28.
 */
public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++)); // 处理当前字符
        return currentChar < string.length(); // 如果还有字符要处理，则返回true
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;

        if (currentSize < 10) {
            return null; // 返回null表示要解析的String已经足够小，可以顺序处理
        }

        // 将试探拆分位置设定为要解析的String的中间
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {

            // 让拆分位置前进直到下一个空格
            if (Character.isWhitespace(string.charAt(splitPos))) {
                // 创建一个新WordCounterSpliterator来解析String从开始到拆分位置的部分
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));

                // 将这个WordCounterSpliterator的起始位置设为拆分位置
                currentChar = splitPos;
                return spliterator;
            }
        }

        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        // 告诉框架这个Spliterator是
        // ORDERED (有次序的)，
        // SIZED (返回值是精确的)，
        // SUBSIZED (trySplit方法创建的其他Spliterator也有确切大小)，
        // NONNULL (String中不能有为null的Character)，
        // IMMUTABLE    (在解析String时不能再添加Character，因为String本身是一个不可变类)
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
