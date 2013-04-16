package net.minecraft.client.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import org.lwjgl.input.Keyboard;

public class InputFix_GuiScreenFix
{

    public static String encoding;

    static
    {
        try
        {
            Properties a = new Properties();
            a.load(new FileInputStream(new File("inputfix.properties")));
            encoding = a.getProperty("encoding", "GBK");
        }
        catch (FileNotFoundException e)
        {
            Properties a = new Properties();
            a.setProperty("encoding", "GBK");
            try
            {
                a.store(new FileOutputStream(new File("inputfix.properties")), null);
            }
            catch (Throwable t)
            {
                t.printStackTrace();
            }
        }
        catch (Throwable t)
        {
            encoding = "GBK";
        }
    }

    public static void handleKeyboardInput(GuiScreen gui)
    {
        if (Keyboard.getEventKeyState())
        {
            do
            {
                int k = Keyboard.getEventKey();
                char c = Keyboard.getEventCharacter();
                if (k == 87)
                {
                    gui.mc.toggleFullscreen();
                    return;
                }
                if (gui.isMacOs && k == 28 && c == 0)
                {
                    k = 29;
                }
                if (c > 127 && Keyboard.next())
                {
                    int k2 = Keyboard.getEventKey();
                    char c2 = Keyboard.getEventCharacter();
                    try
                    {
                        c2 = new String(new byte[]
                            {
                            (byte) c, (byte) c2
                            }, encoding).charAt(0);
                        gui.keyTyped(c2, k);
                    }
                    catch (Throwable t)
                    {
                        gui.keyTyped(c, k);
                        gui.keyTyped(c2, k2);
                    }
                }
                else
                {
                    gui.keyTyped(c, k);
                }
            }
            while (Keyboard.next());
        }
    }

}
