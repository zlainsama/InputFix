package lain.mods.inputfix;

import java.lang.reflect.Method;
import lain.mods.inputfix.interfaces.IGuiScreen;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

public class GuiScreenFix
{

    private static Method keyTyped;

    private static IGuiScreen proxy;
    private static GuiScreen currentGui;

    static
    {
        try
        {
            keyTyped = GuiScreen.class.getDeclaredMethod(InputFix.RUNTIME_DEOBF ? "func_73869_a" : "keyTyped", char.class, int.class);
            keyTyped.setAccessible(true);
        }
        catch (Throwable t)
        {
            throw new RuntimeException(t);
        }

        proxy = new IGuiScreen()
        {
            @Override
            public void keyTyped(char c, int k)
            {
                try
                {
                    if (currentGui != null)
                        keyTyped.invoke(currentGui, c, k);
                }
                catch (Throwable t)
                {
                    throw new RuntimeException(t);
                }
            }
        };
    }

    public static void handleKeyboardInput(GuiScreen gui)
    {
        currentGui = gui;

        if (InputFixSetup.impl != null)
            InputFixSetup.impl.handleKeyboardInput(proxy);
        else if (Keyboard.getEventKeyState())
            proxy.keyTyped(Keyboard.getEventCharacter(), Keyboard.getEventKey());

        gui.mc.dispatchKeypresses();
    }

}
