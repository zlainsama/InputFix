package lain.mods.inputfix;

import java.util.Map;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name("InputFix")
@IFMLLoadingPlugin.MCVersion("")
@IFMLLoadingPlugin.TransformerExclusions("lain.mods.inputfix.")
public class InputFix implements IFMLLoadingPlugin
{

    public static boolean RUNTIME_DEOBF = true;

    public String getAccessTransformerClass()
    {
        return null;
    }

    public String[] getASMTransformerClass()
    {
        return new String[] { "lain.mods.inputfix.InputFixTransformer" };
    }

    public String getModContainerClass()
    {
        return "lain.mods.inputfix.InputFixDummyContainer";
    }

    public String getSetupClass()
    {
        return "lain.mods.inputfix.InputFixSetup";
    }

    public void injectData(Map<String, Object> arg0)
    {
        RUNTIME_DEOBF = (Boolean) arg0.get("runtimeDeobfuscationEnabled");
    }

}
