package org.jozin.generator.defaults;

    import org.jozin.generator.Generator;

public class DefaultLongGenerator implements Generator<Long> {
  @Override
  public Long generate() {
    return Rng.instance().nextLong();
  }
}
