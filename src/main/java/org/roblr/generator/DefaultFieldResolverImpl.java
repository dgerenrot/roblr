package org.roblr.generator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DefaultFieldResolverImpl implements FieldResolver {



    @Override
    public Generator<?> getFieldGenerator(Method method, String objId) {
        return null;
    }

    @Override
    public Generator<?> getFieldGenerator(Field field, String objId) {
        return null;
    }
}
