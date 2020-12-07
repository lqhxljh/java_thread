package com.lqhx.thread.base;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class ThreadMethodTest {
    public static Object mLock = new Object();
    public static Stack<String> queue = new Stack<>();

    public static void threadWaitTest() {
        Thread enqueueThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (queue) {
                    while (queue.size() == 5) {
                        try {
                            /**
                             * 挂起线程 释放对象queue锁
                             */
                            System.out.println("数据已经上线了queue线程进入等待状态");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    String enqueueString = "添加第" + queue.size() + "个元素";
                    System.out.println("enqueue线程将添加第" + enqueueString);
                    queue.add(enqueueString);
                    queue.notify();
                }
            }
        });
        enqueueThread.start();
        Thread dequeueThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            /**
                             * 挂起线程 释放对象queue锁
                             */
                            System.out.println("数据已经取空了dequeue线程进入等待状态");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("dequeue线程将取出第" + queue.size() + "个数据");
                    String ret = queue.pop();
                    System.out.println("dequeue线程将取出第" + ret);
                    queue.notify();
                }
            }
        });
        dequeueThread.start();

    }

}
