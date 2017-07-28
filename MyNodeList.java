
public class MyNodeList implements MyList {

    protected Node header;

	/* default constructor that creates an empty list of Node objects that has a header node only; 
    */

    public MyNodeList() {
        header = new Node(null, null);
        header.setNext(null);
    }

    //adds the given object to the end of the list

    public boolean add(Object o) {
        Node hd = header;
        // walk until hd is not null but its nextNode is null
        while (hd.getNext() != null) {
            hd = hd.getNext();
        }
        Node addNode = new Node(o, null);
        hd.setNext(addNode);
//    	System.out.println( " => " + hd.getNext() + " " + hd.getNext().getData().toString() );
        return true;
    }

    //inserts the given object at the given index in the list

    public boolean insert(int index, Object o) {
        Node currentNode = header.getNext();
        Node ptr = header;

        int count = 0;
        if (index < 0 || index > size()) {
            System.out.println("Index out of range");
            return false;
        }
        boolean inserted = false;
        // walk until the end
        while (currentNode != null) {
            if (count == index) {
                Node newNode = new Node(o, null);
                newNode.setNext(currentNode);
                ptr.setNext(newNode);
                inserted = true;
            }
            currentNode = currentNode.getNext();
            ptr = ptr.getNext();
            count++;
        }

        if (!inserted) {    // add at the end
            Node newNode = new Node(o, null);
            ptr.setNext(newNode);
        }
        return inserted;
    }

    //clears all data from the list

    public void clear() {
        header.setNext(null);
    }

    //returns true if the list contains the given object

    public boolean contains(Object o) {
        boolean hasIt = false;
        Node hd = header.getNext();
        // walk to the list end
        while (hd != null) {
            if (o.equals(hd.getData())) {
                hasIt = true;
                break;
            }
            hd = hd.getNext();
        }
        return hasIt;
    }

    //returns the object at the given index

    public Object get(int index) {
        Node currentNode = header.getNext();
        int count = 0;

        if (index < 0 || index >= size()) {
            System.out.println("Index out of range");
            return null;
        }

        // walk to the end
        Object o = null;
        while (currentNode != null) {
            if (count == index) {
                o = currentNode.getData();
                break;
            }
            currentNode = currentNode.getNext();
            count++;
        }
        return o;
    }

    //returns the index of the given object

    public int indexOf(Object o) {
        Node currentNode = header.getNext();
        int count = 0;
        while (currentNode != null) {
            if (o.equals(currentNode.getData())) {
                return count;
            }
            currentNode = currentNode.getNext();
            count++;

        }
        System.out.println("The object is not in the list");
        return -1;
    }

    //returns true if the list is empty, false if otherwise

    public boolean isEmpty() {
        if (header.getNext() == null) {
            return true;
        } else {
            return false;
        }
    }

    //removes and returns the object at the given index

    public Object remove(int index) {
        Node currentNode = header.getNext();
        Node temp = null;
        Node ptr = header;
        int count = 0;

        if (index < 0 || index >= size()) {
            System.out.println("Index out of range");
            return null;
        }
        // walk to the end
        while (currentNode != null) {
            if (count == index) {
                ptr.setNext(currentNode.getNext());
                temp = currentNode;
                break;
            }
            currentNode = currentNode.getNext();
            ptr = ptr.getNext();
            count++;
        }

        if (temp != null) {
            return temp.getData();
        } else {
            return null;
        }
    }

    //removes the first instance of the object in the list.

    //returns true if an object is successfully removed, false if otherwise

    public boolean remove(Object o) {
        Node currentNode = header.getNext();
        Node ptr = header;
        boolean found = false;

        while (currentNode != null) {
            if (currentNode.getData().equals(o)) {
                ptr.setNext(currentNode.getNext());
                currentNode = null;
                found = true;
                break;
            }
            currentNode = currentNode.getNext();
            ptr = ptr.getNext();
        }
        return found;
    }


    //replaces the object at the given index with the given object

    public void set(int index, Object o) {
        Node currentNode = header.getNext();
        int count = 0;
        if (index < 0 || index > size()) {
            System.out.println("Index out of range");

        }
        while (currentNode != null) {
            if (count == index) {
                currentNode.setData(o);
                break;
            }
            currentNode = currentNode.getNext();
            count++;
        }
    }

    //returns the number of elements in the list

    public int size() {
        int size = 0;
        Node cN = header.getNext();
        // walk to the end
        while (cN != null) {
            size++;
            cN = cN.getNext();
        }
        return size;
    }

    public static void main(String[] args) {
        MyNodeList mN = new MyNodeList();
        mN.add("January");
        mN.add("February");
        mN.add("March");
        mN.add("April");
        mN.add("May");
        mN.add("June");
        mN.add("July");
        mN.add("August");
        mN.add("September");
        mN.add("October");
        mN.add("November");
        mN.add("December");
        System.out.println(mN.contains("January"));
        System.out.println(mN.get(0));
        System.out.println(mN.get(1));
        System.out.println(mN.get(2));
        System.out.println(mN.get(3));
        System.out.println(mN.size());
        mN.set(0, "1st Month");
        System.out.println(mN.get(0));
        System.out.println(mN.contains("January"));
        mN.remove(2);
        mN.remove("October");
        System.out.println(mN.contains("October"));
        mN.insert(5, "5th month");
        System.out.println(mN.get(5));
        mN.insert(9, "^_^");
        System.out.println(mN.get(9));
        System.out.println(mN.indexOf("^_^"));
        mN.clear();
        System.out.println(mN.isEmpty());
    }
}
