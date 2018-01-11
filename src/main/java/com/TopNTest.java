package com;

import java.util.*;

/**
 * Created by lfwang on 2018/1/10.
 */
public class TopNTest {

    public static void main(String... args) {
        Map<Long, List<Long>> map = new HashMap<>();

        List<Long> list1 = Arrays.asList(3l, 2l, 1l);
        map.put(1l, list1);
        List<Long> list2 = Arrays.asList(6l, 5l, 4l, 0l);
        map.put(2l, list2);
        List<Long> list3 = Arrays.asList(7l, 8l);
        map.put(3l, list3);

        System.out.println(topN(map, 9));
    }

    public static List<Long> topN(Map<Long, List<Long>> map, int n) {
        Set<Long> keySet = map.keySet();
        List<Long> keyList = new ArrayList<>(keySet);
        keyList.sort((o1, o2) -> o1.intValue() - o2.intValue());

        System.out.println(keyList);

        List<Long> result = new ArrayList<>(n);

        int index = 0;
        int nIndex = 0;

        while (index < keyList.size() && nIndex < n && result.size() < n) {
            List<Long> list = map.get(keyList.get(index));

            if (nIndex < list.size()) result.add(list.get(nIndex));

            if (index == keyList.size() - 1) {
                index = 0;
                nIndex++;
            }

            index++;
        }

        return result;
    }
}
