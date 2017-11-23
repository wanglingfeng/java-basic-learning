package com.mutiThreadDownload.study;

/**
 * Created by lfwang on 2017/11/23.
 */
public class DownloadTests {
    
    public static void main(String... args) {
        String filePath = "http://oi5y1stl0.bkt.clouddn.com/bridge-pattern.png";

        MultiThreadDownloadTask load = new MultiThreadDownloadTask(filePath ,4);
        load.downloadPart();
    }
}
