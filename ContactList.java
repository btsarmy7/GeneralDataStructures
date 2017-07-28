

public interface ContactList {


    public boolean addContact(Contact entry);


    public boolean isFull(Contact[] list);


    public Contact find(String n);


    public Contact remove();


    public boolean read(String fileName);


    public boolean write(String fileName);


    public void sort();


    public boolean addInOrder(Contact c);

}
