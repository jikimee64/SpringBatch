package com.IO.Byte;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputTest1 {
    public static void main(String[] args) {
        try (  FileInputStream fis = new FileInputStream("input.txt")){
            int i;
            while ((i = fis.read()) != -1) { //파일의 끝에 도달하면 -1 반환
                System.out.println((char)i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
