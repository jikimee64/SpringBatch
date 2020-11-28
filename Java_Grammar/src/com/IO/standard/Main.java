package com.IO.standard;

import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

//        Console console = System.console();
//
//        String name = console.readLine();
//        char[] password = console.readPassword();
//
//        System.out.println(name + ", " + password);



        try {
            int i;
            InputStreamReader isr = new InputStreamReader(System.in);
            while ((i = isr.read()) != '끝') { //엔터치기 전까지 입력
                System.out.println(i);
                System.out.println((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
