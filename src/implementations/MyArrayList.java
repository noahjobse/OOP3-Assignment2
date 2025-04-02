package implementations;

import utilities.IteratorADT;
import utilities.ArrayListADT;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements ArrayListADT<E> {
    private Object[] data;
    private int count;
    private final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    private void resizeIfNeeded() {
        if (count == data.length) {
            Object[] newData = new Object[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        data = new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) throw new NullPointerException("Null values not allowed");
        if (index < 0 || index > count) throw new IndexOutOfBoundsException("Index out of range");

        resizeIfNeeded();
        for (int i = count; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = toAdd;
        count++;
        return true;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Null values not allowed");
        resizeIfNeeded();
        data[count] = toAdd;
        count++;
        return true;
    }

    @Override
    public boolean addAll(ArrayListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("List cannot be null");

        IteratorADT<? extends E> it = toAdd.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= count) throw new IndexOutOfBoundsException("Invalid index");
        return (E) data[index];
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= count) throw new IndexOutOfBoundsException("Invalid index");

        E removed = (E) data[index];
        for (int i = index; i < count - 1; i++) {
            data[i] = data[i + 1];
        }
        count--;
        return removed;
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) throw new NullPointerException("Cannot remove null");

        for (int i = 0; i < count; i++) {
            if (data[i].equals(toRemove)) {
                return remove(i);
            }
        }
        return null;
    }

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException("Null not allowed");
        if (index < 0 || index >= count) throw new IndexOutOfBoundsException("Index out of range");

        E old = (E) data[index];
        data[index] = toChange;
        return old;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException("Cannot search for null");

        for (int i = 0; i < count; i++) {
            if (data[i].equals(toFind)) return true;
        }
        return false;
    }

    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) throw new NullPointerException("Input array is null");

        if (toHold.length < count) {
            toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), count);
        }

        for (int i = 0; i < count; i++) {
            toHold[i] = (E) data[i];
        }

        if (toHold.length > count) {
            toHold[count] = null;
        }

        return toHold;
    }

    @Override
    public Object[] toArray() {
        Object[] copy = new Object[count];
        for (int i = 0; i < count; i++) {
            copy[i] = data[i];
        }
        return copy;
    }

    @Override
    public IteratorADT<E> iterator() {
        return new IteratorADT<E>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < count;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) throw new NoSuchElementException("No more elements");
                return (E) data[pos++];
            }
        };
    }
}
