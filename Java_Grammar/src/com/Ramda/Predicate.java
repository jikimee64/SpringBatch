package com.Ramda;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
