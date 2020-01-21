package org.roblr.generator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface FieldResolver {
    Generator<?> getFieldGenerator(Method method, String objId);
    Generator<?> getFieldGenerator(Field field, String objId);
}
