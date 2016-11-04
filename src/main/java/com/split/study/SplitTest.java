package com.split.study;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lingfeng on 2016/6/23.
 */
public class SplitTest {

    public static void main(String[] args) {
        String str = "法律是代理商的李老师了丢三落四的螺丝钉柳树流水代理商代";

        String str2 = "HSAJDHSJ  \n" +
                "JSJS  SJHFJSFG\n" +
                "JDSHSGFF\n" +
                "\n" +
                "\n" +
                "HDSJDSDGDFHGSF";

        System.out.println(str2.replaceAll("\n", "; "));

        String[] result = splitToSeven(str);
        for (String s : result) {
            System.out.println(s);
        }
    }

    private static String[] splitToSeven(String item) {
        String[] result = new String[7];
        StringBuilder temp = new StringBuilder();

        char[] itemChars = item.toCharArray();
        int index = 1;

        for (int i = 0; i < itemChars.length; i++) {
            temp.append(itemChars[i]);

            if ((index < 7 && (i != 0 && (i + 1) % 35 == 0)) || i == (itemChars.length - 1)) {
                result[index - 1] = temp.toString();
                temp.delete(0, temp.length());
                index++;
            }
        }

        return result;
    }
}
