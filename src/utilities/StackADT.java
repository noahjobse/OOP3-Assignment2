package utilities;

import java.util.EmptyStackException;

/**
 * A basic Stack interface that defines how a stack works.
 * The last element added is the first to be removed (LIFO order).
 *
 * @param <T> The type of elements stored in the stack.
 */
public interface StackADT<T> {

    /**
     * Adds an item to the top of the stack.
     *
     * @param item The element to add.
     * @throws NullPointerException if the item is null.
     * @pre item must not be null.
     * @post item is added to the top of the stack.
     */
    void push(T item);

    /**
     * Removes and returns the item at the top of the stack.
     *
     * @return The element removed from the top.
     * @throws EmptyStackException if the stack is empty.
     * @pre The stack must not be empty.
     * @post The top element is removed, and the stack size is reduced by one.
     */
    T pop() throws EmptyStackException;

    /**
     * Gets the item at the top of the stack without removing it.
     *
     * @return The element at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     * @pre The stack must not be empty.
     * @post The stack remains unchanged.
     */
    T peek() throws EmptyStackException;

    /**
     * Checks if the stack has no elements.
     *
     * @return true if the stack is empty, false otherwise.
     * @pre None.
     * @post Returns true if the stack is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the total number of items in the stack.
     *
     * @return The number of elements in the stack.
     * @pre None.
     * @post Returns the current size of the stack.
     */
    int size();

    /**
     * Clears all elements from the stack.
     * After this operation, the stack will be empty.
     *
     * @pre None.
     * @post The stack is empty.
     */
    void clear();
}
