package com.Pattern.Singleton_pattern;

public class Company {

    //유일한 인스턴스
    private static Company instance = new Company();

   private Company() {}

    //외부에서 인스턴스의 생성과 상관없이 로드하기 위해 static 선언
    public static Company getInstance() {
        if(instance == null) {
            instance = new Company();
        }
        return instance;
    }

}
