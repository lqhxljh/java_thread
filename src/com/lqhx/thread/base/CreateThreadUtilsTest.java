package com.lqhx.thread.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程基本工具内
 */
public class CreateThreadUtilsTest {
    public static void createThreadByRunnableTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this thread create type is runnable");
            }
        }).start();
    }

    public static void createThreadTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this thread create type is Thread");
            }
        }).start();
    }

    /**
     * 该线程也是唯一一个阻塞获取到线程返回值函数
     */
    public static void createCallableThreadTest() {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("this thread create type is callable");
                return "this is the result of callable method return";
            }
        });
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
