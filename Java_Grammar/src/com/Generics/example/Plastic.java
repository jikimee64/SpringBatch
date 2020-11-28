package com.Generics.example;

public class Plastic extends Material{

    public String toString() {
        return "재료는 Plastic 입니다.";
    }

    @Override
    public void doPrinting() {
        System.out.println("Plastic으로 프린팅");
    }
}
