package org.jozin.generator.defaults;

import org.jozin.generator.Generator;

public class DefaultShortGenerator implements Generator<Short> {
  @Override
  public Short generate() {
    return (short) Rng.instance().nextInt();
  }
}
