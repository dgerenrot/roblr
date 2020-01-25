package org.roblr.generator.defaults;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.EnumSet;

import static org.junit.Assert.*;
import static org.roblr.generator.defaults.FieldAdjective.*;

public class FieldAdjectiveTest {

    @Test
    public void testPrimitivables() {
        testPrimitivableClass(Integer.class);
        testPrimitivableClass(Boolean.class);
        testPrimitivableClass(Byte.class);
        testPrimitivableClass(Short.class);
        testPrimitivableClass(Long.class);
        testPrimitivableClass(Double.class);
        testPrimitivableClass(Float.class);
        testPrimitivableClass(Character.class);

        testPrimitiveClass(char.class);
        testPrimitiveClass(int.class);
        testPrimitiveClass(long.class);
        testPrimitiveClass(short.class);
        testPrimitiveClass(byte.class);
        testPrimitiveClass(boolean.class);
        testPrimitiveClass(float.class);
        testPrimitiveClass(double.class);
    }

    @Test
    public void testPrimitives() {

        testPrimitiveClass(char.class);
        testPrimitiveClass(int.class);
        testPrimitiveClass(long.class);
        testPrimitiveClass(short.class);
        testPrimitiveClass(byte.class);
        testPrimitiveClass(boolean.class);
        testPrimitiveClass(float.class);
        testPrimitiveClass(double.class);
    }

    @Test
    public void testFinalPrimitive() throws NoSuchFieldException {
        Field f = TestClass.class.getField("finalPrimitive");
        assertEquals(EnumSet.of(FINAL, PRIMITIVE, PUBLIC, BUILTIN), FieldAdjective.getAdjectives(f));
    }
    @Test
    public void testNotBuiltinClass() throws NoSuchFieldException {
        Field f = TestClass.class.getField("publicNotBuiltin");
        assertEquals(EnumSet.of(PUBLIC), FieldAdjective.getAdjectives(f));

        f = TestClass.class.getDeclaredField("protectedNotBuiltin");
        assertEquals(EnumSet.of(PROTECTED), FieldAdjective.getAdjectives(f));
    }

    private void testPrimitivableClass(Class<?> clazz) {
        assertTrue(getAdjectives(clazz).contains(FieldAdjective.PRIMITIVABLE));
        assertFalse(getAdjectives(clazz).contains(FieldAdjective.PRIMITIVE));
        assertTrue(getAdjectives(clazz).contains(FieldAdjective.BUILTIN));
        assertTrue(getAdjectives(clazz).contains(FINAL));
    }

    private void testPrimitiveClass(Class<?> clazz) {
        assertFalse(getAdjectives(clazz).contains(FieldAdjective.PRIMITIVABLE));
        assertTrue(getAdjectives(clazz).contains(FieldAdjective.PRIMITIVE));
        assertTrue(getAdjectives(clazz).contains(FieldAdjective.BUILTIN));
        assertTrue(getAdjectives(clazz).contains(FINAL));
    }
}


class TestClass {
    public final int finalPrimitive = 42;
    public transient char transientPrimitive = 42;
    public volatile char volatilePrimitive = 42;

    public int intField;
    public short shortField;
    public long longField;
    public boolean booleanField;
    public char charField;

    public final Integer finalPrimitivable = 42;
    public transient Character transientPrimitivable;
    public volatile Character volatilePrimitivable;

    public Integer intObj;
    public Short shortObjField;
    public Long longObjField;
    public Boolean booleanObjField;
    public Character charObjField;

    public static final int finalStaticPrimitive = 42;
    public static transient char transientStaticPrimitive = 42;

    String stringField;
    Date dateField;

    public TestClass publicNotBuiltin;
    protected TestClass protectedNotBuiltin;

}