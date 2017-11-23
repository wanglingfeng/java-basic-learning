package com.mutiThreadDownload.study;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程的断点下载程序，根据输入的url和指定线程数，来完成断点续传功能。
 * 每个线程支负责某一小段的数据下载；再通过RandomAccessFile完成数据的整合。
 */
public class MultiThreadDownloadTask {

    private String filePath = null;
    private String fileName = null;
    private String tmpFileName = null;

    private int threadNum = 0;

    private CountDownLatch latch = null; // 设置一个计数器，代码内主要用来完成对缓存文件的删除

    private long fileLength = 0l;
    private long threadLength = 0l;
    private long[] startPos; // 保留每个线程下载数据的起始位置。
    private long[] endPos; // 保留每个线程下载数据的截止位置。

    private boolean bool = false;

    private URL url = null;

    public MultiThreadDownloadTask(String filePath, int threadNum) {
        this.filePath = filePath;
        this.threadNum = threadNum;
        startPos = new long[this.threadNum];
        endPos = new long[this.threadNum];
        latch = new CountDownLatch(this.threadNum);
    }

    /**
     * 组织断点续传功能的方法
     */
    public void downloadPart() {
        // 在请求url内获取文件资源的名称；此处没考虑文件名为空的情况，此种情况可能需使用UUID来生成一个唯一数来代表文件名。
        fileName = filePath.substring(filePath.lastIndexOf('/') + 1, 
                filePath.contains("?") ? filePath.lastIndexOf('?') : filePath.length());
        tmpFileName = fileName + "_tmp";

        try {
            url = new URL(filePath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            setHeader(connection);
            fileLength = connection.getContentLengthLong(); // 获取请求资源的总长度。

            File file = new File(fileName);
            File tmpFile = new File(tmpFileName);

            threadLength = fileLength / threadNum; // 每个线程需下载的资源大小。
            System.out.println("fileName: " + fileName + " ," + "fileLength= " + fileLength + " the threadLength= " + threadLength);

            if (file.exists() && file.length() == fileLength) {
                System.out.println("the file you want to download has exited!!");
                return;
            } else {
                setBreakPoint(startPos, endPos, tmpFile);
                ExecutorService exec = Executors.newCachedThreadPool();
                for (int i = 0; i < threadNum; i++) {
                    exec.execute(new DownLoadThread(startPos[i], endPos[i], this, i, latch));
                }
                latch.await(); // 当你的计数器减为0之前，会在此处一直阻塞。
                exec.shutdown();
            }

            if (file.length() == fileLength) {
                if (tmpFile.exists()) {
                    System.out.println("delete the temp file!!");
                    tmpFile.deleteOnExit();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 断点设置方法，当有临时文件时，直接在临时文件中读取上次下载中断时的断点位置。没有临时文件，即第一次下载时，重新设置断点。
     * randomAccessFile.seek()跳转到一个位置的目的是为了让各个断点存储的位置尽量分开。
     * 这是实现断点续传的重要基础。
     * 
     * @param startPos
     * @param endPos
     * @param tmpFile
     */
    private void setBreakPoint(long[] startPos, long[] endPos, File tmpFile) {
        RandomAccessFile randomAccessFile = null;
        
        try {
            if (tmpFile.exists()) {
                System.out.println("the download has continued!!");
                randomAccessFile = new RandomAccessFile(tmpFile, "rw");
                
                for (int i = 0; i < threadNum; i++) {
                    randomAccessFile.seek(8 * i + 8);
                    startPos[i] = randomAccessFile.readLong();

                    randomAccessFile.seek(8 * (i + 1000) + 16);
                    endPos[i] = randomAccessFile.readLong();

                    System.out.println("the Array content in the exit file: ");
                    System.out.println("the thread" + (i + 1) + " startPos:" + startPos[i] + ", endPos: " + endPos[i]);
                }
            } else {
                System.out.println("the tmpFile is not available!!");
                randomAccessFile = new RandomAccessFile(tmpFile, "rw");

                // 最后一个线程的截止位置大小为请求资源的大小
                for (int i = 0; i < threadNum; i++) {
                    startPos[i] = threadLength * i;
                    
                    if (i == threadNum - 1) {
                        endPos[i] = fileLength;
                    } else {
                        endPos[i] = threadLength * (i + 1) - 1;
                    }

                    randomAccessFile.seek(8 * i + 8);
                    randomAccessFile.writeLong(startPos[i]);

                    randomAccessFile.seek(8 * (i + 1000) + 16);
                    randomAccessFile.writeLong(endPos[i]);

                    System.out.println("the Array content: ");
                    System.out.println("the thread" + (i + 1) + " startPos:" + startPos[i] + ", endPos: " + endPos[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 实现下载功能的内部类，通过读取断点来设置向服务器请求的数据区间。
     */
    class DownLoadThread implements Runnable {

        private long startPos;
        private long endPos;
        private MultiThreadDownloadTask task = null;
        
        private RandomAccessFile downloadFile = null;
        private RandomAccessFile randomAccessFile = null;
        
        private int id;
        private CountDownLatch latch = null;

        public DownLoadThread(long startPos, long endPos,
                              MultiThreadDownloadTask task, int id,
                              CountDownLatch latch) {
            this.startPos = startPos;
            this.endPos = endPos;
            this.task = task;
            try {
                this.downloadFile = new RandomAccessFile(this.task.fileName, "rw");
                this.randomAccessFile = new RandomAccessFile(this.task.tmpFileName, "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            InputStream is = null;

            System.out.println("the thread " + id + " has started!!");

            while (true) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) task.url.openConnection();
                    setHeader(connection);

                    // 防止网络阻塞，设置指定的超时时间；单位都是ms。超过指定时间，就会抛出异常
                    connection.setReadTimeout(20000); // 读取数据的超时设置
                    connection.setConnectTimeout(20000); // 连接的超时设置

                    if (startPos < endPos) {
                        // 向服务器请求指定区间段的数据，这是实现断点续传的根本。
                        connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

                        System.out.println("Thread " + id + ": the total size ==> " + (endPos - startPos));

                        downloadFile.seek(startPos);

                        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK
                                && connection.getResponseCode() != HttpURLConnection.HTTP_PARTIAL) {
                            this.task.bool = true;
                            connection.disconnect();
                            downloadFile.close();
                            latch.countDown(); // 计数器自减

                            System.out.println("the thread " + id + " has done!!");
                            break;
                        }

                        is = connection.getInputStream(); // 获取服务器返回的资源流
                        long count = 0l;
                        int length;
                        byte[] buf = new byte[1024];

                        while (!this.task.bool && (length = is.read(buf)) != -1) {
                            count += length;
                            downloadFile.write(buf, 0, length);

                            // 不断更新每个线程下载资源的起始位置，并写入临时文件；为断点续传做准备
                            startPos += length;
                            randomAccessFile.seek(8 * id + 8);
                            randomAccessFile.writeLong(startPos);
                        }
                        System.out.println("the thread " + id + " total load count: " + count);

                        // 关闭流
                        is.close();
                        connection.disconnect();
                        downloadFile.close();
                        randomAccessFile.close();
                    } else {
                        latch.countDown(); // 计数器自减
                        System.out.println("the thread " + id + " has done!!");
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 为一个HttpURLConnection模拟请求头，伪装成一个浏览器发出的请求
     * @param con
     */
    private void setHeader(HttpURLConnection con) {
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.3) Gecko/2008092510 Ubuntu/8.04 (hardy) Firefox/3.0.3");
        con.setRequestProperty("Accept-Language", "en-us,en;q=0.7,zh-cn;q=0.3");
        con.setRequestProperty("Accept-Encoding", "aa");
        con.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
        con.setRequestProperty("Keep-Alive", "300");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("If-Modified-Since", "Fri, 02 Jan 2009 17:00:05 GMT");
        con.setRequestProperty("If-None-Match", "\"1261d8-4290-df64d224\"");
        con.setRequestProperty("Cache-Control", "max-age=0");
        con.setRequestProperty("Referer", "http://www.skycn.com/soft/14857.html");
    }
}