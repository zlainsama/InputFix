package lain.mods.inputfix;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import org.lwjgl.Sys;

public class Config
{

    public static String encoding;

    public static boolean leastfix;

    private static Properties internal;

    static
    {
        internal = new Properties();
        try
        {
            internal.load(new FileInputStream(new File("inputfix.properties")));
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
        encoding = get("encoding", "GBK");
        leastfix = get("leastfixlist", "2.8.4;").contains(Sys.getVersion().concat(";"));
        save();
    }

    public static String get(String _key, String _default)
    {
        if (!internal.containsKey(_key))
            internal.setProperty(_key, _default);
        return internal.getProperty(_key);
    }

    public static void save()
    {
        try
        {
            internal.store(new FileOutputStream(new File("inputfix.properties")), null);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }

    public static void set(String _key, String _value)
    {
        internal.setProperty(_key, _value);
    }

}
