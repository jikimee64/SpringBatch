package com.IO.SecondStream;

import java.io.*;
import java.net.Socket;
import java.util.regex.Pattern;

public class FileCopy {
    public static void main(String[] args) throws IOException {
        long milliseconds = 0;

        try(FileInputStream fis = new FileInputStream("a.zip");
            FileOutputStream fos = new FileOutputStream("copy.zip");
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            milliseconds = System.currentTimeMillis(); //현재시간

            int i;
            while((i = fis.read()) != -1){
                fos.write(i);
            }

            milliseconds = System.currentTimeMillis() - milliseconds;

        }catch(IOException e) {
            System.out.println(e);
        }

        Socket socket = new Socket();

        //보조 스트림이 하나의 다른 스트림을 감쌈
        BufferedReader isr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        isr.readLine();

        System.out.println(milliseconds);

    }
}
