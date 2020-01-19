package karakalchev.arraylisthome.program;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

public class ArrayListHome {
    private static ArrayList<String> readFromFile(String fileName) {
        ArrayList<String> stringsList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(fileName), "windows-1251")) {
            while (scanner.hasNextLine()) {
                stringsList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Внимание. Файл с именем \"" + fileName + "\" не найден");
        }

        return stringsList;
    }

    private static void removeEvenNumbers(ArrayList<Integer> numbersList) {
        for (int i = 0; i < numbersList.size(); i++) {
            if (numbersList.get(i) % 2 == 0) {
                numbersList.remove(i);
                i--;
            }
        }
    }

    private static ArrayList<Integer> getNumbersListWithoutRepetition(ArrayList<Integer> numbersList) {
        ArrayList<Integer> resultNumbersList = new ArrayList<>();

        for (Integer e : numbersList) {
            if (!resultNumbersList.contains(e)) {
                resultNumbersList.add(e);
            }
        }

        return resultNumbersList;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название файла:");
        String fileName = scanner.nextLine();

        System.out.println("Чтение данных из файла в список строк:");
        ArrayList<String> stringsList = readFromFile(fileName);
        System.out.println(stringsList);
        System.out.println();

        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        System.out.println("Исходный список чисел: " + numbersList);
        removeEvenNumbers(numbersList);
        System.out.println("Список чисел без четных чисел: " + numbersList);
        System.out.println();

        numbersList = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        System.out.println("Исходный список чисел: " + numbersList);
        System.out.println("Список чисел без повторяющихся елементов: " + getNumbersListWithoutRepetition(numbersList));
    }
}
