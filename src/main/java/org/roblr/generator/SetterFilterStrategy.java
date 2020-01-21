package org.roblr.generator;

import java.lang.reflect.Method;

public interface SetterFilterStrategy {
    boolean shouldGenerateArgs(Method method, String objId, Object obj);
}
