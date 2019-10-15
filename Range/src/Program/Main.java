package Program;

import java.util.Scanner;

import Libraries.Range;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начальное значение числового диапазона: ");
        double startValue = scanner.nextDouble();

        System.out.print("Введите конечное занчение числового диапазона: ");
        double endValue = scanner.nextDouble();

        Range range = new Range(startValue, endValue);

        range.print();
        System.out.println();

        boolean breakIteration = false;

        while (!breakIteration) {
            System.out.println("Ввведите код команды: 0-выход; 1-печать диапазона; 2,3 - изменение границ диапазона, 4 - проверить число; 5 - длина диапазона.");
            int commandCode = scanner.nextInt();

            System.out.println();

            switch (commandCode) {
                case 0:
                    breakIteration = true;
                    break;
                case 1:
                    range.print();
                    break;
                case 2:
                    System.out.print("Введите начальное значение числового диапазона: ");
                    startValue = scanner.nextDouble();

                    range.setFrom(startValue);
                    break;
                case 3:
                    System.out.print("Введите конечное значение числового диапазона: ");
                    endValue = scanner.nextDouble();

                    range.setTo(endValue);
                    break;
                case 4:
                    System.out.print("Введите число для проверки принадлежности к заданному диапазону: ");
                    double value = scanner.nextDouble();

                    if (range.isInside(value)) {
                        System.out.println("Число принадлежит заданному диапазону.");
                    } else {
                        System.out.println("Число не принадлежит заданному диапазону.");
                    }

                    break;
                case 5:
                    System.out.printf("Длина числового диапазона составляет %.3f%n", range.getLength());
                    break;
                default:
                    System.out.printf("Код операции с номером %d не поддерживается%n", commandCode);
            }

            System.out.println();
        }
    }
}
