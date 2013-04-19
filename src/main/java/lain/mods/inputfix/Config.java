package lain.mods.inputfix;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Config
{

    public static String encoding = "GBK";

    public static String get(Properties props, String key, String defaultvalue)
    {
        if (defaultvalue != null && !props.containsKey(key))
            props.setProperty(key, defaultvalue);
        return props.getProperty(key);
    }

    protected static void setup(File configFile)
    {
        Properties config = new Properties();
        try
        {
            config.load(new FileInputStream(configFile));
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
        finally
        {
            try
            {
                encoding = get(config, "encoding", encoding);
                config.store(new FileOutputStream(configFile), null);
            }
            catch (Throwable t)
            {
                t.printStackTrace();
            }
        }
    }

}
