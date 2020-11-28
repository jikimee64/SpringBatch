package com.Interface.Multiple_implements;

public interface Sell {
    void sell();

    default void order() {
        System.out.println("판넬 주문");
    }

}
