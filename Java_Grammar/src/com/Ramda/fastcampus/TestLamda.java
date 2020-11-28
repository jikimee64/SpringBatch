package com.Ramda.fastcampus;

@FunctionalInterface
interface PrintString{
    void showString(String str);
}


public class TestLamda {
    public static void main(String[] args){
        PrintString lambdaStr = s-> System.out.println(s); //구현부가 변수타입으로 바로 대입
        lambdaStr.showString("Test");

        showMyString(lambdaStr);

        PrintString test = returnString(); //함수의 구현부가 변수처럼 반환
        test.showString("Test3");
    }

    public static void showMyString(PrintString p){
        p.showString("Test2");
    }

    public static PrintString returnString() {
        return s-> System.out.println(s + "!!!");
    }
}
