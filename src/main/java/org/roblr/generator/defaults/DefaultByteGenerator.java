package org.roblr.generator.defaults;

    import org.roblr.Rng;
    import org.roblr.generator.Generator;

public class DefaultByteGenerator implements Generator<Byte> {

  @Override
  public Byte generate() {
    return (byte) Rng.instance().nextInt();
  }
}
