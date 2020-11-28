package com.Ramda;

public class Car {
    public String name;
    public int capacity;
    public int price;
    public int age;

    //각각의 필드를 생성자에서 받아서 초기화화
   public Car(String name, int capacity, int price, int age){
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.age = age;
    }
}
