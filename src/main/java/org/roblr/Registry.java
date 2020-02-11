package org.roblr;

public interface Registry<T> {
    T get(String id);
    void put(String id, T val);
}
