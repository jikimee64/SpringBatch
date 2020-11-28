package com.Generics;

class EmployeeInfo{
    public int rank;
    EmployeeInfo(int rank){ this.rank = rank; }
}

class Person<T, S>{
    public T info;
    public S id;
    Person(T info, S id) {
        this.info = info;
        this.id = id;
    }
    public <U> void printInfo(U info){
        System.out.println("info");
    }
}

public class GenericDemo {
    public static void main(String[] args) {
        EmployeeInfo e = new EmployeeInfo(1);
        Integer id = new Integer(1);
        Person<EmployeeInfo, Integer> p1 = new Person<EmployeeInfo, Integer>(e, id);
        p1.<EmployeeInfo>printInfo(e);
    }
}