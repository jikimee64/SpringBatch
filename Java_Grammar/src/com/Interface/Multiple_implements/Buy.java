package com.Interface.Multiple_implements;

public interface Buy {
    void buy();

    default void order() {
        System.out.println("구매 주문");
    }
}
