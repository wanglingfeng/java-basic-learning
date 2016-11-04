package com.drools.study.str;

import com.drools.study.str.service.HelloDroolsService;

/**
 * Created by Lingfeng on 2016/4/20.
 */
public class DroolsStrTest {

    public static void main(String[] args) {
        HelloDroolsService.init();
        HelloDroolsService.initMessage();
        HelloDroolsService.fireRules();
    }
}
