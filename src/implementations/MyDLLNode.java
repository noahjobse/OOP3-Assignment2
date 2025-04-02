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
     * @param element the element to store in the node
     */
    public MyDLLNode(E element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public MyDLLNode<E> getNext() {
        return next;
    }

    public void setNext(MyDLLNode<E> next) {
        this.next = next;
    }

    public MyDLLNode<E> getPrev() {
        return prev;
    }

    public void setPrev(MyDLLNode<E> prev) {
        this.prev = prev;
    }
}
