package lain.mods.inputfix;

import java.util.Map;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name("InputFix")
@IFMLLoadingPlugin.MCVersion("")
@IFMLLoadingPlugin.TransformerExclusions("lain.mods.inputfix.")
public class InputFix implements IFMLLoadingPlugin
{

    public static boolean RUNTIME_DEOBF = true;

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[] { "lain.mods.inputfix.InputFixTransformer" };
    }

    @Override
    public String getModContainerClass()
    {
        return "lain.mods.inputfix.InputFixDummyContainer";
    }

    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {
        RUNTIME_DEOBF = (Boolean) data.get("runtimeDeobfuscationEnabled");
    }

}
