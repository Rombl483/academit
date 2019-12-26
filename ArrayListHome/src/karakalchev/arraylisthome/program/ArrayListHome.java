package karakalchev.arraylisthome.program;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

public class ArrayListHome {
    private static void readFromFile(ArrayList<String> arrayList, String fileName) {
        try (Scanner scanner = new Scanner(new FileInputStream(fileName), "windows-1251")) {
            while (scanner.hasNextLine()) {
                arrayList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Integer> getDifferentElement(ArrayList<Integer> arrayListInteger) {
        ArrayList<Integer> resultArrayListInteger = new ArrayList<>();

        for (Integer e : arrayListInteger) {
            if (resultArrayListInteger.indexOf(e) < 0) {
                resultArrayListInteger.add(e);
            }
        }

        return resultArrayListInteger;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название файла:");
        String fileName = scanner.nextLine();

        ArrayList<String> arrayListString = new ArrayList<>();
        System.out.println("Чтение данных из файла в список строк:");
        readFromFile(arrayListString, fileName);
        System.out.println(arrayListString);
        System.out.println();

        ArrayList<Integer> arrayListNumber = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0));
        System.out.println("Исходный список чисел: " + arrayListNumber);
        arrayListNumber.removeIf(e -> (e != 0 && e % 2 == 0));
        System.out.println("Список чисел без четных чисел: " + arrayListNumber);
        System.out.println();

        ArrayList<Integer> arrayListInteger = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        System.out.println("Исходный список чисел: " + arrayListInteger);
        System.out.println("Список чисел без повторяющихся елементов: " + getDifferentElement(arrayListInteger));
    }
}
