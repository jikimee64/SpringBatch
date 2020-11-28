package com.IO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {
    public static void main(String[] args) throws IOException {

        RandomAccessFile rf = new RandomAccessFile("random.txt", "rw");
        rf.writeInt(100); //4바이트
        System.out.println(rf.getFilePointer()); //위치
        rf.writeDouble(3.14);
        rf.writeUTF("안녕하세요.");

        rf.seek(0); //처음위치로 되돌아감
        int i = rf.readInt();
        double d = rf.readDouble();
        String str = rf.readUTF();

        System.out.println(i);
        rf.close();
    }
}
