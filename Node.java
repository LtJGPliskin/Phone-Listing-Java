package phonelisting;

/**
 *
 * @author austin.boucher
 */
public class Node<E>{
    private E element;
    private Node<E> next;
    
    public Node(E element, Node next) {
        this.element = element;
        this.next = next;
    }
    
    public void setNextNode(Node next) {
        this.next = next;
    }
    
    public Node<E> getNextNode() {
        return next;
    }
    
    public E getElement() {
        return element;
    }
    
    public String toString() {
        return element.toString();
    }
}