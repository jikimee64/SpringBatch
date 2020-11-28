package com.Pattern.Singleton_pattern;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Company company1 = Company.getInstance();
        Company company2 = Company.getInstance();

        System.out.println(company1);
        System.out.println(company2);

        //Calendar calendar = new Calendar(); => X
        Calendar calendar2 = Calendar.getInstance();

    }
}
