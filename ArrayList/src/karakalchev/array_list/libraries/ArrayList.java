package karakalchev.array_list.libraries;


import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int count;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Количество элементов списка на массиве не может быть < 0");
        }

        //noinspection unchecked
        items = (T[]) new Object[initialCapacity];
    }

    public ArrayList(Collection<? extends T> c) {
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
        if (minCapacity < 0) {
            throw new IllegalArgumentException("Параметр может принимать только положительные целые числа.");
        }

        if (minCapacity > items.length) {
            items = Arrays.copyOf(items, minCapacity);
        }
    }

    public void trimToSize() {
        if (count < items.length) {
            items = Arrays.copyOf(items, count);
            modCount++;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", выходит за пределы списка: 0.." + (count - 1));
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", выходит за пределы диапазона добавления в списк: 0.." + count);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T previousValue = items[index];
        items[index] = element;

        return previousValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < count; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = count - 1; i > -1; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) > -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Пустая коллекция.");
        }

        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean add(T element) {
        if (count >= items.length) {
            ensureCapacity(2 * count + 1);
        }

        items[count] = element;
        count++;
        modCount++;

        return true;
    }

    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);

        if (index == count) {
            add(element);
            return;
        }

        if (count >= items.length) {
            ensureCapacity(2 * count);
        }

        System.arraycopy(items, index, items, index + 1, count - index);
        count++;
        modCount++;
        items[index] = element;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(count, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Пустая коллекция.");
        }

        checkIndexForAdd(index);
        int collectionSize = c.size();

        if (collectionSize == 0) {
            return false;
        }

        ensureCapacity(count + collectionSize);

        System.arraycopy(items, index, items, index + collectionSize, count - index);

        int i = index;

        for (T e : c) {
            items[i] = e;
            i++;
        }

        count += collectionSize;

        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int elementIndex = indexOf(o);

        if (elementIndex > -1) {
            remove(elementIndex);
            return true;
        }

        return false;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = items[index];

        if (index < count - 1) {
            System.arraycopy(items, index + 1, items, index, count - index - 1);
        }

        count--;
        items[count] = null;
        modCount++;

        return removedElement;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Не задана коллекция значений.");
        }

        boolean isRemoved = false;

        for (int i = 0; i < count; i++) {
            if (c.contains(items[i])) {
                remove(i);
                i--;
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Не задана коллекция значений.");
        }

        boolean isRemoved = false;

        for (int i = 0; i < count; i++) {
            if (!c.contains(items[i])) {
                remove(i);
                i--;
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        for (int i = 0; i < count; i++) {
            items[i] = null;
        }

        count = 0;
        modCount++;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, count);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (count > a.length) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, count, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, count);

        if (a.length > count) {
            a[count] = null;
        }

        return a;
    }

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int iteratorModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < count;
        }

        @Override
        public T next() {
            if (modCount != iteratorModCount) {
                throw new ConcurrentModificationException("Коллекция была модифицирована.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась.");
            }

            currentIndex++;

            return items[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        result.append("[");

        for (int i = 0; i < count; i++) {
            result.append(items[i]);
            result.append(", ");
        }

        int resultLength = result.length();
        result.delete(resultLength - 2, resultLength);
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
