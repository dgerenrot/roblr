package org.roblr.generator.defaults;

    import org.roblr.generator.Generator;

public class DefaultLongGenerator implements Generator<Long> {
  @Override
  public Long generate() {
    return Rng.instance().nextLong();
  }
}
