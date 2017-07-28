import java.io.Serializable;


public class Contact implements Serializable {

    protected String name;
    protected long phone;
    protected String address;
    protected String comments;

    public Contact() {
        this.name = "";
        this.phone = 0;
        this.address = "";
        this.comments = "";
    }

    public Contact(String nm, int phone, String add, String com) {
        this.name = nm;
        this.phone = phone;
        this.address = add;
        this.comments = com;
    }

    public void setName(String theName) {
        name = theName;
    }

    public String getName() {
        return name;
    }

    public void setPhone(long number) {
        phone = number;
    }

    public long getPhone() {
        return phone;
    }

    public void setAddress(String location) {
        address = location;
    }

    public String getAddress() {
        return address;
    }

    public void setComments(String com) {
        comments = com;
    }

    public String getComments() {
        return comments;
    }

    public String toString() {
        return name + "\n" + phone + "\n" + address + "\n" + comments;
    }

    public boolean equals(Contact c) {
        if (name.equals(c.getName()) && phone == c.getPhone() &&
                address.equals(c.getAddress()) && comments.equals(c.getComments()))
            return true;
        else
            return false;
    }
}
