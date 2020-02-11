package org.roblr.builder;

import java.util.HashMap;
import java.util.Map;

public class DefaultObjectSpecRegistry implements ObjectSpecRegistry {
    private Map<String, ObjectSpec> registry = new HashMap<>();

    @Override
    public ObjectSpec get(String id) {
        return registry.get(id);
    }

    @Override
    public void put(String id, ObjectSpec objectSpec) {
        registry.put(id, objectSpec);
    }
}
