package org.roblr.builder;

import org.roblr.generator.Generator;

import java.util.Deque;

public class DefaultBuilder<T> implements Builder<T> {

  private Deque<StackEntry> stack;
  private Class<T> clazz;

  public DefaultBuilder(Class<T> clazz) {
    this.clazz = clazz;
  }

  private static class StackEntry {
    private String prop;
    private Object obj;

    public StackEntry(String prop, Object obj) {
      this.prop = prop;
      this.obj = obj;
    }

    public String getProp() {
      return prop;
    }

    public Object getObj() {
      return obj;
    }
  }

  public T build() {
    return null;
  }

  public Builder<T> with(String prop, String constraintSpec) {
    return null;
  }

  public Builder<T> with(String prop, Generator<T> generatorSpec) {
    return null;
  }

  public Builder<T> buildSub(String prop) {
    return null;
  }

  public Builder<T> back(String prop, Generator<T> generatorSpec) {
    return null;
  }
}
