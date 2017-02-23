package com.deadlock.study;

/**
 * User: lfwang
 * Date: 15-8-17
 * Time: ����4:59
 *
 * һ���򵥵�������
 * ����Ķ���flag=1ʱ��T1����������O1,˯��500���룬Ȼ������O2��
 * ��T1��˯�ߵ�ʱ����һ��flag=0�Ķ���T2���߳�������������O2,˯��500���룬�ȴ�T1�ͷ�O1��
 * T1˯�߽�������Ҫ����O2���ܼ���ִ�У�����ʱO2�ѱ�T2������
 * T2˯�߽�������Ҫ����O1���ܼ���ִ�У�����ʱO1�ѱ�T1������
 * T1��T2�໥�ȴ�������Ҫ�Է���������Դ���ܼ���ִ�У��Ӷ�������
 */
public class DeadLock implements Runnable {

    public int flag;
    static final Object o1 = new Object();
    static final Object o2 = new Object();

    public void run() {
        System.out.println("flag = " + flag);

        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o2) {
                    System.out.println("1");
                }
            }

            //�����������
//            synchronized (o1) {
//                synchronized (o2) {
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println("1");
//                }
//            }
        }

        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o1) {
                    System.out.println("0");
                }
            }

            //�����������
//            synchronized (o2) {
//                synchronized (o1) {
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println("0");
//                }
//            }
        }
    }
}
