package org.roblr.generator.defaults;

import org.roblr.Rng;
import org.roblr.exceptions.NotImplementedException;
import org.roblr.generator.Generator;

import java.nio.charset.Charset;


public class DefaultCharacterGenerator implements Generator<Character> {

  private static final String LATIN_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String LATIN_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
  private static final String DEC_DIGIT = "0123456789";
  private static final String OCT_DIGIT = "01234567";
  private static final String HEX_DIGIT = "0123456789ABCDEF";
  private static final String HEX_DIGIT_LOWERCASE = "0123456789ABCDEF";
  private static final String PUNCTUATION_MARK = ".,;:'\"?!-()";
  private static final String USKBD_NOT_DIGIT_LETTER_BLANK = "`~!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
  public static final String US_ASCII = "US-ASCII";


  private Charset charset;

  public DefaultCharacterGenerator() {
    charset = Charset.isSupported(US_ASCII) ? Charset.forName(US_ASCII) : Charset.defaultCharset();
  }

  public DefaultCharacterGenerator(String charsetName) {
    charset = Charset.forName(charsetName);
  }

  @Override
  public Character generate() {
    if (!Charset.forName(US_ASCII).aliases().contains(charset.name())) {
      throw new NotImplementedException("So far, only US-ASCII character set is supported");
    }
    return (char) Rng.instance().nextInt();
  }

  public Character latinUppercase() {
    return LATIN_UPPERCASE.charAt(Rng.instance().nextInt(LATIN_UPPERCASE.length()));
  }
  public Character latinLowercase() {
    return LATIN_LOWERCASE.charAt(Rng.instance().nextInt(LATIN_UPPERCASE.length()));
  }
  public Character latin() {
    return Rng.instance().nextInt(2) > 0 ? latinLowercase() : latinUppercase();
  }
  public Character decDigit() {
    return DEC_DIGIT.charAt(Rng.instance().nextInt(DEC_DIGIT.length()));
  }
  public Character octDigit() {
    return OCT_DIGIT.charAt(Rng.instance().nextInt(OCT_DIGIT.length()));
  }
  public Character hexDigit() {
    return HEX_DIGIT.charAt(Rng.instance().nextInt(HEX_DIGIT.length()));
  }
  public Character hexDigitLowerCase() {
    return Character.toLowerCase(hexDigit());
  }
  public Character punctmark() {
    return PUNCTUATION_MARK.charAt(Rng.instance().nextInt(PUNCTUATION_MARK.length()));
  }
  public Character usKbdNotDigitLetterBlank() {
    return USKBD_NOT_DIGIT_LETTER_BLANK
            .charAt(Rng.instance().nextInt(USKBD_NOT_DIGIT_LETTER_BLANK.length()));
  }

  @Override
  public <S> S generate(Class<S> clazz) {
    throw new NotImplementedException();
  }
}
