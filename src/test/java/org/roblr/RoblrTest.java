package org.roblr;

import org.junit.Before;
import org.junit.Test;
import org.roblr.builder.ObjectSpecBuilder;

import static org.junit.Assert.*;

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
    public void testBuildSingleObjectFromSpec() throws ReflectiveOperationException {
        ObjectSpecBuilder osb = new ObjectSpecBuilder(roblr);

        assertNull("Spec for aId should not exist", roblr.getObjectSpecRegistry().get("aId"));
        assertNull("Object for aId should not exist", roblr.getObjectRegistry().get("aId"));

        osb.set("aId", "A");

        assertNotNull("Spec for aId should exist", roblr.getObjectSpecRegistry().get("aId"));
        assertNull("Object for aId should not exist", roblr.getObjectRegistry().get("aId"));

        roblr.buildFromSpec("aId");

        assertNotNull("Object for aId should exist", roblr.getObjectRegistry().get("aId"));
        assertEquals("Object for aId should exist", A.class, roblr.getObjectRegistry().get("aId").getClass());
    }

    @Test
    public void testBuildTwoRelatedFromSpec() throws ReflectiveOperationException {
        ObjectSpecBuilder osb = new ObjectSpecBuilder(roblr);

        assertNull("Spec for aId should not exist", roblr.getObjectSpecRegistry().get("aId"));
        assertNull("Object for aId should not exist", roblr.getObjectRegistry().get("aId"));
        assertNull("Spec for bTopId should not exist", roblr.getObjectSpecRegistry().get("bTopId"));
        assertNull("Object for bTopId should not exist", roblr.getObjectRegistry().get("bTopId"));

        ObjectSpecBuilder osb2 =  osb.set("aId", "A").rel("bTop").set("bTopId", "B");

        assertNotNull("Spec for aId should exist", roblr.getObjectSpecRegistry().get("aId"));
        assertNull("Object for aId should not exist", roblr.getObjectRegistry().get("aId"));
        assertNotNull("Spec for bTopId should exist", roblr.getObjectSpecRegistry().get("bTopId"));
        assertNull("Object for bTopId should not exist", roblr.getObjectRegistry().get("bTopId"));
        assertEquals("bTop relation aId -> bTopId should exist", "bTopId",
                     roblr.getObjectSpecRegistry().get("aId").getRelatedObjId("bTop"));

        roblr.buildFromSpec("aId");

        assertNotNull("Object for aId should exist", roblr.getObjectRegistry().get("aId"));
        assertNotNull("Object for bTopId should exist", roblr.getObjectRegistry().get("bTopId"));
        assertEquals("Object for aId should exist", A.class, roblr.getObjectRegistry().get("aId").getClass());
        assertEquals("Object for aId should exist", B.class, roblr.getObjectRegistry().get("bTopId").getClass());
        assertEquals("Object for aId should exist", roblr.getObjectRegistry().get("bTopId"),
                                                    ((A) roblr.getObjectRegistry().get("aId")).getBTop());
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

        public B getBTop() {
            return bTop;
        }

        public void setBTop(B bTop) {
            this.bTop = bTop;
        }

        public B getBBot() {
            return bBot;
        }

        public void setBBot(B bBot) {
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