package com.Interface;

public class CompleteCalc extends Calculator{

    @Override
    public int times(int num1, int num2) {
        return num1 * num2;
    }

    @Override
    public int divide(int num1, int num2) {
        if(num2 == 0)
            return ERROR;
         else
            return num1 / num2;
    }

    public void showInfo() {
        System.out.println("아잉 기모띠");
    }
//    @Override
//    public void description() {
//        System.out.println("재정의랑께.");
//    }


}
