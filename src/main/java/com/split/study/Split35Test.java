package com.split.study;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lfwang on 2016/9/28.
 */
public class Split35Test {

    public static void main(String[] args) {
        String message1 = "CHONGQING CHANGFENG CHEMICAL\n" +
                "CO., LTD. \n" +
                "30TH FL., LONGHU ZIDUXINGZUO BUILDING A, 1ST BRANCH,YUSONG RD.,YUBEI DISTRICT CHONGQING CHINA\n";

        String message2 = "IGM RESINS B.V.\n" +
                "GOMPENSTRAAT 49, 5145 RM WAALWIJK\n" +
                "THE NETHERLANDS\n";

        String message3 = "IGM RESINS B.V.\n" +
                "GOMPENSTRAAT 49, 5145 RM WAALWIJK\n" +
                "THE NETHERLANDS\n" +
                "CONTACT: FRANS VAN DEN BERG\n" +
                "P: +31 416 316 657  F: +31 416 5624 632\n";

        String soLong = "CHONGQING CHANGFENG CHEMICAL\n" +
                "CO., LTD. \n" +
                "CO., LTD. \n" +
                "CO., LTD. \n" +
                "CO., LTD. \n" +
                "CO., LTD. \n" +
                "30TH FL., LONGHU ZIDUXINGZUO BUILDING A, 1ST BRANCH,YUSONG RD.,YUBEI DISTRICT CHONGQING CHINA\n";

        String soLongNoBlank = "CHONGQINGCHANGFENGCHEMICAL" +
                "CO.,LTD." +
                "CO.,LTD." +
                "CO.,LTD." +
                "CO.,LTD." +
                "CO.,LTD." +
                "30THFL.,LONGHUZIDUXINGZUOBUILDINGA,1STBRANCH,YUSONGRD.,YUBEIDISTRICTCHONGQINGCHINA\n";

        String soShort = "A";

        String[] result = new String[7];

        List<String> resultList = new ArrayList<>();
        splitOver35SizeIn7Line(resultList, message3);

        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        for (String s : result) {
            System.out.println(s);
        }
    }

    public static void splitOver35SizeIn7Line(List<String> list, String str) {
        // switch
        if (null == list || list.size() > 6 || StringUtils.isEmpty(str)) return;


        int changeLineIndex = str.indexOf("\n");
        String startStr = changeLineIndex < 0 ? str : str.substring(0, changeLineIndex++);

        if (startStr.length() > 35 && list.size() < 6) {
            changeLineIndex = 35;
            startStr = startStr.substring(0, changeLineIndex);

            int blankIndex = startStr.lastIndexOf(" ");
            if (blankIndex > -1) {
                startStr = startStr.substring(0, ++blankIndex);
                changeLineIndex = blankIndex;
            }
        }

        list.add(startStr);
        if (changeLineIndex > -1) splitOver35SizeIn7Line(list, str.substring(changeLineIndex));
    }
}
