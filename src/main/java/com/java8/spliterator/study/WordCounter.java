package com.java8.spliterator.study;

/**
 * Created by Lingfeng on 2016/7/28.
 */
public class WordCounter {

    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) { // 和迭代算法一样,accumulate方法一个个遍历Character
        if (Character.isWhitespace(c)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            // 上一个字符是空格,而当前遍历的字符不是空格时，将单词计数器加一
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) { // 合并两个WordCounter,把其计数器加起来
        // 仅需要计数器的总和,无需关心lastSpace
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return this.counter;
    }
}
