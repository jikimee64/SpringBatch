package com.Ramda;

import java.util.ArrayList;
import java.util.List;

public class CarExam3 {
    public static void main(String[] args) {
        //Car객체를 만들어 cars에 넣는다
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("작은차", 2, 800, 3));
        cars.add(new Car("작은차", 2, 800, 3));
        cars.add(new Car("작은차", 2, 800, 3));
        cars.add(new Car("작은차", 2, 800, 3));

        CarExam3 carExam3 = new CarExam3();
        carExam3.printCar(cars,
                //인터페이스 CheckCar의 test메소드에 대응하는 람다를 만든다
                (Car car) -> {
                    return car.capacity >= 4 && car.price < 2500;
                }
        );
    }

    public static void printCar(List<Car> cars, CheckCar tester) {
        for (Car car : cars) {
            if (tester.test(car)) {
                System.out.println(car);
            }
        }
    }

    interface CheckCar {
        boolean test(Car car);
    }
}
