package com.drools.study.str.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Lingfeng on 2016/4/20.
 */
@Data
public class Message {

    public static final int HELLO   = 0;
    public static final int GOODBYE = 1;

    private String message;
    private int status;

    public static Message doSomething(Message message) {
            return message;
        }

    public boolean isSomething(String msg, List<Object> list) {
        list.add(this);
        return this.message.equals(msg);
    }
}
