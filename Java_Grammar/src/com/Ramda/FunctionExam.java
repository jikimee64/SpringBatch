package com.Ramda;

public class FunctionExam {
    public static void main(String[] args) {
        Function<String,Integer> intFunctionLambda = (String str)-> str.compareTo("abc")==0?1:0;
        System.out.println("intFunctionLambda test for abc="+ intFunctionLambda.apply("abc"));

        Function<String,Float> floatFunctionLambda = (String str)-> str.compareTo("abc")==0?(float)1.0:(float)0.0;
        System.out.println("floatFunctionLambda test for abc="+ floatFunctionLambda.apply("abc"));
    }
}
