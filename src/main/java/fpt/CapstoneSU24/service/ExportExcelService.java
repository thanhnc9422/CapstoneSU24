package fpt.CapstoneSU24.service;

import fpt.CapstoneSU24.model.Item;
import fpt.CapstoneSU24.util.TimeStampUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.sql.DataSource;

@Service
public class ExportExcelService {

   private final TimeStampUtil timeStampUtil;
    @Autowired
    public ExportExcelService(TimeStampUtil timeStampUtil){
        this.timeStampUtil = timeStampUtil;
    }
    public byte[] exportItemToExcel(List<Item> itemList) throws IOException {

        Workbook workbook = new XSSFWorkbook();


        XSSFFont fontTr = ((XSSFWorkbook) workbook).createFont();
        fontTr.setFontName("Arial");
        fontTr.setFontHeightInPoints((short) 16);
        fontTr.setBold(true);

        XSSFFont fontTd = ((XSSFWorkbook) workbook).createFont();
        fontTd.setFontName("Arial");
        fontTd.setFontHeightInPoints((short) 16);

        Sheet sheetItem = workbook.createSheet("item");

        ContentReportIteml(sheetItem, workbook, fontTr, fontTd, itemList);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
    }

    public void ContentReportIteml(Sheet sheet, Workbook workbook, XSSFFont fontTr, XSSFFont fontTd, List<Item> itemList) {
        //set width of col header
        sheet.setColumnWidth(0, 7900);
        sheet.setColumnWidth(1, 7900);
        sheet.setColumnWidth(2, 7900);
        sheet.setColumnWidth(3, 7900);
        sheet.setColumnWidth(4, 2270);
        Row header = sheet.createRow(0);

        //style row header
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFont(fontTr);

        //header row
        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("productName");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("currentOwner");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("createdAt");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("productRecognition");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(4);
        headerCell.setCellValue("status");
        headerCell.setCellStyle(headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(false);
        style.setFont(fontTd);

        int j = 0;
        for (Item item : itemList) {
            Row row = sheet.createRow(++j);
            Cell cell = row.createCell(0);
            cell.setCellValue(item.getProduct().getProductName());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(item.getCurrentOwner());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(timeStampUtil.convertTimestampToDate(item.getCreatedAt()).toString());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(item.getProductRecognition());
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(item.getStatus());
            cell.setCellStyle(style);

        }
    }
}

