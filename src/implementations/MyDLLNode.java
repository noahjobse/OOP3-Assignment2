package implementations;

/**
 * MyDLLNode is a node used in a doubly linked list. It holds a reference to an element,
 * as well as references to the previous and next nodes in the list.
 *
 * @param <E> the type of the element stored in the node
 */
public class MyDLLNode<E> {
    private E element;
    private MyDLLNode<E> next;
    private MyDLLNode<E> prev;

    /**
     * Constructs a new node containing the specified element.
     *
     * Precondition: element is not null.
     * Postcondition: A new node is created with the given element.
     * The next and prev references are initialized to null.
     *
     * @param element the element to store in the node
     */
    public MyDLLNode(E element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }

    /**
     * Returns the element stored in the node.
     *
     * Precondition: None
     * Postcondition: The element is returned unchanged.
     */
    public E getElement() {
        return element;
    }

    /**
     * Sets the element stored in the node.
     *
     * Precondition: element is not null.
     * Postcondition: This node stores the given element.
     *
     * @param element the new element to store
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Returns the next node.
     *
     * Precondition: None
     * Postcondition: Returns the reference to the next node, or null if none.
     */
    public MyDLLNode<E> getNext() {
        return next;
    }

    /**
     * Sets the reference to the next node.
     *
     * Precondition: None
     * Postcondition: This node’s next reference is updated to the given node.
     *
     * @param next the next node
     */
    public void setNext(MyDLLNode<E> next) {
        this.next = next;
    }

    /**
     * Returns the previous node.
     *
     * Precondition: None
     * Postcondition: Returns the reference to the previous node, or null if none.
     */
    public MyDLLNode<E> getPrev() {
        return prev;
    }

    /**
     * Sets the reference to the previous node.
     *
     * Precondition: None
     * Postcondition: This node’s prev reference is updated to the given node.
     *
     * @param prev the previous node
     */
    public void setPrev(MyDLLNode<E> prev) {
        this.prev = prev;
    }
}
