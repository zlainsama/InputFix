package lain.mods.inputfix;

import java.util.Map;
import lain.mods.inputfix.impl.GuiScreenFixOthers;
import lain.mods.inputfix.impl.GuiScreenFixWindows;
import lain.mods.inputfix.interfaces.IGuiScreenFix;
import lain.mods.inputfix.utils.OSDetector;
import cpw.mods.fml.relauncher.IFMLCallHook;

public class InputFixSetup implements IFMLCallHook
{

    public static IGuiScreenFix impl;

    public Void call() throws Exception
    {
        OSDetector.OS OS = OSDetector.detectOS();
        switch (OS)
        {
            case Windows:
                impl = new GuiScreenFixWindows();
                break;
            case Linux:
                impl = new GuiScreenFixOthers();
                break;
            case Mac:
                impl = new GuiScreenFixOthers();
                break;
            case Unknown:
            default:
                break;
        }
        return null;
    }

    public void injectData(Map<String, Object> arg0)
    {
    }

}
