package lain.mods.inputfix;

import java.lang.reflect.Method;
import lain.mods.inputfix.interfaces.IGuiScreen;
import lain.mods.inputfix.utils.ReflectionHelper;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

public class GuiScreenFix
{

    private static final Method keyTyped = ReflectionHelper.findMethod(GuiScreen.class, new String[] { "func_73869_a", "keyTyped" }, new Class<?>[] { char.class, int.class });
    private static final IGuiScreen proxy = new IGuiScreen()
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

    private static GuiScreen currentGui;

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
