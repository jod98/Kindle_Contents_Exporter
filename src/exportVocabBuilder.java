import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import com.spire.xls.*;
import org.apache.poi.xwpf.usermodel.*;

public class exportVocabBuilder {

    /* Global File Paths & Constants */
    private static final String File = "Kindle_VocabBuilder_Initial.xlsx";
    private static final String FileRemovedCols = "Kindle_VocabBuilder_Initial_Columns_Removed.xlsx";
    private static final String kindleVocabFile = "Kindle_Vocab.docx";
    private static final int HEADER = 1;
    private static final int STEM = 2;
    private static final int FREQUENCY = 3;
    private static final int CATEGORY = 3;
    private static final int DATE = 3;
    private static final int USAGE = 3;
    private static final int DEFINITION = 2;
    private static final ArrayList<String> words = new ArrayList<>();

    /*----------------------------------------------------------------------------------------------*/

    /* Remove Unwanted Data (Columns) From .XLSX File */
    private static void removeUnwantedData() {
        Workbook wb = new Workbook();
        wb.loadFromFile(File);
        Worksheet sheet = wb.getWorksheets().get(0);
        sheet.deleteRow(HEADER);
        sheet.deleteColumn(STEM);
        sheet.deleteColumn(FREQUENCY);
        sheet.deleteColumn(CATEGORY);
        sheet.deleteColumn(DATE);
        sheet.deleteColumn(USAGE);
        sheet.deleteColumn(DEFINITION);
        wb.saveToFile(FileRemovedCols);
    }

    /* Reading Vocab (.XSLX) File's Contents */
    private static void readXSLX(){
        try
        {
            File file = new File(FileRemovedCols);
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();

            String word = new String("");

            while (itr.hasNext())
            {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext())
                {
                    word = cellIterator.next().toString();
                    word = word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
                    words.add(word);
                }
            }
            fis.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /* Exporting ArrayList Contents to Output File ('Kindle_Vocab.docx') */
    private static void exportToDocx() {
        try {
            FileOutputStream out = new FileOutputStream(new File(kindleVocabFile));
            XWPFDocument doc = new XWPFDocument();
            XWPFTable table = doc.createTable();
            XWPFTableRow row = table.getRow(0);
            row.getCell(0).setText("Words");
            row.addNewTableCell().setText("Definitions");

            for(int i=0; i<words.size(); i++) {
                XWPFTableRow currRow = table.createRow();
                currRow.getCell(0).setText(words.get(i));
                currRow.getCell(1).setText("");
            }

            doc.write(out);
            out.close();
        }

        catch (Exception e) {
            System.out.println("Error: Writing Contents to File");
        }
    }

    private static void deleteUnncessaryFiles() {
        try{
            Files.delete(Paths.get(FileRemovedCols));
            Files.delete(Paths.get(File));
        }
        catch (Exception e) {
            System.out.println("Unable to Delete Files");
        }
    }

    private static void kindleVocabExporter() {
        removeUnwantedData();
        readXSLX();
        exportToDocx();
        deleteUnncessaryFiles();
    }

    public static void main(String[] args) {
        kindleVocabExporter();
    }
}

