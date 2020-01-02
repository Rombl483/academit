package karakalchev.arraylisthome.program;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

public class ArrayListHome {
    private static void readFromFile(ArrayList<String> stringsList, String fileName) {
        try (Scanner scanner = new Scanner(new FileInputStream(fileName), "windows-1251")) {
            while (scanner.hasNextLine()) {
                stringsList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Внимание. Файл с именем \"" + fileName + "\" не найден");
        }
    }

    private static void removeEvenNumbers(ArrayList<Integer> numbersList) {
        ArrayList<Integer> evenNumbersList = new ArrayList<>();

        for (Integer e : numbersList) {
            if (e % 2 == 0) {
                evenNumbersList.add(e);
            }
        }

        numbersList.removeAll(evenNumbersList);
    }

    private static ArrayList<Integer> getNumbersListWithoutRepetition(ArrayList<Integer> numbersList) {
        ArrayList<Integer> numbersListResult = new ArrayList<>();

        for (Integer e : numbersList) {
            if (!numbersListResult.contains(e)) {
                numbersListResult.add(e);
            }
        }

        return numbersListResult;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название файла:");
        String fileName = scanner.nextLine();

        ArrayList<String> stringsList = new ArrayList<>();
        System.out.println("Чтение данных из файла в список строк:");
        readFromFile(stringsList, fileName);
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
