package karakalchev.parsercsv.program;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ParserCSV {
    private static StringBuilder readCSVFile(String fileName) {
        StringBuilder contentCSV = new StringBuilder();
        try (Scanner scannerFile = new Scanner(new FileInputStream(fileName), "utf-8")) {

            while (scannerFile.hasNextLine()) {
                contentCSV.append(scannerFile.nextLine());
                contentCSV.append("\n");
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return contentCSV;
    }

    private static void createHTMLFile(StringBuilder contentHTML, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(contentHTML.toString());
        } catch (FileNotFoundException e) {
            System.out.println("test.html " + e.getMessage());
        }
    }

    private static StringBuilder convertFromCSVToHTML(StringBuilder contentCSV) {
        StringBuilder contentHTML = new StringBuilder();

        contentHTML.append("<html>\n");
        contentHTML.append("    <head> Конвертация CSV в HTML </head>\n");
        contentHTML.append("    <body>\n");
        contentHTML.append("        <table cols = \"5\" alignt = \"center\" border = \"1%\">\n");

        for (String rows : contentCSV.toString().split("\\n")) {
            contentHTML.append("        <tr align = \"center\">\n");

            for (String columns : rows.split(",")) {
                contentHTML.append("            <td>\n");
                contentHTML.append("                ");
                contentHTML.append(columns);
                contentHTML.append("\n");
                contentHTML.append("            </td>\n");
            }

            contentHTML.append("        </tr>\n");
        }

        contentHTML.append("        </table>\n");
        contentHTML.append("    </body>\n");
        contentHTML.append("</html>");

        return contentHTML;
    }

/*
    private static StringBuilder convertFromCSVToHTML(StringBuilder contentCSV){
        StringBuilder contentHTML = new StringBuilder();
        boolean trIsOpen = false;
        boolean tdIsOpen = false;

        contentHTML.append("<html>\n");
        contentHTML.append("    <head> Конвертация CSV в HTML </head>\n");
        contentHTML.append("    <body>\n");
        contentHTML.append("        <table cols = \"5\" alignt = \"center\" border = \"1%\">\n");

        for (String rows : contentCSV.toString().split("\\n")) {
            if (!trIsOpen) {
                contentHTML.append("        <tr align = \"center\">\n");
                trIsOpen = true;
            }

            for (String columns : rows.split(",")) {
                if (!tdIsOpen) {
                    contentHTML.append("            <td>\n");
                    tdIsOpen = true;
                }

//                contentHTML.append("                "+columns.replaceAll("\"\"","\""));
//                contentHTML.append("\n");
//                contentHTML.append("            </td>\n");
//                tdIsOpen = false;

                if (tdIsOpen && !columns.contains("\"")) {
                    contentHTML.append("            </td>\n");
                    tdIsOpen = false;
                }

            }

            if (trIsOpen && !tdIsOpen) {
                contentHTML.append("        </tr>\n");
                trIsOpen = false;
            }
        }

        contentHTML.append("        </table>\n");
        contentHTML.append("    </body>\n");
        contentHTML.append("</html>");

        return contentHTML;
    }


    private static String getTextInQuotes(String str) {
        int qoutesIndex = str.indexOf("\"\"", 0);
        while (qoutesIndex < str.length() &&  qoutesIndex >= 0) {
            System.out.println("index = " + qoutesIndex);
            str = str.replaceFirst("\"\"","\"");
            System.out.println(str);
            qoutesIndex = str.indexOf("\"\"", qoutesIndex);
        }
        return str;
    }
*/

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название CSV-файла:");
        String fileName = scanner.nextLine();

        System.out.println("Загрузка CSV файла");
//        StringBuilder contentCSV = readCSVFile("D:\\Academ_IT_School\\Java\\test.csv");
        StringBuilder contentCSV = readCSVFile(fileName);

        fileName = fileName.replaceAll(".csv", ".html");

        System.out.println("Ковертация CSV в HTML");
        StringBuilder contentHTML = convertFromCSVToHTML(contentCSV);

        System.out.println("Запись в HTML файл");
        createHTMLFile(contentHTML, fileName);

//        String test = "\"\"\"текст1\"\",текст2, \"\"текс3\"";
//        String test = "\"вот они: \"\",\"";
        //System.out.println(test.replaceAll("\"\"","\""));
        //test = test.replaceAll("^\"|\"$","");
/*
        String test2 = test.replace("\"\"","\"");
        System.out.println("test = " + test);
        System.out.println("test2 = " + test2);
        System.out.println("getTextInQuotes(test) = " + getTextInQuotes(test));
        String[] stringsplit = test.split("^\"|\"$");
        System.out.println(Arrays.toString(stringsplit));
        System.out.println("length = "+stringsplit.length);
        for (String e : stringsplit) {
            System.out.println(e);
        }
*/
    }
}
