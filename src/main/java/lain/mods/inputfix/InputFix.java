package lain.mods.inputfix;

import java.util.Map;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("")
@IFMLLoadingPlugin.TransformerExclusions("lain.mods.inputfix.ASMTransformer")
public class InputFix implements IFMLLoadingPlugin
{

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[]
            {
                "lain.mods.inputfix.ASMTransformer"
            };
    }

    @Override
    public String[] getLibraryRequestClass()
    {
        return null;
    }

    @Override
    public String getModContainerClass()
    {
        return "lain.mods.inputfix.InjectedModContainer";
    }

    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {
    }

}
