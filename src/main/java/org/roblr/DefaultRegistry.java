package org.roblr;

import java.util.HashMap;
import java.util.Map;

public class DefaultRegistry<T> implements Registry<T> {
    private Map<String, T> registry = new HashMap<>();

    @Override
    public T get(String id) {
        return registry.get(id);
    }

    @Override
    public void put(String id, T val) {
        registry.put(id, val);
    }
}
