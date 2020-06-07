package net.vv2.util;

/**
 * @author zhongfs
 */
public class HandleStringUtils {
    private HandleStringUtils() {

    }

    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}
