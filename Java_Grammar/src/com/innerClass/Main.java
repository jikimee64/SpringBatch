package com.innerClass;

class OutClass{
    private int num = 10;
    private static int sNum = 20;
    private InClass inClass;

    public OutClass(){
        inClass = new InClass();
    }

    class InClass{ //OutClass가 먼저 생성이 되야 사용할 수 있음
        int iNum = 100;

        void inTest() {
            System.out.println(num);
            System.out.println(sNum);
        }
    }
    public void usingInner() {
        inClass.inTest();
    }
    static class InStaticClass { //static클래스인 경우 static변수, static메소드 선언 가능
        int inNum = 100;
        static int sInNum = 200;

        void inTest() {
            System.out.println(inNum);
            System.out.println(sInNum);
            System.out.println(sNum);
        }
        static void sTest(){
           // System.out.println(inNum); //static 변수만 사용 가능
            System.out.println(sInNum);
            System.out.println(sNum);
        }
    }
}

public class Main {
    public static void main(String[] args){
        OutClass outClass = new OutClass();
        outClass.usingInner();

        OutClass.InClass myInClass = outClass.new InClass(); //자주 쓰이진 않음

        OutClass.InStaticClass isCLass = new OutClass.InStaticClass();
        isCLass.inTest();

        OutClass.InStaticClass.sTest(); //static 바로 불러오기 가능
    }
}
