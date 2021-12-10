package dev.hyperskys.serverpatcher.patcher.log4j.tools;

import sun.misc.Unsafe;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

public class ReflectionUtils {

    protected static MethodHandle theUnsafeFieldMethodHandle;

    static {
        try {
            Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            theUnsafeFieldMethodHandle = MethodHandles.lookup().unreflectGetter(theUnsafeField);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private ReflectionUtils() {}
}
