package org.roblr.builder;

import java.util.HashMap;
import java.util.Map;

public class DefaultObjectRegistry implements ObjectRegistry {
    private Map<String, ObjectSpec> registry = new HashMap<>();

    @Override
    public ObjectSpec getById(String id) {
        return registry.get(id);
    }

    @Override
    public void add(String id, ObjectSpec objectSpec) {
        registry.put(id, objectSpec);
    }
}
