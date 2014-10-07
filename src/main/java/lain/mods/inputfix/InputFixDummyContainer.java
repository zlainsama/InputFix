package lain.mods.inputfix;

import java.util.Arrays;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import com.google.common.eventbus.EventBus;

public class InputFixDummyContainer extends DummyModContainer
{

    public InputFixDummyContainer()
    {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "InputFix";
        meta.name = "InputFix";
        meta.version = "1.8-v1-dev";
        meta.authorList = Arrays.asList("zlainsama");
        meta.description = "InputFix is a way to enable multi-byte input method in Minecraft.";
        meta.credits = "Dear_ICE, pa001024, sunny00123, crafteverywhere";
        meta.url = "https://github.com/zlainsama/inputfix";
        meta.updateUrl = "https://github.com/zlainsama/inputfix/releases";
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        return true;
    }

}
