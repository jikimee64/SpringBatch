package com.Generics.example;

//Material에서 상속받은 애들만 제네릭으로 사용할 수 있음
public class GenericPrinter<T extends Material> {

    private T material;

    public T getMaterial() {
        return material;
    }

    public void setMaterial(T material) {
        this.material = material;
    }

    public String toString() {
        return material.toString();
    }

    public void printing(){
        material.doPrinting();
    }
}
