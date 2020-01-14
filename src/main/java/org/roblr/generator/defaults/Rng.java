package org.roblr.generator.defaults;

import java.util.Random;

public class Rng {
  private static Random RNG;

  public static Random instance() {
    if (RNG == null)
      RNG = new Random(System.currentTimeMillis());
    return RNG;
  }
}
