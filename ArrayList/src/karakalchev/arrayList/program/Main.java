package karakalchev.arrayList.program;

import karakalchev.arrayList.libraries.MyArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        try {
            MyArrayList<Integer> arrayList = new MyArrayList<>();
            System.out.println(Arrays.toString(arrayList.toArray()));
            arrayList.add(1);
            arrayList.add(1);
            arrayList.add(1);
            arrayList.add(1);
            arrayList.add(1);
            arrayList.add(1);
            arrayList.add(1);
            arrayList.add(1);
            arrayList.add(1);
            arrayList.add(1);
            arrayList.add(1);
            arrayList.add(1);
            arrayList.add(1);
            arrayList.add(1);
            arrayList.add(1);
            System.out.println(arrayList);

            MyArrayList<Integer> numbersList = new MyArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
            System.out.println(numbersList);
            numbersList.remove(15);
            numbersList.remove(0);
            numbersList.remove(8);
            System.out.println(numbersList);

            System.out.printf("size() = %d%n", numbersList.size());
            numbersList.add(0, 0);
            System.out.println(numbersList);

            System.out.printf("size() = %d%n", numbersList.size());
            numbersList.add(9, 9);
            System.out.println(numbersList);

            System.out.printf("size() = %d%n", numbersList.size());
            numbersList.add(15, 15);
            System.out.println(numbersList);

            System.out.printf("size() = %d%n", numbersList.size());
            numbersList.add(16, 16);
            System.out.println(numbersList);

            numbersList.add(16, 16);
            System.out.println(numbersList);

            numbersList.add(16, 16);
            System.out.println(numbersList);

            System.out.printf("size() = %d%n", numbersList.size());
            numbersList.add(16, null);
            System.out.println(numbersList);

            if (numbersList.contains(null)) {
                System.out.println("Содержит null");
                numbersList.remove(null);
            } else {
                System.out.println("Не содержит null");
            }

            if (numbersList.contains(10)) {
                System.out.println("Содержит 10");
                numbersList.remove(10);
            } else {
                System.out.println("Не содержит 10");
            }

            if (numbersList.contains(20)) {
                System.out.println("Содержит 20");
            } else {
                System.out.println("Не содержит 20");
            }

            System.out.println(numbersList);
            System.out.println("numbersList.lastIndexOf(16) = " + numbersList.lastIndexOf(16));
            numbersList.addAll(3, Arrays.asList(1, 2, 3, 4, 5, 6, 7));
            System.out.println(numbersList);


            if (numbersList.removeAll(Arrays.asList(3, 4, 5))) {
                System.out.println("removedAll");
                System.out.println(numbersList);
            }

            if (numbersList.retainAll(Arrays.asList(7, 8, 9))) {
                System.out.println("retainAll");
                System.out.println(numbersList);
            }

            numbersList.trimToSize();

            System.out.println("Массив объектов:");
            System.out.println(Arrays.toString(numbersList.toArray()));

            System.out.println("Массив определенного типа:");
            System.out.println(Arrays.toString(numbersList.toArray(new Integer[0])));

            System.out.println("Iterator");
            Iterator<Integer> iterator = numbersList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

            arrayList.add(1);
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

        } catch (IllegalArgumentException | NullPointerException | ConcurrentModificationException | NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
