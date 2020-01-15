package karakalchev.list.program;

import karakalchev.list.libraries.SinglyLinkedList;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        try {
            SinglyLinkedList<Integer> listInteger = new SinglyLinkedList<>();

            listInteger.addFront(1);
            listInteger.addFront(2);
            listInteger.addFront(3);
            listInteger.addFront(4);
            listInteger.addFront(null);
            listInteger.addFront(5);

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

            System.out.println("Добавление элемента по индексу 5");
            listInteger.addAt(5, 7);
            System.out.println(listInteger);

            System.out.println("Удаление елемента[6] = " + listInteger.deleteAt(6));
            System.out.println(listInteger);

            System.out.println("Удаление первого елемента = " + listInteger.deleteFront());
            System.out.println(listInteger);

            System.out.println("Удаление елемента со значением null");

            if (listInteger.deleteElementByData(null)) {
                System.out.println(listInteger);
            }

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
