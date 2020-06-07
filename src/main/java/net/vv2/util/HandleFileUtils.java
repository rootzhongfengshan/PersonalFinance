package net.vv2.util;


import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author zhongfs
 */
public class HandleFileUtils {
    public static void DownExcelTemplate(HttpServletResponse response, String Quota_FILE_PATH, String fileName) throws Exception {
        String filepath = Quota_FILE_PATH + fileName;
        OutputStream out = null;
        // inputStream：读文件，前提是这个文件必须存在，要不就会报错
        InputStream is = new FileInputStream(filepath);
        response.setContentType("application/octet-stream");
        response.addHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "utf-8"));
        // 2.下载
        out = response.getOutputStream();
        byte[] b = new byte[4096];
        int size = is.read(b);
        while (size > 0) {
            out.write(b, 0, size);
            size = is.read(b);
        }
        out.close();
        is.close();
    }

    public static void createExcelFileOnTheServer(HttpServletResponse funResponse, String Quota_FILE_PATH, String fileName, Workbook wb) throws Exception {
        funResponse.setContentType("application/octet-stream");
        funResponse.setHeader("Content-disposition", "attachment; filename="
                + fileName);
        // 设定输出文件头
        //判断是否存在目录. 不存在则创建
        isChartPathExist(Quota_FILE_PATH);
        //输出Excel文件1
        FileOutputStream output = new FileOutputStream(Quota_FILE_PATH + fileName);
        //写入磁盘
        wb.write(output);
        output.close();
        //输出excel
        ServletOutputStream out = funResponse.getOutputStream();
        wb.write(out);
        out.close();
        wb.close();
    }

    private static void isChartPathExist(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static boolean deleteFile(String Quota_FILE_PATH, String fileName) {
        File file = new File(Quota_FILE_PATH + fileName);
        return file.delete();
    }
}
