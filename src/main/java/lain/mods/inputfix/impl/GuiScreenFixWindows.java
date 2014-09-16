package lain.mods.inputfix.impl;

import lain.mods.inputfix.interfaces.IGuiScreen;
import lain.mods.inputfix.interfaces.IGuiScreenFix;
import org.lwjgl.input.Keyboard;

public class GuiScreenFixWindows implements IGuiScreenFix
{

    public void handleKeyboardInput(IGuiScreen gui, char c, int k)
    {
        if (Keyboard.getEventKeyState() || (k == 0 && Character.isDefined(c)))
        {
            gui.keyTyped(c, k);
        }
    }

}
