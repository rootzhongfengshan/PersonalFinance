package net.vv2.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;

public class PdfUtil {
	public static void topdf(String content,String path) throws  FileNotFoundException, DocumentException{
		File file=new File(path);
		FileOutputStream fo=new FileOutputStream(file);
		ITextRenderer renderer = new ITextRenderer();
		    ITextFontResolver fontResolver = renderer.getFontResolver(); 
		    try {
		    //设置字体，否则不支持中文,在html中使用字体，html{ font-family: SimSun;}
				fontResolver.addFont("templates/ttf/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    renderer.setDocumentFromString(content);
		    renderer.layout();
		    renderer.createPDF(fo);
	}

}
