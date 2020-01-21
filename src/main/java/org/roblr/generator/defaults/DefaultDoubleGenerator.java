package org.roblr.generator.defaults;

import org.roblr.Rng;
import org.roblr.exceptions.NotImplementedException;
import org.roblr.generator.Generator;

public class DefaultDoubleGenerator implements Generator<Double> {

    /**
     *
     * @return a uniformly distributed double
     *         from 0 (inclusive) to 1 (exclusive).
     */
    @Override
    public Double generate() {
        return Rng.instance().nextDouble();
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