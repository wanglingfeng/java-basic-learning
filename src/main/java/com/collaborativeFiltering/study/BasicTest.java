package com.collaborativeFiltering.study;

import java.util.*;

/**
 * Created by lfwang on 2017/5/8.
 */
public class BasicTest {

    public static void main(String... args) {
        Map<String, Map<String, Integer>> userMap = DateStore.init();

        String output1 = "皮尔逊相关系数:\n", output2 = "欧几里得距离:\n";
        for (String userName : userMap.keySet()) {
            if (!"p5".equals(userName)) {
                double sim = MathExecutor.getPearsonCorrelationCoefficient(userMap.get("p5"), userMap.get(userName));
                double distance = MathExecutor.getEuclidDistance(userMap.get("p5"), userMap.get(userName));
                output1 += "p5 与 " + userName + " 之间的相关系数: " + sim + "\n";
                output2 += "p5 与 " + userName + " 之间的距离: " + distance + "\n";
            }
        }
        System.out.println(output1);
        System.out.println(output2);
    }
}
