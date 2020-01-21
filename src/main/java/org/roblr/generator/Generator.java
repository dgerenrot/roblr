package org.roblr.generator;

import java.lang.reflect.InvocationTargetException;

public interface Generator<T> {
  T generate();
  <S> S generate(Class<S> clazz) throws ReflectiveOperationException;
}
