package com.Thread.basic;

//class MyThread extends Thread{
////    public void run(){
////        int i;
////        for(i=0; i<=50; i++){
////            System.out.print(i + "\t");
////            try {
////                sleep(100); //static 메소드
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////        }
////    }
////}

class MyThread implements Runnable{
    public void run(){
        int i;
        for(i=0; i<=50; i++){
            System.out.print(i + "\t");
            try {
                Thread.sleep(100); //static 메소드
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadTest {
    public static void main(String[] args){ //메인 스레드
//        MyThread t1 = new MyThread(); //보조스레드
//        t1.start();
        MyThread runner1 = new MyThread();
        Thread th1 = new Thread(runner1);
        th1.start();

        Thread t = Thread.currentThread();
        System.out.println(t);
    }
}
