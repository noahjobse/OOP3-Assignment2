package implementations;

import utilities.ListADT;
import utilities.Iterator;

import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Objects;

/**
 * A custom implementation of a generic ArrayList.
 *
 * @param <T> the type of elements stored in the list
 */
public class MyArrayList<T> implements ListADT<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;
    private int modCount;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        modCount = 0; 
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        modCount++;
    }

    @Override
    public boolean add(int index, T toAdd) {
        if (toAdd == null) throw new NullPointerException("Null values not allowed.");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        ensureCapacity();
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = toAdd;
        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean add(T toAdd) {
        if (toAdd == null) throw new NullPointerException("Null values not allowed.");
        ensureCapacity();
        elements[size++] = toAdd;
        modCount++;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends T> toAdd) {
        if (toAdd == null) throw new NullPointerException("Input list cannot be null.");

        for (int i = 0; i < toAdd.size(); i++) {
            this.add(toAdd.get(i));
        }
        return true;
    }

    @Override
    public T get(int index) {
        checkBounds(index);
        return elements[index];
    }

    @Override
    public T remove(int index) {
        checkBounds(index);
        T removed = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        modCount++;
        return removed;
    }

    @Override
    public T remove(T toRemove) {
        if (toRemove == null) throw new NullPointerException("Null values not allowed.");

        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], toRemove)) {
                return remove(i);
            }
        }
        return null;
    }

    @Override
    public T set(int index, T toChange) {
        if (toChange == null) throw new NullPointerException("Null values not allowed.");
        checkBounds(index);
        T old = elements[index];
        elements[index] = toChange;
        modCount++;
        return old;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T toFind) {
        if (toFind == null) throw new NullPointerException("Null values not allowed.");
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], toFind)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T[] toArray(T[] toHold) {
        if (toHold == null) throw new NullPointerException("Input array cannot be null.");
        if (toHold.length < size) {
            return java.util.Arrays.copyOf(elements, size, (Class<? extends T[]>) toHold.getClass());
        }
        System.arraycopy(elements, 0, toHold, 0, size);
        if (toHold.length > size) {
            toHold[size] = null;
        }
        return toHold;
    }

    @Override
    public Object[] toArray() {
        return java.util.Arrays.copyOf(elements, size);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = java.util.Arrays.copyOf(elements, elements.length * 2);
        }
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int currentIndex = 0;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException("List was modified.");
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return elements[currentIndex++];
        }
    }
}