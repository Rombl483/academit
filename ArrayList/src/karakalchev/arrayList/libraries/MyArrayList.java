package karakalchev.arrayList.libraries;


import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int count;

    public MyArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Количество элементов списка на массиве не может быть < 0");
        }

        //noinspection unchecked
        items = (T[]) new Object[initialCapacity];
    }

    public MyArrayList(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Пустая коллекция.");
        }

        //noinspection unchecked
        items = (T[]) new Object[c.size()];
        addAll(c);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= 0 || minCapacity >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Параметр может принимать только положительные целые числа.");
        }
        if (minCapacity > items.length) {
            items = Arrays.copyOf(items, minCapacity);
        }
    }

    public void trimToSize() {
        items = Arrays.copyOf(items, size());
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Индекс выходит за размерность списка.");
        }

        return items[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Индекс выходит за размерность списка.");
        }

        T prevValue = items[index];
        items[index] = element;

        return prevValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size() - 1; i > -1; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(items[i], o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Пустая коллекция.");
        }

        if (c.toArray()[0].getClass() != items[0].getClass()) {
            throw new ClassCastException("Несовместимый тип коллекции и элементов списка");
        }

        for (int i = 0; i < size(); i++) {
            if (!c.contains(items[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean add(T element) {
        if (count >= items.length) {
            ensureCapacity(2 * size());
        }

        items[count] = element;
        count++;

        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Индекс выходит за размерность списка.");
        }

        if (count >= items.length) {
            ensureCapacity(2 * size());
        }

        if (index <= size()) {
            System.arraycopy(items, index, items, index + 1, size() - index);
            count++;
            items[index] = element;
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Пустая коллекция.");
        }

        for (T e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Пустая коллекция.");
        }

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Индекс выходит за размерность списка.");
        }

        for (T e : c) {
            add(index, e);
            index++;
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(items[i], o)) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Индекс выходит за размерность списка.");
        }

        T elementRemoved = items[index];

        if (index < size() - 1) {
            System.arraycopy(items, index + 1, items, index, size() - index - 1);
        }

        count--;

        return elementRemoved;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Пустая коллекция.");
        }

        if (c.toArray()[0].getClass() != items[0].getClass()) {
            throw new ClassCastException("Несовместимый тип коллекции и элементов списка");
        }

        boolean isRemoved = false;

        for (Object e : c) {
            for (int i = 0; i < size(); i++) {
                if (Objects.equals(items[i], e)) {
                    remove(i);
                    i--;
                    if (!isRemoved) {
                        isRemoved = true;
                    }
                }
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Пустая коллекция.");
        }

        if (c.toArray()[0].getClass() != items[0].getClass()) {
            throw new ClassCastException("Несовместимый тип коллекции и элементов списка");
        }

        boolean isRemoved = false;

        for (int i = 0; i < size(); i++) {
            if (!c.contains(items[i])) {
                remove(i);
                i--;
                if (!isRemoved) {
                    isRemoved = true;
                }
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        //noinspection unchecked
        items = (T[]) new Object[10];
        count = 0;
    }

    @Override
    public Object[] toArray() {
        if (size() == 0) {
            throw new NullPointerException("Список на массиве пуст.");
        }

        return Arrays.copyOf(items, size());
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (size() == 0) {
            throw new NullPointerException("Список на массиве пуст.");
        }

        //noinspection unchecked
        return ((T1[]) Arrays.copyOf(items, size()));
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = -1;
            private int modCount = size();

            @Override
            public boolean hasNext() {
                return currentIndex + 1 < count;
            }

            @Override
            public T next() {
                if (modCount != size()) {
                    throw new ConcurrentModificationException("Несоответсвтие размера коллекции.");
                }

                if (currentIndex > size()) {
                    throw new NoSuchElementException("Коллекция закончилась.");
                }

                currentIndex++;
                return items[currentIndex];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");

        for (int i = 0; i < count; i++) {
            result.append(items[i]);
            result.append(", ");
        }

        int expressionIndex = result.lastIndexOf(", ");

        if (expressionIndex > -1) {
            result.delete(expressionIndex, result.length());
        }

        result.append("]");

        return result.toString();
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
