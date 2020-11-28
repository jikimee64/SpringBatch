package com.Object;

class Book{
    String title;
    String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}

public class ToStringTest { //extends Object는 컴파일러가 자동으로 붙여줌(모든 클래스..)
    public static void main(String[] args) throws CloneNotSupportedException {
        Book book = new Book("토지", "박경리");

        System.out.println(book);

        String str = new String("토지");
        System.out.println(str.toString());
    }
}
