package com.doge;

public class OsUtils {
    private static String OS = null;
    public static String getOSName(){
        if(OS == null) { OS = System.getProperty("os.name"); }
        return OS;
    }
    public static boolean isWindows(){
        return getOSName().startsWith("Windows");
    }
}
