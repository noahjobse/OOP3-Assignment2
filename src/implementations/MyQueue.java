package implementations;

import exceptions.EmptyQueueException;
import utilities.QueueADT;
import utilities.IteratorADT;

import java.io.Serializable;

/**
 * MyQueue is an implementation of the QueueADT interface that uses a dynamic
 * doubly linked list (MyDLL) to store elements. It follows the First-In-First-Out
 * (FIFO) principle and supports standard queue operations.
 *
 * @param <E> the type of elements stored in the queue
 */
public class MyQueue<E> implements QueueADT<E>, Serializable {
    private static final long serialVersionUID = 1L;

    private MyDLL<E> dll;

    /**
     * Constructs an empty queue.
     */
    public MyQueue() {
        dll = new MyDLL<>();
    }

    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        dll.add(toAdd);
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException();
        return dll.remove(0);
    }

    @Override
    public E peek() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException();
        return dll.get(0);
    }

    @Override
    public void dequeueAll() {
        dll.clear();
    }

    @Override
    public boolean isEmpty() {
        return dll.isEmpty();
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return dll.contains(toFind);
    }

    @Override
    public int search(E toFind) {
        IteratorADT<E> it = iterator();
        int index = 1;
        while (it.hasNext()) {
            E current = it.next();
            if ((toFind == null && current == null) || (toFind != null && toFind.equals(current))) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public IteratorADT<E> iterator() {
        return dll.iterator();
    }

    @Override
    public boolean equals(QueueADT<E> that) {
        if (that == null || that.size() != this.size()) return false;

        Object[] thisArr = this.toArray();
        Object[] thatArr = that.toArray();

        for (int i = 0; i < thisArr.length; i++) {
            if (!thisArr[i].equals(thatArr[i])) return false;
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        return dll.toArray();
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return dll.toArray(holder);
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return dll.size();
    }
}
