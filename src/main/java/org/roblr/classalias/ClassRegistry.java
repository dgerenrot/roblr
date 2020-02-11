package org.roblr.classalias;

import org.roblr.Registry;

public interface ClassRegistry extends Registry<Class> {
    void putClassName(String alias, String className) throws ClassNotFoundException;
    void put(String alias, Class<?> clazz);
    Class<?> get(String alias);
    String getAlias(Class<?> clazz);
    String getAlias(String className);
}
