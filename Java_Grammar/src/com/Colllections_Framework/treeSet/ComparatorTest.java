package com.Colllections_Framework.treeSet;

import java.util.Comparator;
import java.util.TreeSet;

class MyCompare implements Comparator<String> {
    @Override
    public int compare(String s1, String s2){
        return s1.compareTo(s2);
    }
}


public class ComparatorTest {
    public static void main(String[] args) {

        TreeSet<String> treeSet = new TreeSet<String>(new MyCompare());
        //new MyComapre()를 적어줘야만 오버라이딩한것을 사용함
        treeSet.add("리순신1");
        treeSet.add("리순신2");
        treeSet.add("리순신3");

        for(String str : treeSet){
            System.out.println(str);
        }

    }
}

