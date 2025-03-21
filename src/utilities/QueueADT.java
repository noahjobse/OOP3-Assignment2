package utilities;

import java.util.NoSuchElementException;

/**
 * A simple and efficient Queue interface that defines how a queue works.
 * Queues follow the First-In, First-Out (FIFO) rule: 
 * the first element added is the first to be removed.
 *
 * @param <T> The type of elements stored in the queue.
 */
public interface QueueADT<T> {

    /**
     * Adds an item to the back of the queue.
     *
     * @param item The element to add.
     * @throws NullPointerException if the item is null.
     */
    void enqueue(T item);

    /**
     * Removes and returns the item at the front of the queue.
     *
     * @return The element removed from the front.
     * @throws NoSuchElementException if the queue is empty.
     */
    T dequeue() throws NoSuchElementException;

    /**
     * Gets the item at the front of the queue without removing it.
     *
     * @return The element at the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    T peek() throws NoSuchElementException;

    /**
     * Checks if the queue has no elements.
     *
     * @return true if the queue is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the total number of items in the queue.
     *
     * @return The number of elements in the queue.
     */
    int size();

    /**
     * Clears all elements from the queue.
     * After this operation, the queue will be empty.
     */
    void clear();
}
