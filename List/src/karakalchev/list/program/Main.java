package karakalchev.list.program;

import karakalchev.list.libraries.SinglyLinkedList;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        try {
            SinglyLinkedList<Integer> listInteger = new SinglyLinkedList<>();

            listInteger.add(1);
            listInteger.add(2);
            listInteger.add(3);
            listInteger.add(4);
            listInteger.add(5);

            SinglyLinkedList<Integer> listIntegerCopy = listInteger.getCopy();

            System.out.println("Список:");
            System.out.println(listInteger);

            System.out.printf("Размер списка = %d\n", listInteger.getSize());
            System.out.println("Значение первого элемента = " + listInteger.getFirstElementData());
            System.out.println("Значение элемента[4] = " + listInteger.getElementDataAt(4));

            System.out.println("Разворот списока:");
            listInteger.revert();
            System.out.println(listInteger);

            System.out.println("Значение элемента[4] = " + listInteger.getElementDataAt(4));
            System.out.println("Замена значение элемента[3] = " + listInteger.setElementDataAt(3, 10));
            System.out.println(listInteger);

            System.out.println("Добавление элемента по индексу 4");
            listInteger.addAt(4, 7);
            System.out.println(listInteger);

            System.out.println("Удаление елемента[2] = " + listInteger.deleteAt(2));
            System.out.println(listInteger);

            System.out.println("Удаление елемента со значением 10");

            if (listInteger.deleteElementByData(10)) {
                System.out.println(listInteger);
            }

            System.out.println("Копия списка");
            System.out.println(listIntegerCopy);
            System.out.printf("Размер списка = %d\n", listIntegerCopy.getSize());
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
