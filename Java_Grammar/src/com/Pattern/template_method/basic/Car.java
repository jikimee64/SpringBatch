package com.Pattern.template_method.basic;

public abstract class Car {

    public abstract void drive();
    public abstract void stop();

    public void startCar() {
        System.out.println("시동을 켭니다.");
    }

    public void turnOff() {
        System.out.println("시동을 끕니다.");
    }

    //이 순서는 변하면 안되서 final을 붙힘(재정의 막음)
    final public void run() {
        startCar();
        drive();
        stop();
        turnOff();
    }

}
