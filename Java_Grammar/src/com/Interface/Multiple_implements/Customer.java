package com.Interface.Multiple_implements;

public class Customer implements Buy, Sell {

    @Override
    public void buy() {
        System.out.println("customer buy");
    }

    @Override
    public void sell() {
        System.out.println("customer sell");
    }

    public void order() {
        //Buy.super.order(); //인터페이스를 정하여 디폴트 메서드를 불러오거나 재정의
        System.out.println("customer order");
    }

    public void sayHello(){
        System.out.println("hello");
    }


}
