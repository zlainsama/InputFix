package lain.mods.inputfix.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionHelper
{

    public static Field findField(Class<?> clazz, String[] fieldNames)
    {
        Exception failed = null;
        for (String fieldName : fieldNames)
        {
            try
            {
                Field f = clazz.getDeclaredField(fieldName);
                f.setAccessible(true);
                return f;
            }
            catch (Exception e)
            {
                failed = e;
            }
        }
        throw new RuntimeException(failed);
    }

    public static Method findMethod(Class<?> clazz, String[] methodNames, Class<?>[] methodTypes)
    {
        Exception failed = null;
        for (String methodName : methodNames)
        {
            try
            {
                Method m = clazz.getDeclaredMethod(methodName, methodTypes);
                m.setAccessible(true);
                return m;
            }
            catch (Exception e)
            {
                failed = e;
            }
        }
        throw new RuntimeException(failed);
    }

}
