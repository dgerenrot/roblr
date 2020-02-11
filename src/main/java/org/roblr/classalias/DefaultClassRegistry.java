package org.roblr.classalias;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The general intent is for the registry to contain entity types for
 * objects under test. We therefore disallow arrays, collections, etc.
 *
 */
public class DefaultClassRegistry implements ClassRegistry {
    private Map<String, Class> registry = new HashMap<>();
    private Map<String, String> aliases = new HashMap<>();

    @Override
    public void putClassName(String alias, String className) throws ClassNotFoundException {
        if (registry.containsKey(alias))
            throw new AliasAlreadyRegisteredException(null, alias);

        if (aliases.containsKey(className))
            throw new ClassAlreadyRegisteredException(null, className);

        Class clazz = Class.forName(className);
        validateClass(clazz);

        registry.put(alias, clazz);
        aliases.put(clazz.getName(), alias);
    }

    @Override
    public void put(String alias, Class clazz) {
        validateClass(clazz);

        if (registry.containsKey(alias))
            throw new AliasAlreadyRegisteredException(null, alias);

        String className = clazz.getName();

        if (aliases.containsKey(className))
            throw new ClassAlreadyRegisteredException(null, className);

        registry.put(alias, clazz);
        aliases.put(clazz.getName(), alias);
    }

    @Override
    public Class<?> get(String alias) {
        return registry.get(alias);
    }

    @Override
    public String getAlias(Class<?> clazz) {
        return aliases.get(clazz.getName());
    }

    @Override
    public String getAlias(String className) {
        return aliases.get(className);
    }

    private void validateClass(Class clazz) {
        if (clazz.getName().startsWith("["))
            throw new IllegalArgumentException("Array types are not allowed: " + clazz.getName());

        if (Collection.class.isAssignableFrom(clazz))
            throw new IllegalArgumentException("Collection types are not allowed: " + clazz.getName());

        if (Map.class.isAssignableFrom(clazz))
            throw new IllegalArgumentException("Map types are not allowed: " + clazz.getName());

        if (clazz.getName().startsWith("java."))
            throw new IllegalArgumentException("java.* are not allowed: " + clazz.getName());
    }
}
