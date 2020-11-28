package com.Generics;
interface Info{
    int getLevel();
}

class EmployeeInfo2 implements Info{
    public int rank;
    EmployeeInfo2(int rank){ this.rank = rank; }
    public int getLevel(){
        return this.rank;
    }
}

class Person2<T extends Info>{
    public T info;
    Person2(T info) {
        this.info = info;
        info.getLevel(); //extends 했기 때문에 사용가능
    }
    public <U> void printInfo(U info){
        System.out.println("info");
    }
}

public class GenericExtends {
    public static void main(String[] args) {
        Person2<EmployeeInfo2> p1 = new Person2<EmployeeInfo2>(new EmployeeInfo2(1));
        //Person2<String> p2 = new Person2<String>("부장");
        //extends에 의해 막힘
    }
}