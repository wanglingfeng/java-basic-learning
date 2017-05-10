package com.collaborativeFiltering.study;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by lfwang on 2017/5/10.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private List<Video> videos;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Video {
        private Long id;
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User(1l, "joseph", Arrays.asList(new Video(1001l), new Video(1002l))));
        users.add(new User(2l, "joseph", Arrays.asList(new Video(1003l), new Video(1004l))));
        users.add(new User(3l, "joseph", Arrays.asList(new Video(1001l), new Video(1005l), new Video(1002l))));
        users.add(new User(4l, "joseph", Arrays.asList(new Video(1005l), new Video(1009l))));
        users.add(new User(5l, "joseph", Collections.singletonList(new Video(1005l))));
        users.add(new User(6l, "joseph", Arrays.asList(new Video(1006l), new Video(1004l))));
        users.add(new User(7l, "joseph", Arrays.asList(new Video(1008l), new Video(1006l), new Video(1002l))));
        users.add(new User(8l, "joseph", Arrays.asList(new Video(1007l), new Video(1008l))));
        users.add(new User(9l, "joseph", Arrays.asList(new Video(1001l), new Video(1004l), new Video(1002l), new Video(1007l))));
        users.add(new User(10l, "joseph", Arrays.asList(new Video(1001l), new Video(1003l))));

        return users;
    }
}
