package com.stream.test;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Member member = new Member("이순신", 10, 100);
        Member member2 = new Member("김유신", 50, 200);
        Member member3 = new Member("강감찬", 30, 300);

        List<Member> list = new ArrayList<Member>();
        list.add(member);
        list.add(member2);
        list.add(member3);


        list.stream().map( c-> c.getName()).forEach(s -> {
            System.out.println(s);
        });
        int total = list.stream().mapToInt( c-> c.getPrice()).sum();
        System.out.println(total);

        list.stream().filter(c->c.getAge() >= 20).map(c->c.getName()).sorted().forEach(s->{
            System.out.println(s);
        });



    }
}
