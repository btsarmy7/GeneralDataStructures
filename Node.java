// NewNode.java
// Improved linked list Node example that uses a more general Object data field
// Other features remain the same, except that ordering is done by using
// the compareTo() method from String after using toString() to obtain a
// String representation of the data.
// A list of NewNodes is assumed to be headed

public class Node {

    public Node(Object initData, Node initLink) {
        data = initData;
        next = initLink;
    }

    // selectors and modifiers

    public void setData(Object newData) {
        data = newData;
    }

    public void setNext(Node newLink) {
        next = newLink;
    }

    public Object getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    // methods

    public void display() {
        System.out.println(data.toString());
    }

    public void printFrom() {
        display();
        if (getNext() != null)
            getNext().printFrom();
    }

    public static void printList(Node begin) {
        Node ptr = begin.getNext();
        while (ptr != null) {
            ptr.display();
            ptr = ptr.getNext();
        }
    }

    public static void addInOrder2(Node newItem, Node list) {
        Node ptr = list.getNext();
        Node trailer = list;
        while (ptr != null &&
                newItem.getData().toString().compareTo(ptr.getData().toString())
                        < 0) {
            ptr = ptr.getNext();
            trailer = trailer.getNext();
        }
        newItem.setNext(ptr);
        trailer.setNext(newItem);
    }

    // instance data

    private Object data;
    private Node next;

}  // NewNode
