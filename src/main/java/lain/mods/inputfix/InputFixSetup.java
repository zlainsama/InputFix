package lain.mods.inputfix;

import java.util.Map;
import lain.mods.inputfix.impl.GuiScreenFixOthers;
import lain.mods.inputfix.impl.GuiScreenFixWindows;
import lain.mods.inputfix.interfaces.IGuiScreenFix;
import lain.mods.inputfix.utils.OSDetector;
import cpw.mods.fml.relauncher.IFMLCallHook;

public class InputFixSetup
{

    public static IGuiScreenFix impl;

    public static void setup()
    {
        OSDetector.OS OS = OSDetector.detectOS();
        switch (OS)
        {
            case Windows:
                impl = new GuiScreenFixWindows();
                break;
            case Linux:
            case Mac:
                try
                {
                    impl = new GuiScreenFixOthers();
                }
                catch (Throwable t)
                {
                    impl = new GuiScreenFixWindows();
                }
                break;
            case Unknown:
            default:
                break;
        }
    }

}
