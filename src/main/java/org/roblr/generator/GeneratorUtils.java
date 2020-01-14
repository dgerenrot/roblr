package org.roblr.generator;

import com.google.gson.Gson;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;

public class GeneratorUtils {

  private static final Logger log = LoggerFactory.getLogger(GeneratorUtils.class);

  private Map<Class, Generator> zeroGenerators = new HashMap<Class, Generator>();
  private Map<Class, Generator> randomGenerators = new HashMap<Class, Generator>();

  public GeneratorUtils() {
  }

  public void registerZeroGenerator(Class clazz, Generator gen) {
    zeroGenerators.put(clazz, gen);
  }
  public void registerRandomGenerator(Class clazz, Generator gen) {
    randomGenerators.put(clazz, gen);
  }

  @SuppressWarnings("unchecked")
  private static final Generator NULL_GENERATOR = new Generator() {
    @Override
    public Object generate() {
      return null;
    }
  };

  public <T> Generator<T> nullGenerator() {
    return (Generator<T>) NULL_GENERATOR;
  }

  public static <T> Generator<T> sameRefGenerator(final T objToReturn) {
    return new Generator<T> () {

      @Override
      public T generate() {
        return objToReturn;
      }
    } ;
  }

  // TODO
  public <T> Generator<T> copyGenerator(final T objToReturn) {
    return deepCopyGenerator(objToReturn);
  }

  /**
   * This generator might not return a copy of exactly the same type!
   * This happens, e.g., for lists created from Arrays.asList(...), which is
   * of class Arrays$ArrayList. That class, however, is private to Arrays, so
   * you are fine, unless you are modifying the code inside Arrays.
   *
   * @param objToReturn
   * @param <T>
   * @return
   * @throws ClassCastException
   */
  public <T> Generator<T> deepCopyGenerator(final T objToReturn)
    throws ClassCastException {

    return new Generator<T> () {
      @Override
      public T generate() {
        if (objToReturn == null)
          return null;

        Class clazz = objToReturn.getClass();
        try {
          clazz.getConstructor();
          Serializable s = (Serializable) objToReturn;
          return (T) SerializationUtils.clone(s);
        } catch (NoSuchMethodException e) {
          log.debug("The class {} has no defaults constructor {}. Using gson serializer method", clazz);
        } catch (ClassCastException e) {
          log.debug("The class {} is not Serializable. Using gson serializer method", clazz);
        }

        Gson gson = new Gson();
        return (T) gson.fromJson(gson.toJson(objToReturn), Object.class);
      }
    };
  }


  // TODO: remove
  public static void main(String[] args) {
    Generator<String> nulls = new GeneratorUtils().nullGenerator();
    String s = nulls.generate();
    System.out.println(s);
    List<String> ls = (Arrays.asList("a", "BB", "ccc", "a"));

    Generator<List<String>> copier = new GeneratorUtils().deepCopyGenerator(ls);
    List<String> ls2 = copier.generate();

    System.out.println(Objects.toString(ls));
    System.out.println(Objects.toString(ls2));
  }



}
