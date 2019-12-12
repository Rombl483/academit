package karakalchev.parsercsv.program;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ParserCSV {
    private static void readCSVFile(String fileName, StringBuilder contentCSV) {
        try {
            Scanner scannerFile = new Scanner(new FileInputStream(fileName), "utf-8");
            while (scannerFile.hasNextLine()) {
                contentCSV.append(scannerFile.nextLine());
                contentCSV.append((char) 13);
                contentCSV.append((char) 10);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createHTMLFile(StringBuilder contentCSV) {
        try (PrintWriter writer = new PrintWriter("D:\\Academ_IT_School\\Java\\test3.html")) {
            System.out.println("Log 1");
            System.out.println("Log 2");
//            writer.println(contentCSV);

            writer.println("<html>");
            writer.println("    <head> TEST Convert CSV to HTML </head>");
            writer.println("<body>");
            writer.println("    <table>");
            writer.println("        <tr>");
            writer.println("            <td>");
            writer.println("                Фамилия");
            writer.println("            </td>");
            writer.println("            <td>");
            writer.println("                Имя");
            writer.println("            </td>");
            writer.println("            <td>");
            writer.println("                Отчество");
            writer.println("            </td>");
            writer.println("        </tr>");
            writer.println("        <tr>");
            writer.println("            <td>");
            writer.println("                Фамилия");
            writer.println("            </td>");
            writer.println("            <td>");
            writer.println("                Имя");
            writer.println("            </td>");
            writer.println("            <td>");
            writer.println("                Отчество");
            writer.println("            </td>");
            writer.println("        </tr>");
            writer.println("        <tr>");
            writer.println("            <td>");
            writer.println("                Фамилия");
            writer.println("            </td>");
            writer.println("            <td>");
            writer.println("                Имя");
            writer.println("            </td>");
            writer.println("            <td>");
            writer.println("                Отчество");
            writer.println("            </td>");
            writer.println("        </tr>");
            writer.println("    </table>");
            writer.println("</body>");
            writer.println("</html>");
            System.out.println("Log 3");
        } catch (FileNotFoundException e) {
            System.out.println("Запись не пошла!");
            System.out.println("test.html " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        StringBuilder contentCSV = new StringBuilder();
        readCSVFile("D:\\Academ_IT_School\\Java\\test.csv", contentCSV);
        System.out.println(contentCSV);
        createHTMLFile(contentCSV);
    }
}
