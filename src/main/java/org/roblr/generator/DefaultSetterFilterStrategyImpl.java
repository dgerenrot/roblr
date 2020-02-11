package org.roblr.generator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class DefaultSetterFilterStrategyImpl implements SetterFilterStrategy {
    private static final String SETTER_PREFIX = "set";

    @Override
    public boolean shouldGenerateArgs(Method method, String objId, Object obj) {
        return method != null
                && (method.getModifiers() & Modifier.PUBLIC) != 0
                && method.getName().startsWith(SETTER_PREFIX)
                && method.getParameterCount() == 1;
    }
}
