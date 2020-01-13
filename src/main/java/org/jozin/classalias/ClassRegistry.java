package org.jozin.classalias;

public interface ClassRegistry {
    void register(String alias, String className) throws ClassNotFoundException;
    void register(String alias, Class<?> clazz);
    Class<?> get(String alias);
    String getAlias(Class<?> clazz);
    String getAlias(String className);
}
