package phonelisting;

/**
 *
 * @author austin.boucher
 */
public class Stack<E>{
    private Node<E> head;
    private Node<E> tail;
    private int size;
    public Stack() 
    {

    }
    
    public void push(E element) 
    {
        head = new Node(element, head);
        if (size == 0) {
            tail = head;
        }
        size++;
        
    }
    public E pop() 
    {
        if (head != null) 
        {
            E element = head.getElement();
            head = head.getNextNode();
            size--;
            return element;
        }
        return null;
    }

    public E peek() {
        if (head != null) 
        {
            return head.getElement();
        }
        return null;
    }
    public int getSize() {
        return size;
    }
    
}