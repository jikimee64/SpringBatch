package com.Ramda;

public class CompareExam {
    public static void exec(Compare compare) {
        int k = 10;
        int m = 20;
        int value = compare.compareTo(k, m);
        System.out.println(value);
    }

    public static void main(String[] args){
        exec((i,j) -> {
            return i - j;
        });
    }

}