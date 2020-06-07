package net.vv2.util;

import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author zhongfs
 */
public class ExportUtil {
    private ExportUtil() {
    }

    public static String codeFileName(HttpServletRequest request, String fileName) throws IOException {
        if (isIE(request)) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF8");
        } else if (request.getHeader("USER-AGENT") != null
                && request.getHeader("USER-AGENT").toLowerCase().indexOf("firefox") > 0) {
            fileName = "=?UTF-8?B?" + (Base64.encodeBase64String((fileName.getBytes("utf-8"))))
                    + "?=";
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        return fileName;
    }

    private static boolean isIE(HttpServletRequest request) {
        return (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0
                || request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0
                || request.getHeader("USER-AGENT").toLowerCase().indexOf("edge") > 0) ? true
                : false;
    }
}
