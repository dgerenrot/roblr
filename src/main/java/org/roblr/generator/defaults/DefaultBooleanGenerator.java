package org.roblr.generator.defaults;

import org.roblr.Rng;
import org.roblr.exceptions.NotImplementedException;
import org.roblr.generator.Generator;

public class DefaultBooleanGenerator implements Generator<Boolean> {
  @Override
  public Boolean generate() {
    return Rng.instance().nextInt() % 2 == 1;
  }

  @Override
  public <S> S generate(Class<S> clazz) {
    throw new NotImplementedException();
  }
}
