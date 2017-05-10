package com.collaborativeFiltering.study;

import java.util.Map;

/**
 * Created by lfwang on 2017/5/8.
 */
public class MathExecutor {

    /**
     * 皮尔逊相关系数
     *
     * @param pm1
     * @param pm2
     * @return
     */
    public static double getPearsonCorrelationCoefficient(Map<String, Integer> pm1, Map<String, Integer> pm2) {
        int n = 0; // 数量n
        int sxy = 0; // Σxy=x1*y1+x2*y2+....xn*yn
        int sx = 0; // Σx=x1+x2+....xn
        int sy = 0; // Σy=y1+y2+...yn
        int sx2 = 0; // Σx2=(x1)2+(x2)2+....(xn)2
        int sy2 = 0; // Σy2=(y1)2+(y2)2+....(yn)2

        for (String key : pm1.keySet()) {
            Integer x = pm1.get(key);
            Integer y = pm2.get(key);

            if (x != null && y != null) {
                n++;
                sxy += x * y;
                sx += x;
                sy += y;
                sx2 += Math.pow(x, 2);
                sy2 += Math.pow(y, 2);
            }
        }

        // p=(Σxy-Σx*Σy/n)/Math.sqrt((Σx2-(Σx)2/n)(Σy2-(Σy)2/n));
        double sd = sxy - sx * sy / n;
        double sm = Math.sqrt((sx2 - Math.pow(sx, 2) / n) * (sy2 - Math.pow(sy, 2) / n));
        return Math.abs(sm == 0 ? 1 : sd / sm);
    }

    /**
     * 欧几里德距离
     *
     * @param pm1
     * @param pm2
     * @return
     */
    public static double getEuclidDistance(Map<String, Integer> pm1, Map<String, Integer> pm2) {
        double totalScore = 0.0;

        for (String key : pm1.keySet()) {
            Integer a1 = pm1.get(key);
            Integer b1 = pm2.get(key);

            if (a1 != null && b1 != null) {
                double a = Math.pow(a1 - b1, 2);
                totalScore += Math.abs(a);
            }
        }
        return Math.sqrt(totalScore);
    }
}
