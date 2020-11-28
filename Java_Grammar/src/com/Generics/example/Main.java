package com.Generics.example;

public class Main {
    public static void main(String[] args) {

        //Powder 클래스 Material 상속 필요
        GenericPrinter<Powder> powderPrinter = new GenericPrinter<Powder>();
        Powder powder = new Powder();
        powderPrinter.setMaterial(powder);
        System.out.println(powderPrinter);

        //Plastic 클래스 Material 상속 필요
        GenericPrinter<Plastic> plasticPrinter = new GenericPrinter<Plastic>();
        Plastic plastic = new Plastic();
        plasticPrinter.setMaterial(plastic);
        System.out.println(plasticPrinter);

        powder.doPrinting();
        plastic.doPrinting();




    }
}
