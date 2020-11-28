package com.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayListStreamTest {
    public static void main(String[] args){
        List<String> sList = new ArrayList<String>();
        sList.add("Tomas");
        sList.add("Tomas2");
        sList.add("Tomas34");

        Stream<String> stream = sList.stream(); //Collections 경우 스트림 메소드 즉시 사용 가능
        stream.forEach(s -> System.out.print(s + " "));
        System.out.println();

        sList.stream().sorted().forEach(s -> System.out.println(s));
        System.out.println();
        sList.stream().map(s->s.length()).forEach(n -> System.out.println(n));



    }
}
