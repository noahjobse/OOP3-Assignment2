package implementations;

import utilities.StackADT;
import utilities.IteratorADT;

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * MyStack is an implementation of the StackADT interface that uses a dynamic
 * doubly linked list (MyDLL) to store elements. It follows the Last-In-First-Out
 * (LIFO) principle and supports standard stack operations.
 *
 * @param <E> the type of elements stored in the stack
 */
public class MyStack<E> implements StackADT<E>, Serializable {
    private static final long serialVersionUID = 1L;

    private MyDLL<E> dll;

    /**
     * Constructs an empty stack.
     */
    public MyStack() {
        dll = new MyDLL<>();
    }

    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException();
        dll.add(toAdd); 
    }

    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        return dll.remove(dll.size() - 1);
    }

    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        return dll.get(dll.size() - 1);
    }

    @Override
    public void clear() {
        dll.clear();
    }

    @Override
    public boolean isEmpty() {
        return dll.isEmpty();
    }

    @Override
    public Object[] toArray() {
        Object[] orig = dll.toArray();
        Object[] reversed = new Object[orig.length];
        for (int i = 0; i < orig.length; i++) {
            reversed[i] = orig[orig.length - 1 - i];
        }
        return reversed;
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null) throw new NullPointerException();
        E[] result = dll.toArray(holder);
        int n = size();
        for (int i = 0; i < n / 2; i++) {
            E temp = result[i];
            result[i] = result[n - i - 1];
            result[n - i - 1] = temp;
        }
        return result;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return dll.contains(toFind);
    }

    @Override
    public int search(E toFind) {
        for (int i = dll.size() - 1, position = 1; i >= 0; i--, position++) {
            E current = dll.get(i);
            if ((toFind == null && current == null) || (toFind != null && toFind.equals(current))) {
                return position;
            }
        }
        return -1;
    }

    @Override
    public IteratorADT<E> iterator() {
        return new IteratorADT<E>() {
            private int index = dll.size() - 1;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                return dll.get(index--);
            }
        };
    }

    @Override
    public boolean equals(StackADT<E> that) {
        if (that == null || that.size() != this.size()) return false;
        Object[] thisArr = this.toArray();
        Object[] thatArr = that.toArray();
        for (int i = 0; i < thisArr.length; i++) {
            if (!thisArr[i].equals(thatArr[i])) return false;
        }
        return true;
    }

    @Override
    public int size() {
        return dll.size();
    }

    @Override
    public boolean stackOverflow() {
        return false;
    }
}
