import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class exportNotes {

    /* Global File Paths */
    private static String kindleInputFile = "G:/documents/My Clippings.txt";
    private static String kindleHighlightsFile = "Kindle_Highlights.docx";

    /* Global Data Structures */
    private static ArrayList<String> titles = new ArrayList<>();
    private static ArrayList<String> highlights = new ArrayList<>();
    private static HashMap<String, ArrayList<String>> map = new HashMap<>();

    /*----------------------------------------------------------------------------------------------*/

    /* Retrieving Title */
    private static void retrieveTitle(String currLine, Scanner myReader, ArrayList<String> titles) {
        if(currLine.equals("==========") && myReader.hasNextLine()) {
            titles.add(myReader.nextLine());
        }
    }

    /* Retrieving Highlight */
    private static void retrieveHighlight(String currLine, String prevLine, ArrayList<String> highlights) {
        if(currLine.equals("==========")) {
            highlights.add(prevLine);
        }
    }

    /* Reading Kindle File's Contents */
    private static void readKindleFile() {
        String prevLine = new String("");

        try {
            File kindleFile = new File(kindleInputFile);
            Scanner myReader = new Scanner(kindleFile);
            String firstTitle = Files.readAllLines(Paths.get(kindleInputFile)).get(1);
            titles.add(firstTitle);

            while(myReader.hasNextLine()) {
                String currLine = myReader.nextLine();
                retrieveTitle(currLine, myReader, titles);
                retrieveHighlight(currLine, prevLine, highlights);
                prevLine = currLine;
            }
        }
        catch (Exception e) {
            System.out.println("Error: Reading Kindle File's Contents");
            e.printStackTrace();
        }
    }

    /* Exporting ArrayList Contents ('titles' and 'highlights') to HashMap - Sorting*/
    private static void exportToHashMap() {
        for(int i=0; i<highlights.size(); i++) {
            if(!map.containsKey(titles.get(i))) {
                map.put(titles.get(i), new ArrayList<String>());
                map.get(titles.get(i)).add('"' + highlights.get(i) + '"');
            }
            else {
                map.get(titles.get(i)).add('"' + highlights.get(i) + '"');
            }
        }
    }

    /* Exporting HashMap Contents to Output File ('Kindle_Highlights.docx') */
    private static void exportToDocx() {
        int highlightCount = 1;

        try {
            FileOutputStream out = new FileOutputStream(new File(kindleHighlightsFile));
            XWPFDocument doc = new XWPFDocument();

            for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
                XWPFParagraph title = doc.createParagraph();
                title.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun writeTitle = title.createRun();
                writeTitle.setFontFamily("Segoe UI Semilight");
                writeTitle.setBold(true);
                writeTitle.setText(entry.getKey());

                for (String S : entry.getValue()) {
                    XWPFParagraph highlight = doc.createParagraph();
                    highlight.setAlignment(ParagraphAlignment.LEFT);
                    XWPFRun writeHighlight = highlight.createRun();
                    writeHighlight.setFontFamily("Segoe UI Semilight");
                    writeHighlight.setItalic(true);
                    writeHighlight.setText(Integer.toString(highlightCount) + ". " + S);
                    highlightCount++;
                }
                highlightCount = 1;
                title.setPageBreak(true);
            }
            doc.write(out);
            out.close();
        }

        catch (Exception e) {
            System.out.println("Error: Writing Contents to File -> File Currently Opened");
        }
    }

    private static void kindleExporter() {
        readKindleFile();
        exportToHashMap();
        exportToDocx();
    }


    /* Initialising Kindle Exporter */
    public static void main(String[] args) {
        kindleExporter();
    }
}
