package karakalchev.program;

import karakalchev.libraries.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    private static void workWithPerson() {
        List<Person> personsList = Arrays.asList(
                new Person("Иван", 22),
                new Person("Сергей", 22),
                new Person("Мария", 18),
                new Person("Сергей", 42),
                new Person("Сергей", 11),
                new Person("Сергей", 2),
                new Person("Сергей", 12),
                new Person("Алексей", 30));

        System.out.println("Данные:");
        personsList.forEach(System.out::println);
        System.out.println();

        String uniqueNames = personsList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println("Список уникальных имен:");
        System.out.println(uniqueNames);
        System.out.println();

        List<Person> teensList = personsList.stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.toList());

        System.out.println("Список подростков:");
        System.out.println(teensList);
        System.out.printf("Средний возраст подростка = %.2f%n",
                teensList.stream()
                        .mapToInt(Person::getAge)
                        .average()
                        .orElse(0));

        System.out.println();
        System.out.println("Средний возраст по именам:");

        Map<String, Double> averageAgeByName = personsList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        averageAgeByName.forEach((name, averageAge) -> System.out.printf("имя: %s; средний возраст: %.2f%n", name, averageAge));

        System.out.println();
        System.out.println("Список имен людей отсортированых по возрасту в порядке убывания в диапазоне от 20 до 45:");

        List<String> names = personsList.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.toList());

        names.forEach(System.out::println);
    }

    private static void printSqrtSequence(int sequenceSize) {
        DoubleStream sequenceSQRT = DoubleStream.iterate(0, x -> x + 1)
                .limit(sequenceSize)
                .map(Math::sqrt);

        System.out.println("Последовательность квардратных корней чисел:");
        sequenceSQRT.forEach(x -> System.out.printf("%.2f   ", x));
        System.out.println();
    }

    private static void printFibonacciSequence(int sequenceSize) {
        IntStream sequenceFibonacci = Stream.iterate(new int[]{0, 1}, x -> new int[]{x[1], x[0] + x[1]})
                .limit(sequenceSize)
                .map(x -> x[0])
                .mapToInt(Integer::intValue);

        System.out.println("Последовательность чисел Фибоначчи:");
        sequenceFibonacci.forEach(x -> System.out.printf("%d   ", x));
        System.out.println();
    }

    public static void main(String[] args) {
        workWithPerson();
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество элементов последовательности:");
        int sequenceSize = scanner.nextInt();

        printSqrtSequence(sequenceSize);
        printFibonacciSequence(sequenceSize);
    }
}
