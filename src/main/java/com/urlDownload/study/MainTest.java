package com.urlDownload.study;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by lfwang on 2016/8/29.
 */
public class MainTest {

    public static void main(String[] args) throws IOException {
        String path = "http://o701fmjvo.bkt.clouddn.com/1-06df9956-135c-4a26-b9c6-1a2f24263a3d.gif";
        URL website = new URL(path);

        System.out.println(website);

        InputStream inputStream = website.openStream();

        byte[] content;

        try (ByteArrayOutputStream swapStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                swapStream.write(buffer, 0 , length);
            }

            content = swapStream.toByteArray();
        }

        MultipartFile file = new ByteArrayMultipartFile(path.substring(path.lastIndexOf("/") + 1), content);

        System.out.println(file);
    }
}
