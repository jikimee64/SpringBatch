package com.IO.SecondStream;

import java.io.*;

public class DataStreamTest {
    public static void main(String[] args){

        try (FileOutputStream fos = new FileOutputStream("data.txt");
            DataOutputStream dos = new DataOutputStream(fos);
            FileInputStream fis = new FileInputStream("data.txt");
            DataInputStream dis = new DataInputStream(fis)) {

            dos.writeByte(100); //1바이트
            dos.write(100); //4바이트
            dos.writeChar('A');
            dos.writeUTF("안녕하세요.");

            System.out.println(dis.readByte());
            System.out.println(dis.read());
            System.out.println(dis.readChar());
            System.out.println(dis.readUTF());



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
