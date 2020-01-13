package org.jozin.generator.defaults;

import org.jozin.generator.Generator;

public class DefaultStringGenerator implements Generator<String> {
    private Generator<Character> charGen = new DefaultCharacterGenerator();
    private StringBuffer sb = new StringBuffer();

    // TODO; character sets? First name/Lastname CityName generators
    // should be separate.

    @Override
    public String generate() {
        return null;
    }

    public String generateUpToLength(int upTo) {
        int len = 1 + Rng.instance().nextInt(upTo);
        for (int i = 0; i < len; i++) {
            sb.append(charGen.generate());
        }
        String retVal = sb.toString();
        sb.setLength(0);
        return retVal;
    }
}