package karakalchev.list.libraries;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    private boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return count;
    }

    public T getFirstElementData() {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст.");
        }

        return head.getData();
    }

    public T getElementDataAt(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст.");
        }

        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Индекс выходит за размерность списка.");
        }

        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (i == index) {
                return p.getData();
            }

            i++;
        }

        return null;
    }

    public T setElementDataAt(int index, T data) {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст.");
        }

        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Индекс выходит за размерность списка.");
        }

        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (i == index) {
                T prevValue = p.getData();
                p.setData(data);
                return prevValue;
            }

            i++;
        }

        return null;
    }

    public void add(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void addAt(int index, T data) {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст.");
        }

        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Индекс выходит за размерность списка.");
        }

        if (index == 0) {
            add(data);
        } else {
            int i = 0;

            for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
                if (i == index) {
                    ListItem<T> newElement = new ListItem<>(data, p);
                    prev.setNext(newElement);
                    count++;
                }

                i++;
            }
        }
    }

    public T delete() {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст.");
        }

        T prevValue = head.getData();
        head = head.getNext();
        count--;
        return prevValue;
    }

    public T deleteAt(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст.");
        }

        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Индекс выходит за размерность списка.");
        }

        if (index == 0) {
            return delete();
        }

        int i = 0;

        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (i == index) {
                T prevValue = p.getData();
                prev.setNext(p.getNext());
                count--;
                return prevValue;
            }

            i++;
        }

        return null;
    }

    public boolean deleteElementByData(T data) {
        if (isEmpty()) {
            return false;
        }

        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (data.equals(p.getData())) {
                count--;

                if (prev == null) {
                    delete();
                } else {
                    prev.setNext(p.getNext());
                }

                return true;
            }
        }

        return false;
    }

    public void revert() {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст.");
        }

        ListItem<T> prev = null;
        ListItem<T> p = head;

        while (p != null) {
            ListItem<T> next = p.getNext();
            p.setNext(prev);
            prev = p;
            p = next;
        }

        head = prev;
    }

    public SinglyLinkedList<T> getCopy() {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст.");
        }

        SinglyLinkedList<T> cloneSinglyLinkedList = new SinglyLinkedList<>();
        ListItem<T> pClone = new ListItem<>(head.getData(), head.getNext());
        cloneSinglyLinkedList.head = pClone;

        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            ListItem<T> elementCopy = new ListItem<>(p.getData(), p.getNext());
            pClone.setNext(elementCopy);
            pClone = pClone.getNext();
        }

        cloneSinglyLinkedList.count = count;
        return cloneSinglyLinkedList;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст.");
        }

        StringBuilder result = new StringBuilder();
        result.append("[");

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            result.append(p.getData());
            result.append("; ");
        }

        result.delete(result.lastIndexOf("; "), result.length());
        result.append("]");
        return result.toString();
    }
}
