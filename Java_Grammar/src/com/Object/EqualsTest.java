package com.Object;

public class EqualsTest {
    public static void main(String[] args) {


        Integer i1 = new Integer(100);
        Integer i2 = new Integer(100);

        System.out.println(i1.equals(i2));
        System.out.println(i1.hashCode());
        System.out.println(i1.hashCode());

        System.out.println(System.identityHashCode(i1)); //실제 메모리 주소값 출력
        System.out.println(System.identityHashCode(i2)); //실제 메모리 주소값 출력
    }
}
