package lain.mods.inputfix;

import java.util.Map;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name("InputFix")
@IFMLLoadingPlugin.MCVersion("1.7.10")
public class InputFix implements IFMLLoadingPlugin
{

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
    public void injectData(Map<String, Object> arg0)
    {
    }

}
