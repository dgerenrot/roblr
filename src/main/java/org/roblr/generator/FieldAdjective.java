package org.roblr.generator;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Hard-coded set of adjectives?
 */
public enum FieldAdjective {

    PUBLIC(Modifier.PUBLIC),
    PRIVATE(Modifier.PRIVATE),
    PROTECTED(Modifier.PROTECTED),
    STATIC(Modifier.STATIC),
    VOLATILE(Modifier.VOLATILE),
    TRANSIENT(Modifier.TRANSIENT),
    FINAL(Modifier.PRIVATE),
    PRIMITIVE(null),
    PRIMITIVABLE(null),
    BUILTIN(null);

    private Integer modifier;
    private static final Set<String> BUILTIN_CLASS_NAMES
            = new HashSet<>(Arrays.asList("java.lang.String", "java.lang.Date"));
    private static final Set<String> PRIMITIVABLE_CLASS_NAMES
            = new HashSet<>(Arrays.asList("java.lang.Integer",
                                          "java.lang.Long",
                                          "java.lang.Short",
                                          "java.lang.Byte",
                                          "java.lang.Character",
                                          "java.lang.Boolean",
                                          "java.lang.Double",
                                          "java.lang.Float"));

    FieldAdjective(Integer modifier) {
        this.modifier = modifier;
    }

    public static Set<FieldAdjective> getAdjectives(Class<?> clazz) {
        Set<FieldAdjective> retVal = EnumSet.noneOf(FieldAdjective.class);
        if (clazz.isPrimitive()) {
            retVal.add(PRIMITIVE);
            retVal.add(BUILTIN);
        } else if (PRIMITIVABLE_CLASS_NAMES.contains(clazz.getName())) {
            retVal.add(PRIMITIVABLE);
            retVal.add(BUILTIN);
        } else if (BUILTIN_CLASS_NAMES.contains(clazz.getName())) {
            retVal.add(BUILTIN);
        }
        if ((clazz.getModifiers() & Modifier.FINAL) != 0) {
            retVal.add(FINAL);
        }

        return retVal;
    }

    public static Set<FieldAdjective> getAdjectives(Field field) {
        Set<FieldAdjective> retVal = getAdjectives(field.getType());
        if ((field.getModifiers() & Modifier.FINAL) != 0) {
            retVal.add(FINAL);
        }
        if ((field.getModifiers() & Modifier.STATIC) != 0) {
            retVal.add(STATIC);
        }
        if ((field.getModifiers() & Modifier.TRANSIENT) != 0) {
            retVal.add(TRANSIENT);
        }
        if ((field.getModifiers() & Modifier.VOLATILE) != 0) {
            retVal.add(VOLATILE);
        }
        if ((field.getModifiers() & Modifier.PUBLIC) != 0) {
            retVal.add(PUBLIC);
        }
        if ((field.getModifiers() & Modifier.PROTECTED) != 0) {
            retVal.add(PROTECTED);
        }
        if ((field.getModifiers() & Modifier.PRIVATE) != 0) {
            retVal.add(PRIVATE);
        }

        return retVal;

    }

    public Integer getModifier() {
        return modifier;
    }
}
