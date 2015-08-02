import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelEditor {

    private static FileInputStream in;
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static ArrayList<String> stringResultList;
    private static ArrayList<Integer> integerResultList;

    public ArrayList extractColumn(String path, int sheetNumber, int columnNumber, int firstRowNumber) throws IOException {
        stringResultList = new ArrayList<String>();
        integerResultList = new ArrayList<Integer>();

        prepareToWorkWithSheet(path, sheetNumber);

        for (Row row : sheet) {
            if (row.getRowNum() >= firstRowNumber - 1) {
                Cell cell = row.getCell(columnNumber - 1);
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            integerResultList.add((int) cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
                            stringResultList.add(cell.getStringCellValue());
                            break;
                    }
                }
            }
        }
        in.close();

        if (sheet.getRow(firstRowNumber - 1).getCell(columnNumber - 1).getCellType() == Cell.CELL_TYPE_NUMERIC)
            return integerResultList;
            else
            return stringResultList;
    }

    public void writeColumn(String path, int sheetNumber, int columnNumber, int firstRowNumber) throws IOException {
        prepareToWorkWithSheet(path, sheetNumber);

        for (Row row : sheet) {
            if (row.getRowNum() >= firstRowNumber - 1) {
                Cell cell = row.createCell(columnNumber - 1);
                cell.setCellValue(1);
            }
        }

        FileOutputStream fileOut = new FileOutputStream(path);

        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    public void writeCell(String path, int sheetNumber, int columnNumber, int rowNumber, ArrayList<String> values) throws IOException {
        prepareToWorkWithSheet(path, sheetNumber);

        String result = "";
        for (String value : values) {
             result += value;
            }
        Cell cell = sheet.getRow(rowNumber - 1).createCell(columnNumber - 1);
        cell.setCellValue(result);

        FileOutputStream fileOut = new FileOutputStream(path);

        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
    public void writeRow(String path, int sheetNumber, int columnNumber, int rowNumber, ArrayList<String> values) throws IOException {

    }
        public void writeTable(String path, int sheetNumber, int columnNumber, int firstRowNumber, ArrayList<ArrayList> values) throws IOException {
        for (int rowNum = 0; rowNum < values.size(); rowNum++){
            writeCell(path, sheetNumber, columnNumber, rowNum + firstRowNumber, values.get(rowNum));
        }
    }

    public void extractSheet(String path, int sheetNum) throws IOException {
        FileInputStream in = new FileInputStream(new File(path));
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet sheet = workbook.getSheetAt(sheetNum - 1);

        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print((int) cell.getNumericCellValue() + " ");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + " ");
                }

            }
            System.out.println("");
        }
        in.close();
    }

    private void prepareToWorkWithSheet(String path, int sheetNumber) throws IOException {
        in = new FileInputStream(new File(path));
        workbook = new XSSFWorkbook(in);
        sheet = workbook.getSheetAt(sheetNumber - 1);
    }
}