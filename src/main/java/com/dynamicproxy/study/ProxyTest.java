package com.dynamicproxy.study;

import com.dynamicproxy.study.service.HelloService;
import com.dynamicproxy.study.service.impl.HelloServiceImpl;

/**
 * Created by lfwang on 2017/2/28.
 */
public class ProxyTest {

    public static void main(String... args) {
        HelloServiceProxy proxy = new HelloServiceProxy();
        HelloService service = new HelloServiceImpl();
        // 绑定代理对象
        HelloService serviceProxy = (HelloService) proxy.bind(service);

        // 这里service经过绑定，就会进入invoke方法里面了
        serviceProxy.sayHello("Joseph");
    }
}
