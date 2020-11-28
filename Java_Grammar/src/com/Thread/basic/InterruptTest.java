package com.Thread.basic;

public class InterruptTest extends Thread {

    public void run() {
        int i;
        for(i=0; i<100; i++){
            System.out.println(i);
        }
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        InterruptTest test = new InterruptTest();
        test.start();
        test.interrupt();

        System.out.println("end");

    }
}
