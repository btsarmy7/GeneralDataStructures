import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ContactLinkedList extends MyNodeList implements ContactList, Serializable {

    static final long serialVersionUID = 1L;
    private ObjectInputStream i;
    private ObjectOutputStream o;

    public Contact remove() {

        Node currentNode = header.getNext();
        // walk until currentNode is not null but its nextNode is null
        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }

        Contact temp = (Contact) currentNode.getData();
        currentNode = null;
        return temp;
    }

    public boolean read(String fileName) {

        try {   // test for Exception in call to File constructor

            FileInputStream fin = new FileInputStream(fileName);
            i = new ObjectInputStream(fin);

            while (true) { // loop until the file stream is at the end
                Contact read1 = (Contact) i.readObject(); // where read1 is a Contact
//				System.out.println( "==> " + read1.toString() );
                this.addContact(read1);
            }

        } catch (EOFException e) {
            System.out.println("End of File read");
        } catch (IOException e) {
            System.out.println("IO problem to fix");
        } catch (ClassNotFoundException e) {
            System.out.println("Class does not exist");
        }
        // use s as with System.in, except reading will be done from data.txt

        return true;
    }


    public boolean write(String fileName) {
        try {
            o = new ObjectOutputStream(new FileOutputStream(fileName));

            // walk until the end of list i.e: currentNode ! = null
            for (Node currentNode = header.getNext(); currentNode != null;
                 currentNode = currentNode.getNext()) {
                if (currentNode != null) {
                    //System.out.println("The list" + currentNode.getData().toString());
                    o.writeObject(currentNode.getData()); // write Contact object to file
                }
            }
            o.flush();
        } catch (IOException e) {
            System.out.println("File write problem to fix");
        }

        return true;
    }

    // add new contact Node just after header
    public boolean addContact(Contact c) {
        Node currentNode = header.getNext();
        Node newContact = new Node(null, null);
        newContact.setData(c);
        header.setNext(newContact);
        newContact.setNext(currentNode);
        return true;
    }

    public void sort() { // selection sort
        int sz = this.size();
//		 System.out.println(" sz = " + sz);

        Node newHeader = new Node(null, null);
        for (int i = sz; i > 0; i--) {
            Node lagNode = findLargestNode();
            lagNode.setNext(newHeader.getNext());
            newHeader.setNext(lagNode);
        }
        // after sorting, set sorted list to header
        this.header = newHeader;
    }

    public Node findLargestNode() {
        Node cN1 = header.getNext();
        Node ptr = header;
        Node tmp = cN1;
        Node tmpTail = ptr;

        // walk to list end
        while (cN1 != null) {
            Contact c = (Contact) tmp.getData();
            Contact t = (Contact) cN1.getData();
            if (t.getName().compareTo(c.getName()) > 0) {
                tmp = cN1;
                tmpTail = ptr;
            }
            cN1 = cN1.getNext();
            ptr = ptr.getNext();
        }

        if (tmp.getNext() != null) {    // not last Node
            tmpTail.setNext(tmp.getNext());
        } else {                        // last Node
            tmpTail.setNext(null);        // set new end
        }
        return tmp;
    }

    public boolean addInOrder(Contact c) {

        if (this.header.getNext() == null) {
            Node temp = new Node(null, null);
            temp.setData(c);
            header.setNext(temp);
            return true;
        }

        Node current = header.getNext();
        // walk until the end non null Node
        while (current != null) {
            Contact t = (Contact) current.getData();
            // insert c before t if t >= c
            if (t.getName().compareTo(c.getName()) >= 0) {
                Node temp = new Node(null, null);
                temp.setData(c);
                temp.setNext(current.getNext());
                current.setNext(temp);
                return true;
            }
            current = current.getNext();
        }

        Node temp = new Node(null, null);
        temp.setData(c);
        current.setNext(temp);
        return true;
    }

    public Contact find(String n) {
        Node currentNode = header.getNext();
        // walk until the end
        while (currentNode != null) {
            Contact c = (Contact) currentNode.getData();
            if (c.getName().equals(n)) {
                return c;
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public Contact getCurrent(){
        if( header.getNext() != null ) {
            Node currentNode = header.getNext();
            return ((Contact) currentNode.getData());
        } else {
            return null;
        }
    }

    public boolean isFull(Contact[] a) {
        return false;
    }


    public static void main(String[] args) {

        ContactLinkedList mN = new ContactLinkedList();

        Contact newContact = new Contact();
        newContact.setName("Wendy");
        newContact.setPhone(1234567);
        newContact.setAddress("2015 21st ST");
        newContact.setComments("Comments for Wendy");
        mN.add(newContact);

        Contact newContact2 = new Contact();
        newContact2.setName("Irene");
        newContact2.setPhone(89101112);
        newContact2.setAddress("200 24th ST");
        newContact2.setComments("Comments for Irene");
        mN.add(newContact2);

        Contact newContact3 = new Contact();
        newContact3.setName("Joy");
        newContact3.setPhone(13141516);
        newContact3.setAddress("901 24th ST");
        newContact3.setComments("Comments for Joy");
        mN.add(newContact3);

        System.out.println(mN.size());

        Node t = mN.header.getNext();
        while (t.getNext() != null) {
//			System.out.println(t.getData().toString());
            t = t.getNext();
        }
//		  System.out.println(t.getData().toString());

        System.out.println(mN.contains(newContact3));
        System.out.println(mN.get(0));
        System.out.println(mN.get(1));
        System.out.println(mN.get(2));

        mN.sort();

        System.out.println("\nAfter sorting:");
        t = mN.header.getNext();
        while (t != null) {
            System.out.println(t.getData().toString());
            t = t.getNext();
        }

        // test write and read functions
        mN.write("data.txt");
        mN.read("data.txt");
        System.out.println("\nFind object => \n" + mN.find("Joy"));
    }
}
