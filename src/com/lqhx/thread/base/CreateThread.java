package com.lqhx.thread.base;

public class CreateThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("this create thread method is extend thread");
    }
}
