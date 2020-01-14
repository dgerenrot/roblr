package org.roblr.generator.defaults;

import org.roblr.Rng;
import org.roblr.generator.Generator;

public class DefaultIntegerGenerator implements Generator<Integer> {
  @Override
  public Integer generate() {
    return Rng.instance().nextInt();
  }
}
