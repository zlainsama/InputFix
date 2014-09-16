package lain.mods.inputfix;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import lain.mods.inputfix.interfaces.IGuiScreen;
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
            mc = GuiScreen.class.getDeclaredField(InputFix.RUNTIME_DEOBF ? "field_146297_k" : "mc");
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

            if (InputFixSetup.impl != null)
            {
                final Object obj = gui;
                IGuiScreen g = new IGuiScreen()
                {
                    public void keyTyped(char c, int k)
                    {
                        try
                        {
                            keyTyped.invoke(obj, c, k);
                        }
                        catch (Throwable t)
                        {
                            throw new RuntimeException(t);
                        }
                    }
                };
                InputFixSetup.impl.handleKeyboardInput(g, c, k);
            }
            else if (Keyboard.getEventKeyState())
            {
                keyTyped.invoke(gui, c, k);
            }

            ((Minecraft) mc.get(gui)).func_152348_aa();
        }
        catch (Throwable t)
        {
            throw new RuntimeException(t);
        }
    }

}
