package org.roblr.generator.defaults;

import org.roblr.generator.*;

import java.lang.reflect.Method;

/**
 * Creates an object with all non-primitive fields initialized to null;
 */
public class DefaultGenerator implements Generator<Object> {

    private SetterFilterStrategy setterFilterStrategy;
    private FieldResolver fieldResolver;

    public DefaultGenerator() {
        this.setterFilterStrategy = new DefaultSetterFilterStrategyImpl();
        this.fieldResolver = new DefaultFieldResolverImpl();
    }

    @Override
    public Object generate() {
        return new Object();
    }

    @Override
    public <S> S generate(Class<S> clazz) throws ReflectiveOperationException {
        S retVal = clazz.getDeclaredConstructor().newInstance();

        for (Method method : clazz.getDeclaredMethods()) {
            if (setterFilterStrategy.shouldGenerateArgs(method, null, null)) {
                Generator generator = fieldResolver.getFieldGenerator(method, null);
                for (Class<?> paramClazz : method.getParameterTypes()) {
                    Object arg = generator.generate(paramClazz);
                    method.invoke(retVal, arg);
                }
            }
        }

        return retVal;
    }
}
