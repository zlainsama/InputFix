package lain.mods.inputfix;

import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name("InputFix")
@IFMLLoadingPlugin.MCVersion("")
@IFMLLoadingPlugin.TransformerExclusions("lain.mods.inputfix.")
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
        return "lain.mods.inputfix.InputFixSetup";
    }

    @Override
    public void injectData(Map<String, Object> arg0)
    {
    }

}
