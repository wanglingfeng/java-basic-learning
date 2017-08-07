package com.mp3.study;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by lfwang on 2017/7/5.
 */
public class ReadMP3FileTests {
    
    public static void main(String... args) throws IOException, UnsupportedAudioFileException {
        File file = new File("/Users/lfwang/Music/网易云音乐/1,2,3,4.mp3");
        /*File file = new File("/Users/lfwang/Music/网易云音乐/Fitz & the Tantrums - HandClap.mp3");*/

        AudioFileFormat baseFileFormat = new MpegAudioFileReader().getAudioFileFormat(file);
        Map<String,Object> properties = baseFileFormat.properties();
        
        String name = (String) properties.get("title");
        System.out.println("name = " + name);
        
        String singer = (String) properties.get("author");
        System.out.println("singer = " + singer);
        
        Long microseconds = (Long) properties.get("duration");
        int milliseconds = (int) (microseconds / 1000);
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / 1000) / 60;
        System.out.println("time = " + minutes + ":" + seconds);

        System.out.println("--------------------");
        
        Set<String> keySet = properties.keySet();
        keySet.forEach(k -> {
            String value = properties.get(k).toString();
            System.out.println(k + " ==> " + value);
        });
    }
}
