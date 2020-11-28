package com.Thread.multi_thread;

class Bank {
    private int money = 10000;

    public synchronized void saveMoney(int save){
        int m = this.getMoney();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setMoney(m + save);
    }

    public synchronized void minusMoney(int minus){
        int m = this.getMoney();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setMoney(m - minus);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

class Park extends Thread {
    public void run() {
        synchronized (SyncTest.myBank){ //블럭 방식
            System.out.println("start save");
            SyncTest.myBank.saveMoney(3000);
            System.out.println("save money:" + SyncTest.myBank.getMoney());
        }
    }
}

class ParkWife extends Thread {
    public void run() {
        System.out.println("start minus");
        SyncTest.myBank.minusMoney(1000);
        System.out.println("minus money:" + SyncTest.myBank.getMoney());
    }
}

public class SyncTest {

    public static Bank myBank = new Bank();

    public static void main(String[] args) throws InterruptedException {
        Park p = new Park();
        p.start();

        Thread.sleep(200);
        ParkWife pw = new ParkWife();
        pw.start();

    }
}
