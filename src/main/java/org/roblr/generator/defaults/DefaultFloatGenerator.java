package org.roblr.generator.defaults;

import org.roblr.Rng;
import org.roblr.exceptions.NotImplementedException;
import org.roblr.generator.Generator;

public class DefaultFloatGenerator implements Generator<Float> {

    /**
     *
     * @return a uniformly distributed float
     *         from 0 (inclusive) to 1 (exclusive).
     */
    @Override
    public Float generate() {
        return Rng.instance().nextFloat();
    }

    /**
     * Not Implemented
     * @param clazz
     * @param <S>
     * @return
     */
    @Override
    public <S> S generate(Class<S> clazz) {
        throw new NotImplementedException();
    }
}