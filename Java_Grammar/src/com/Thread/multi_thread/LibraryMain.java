package com.Thread.multi_thread;

import java.util.ArrayList;
import java.util.List;

class FastLibrary{
    public List<String> books = new ArrayList<String>();

    public FastLibrary() {
        books.add("태백산맥 1");
        books.add("태백산맥 2");
        //books.add("태백산맥 3");
    }

    public synchronized String lendBook() throws InterruptedException {

        Thread t = Thread.currentThread();
        while(books.size() == 0){
            System.out.println(t.getName() + "waiting start");
            wait();
            System.out.println(t.getName() + "waiting end");
        }
        String title = books.remove(0);
        System.out.println(t.getName() + ":" + title + " lend");
        return title;
    }

    public synchronized void returnBook(String title){
        Thread t = Thread.currentThread();
        books.add(title);
        notifyAll();
        System.out.println(t.getName() + ":" + title + " return");
    }
}

class Student extends Thread{
    public void run() {
        try {
            String title = LibraryMain.library.lendBook();
            sleep(5000);
            LibraryMain.library.returnBook(title);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class LibraryMain {
    public static FastLibrary library = new FastLibrary();
    public static void main(String[] args){
        Student std1 = new Student();
        Student std2 = new Student();
        Student std3 = new Student();

        std1.start();
        std2.start();
        std3.start();
    }
}
