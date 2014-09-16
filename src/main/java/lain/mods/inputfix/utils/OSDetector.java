package lain.mods.inputfix.utils;

public class OSDetector
{

    public enum OS
    {
        Windows,
        Linux,
        Mac,
        Unknown;
    }

    public static OS detectOS()
    {
        OSDetector detector = new OSDetector();
        if (detector.isWindows())
            return OS.Windows;
        else if (detector.isLinux())
            return OS.Linux;
        else if (detector.isMac())
            return OS.Mac;
        else
            return OS.Unknown;
    }

    String osName = System.getProperty("os.name");

    private OSDetector()
    {
    }

    boolean isLinux()
    {
        return osName.startsWith("Linux") || osName.startsWith("FreeBSD") || osName.startsWith("SunOS") || osName.startsWith("Unix");
    }

    boolean isMac()
    {
        return osName.startsWith("Mac OS X") || osName.startsWith("Darwin");
    }

    boolean isWindows()
    {
        return osName.startsWith("Windows");
    }

}
