package implementations;

import utilities.Iterator;
import utilities.ListADT;
import java.util.NoSuchElementException;

public class MyDLL<E> implements ListADT<E> {
    private MyDLLNode<E> head, tail;
    private int size;

    public MyDLL() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean add(E item) {
        if (item == null) throw new NullPointerException();
        MyDLLNode<E> node = new MyDLLNode<>(item);

        if (size == 0) {
            head = tail = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }

        size++;
        return true;
    }

    @Override
    public boolean add(int index, E item) {
        if (item == null) throw new NullPointerException();
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        MyDLLNode<E> node = new MyDLLNode<>(item);

        if (index == 0) {
            node.setNext(head);
            if (head != null) head.setPrev(node);
            head = node;
            if (size == 0) tail = node;
        } else if (index == size) {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        } else {
            MyDLLNode<E> current = getNode(index);
            MyDLLNode<E> prev = current.getPrev();

            prev.setNext(node);
            node.setPrev(prev);
            node.setNext(current);
            current.setPrev(node);
        }

        size++;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> list) {
        if (list == null) throw new NullPointerException();
        Iterator<? extends E> it = list.iterator();
        while (it.hasNext()) add(it.next());
        return true;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean contains(E item) {
        if (item == null) throw new NullPointerException();
        MyDLLNode<E> curr = head;
        while (curr != null) {
            if (curr.getElement().equals(item)) return true;
            curr = curr.getNext();
        }
        return false;
    }

    @Override
    public E get(int index) {
        validate(index);
        return getNode(index).getElement();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private MyDLLNode<E> pointer = head;

            public boolean hasNext() {
                return pointer != null;
            }

            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E val = pointer.getElement();
                pointer = pointer.getNext();
                return val;
            }
        };
    }

    @Override
    public E remove(int index) {
        validate(index);
        MyDLLNode<E> node = getNode(index);
        E val = node.getElement();

        if (node == head) {
            head = node.getNext();
            if (head != null) head.setPrev(null);
        } else if (node == tail) {
            tail = node.getPrev();
            if (tail != null) tail.setNext(null);
        } else {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }

        size--;
        return val;
    }

    @Override
    public E remove(E item) {
        if (item == null) throw new NullPointerException();
        MyDLLNode<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.getElement().equals(item)) {
                return remove(index);
            }
            current = current.getNext();
            index++;
        }
        return null;
    }

    @Override
    public E set(int index, E value) {
        if (value == null) throw new NullPointerException();
        validate(index);
        MyDLLNode<E> node = getNode(index);
        E old = node.getElement();
        node.setElement(value);
        return old;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        MyDLLNode<E> curr = head;
        int i = 0;
        while (curr != null) {
            arr[i++] = curr.getElement();
            curr = curr.getNext();
        }
        return arr;
    }

    @Override
    public E[] toArray(E[] target) {
        if (target == null) throw new NullPointerException();
        if (target.length < size) {
            target = (E[]) java.lang.reflect.Array.newInstance(
                target.getClass().getComponentType(), size);
        }

        MyDLLNode<E> curr = head;
        for (int i = 0; i < size; i++) {
            target[i] = curr.getElement();
            curr = curr.getNext();
        }

        if (target.length > size) {
            target[size] = null;
        }

        return target;
    }

    private void validate(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
    }

    private MyDLLNode<E> getNode(int idx) {
        MyDLLNode<E> current;
        if (idx < size / 2) {
            current = head;
            for (int i = 0; i < idx; i++) current = current.getNext();
        } else {
            current = tail;
            for (int i = size - 1; i > idx; i--) current = current.getPrev();
        }
        return current;
    }
}
