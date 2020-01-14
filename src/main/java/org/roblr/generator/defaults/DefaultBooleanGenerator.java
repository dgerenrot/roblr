package org.roblr.generator.defaults;

import org.roblr.generator.Generator;

public class DefaultBooleanGenerator implements Generator<Boolean> {
  @Override
  public Boolean generate() {
    return Rng.instance().nextInt() % 2 == 1;
  }
}
