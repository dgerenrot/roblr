package org.roblr.generator.defaults;

import org.roblr.generator.Generator;

public class DefaultShortGenerator implements Generator<Short> {
  @Override
  public Short generate() {
    return (short) Rng.instance().nextInt();
  }
}
