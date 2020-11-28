package com.Deepclone;

//Cloneable 인터페이스 구현 -> clone()메소드 재정의
public class Family implements Cloneable {
    String name;

    public Family() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
