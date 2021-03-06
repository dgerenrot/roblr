package org.roblr.generator.defaults;

import org.roblr.Rng;
import org.roblr.exceptions.NotImplementedException;
import org.roblr.generator.Generator;

public class DefaultIntegerGenerator implements Generator<Integer> {
  @Override
  public Integer generate() {
    return Rng.instance().nextInt();
  }

  @Override
  public <S> S generate(Class<S> clazz) {
    throw new NotImplementedException();
  }
}
