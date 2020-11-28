package com.Ramda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsumerExam {
    public static void main(String[] args) {
        List list = new ArrayList(10);
        list.add(1);
        list.add(2);
        Collections.sort(list,(Integer a, Integer b)->a>b?0:-1);
    }
}
