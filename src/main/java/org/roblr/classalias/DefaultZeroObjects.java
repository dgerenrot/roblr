package org.roblr.classalias;

import java.util.*;

public class DefaultZeroObjects {
    private Map<Class, Object> byClass;

    private ClassRegistry classRegistry;

    private boolean allEmptyMaps;

    private boolean allEmptyCollections;

    public DefaultZeroObjects() {
        byClass = new HashMap<>();
        initDefaults(new DefaultClassRegistryImpl());
    }

    public DefaultZeroObjects(ClassRegistry classRegistry) {
        byClass = new HashMap<>();
        initDefaults(classRegistry);
    }

    public void initDefaults(ClassRegistry classRegistry) {
        this.classRegistry = classRegistry != null ? classRegistry : new DefaultClassRegistryImpl();
        allEmptyMaps = true;
        allEmptyCollections = true;
        byClass.put(String.class, "");
        byClass.put(Integer.class, 0);
        byClass.put(Long.class, 0L);
        byClass.put(Short.class, (short) 0);
        byClass.put(Byte.class, (byte) 0);
        byClass.put(Float.class, 0.0f);
        byClass.put(Double.class, 0.0);
        byClass.put(Boolean.class, false);
        byClass.put(Character.class, (char) 0);
        byClass.put(Date.class, new Date(0L));
        byClass.put(List.class, Collections.emptyList());
        byClass.put(Set.class, Collections.emptyList());
        byClass.put(Map.class, Collections.emptyMap());
    }

    public Set<Class> keySet() {
        return byClass.keySet();
    }

    public void clear() {
        byClass.clear();
    }

    public <T> T byClass(Class<T> clazz) {
        Class parent = clazz;

        while (!byClass.containsKey(parent) && parent != Object.class) {
            parent = parent.getSuperclass();
        }

        return (T) byClass.get(clazz);
    }

    public <T> T byAlias(String alias) {
        Class<T> clazz = (Class<T>) classRegistry.get(alias);
        return (T) byClass.get(clazz);
    }

    public <T> T byClassName(String name) throws ClassNotFoundException {
        Class<T> clazz = (Class<T>) Class.forName(name);
        return (T) byClass.get(clazz);
    }

    public ClassRegistry getClassRegistry() {
        return classRegistry;
    }

    public void setClassRegistry(ClassRegistry classRegistry) {
        this.classRegistry = classRegistry;
    }

    public boolean isAllEmptyMaps() {
        return allEmptyMaps;
    }

    public void setAllEmptyMaps(boolean allEmptyMaps) {
        this.allEmptyMaps = allEmptyMaps;
    }

    public boolean isAllEmptyCollections() {
        return allEmptyCollections;
    }

    public void setAllEmptyCollections(boolean allEmptyCollections) {
        this.allEmptyCollections = allEmptyCollections;
    }

    public <T> void register(Class<T> clazz, Object obj) {
        if (!clazz.isAssignableFrom(obj.getClass())) {
            throw new IllegalArgumentException(clazz + " is not assignambe from " + obj.getClass());
        }

        byClass.put(clazz, obj);
    }
}
