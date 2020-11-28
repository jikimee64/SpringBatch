package com.IO.Seriallize;

import java.io.*;

class Person implements Serializable {
    String name;
    transient String job; //직렬화 불가능

    @Override
    public String toString() {
        return name + ", " + job;
    }

    public Person(String name, String job) {
        this.name = name;
        this.job = job;
    }
}

public class Main {
    public static void main(String[] args) {
        Person personLee = new Person("이순신", "엔지니어");
        Person personKim = new Person("강감찬", "학생");

        try(FileOutputStream fos = new FileOutputStream("serial.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(personLee);
            oos.writeObject(personKim);
        }catch(IOException e){
            e.printStackTrace();
        }

        try(FileInputStream fis = new FileInputStream("serial.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);) {
            Person p1 = (Person)ois.readObject();
            //Person p2 = (Person)ois.readObject();

            System.out.println(p1);

        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
