package Poon.FileCreators.ExcelCreator;

import Poon.Models.Track;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class EXCEL {
    public void creatExcel(List<Track> trackList) {
        try {
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("playlist");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("author");
            headerRow.createCell(1).setCellValue("title");
            headerRow.createCell(2).setCellValue("url");

            for (int i = 0; i < trackList.size(); i++) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(trackList.get(i).getTrackAuthor());
                row.createCell(1).setCellValue(trackList.get(i).getTrackName());
                row.createCell(2).setCellValue(trackList.get(i).getUrl());
            }
            String filePath = "src/main/java/Poon/FileCreators/ExcelCreator/playlist.xls";
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

            System.out.println("Данные записаны в файл: " + filePath);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
