package com.Pattern.Decorator_pattern;

public class Mocha extends Decorator {

    public Mocha(Coffee coffee) {
        super(coffee);
    }

    public void brewing() {
        super.brewing();
        System.out.println("Mocha adding");

    }
}
