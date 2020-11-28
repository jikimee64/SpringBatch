package com.Deepclone;
import org.apache.http.client.utils.CloneUtils;

public class Student implements Cloneable {
        public static void main(String[] args) throws CloneNotSupportedException {

        Family f1 = new Family();
        Family f2 = (Family) f1.clone(); //방법1
        Family two = (Family) CloneUtils.clone(f1); //방법2, try,catch가 구현된 유틸리티 메소드
        Family f3 = f1; //얕은 복사

        System.out.println(f1.hashCode());
        System.out.println(f2.hashCode());
        System.out.println(f3.hashCode());
    }
}
