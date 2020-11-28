package com.Interface;

public interface Calc {

    public final static double PI = 3.14;
    public final static int ERROR = -99999;

    int add(int num1, int num2);
    int substract(int num1, int num2);
    int times(int num1, int num2);
    int divide(int num1, int num2);

    default void description() {
        System.out.println("정수 계산기를 구현합니다.");
        myMethod();
    }
    //인터페이스 타입으로 가져다 사용할 수 있음
    static int total(int[] arr) {
        int total = 0;
        for(int i : arr){
            total += i;
        }
        mystaticMethod();
        return total;
    }

    private void myMethod() {
        System.out.println("private method");
    }

    private static void mystaticMethod() {
        System.out.println("private static method");
    }


}
