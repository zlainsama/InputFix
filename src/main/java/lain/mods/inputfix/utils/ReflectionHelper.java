package lain.mods.inputfix.utils;

import java.lang.reflect.Method;

public class ReflectionHelper
{

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
