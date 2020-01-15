package karakalchev.list.libraries;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    private boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return count;
    }

    private ListItem<T> getElementAt(int index) {
        int i = 0;
        ListItem<T> p = head;

        while (p != null) {
            if (i == index) {
                break;
            }

            p = p.getNext();
            i++;
        }

        return p;
    }

    public T getFirstElementData() {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст.");
        }

        return head.getData();
    }

    public T getElementDataAt(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Индекс выходит за размерность списка.");
        }

        return getElementAt(index).getData();
    }

    public T setElementDataAt(int index, T data) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Индекс выходит за размерность списка.");
        }

        ListItem<T> p = getElementAt(index);
        T prevValue = p.getData();
        p.setData(data);
        return prevValue;
    }

    public void addFront(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void addAt(int index, T data) {
        if (index < 0 || index > getSize()) {
            throw new IndexOutOfBoundsException("Индекс выходит за размерность списка.");
        }

        if (index == 0) {
            addFront(data);
        } else {
            ListItem<T> p = getElementAt(index - 1);
            ListItem<T> newElement = new ListItem<>(data, p.getNext());
            p.setNext(newElement);
            count++;
        }
    }

    public T deleteFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст.");
        }

        T prevValue = head.getData();
        head = head.getNext();
        count--;
        return prevValue;
    }

    public T deleteAt(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Индекс выходит за размерность списка.");
        }

        if (index == 0) {
            return deleteFront();
        }

        ListItem<T> p = getElementAt(index - 1);
        T prevValue = p.getNext().getData();
        p.setNext(p.getNext().getNext());
        count--;
        return prevValue;
    }

    public boolean deleteElementByData(T data) {
        if (isEmpty()) {
            return false;
        }

        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (Objects.equals(data, p.getData())) {
                count--;

                if (prev == null) {
                    deleteFront();
                } else {
                    prev.setNext(p.getNext());
                }

                return true;
            }
        }

        return false;
    }

    public void revert() {
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
        SinglyLinkedList<T> clone = new SinglyLinkedList<>();

        if (isEmpty()) {
            return clone;
        }

        ListItem<T> pClone = new ListItem<>(head.getData());
        clone.head = pClone;

        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            ListItem<T> elementCopy = new ListItem<>(p.getData(), p.getNext());
            pClone.setNext(elementCopy);
            pClone = pClone.getNext();
        }

        clone.count = count;
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            result.append(p.getData());
            result.append(", ");
        }

        int expressionIndex = result.lastIndexOf(", ");

        if (expressionIndex > -1) {
            result.delete(expressionIndex, result.length());
        }

        result.append("]");
        return result.toString();
    }
}
