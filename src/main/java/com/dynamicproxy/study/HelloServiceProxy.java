package com.dynamicproxy.study;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lfwang on 2017/2/28.
 */
public class HelloServiceProxy implements InvocationHandler {

    private Object target;

    /**
     * 绑定委托对象并返回一个【代理占位】
     *
     * bind方法中的newProxyInstanc方法，就是生成一个代理对象，
     * 第一个参数是类加载器，
     * 第二个参数是真实委托对象所实现的的接口（代理对象挂在那个接口下），
     * 第三个参数this代表当前HelloServiceProxy类，
     * 换句话说是使用HelloServiceProxy作为对象的代理。
     *
     * @param target 真实对象
     * @return 代理对象【占位】
     */
    public Object bind(Object target) {
        this.target = target;

        // 取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 通过代理对象调用方法
     * @param proxy 代理对象
     * @param method 方法，被调用方法
     * @param args 方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--------- Im JDK Dynamic Proxy ---------");
        Object result = null;

        // 反射方法前调用
        System.out.println("Im ready to say Hello");

        // 反射执行方法，相当于调用target.sayHello
        result = method.invoke(target, args);

        // 反射方法后调用
        System.out.println("I already said Hello");

        return result;
    }
}
