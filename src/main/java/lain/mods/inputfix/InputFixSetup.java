package lain.mods.inputfix;

import java.util.Map;
import lain.mods.inputfix.impl.GuiScreenFixOthers;
import lain.mods.inputfix.impl.GuiScreenFixWindows;
import lain.mods.inputfix.interfaces.IGuiScreenFix;
import lain.mods.inputfix.utils.OSDetector;
import net.minecraftforge.fml.relauncher.IFMLCallHook;

public class InputFixSetup implements IFMLCallHook
{

    public static IGuiScreenFix impl;

    @Override
    public Void call() throws Exception
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
        return null;
    }

    @Override
    public void injectData(Map<String, Object> arg0)
    {
    }

}
