package org.roblr.generator.defaults;

    import org.roblr.Rng;
    import org.roblr.exceptions.NotImplementedException;
    import org.roblr.generator.Generator;

public class DefaultLongGenerator implements Generator<Long> {
  @Override
  public Long generate() {
    return Rng.instance().nextLong();
  }

    @Override
    public <S> S generate(Class<S> clazz) {
        throw new NotImplementedException();
    }
}
