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

    private static int getDelimiterStrIndex(String str, int indexFrom, String strDelimiter) {
        String subStrDoubleQuotes = "\"\"";
        String subStrQuotes = "\"";
        int delimiterStrIndex = str.indexOf(strDelimiter, indexFrom);

        if (strDelimiter.equals("\",")) {
            delimiterStrIndex = str.indexOf(subStrQuotes, indexFrom);
            int doubleQuotesIndex = str.indexOf(subStrDoubleQuotes, indexFrom);

            while (doubleQuotesIndex >= 0 && doubleQuotesIndex == delimiterStrIndex) {
                delimiterStrIndex = str.indexOf(subStrQuotes, doubleQuotesIndex + 2);
                doubleQuotesIndex = str.indexOf(subStrDoubleQuotes, doubleQuotesIndex + 2);
            }
        }

        return delimiterStrIndex;
    }

    private static boolean makeCells(StringBuilder contentHTML, boolean tdIsOpen, String row) {
        int rowLength = row.length();
        int subStrBeginIndex = 0;

        while (subStrBeginIndex <= rowLength) {
            if (!tdIsOpen) {
                contentHTML.append("            <td>\n");
            }

            String rowDelimiter = ",";
            int subStrEndIndex = rowLength;

            if (tdIsOpen || (row.indexOf("\"", subStrBeginIndex) == subStrBeginIndex)) {
                if (!tdIsOpen) {
                    subStrBeginIndex = subStrBeginIndex + 1;
                }

                tdIsOpen = true;
                rowDelimiter = "\",";
            }

            int delimiterIndex = getDelimiterStrIndex(row, subStrBeginIndex, rowDelimiter);

            if (delimiterIndex >= 0 && delimiterIndex < rowLength) {
                subStrEndIndex = delimiterIndex;
                tdIsOpen = false;
            }

            String cellText = row.substring(subStrBeginIndex, subStrEndIndex);
            subStrBeginIndex = subStrEndIndex + rowDelimiter.length();

            contentHTML.append("                ");

            cellText = cellText
                    .replaceAll("\"\"", "\"")
                    .replaceAll("&", "&amp")
                    .replaceAll("<", "&lt")
                    .replaceAll(">", "&gt");

            contentHTML.append(cellText);

            if (tdIsOpen) {
                contentHTML.append("<br>");
            }

            contentHTML.append("\n");

            if (!tdIsOpen) {
                contentHTML.append("            </td>\n");
            }
        }

        return tdIsOpen;
    }

    private static StringBuilder convertFromCSVToHTML(StringBuilder contentCSV) {
        StringBuilder contentHTML = new StringBuilder();

        contentHTML.append("<html>\n");
        contentHTML.append("    <head> Конвертация CSV в HTML </head>\n");
        contentHTML.append("    <body>\n");
        contentHTML.append("        <table cols = \"5\" alignt = \"center\" border = \"1%\">\n");

        boolean tdIsOpen = false;

        for (String row : contentCSV.toString().split("\\n")) {
            if (!tdIsOpen) {
                contentHTML.append("        <tr align = \"center\">\n");
            }

            tdIsOpen = makeCells(contentHTML, tdIsOpen, row);

            if (!tdIsOpen) {
                contentHTML.append("        </tr>\n");
            }
        }

        contentHTML.append("        </table>\n");
        contentHTML.append("    </body>\n");
        contentHTML.append("</html>\n");

        return contentHTML;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название CSV-файла:");
        String fileName = scanner.nextLine();

        System.out.println("Загрузка CSV файла");
        StringBuilder contentCSV = readCSVFile(fileName);

        fileName = fileName.replaceAll(".csv", ".html");

        System.out.println("Ковертация CSV в HTML:" + fileName);
        StringBuilder contentHTML = convertFromCSVToHTML(contentCSV);

        System.out.println("Запись в HTML файл");
        createHTMLFile(contentHTML, fileName);
    }
}
