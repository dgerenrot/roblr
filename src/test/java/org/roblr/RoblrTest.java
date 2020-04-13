package org.roblr;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.roblr.builder.ObjectSpecBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RoblrTest {

    private Roblr roblr = new Roblr();

    @Before
    public void init() {
        roblr = new Roblr();
        roblr.setClassAlias("A", A.class);
        roblr.setClassAlias("B", B.class);
        roblr.setClassAlias("C", C.class);
    }

    @Test
    public void testBuildFromSpec() throws ReflectiveOperationException {
        ObjectSpecBuilder osb = new ObjectSpecBuilder(roblr);
        assertEquals("Spec for aId should not exist", null, roblr.getObjectSpecRegistry().get("aId"));
        assertEquals("Object for aId should not exist", null, roblr.getObjectRegistry().get("aId"));
        osb.set("aId", "A");
        assertNotEquals("Spec for aId should exist", null, roblr.getObjectSpecRegistry().get("aId"));
        assertEquals("Object for aId should not exist", null, roblr.getObjectRegistry().get("aId"));
        roblr.buildFromSpec("aId");
        assertNotEquals("Object for aId should exist", null, roblr.getObjectRegistry().get("aId"));
        assertEquals("Object for aId should exist", A.class, roblr.getObjectRegistry().get("aId").getClass());
    }

    private static class A {
        private int x;
        private String s;
        private B bTop;
        private B bBot;

        public A() {
        }

        public A(int x, String s, B bTop, B bBot) {
            this.x = x;
            this.s = s;
            this.bTop = bTop;
            this.bBot = bBot;
        }

        public int getX() {
            return x;
        }

        public void setX(String s) {
            this.s = s;
        }

        public B getbTop() {
            return bTop;
        }

        public void setbTop(B bTop) {
            this.bTop = bTop;
        }

        public B getbBot() {
            return bBot;
        }

        public void setbBot(B bBot) {
            this.bBot = bBot;
        }

        public void setX(int x) {
            this.x = x;
        }

    }

    private static class B {
        private A a;
        private C cN;
        private C cS;

        public B() {}

        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }

        public C getcN() {
            return cN;
        }

        public void setcN(C cN) {
            this.cN = cN;
        }

        public C getcS() {
            return cS;
        }

        public void setcS(C cS) {
            this.cS = cS;
        }

    }

    private static class C {
        private C next;

        public C getNext() {
            return next;
        }

        public void setNext(C next) {
            this.next = next;
        }
    }
}