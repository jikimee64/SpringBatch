package com.IO.Byte;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputTest2 {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("input.txt")) {
            int i;
            byte[] bs = new byte[10];
            while ((i = fis.read(bs)) != -1) { //파일의 끝에 도달하면 -1 반환

                for(byte b : bs){ //가비지 남음
                    System.out.println((char)b);
                }

                for(int k = 0; k < i; k++){ //가비지 안남음
                    System.out.println((char)bs[k]);
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
