package com;

import org.junit.Before;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author lfwang
 * @date Created in 2018/4/12
 */
public class LayoutTests {

    private Unsafe unsafe;

    @Before
    public void setUp() throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        unsafe = (Unsafe) theUnsafe.get(Unsafe.class);
    }

    /*
     * | mark 8 bytes | klass 4 bytes | [len 4 bytes] | elems/fields
     *
     * 92 //    [ptr             | 00]  locked             ptr points to real header on stack
     * 93 //    [header      | 0 | 01]  unlocked           regular object header
     * 94 //    [ptr             | 10]  monitor            inflated lock (header is wapped out)
     * 95 //    [ptr             | 11]  marked             used by markSweep to mark an object
     * 96 //                                               not valid at any other time
     * <p>
     * // unused:25 hash:31 -->| unused:1   age:4    biased_lock:1 lock:2
     */

    /**
     * mark头分析
     * @throws Exception
     */
    @Test
    public void markTest() throws Exception {
        final Object o = new Object();
        // regular mark, the value of unused
        System.out.println("regular mark : " + unsafe.getLong(o, 0L));
        // compute hash
        int hash = System.identityHashCode(o);
        System.out.println("compute hash: " + hash);
        System.out.println("compute hash: " + (unsafe.getLong(o, 0L) >> 8));
        // lightweight lock
        synchronized (o) {
            System.out.println(Long.toBinaryString(unsafe.getLong(o, 0L)));

            // inflate to ObjectMonitor
            new Thread(() -> {
                synchronized (o) {}
            }).start();
            Thread.sleep(100);

            System.out.println(Long.toBinaryString(unsafe.getLong(o, 0L)));
        }
    }

    class A {
        public A(int i, long l) {
            this.i = i;
            this.l = l;
        }

        int i;
        long l;

        @Override
        public String toString() {
            return "A{" +
                    "i=" + i +
                    ", l=" + l +
                    '}';
        }
    }

    class B {
        int ii;
        int ll;

        @Override
        public String toString() {
            return "B{" +
                    "ii=" + ii +
                    ", ll=" + ll +
                    '}';
        }
    }

    /**
     * 对象描述替换
     */
    @Test
    public void klassTest() {
        // convert type
        Object a = new A(12, 2333);
        System.out.println(a);

        int klassB = unsafe.getInt(new B(), 8L);
        unsafe.putInt(a, 8L, klassB);
        System.out.println(a);
    }

    /**
     * 数组长度修改
     */
    @Test
    public void arrayTest() {
        // get array len
        byte[] bb = new byte[100];
        System.out.println(bb.length);
        System.out.println(unsafe.getInt(bb, 12L));
        // truncate arr len
        unsafe.putInt(bb, 12L, 10);
        System.out.println(bb.length);
    }

    @Test
    public void serialize() {
        byte[] bb = new byte[24];
        A a = new A(12, 127);

        // unsafe.copyMemory(sourceObject, sourceOffset, targetObject, targetOffset, count);
        unsafe.copyMemory(a, 0, bb, 16/* offset of bb[0] */, 24);

        System.out.println(Arrays.toString(bb));

        int bbPtr = Obj2Addr(bb);
        int aPtr = bbPtr + (16 >> 3) /* compressedOop shift */;

        System.out.println(addr2Obj(aPtr));
    }


    private Object addr2Obj(int addr) {
        ObjHolder oh = new ObjHolder(null);
        unsafe.putInt(oh, 12L, addr);
        return oh.o;
    }

    private int Obj2Addr(Object o) {
        ObjHolder oh = new ObjHolder(o);
        return unsafe.getInt(oh, 12L);
    }

    class ObjHolder {
        Object o;

        ObjHolder(Object o) {
            this.o = o;
        }
    }
}
