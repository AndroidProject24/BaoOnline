package com.toan_itc.data.repository.time;

public interface CachingStrategy<T> {
    boolean isValid(T value);
}
