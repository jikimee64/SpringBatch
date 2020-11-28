package com.Ramda;
import java.util.ArrayList;
import java.util.List;

public class CarExam {
    public static void main(String[] args){
        //Car객체를 만들어 cars에 넣는다
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("작은차",2,800,3));
        cars.add(new Car("작은차",2,800,3));
        cars.add(new Car("작은차",2,800,3));
        cars.add(new Car("작은차",2,800,3));

        printCar(cars, new CheckCarForBigAndNotExpensive());
    }

    public static void printCar(List<Car> cars, CheckCar tester){
        for(Car car : cars){
            if(tester.test(car)){
                System.out.println(car);
            }
        }
    }

    interface CheckCar{
        boolean test(Car car);
    }

    //내부클래스를 만들어서 사용
    static class CheckCarForBigAndNotExpensive implements CheckCar {
        @Override
        public boolean test(Car car) {
            return car.capacity >=4 && car.price < 2500;
        }
    }
}
