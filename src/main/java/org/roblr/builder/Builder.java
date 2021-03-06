package org.roblr.builder;

import org.roblr.generator.Generator;

public interface Builder<T> {
  T build();
  Builder<T> with(String prop, String constraintSpec);
  Builder<T> with(String prop, Generator<T> generatorSpec);
  Builder<T> buildSub(String prop);
  Builder<T> back(String prop, Generator<T> generatorSpec);
}
