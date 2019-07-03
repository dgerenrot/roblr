package org.jozin.generator.defaults;

import org.jozin.generator.Generator;

public class DefaultIntegerGenerator implements Generator<Integer> {
  @Override
  public Integer generate() {
    return Rng.instance().nextInt();
  }
}
