package net.vv2.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestPdf {
    public static void main(String[] args) throws Exception {

        TestPdf pdf = new TestPdf();
        String filename = "D://pdfTest//testTable3.pdf";
        pdf.createPDF(filename);
        System.out.println("打印完成");

    }
    public void createPDF(String filename) throws IOException {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.addTitle("example of PDF");
            document.open();
            document.add(new Paragraph("Hello World!"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
    public static void createPDFWithString(String filename,String title,String str) throws IOException {
        Document document = new Document(PageSize.A4);
        try {
            Font font = new Font(BaseFont.createFont("E:\\00-project\\idea_project\\PersonalFinance\\1536=方正特雅宋体.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED));
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.addTitle(title);
            document.open();
            document.add(new Paragraph(str,font));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}