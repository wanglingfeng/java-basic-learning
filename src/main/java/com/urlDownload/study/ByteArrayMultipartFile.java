package com.urlDownload.study;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by lfwang on 2016/8/29.
 */
public class ByteArrayMultipartFile implements MultipartFile {

    private final String name;
    private final byte[] content;

    public ByteArrayMultipartFile(String name, byte[] imgContent) {
        this.name = name;
        this.content = imgContent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return name;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return null == content || content.length == 0;
    }

    @Override
    public long getSize() {
        return (null == content) ? 0L : content.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return content;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(content);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(content);
    }
}
