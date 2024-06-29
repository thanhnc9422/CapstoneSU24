package fpt.CapstoneSU24.util;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

@Service
public class DocumentGenerator {
    public String htmlToPdf(String processedHtml) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {

            PdfWriter pdfwriter = new PdfWriter(byteArrayOutputStream);

            DefaultFontProvider defaultFont = new DefaultFontProvider(true, false, false);

            ConverterProperties converterProperties = new ConverterProperties();

            converterProperties.setFontProvider(defaultFont);

            HtmlConverter.convertToPdf(processedHtml, pdfwriter, converterProperties);
            String userHome = System.getProperty("user.home");
            String outputFilePath = userHome + "/TEST_CAPton.pdf";
            FileOutputStream fout = new FileOutputStream(outputFilePath);

            byteArrayOutputStream.writeTo(fout);
            byteArrayOutputStream.close();

            byteArrayOutputStream.flush();
            fout.close();

            return null;

        } catch (Exception ex) {

            System.out.println("Loi in PDFFFFFFF: " + ex.getMessage());
        }

        return null;
    }

    public byte[] onlineHtmlToPdf(String processedHtml) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);

            DefaultFontProvider defaultFont = new DefaultFontProvider(false, false, true);

            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(defaultFont);

            HtmlConverter.convertToPdf(processedHtml, pdfWriter, converterProperties);

            return byteArrayOutputStream.toByteArray();
        } catch (Exception ex) {
            System.out.println("Error generating PDF: " + ex.getMessage());
        }

        return null;
    }

    public byte[] generatePdfFromHtml(String html) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            // Create PdfWriter
            PdfWriter writer = new PdfWriter(baos);
            // Create PdfDocument
            PdfDocument pdf = new PdfDocument(writer);
            // Set landscape orientation
            pdf.setDefaultPageSize(PageSize.A4.rotate());
            PageSize pageSize = PageSize.A4.rotate();
            pdf.setDefaultPageSize(pageSize);

            // Convert HTML to PDF
            ConverterProperties props = new ConverterProperties();
            HtmlConverter.convertToPdf(html, pdf, props);

            // Close PdfDocument
            pdf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }
    //  CMT vì đang nghĩ là nên lưu ở dưới DB vì nếu lưu trên kia clouddinary sẽ là public thì sau người dùng sẽ có link lấy cert
//    public void generatePdfFile(String contentToGenerate, String outputPath) throws IOException {
//        byte[] pdfBytes = generatePdfFromHtml(contentToGenerate,"");
//        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
//            fos.write(pdfBytes);
//        }
//        System.out.println("PDF created successfully!");
//    }

    public byte[] getCertificate(byte[] pdfData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "certificate.pdf");

        return pdfData;
    }
}
