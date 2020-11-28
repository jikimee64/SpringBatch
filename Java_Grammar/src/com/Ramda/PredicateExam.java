package com.Ramda;

public class PredicateExam {
    public static void main(String[] args) {
        Predicate<String> stringCompare = (String str)-> str.compareTo("abc")==0?true:false;
        System.out.println("Predicate test for abc="+ stringCompare.test("abc"));
    }
}