package karakalchev.program;

import java.util.Scanner;

import karakalchev.libraries.Range;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начальное значение числового диапазона №1: ");
        double startValue = scanner.nextDouble();

        System.out.print("Введите конечное занчение числового диапазона №1: ");
        double endValue = scanner.nextDouble();

        Range range1 = new Range(startValue, endValue);

        System.out.print("Введите начальное значение числового диапазона №2: ");
        startValue = scanner.nextDouble();

        System.out.print("Введите конечное занчение числового диапазона №2: ");
        endValue = scanner.nextDouble();

        Range range2 = new Range(startValue, endValue);

        range1.print();
        range2.print();
        System.out.println();

        boolean breakIteration = false;

        while (!breakIteration) {
            System.out.printf("Ввведите код команды:%n" +
                    "0-выход;%n" +
                    "1-печать диапазона; " +
                    "2,3 - изменение границ диапазона; " +
                    "4 - проверить число; " +
                    "5 - длина диапазона;%n" +
                    "6 - пересечение интервалов; " +
                    "7 - объединение интервалов; " +
                    "8 - разность интервалов.%n");

            int commandCode = scanner.nextInt();

            System.out.println();

            switch (commandCode) {
                case 0:
                    breakIteration = true;
                    break;
                case 1:
                    range1.print();
                    break;
                case 2:
                    System.out.print("Введите начальное значение числового диапазона: ");
                    startValue = scanner.nextDouble();

                    range1.setFrom(startValue);
                    break;
                case 3:
                    System.out.print("Введите конечное значение числового диапазона: ");
                    endValue = scanner.nextDouble();

                    range1.setTo(endValue);
                    break;
                case 4:
                    System.out.print("Введите число для проверки принадлежности к заданному диапазону: ");
                    double value = scanner.nextDouble();

                    if (range1.isInside(value)) {
                        System.out.println("Число принадлежит заданному диапазону.");
                    } else {
                        System.out.println("Число не принадлежит заданному диапазону.");
                    }

                    break;
                case 5:
                    System.out.printf("Длина числового диапазона составляет %.3f%n", range1.getLength());
                    break;
                case 6:
                    Range range3 = Range.getIntersection(range1, range2);

                    if (range3 != null) {
                        System.out.printf("Пересечение интервалов = [%.2f .. %.2f]%n", range3.getFrom(), range3.getTo());
                    } else {
                        System.out.println("Пересечение интервалов нет!");
                    }

                    break;
                case 7:
                    Range[] rangesUnion = Range.getUnion(range1, range2);

                    System.out.println("Объединение интервалов: ");

                    for (Range e : rangesUnion) {
                        if (e != null) {
                            System.out.printf("[%.2f .. %.2f]%n", e.getFrom(), e.getTo());
                        }
                    }

                    break;
                case 8:
                    Range[] rangesDifference = Range.getDifference(range1, range2);

                    System.out.println("Разность интервалов: ");

                    for (Range e : rangesDifference) {
                        if (e != null) {
                            System.out.printf("[%.2f .. %.2f]%n", e.getFrom(), e.getTo());
                        }
                    }

                    break;
                default:
                    System.out.printf("Код операции с номером %d не поддерживается%n", commandCode);
            }

            System.out.println();
        }
    }
}
