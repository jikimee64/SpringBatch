package com.Ramda;
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
