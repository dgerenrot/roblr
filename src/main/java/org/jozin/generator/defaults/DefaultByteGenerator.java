package org.jozin.generator.defaults;

    import org.jozin.generator.Generator;

public class DefaultByteGenerator implements Generator<Byte> {

  @Override
  public Byte generate() {
    return (byte) Rng.instance().nextInt();
  }
}
