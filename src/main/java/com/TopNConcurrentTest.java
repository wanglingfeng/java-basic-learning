package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lfwang on 2018/1/10.
 */
public class TopNConcurrentTest {

    /**
     * topN 用多线程实现？
     * @param file
     * @param n
     * @throws Exception
     */
    public void execute(File file, int n) throws Exception {
        if (n > 100000) throw new Exception("top内容过多无法处理");

        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader bufferedReader = new BufferedReader(reader);

        List<Integer> top = new ArrayList<>(n);
        String lineText;

        int index = 0;

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        while ((lineText = bufferedReader.readLine()) != null) {
            if (index < n) {
                top.add(Integer.valueOf(lineText));
            } else {
                top.remove(top.size() - 1);
                top.add(Integer.valueOf(lineText));
            }
            top.sort((o1, o2) -> o1 - o2);
            index++;
        }
    }
}
