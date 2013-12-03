package lain.mods.inputfix;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExpandedCharacters
{

    public static final String characters = getCharacters();

    private static String getCharacters()
    {
        String s = "";
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(ExpandedCharacters.class.getResourceAsStream("/echars.txt"), "UTF-8"));
            String s1 = "";
            while ((s1 = bufferedreader.readLine()) != null)
            {
                if (!s1.startsWith("#"))
                {
                    s = s + s1;
                }
            }
            bufferedreader.close();
        }
        catch (Exception ignored)
        {
        }
        return s;
    }

}
