package utilities;

import java.util.EmptyStackException;

public interface StackADT<T> {

    // Pre-condition: element is not null
    // Post-condition: element is added to the top of the stack
    void push(T element);

    // Pre-condition: stack is not empty
    // Post-condition: removes and returns the top element from the stack
    // Throws EmptyStackException if the stack is empty
    T pop() throws EmptyStackException;

    // Pre-condition: stack is not empty
    // Post-condition: returns (but does not remove) the top element from the stack
    // Throws EmptyStackException if the stack is empty
    T peek() throws EmptyStackException;

    // Pre-condition: none
    // Post-condition: returns true if the stack contains no elements; false otherwise
    boolean isEmpty();

    // Pre-condition: none
    // Post-condition: returns the number of elements currently in the stack
    int size();

    // Pre-condition: none
    // Post-condition: removes all elements from the stack
    void clear();
}
