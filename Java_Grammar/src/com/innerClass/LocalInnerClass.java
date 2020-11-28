package com.innerClass;

class Outer {

    int outNum = 100;
    static int sNum = 200;

    //i랑 num은 매소드의 지역변수,
    Runnable getRunnable(int i) {
        int num = 100;
        return new Runnable() { //익명 이너 클래스
            @Override
            public void run() {
                System.out.println(outNum);
                System.out.println(Outer.sNum);
            }
        };
    }

    Runnable runner = new Runnable() {
        @Override
        public void run() {

        }
    };

}

    public class LocalInnerClass {
        public static void main(String[] args) {
            Outer outer = new Outer();
            Runnable runnable = outer.getRunnable(50); //이 메서드는 호출되고 끝 -> 메서드안의 지역변수는 참조만가능(상수역할), 수정은 불가

            runnable.run(); //run은 언제든지 호출 가능
        }
    }
