package net.vv2.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteFileUtil {

	public static boolean WriteStringToHtml(String str, String saveFileName) throws IOException {
		boolean bool = false;
		File file = new File(saveFileName);
		BufferedWriter bufferedWriter;
		bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		// 写入信息
		bufferedWriter.write(str);
		bufferedWriter.flush();// 清空缓冲区
		bufferedWriter.close();// 关闭输出流
		bool=true;
		return bool;
	}
}
