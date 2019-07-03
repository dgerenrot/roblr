package org.jozin.generator.defaults;

import org.jozin.generator.Generator;

public class DefaultBooleanGenerator implements Generator<Boolean> {
  @Override
  public Boolean generate() {
    return Rng.instance().nextInt() % 2 == 1;
  }
}
