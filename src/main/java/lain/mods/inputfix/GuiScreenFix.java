package lain.mods.inputfix;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

public class GuiScreenFix
{

    private static Field mc;
    private static Method keyTyped;

    static
    {
        try
        {
            mc = GuiScreen.class.getDeclaredField(InputFix.RUNTIME_DEOBF ? "field_73882_e" : "mc");
            mc.setAccessible(true);
            keyTyped = GuiScreen.class.getDeclaredMethod(InputFix.RUNTIME_DEOBF ? "func_73869_a" : "keyTyped", char.class, int.class);
            keyTyped.setAccessible(true);
        }
        catch (Throwable t)
        {
            throw new RuntimeException(t);
        }
    }

    public static void handleKeyboardInput(GuiScreen gui)
    {
        try
        {
            int k = Keyboard.getEventKey();
            char c = Keyboard.getEventCharacter();
            if (Keyboard.getEventKeyState() || (k == 0 && Character.isDefined(c)))
            {
                if (k == 87)
                {
                    ((Minecraft) mc.get(gui)).toggleFullscreen();
                    return;
                }
                keyTyped.invoke(gui, c, k);
            }
        }
        catch (Throwable t)
        {
            throw new RuntimeException(t);
        }
    }

}
