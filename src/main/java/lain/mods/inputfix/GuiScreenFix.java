package lain.mods.inputfix;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import lain.mods.inputfix.interfaces.IGuiScreen;
import lain.mods.inputfix.utils.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

public class GuiScreenFix
{

    private static class Proxy implements IGuiScreen
    {

        private GuiScreen gui;

        @Override
        public void keyTyped(char c, int k)
        {
            try
            {
                if (gui != null)
                    keyTyped.invoke(gui, c, k);
            }
            catch (Throwable t)
            {
                throw new RuntimeException(t);
            }
        }

        public Proxy setGui(GuiScreen gui)
        {
            this.gui = gui;
            return this;
        }

        @Override
        public void toggleFullscreen()
        {
            try
            {
                if (gui != null)
                    ((Minecraft) mc.get(gui)).toggleFullscreen();
            }
            catch (Throwable t)
            {
                throw new RuntimeException(t);
            }
        }

    }

    private static final ThreadLocal<Proxy> proxies = new ThreadLocal<Proxy>()
    {

        @Override
        protected Proxy initialValue()
        {
            return new Proxy();
        }

    };

    private static final Method keyTyped = ReflectionHelper.findMethod(GuiScreen.class, new String[] { "func_73869_a", "keyTyped" }, new Class<?>[] { char.class, int.class });
    private static final Field mc = ReflectionHelper.findField(GuiScreen.class, new String[] { "field_73882_e", "mc" });

    public static void handleKeyboardInput(GuiScreen gui)
    {
        Proxy p = proxies.get().setGui(gui);

        if (InputFixSetup.impl != null)
            InputFixSetup.impl.handleKeyboardInput(p);
        else if (Keyboard.getEventKeyState())
        {
            int k = Keyboard.getEventKey();
            char c = Keyboard.getEventCharacter();
            if (k == 87)
            {
                p.toggleFullscreen();
                return;
            }
            p.keyTyped(c, k);
        }
    }

}
