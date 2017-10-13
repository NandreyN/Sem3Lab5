package NandreyN;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;

public class ExcelWriter {
    private HSSFWorkbook workBook;
    private HSSFSheet sheet;

    public ExcelWriter() {
        workBook = new HSSFWorkbook();
        sheet = workBook.createSheet("Series");
        Row titles = sheet.createRow(0);
        titles.createCell(0).setCellValue("K");
        titles.createCell(1).setCellValue("Current");
        titles.createCell(2).setCellValue("Sum");
    }

    public void writeLine(String[] data) {
        // data = {k , delta, sum }
        if (data.length != 3)
            throw new InvalidParameterException("Invalid arguments for writing");

        int k = Integer.parseInt(data[0]);
        Row row = sheet.createRow(k);
        Cell cellK = row.createCell(0);
        Cell cellDelta = row.createCell(1);
        Cell cellSum = row.createCell(2);

        cellK.setCellValue(Integer.parseInt(data[0]));
        cellDelta.setCellValue(data[1]);
        cellSum.setCellValue(data[2]);
    }

    public void save(){
        try (FileOutputStream outputStream = new FileOutputStream(new File("SeriesResults.xls"))) {
            workBook.write(outputStream);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
