
public class MyArrayList implements MyList {

    public Object[] mList;
    public int arrayIndex;

    public MyArrayList() {
        mList = new Object[2];
        arrayIndex = 0;
    }

    private void add() {
        if (isFull()) {
            int n = mList.length;
            Object[] mmList = new Object[2 * n];
            for (int i = 0; i < mList.length; i++) {
                mmList[i] = mList[i];
            }
            mList = mmList;
        }
    }

    public boolean isFull() {
        if (arrayIndex == mList.length) {
            return true;
        } else {
            return false;
        }
    }

    //adds the given object to the end of the list

    public boolean add(Object o) {

        if (!isFull() && mList[arrayIndex] == null) {
            mList[arrayIndex] = o;
            arrayIndex++;
            return true;
        } else {
            int n = mList.length;
            Object[] mmList = new Object[2 * n];
            for (int i = 0; i < n; i++) {
                mmList[i] = mList[i];
            }
            mList = mmList;
            mList[arrayIndex] = o;
            arrayIndex++;
            return true;
        }
    }

    //inserts the given object at the given index in the list

    public boolean insert(int index, Object o) {
        if (index < 0 || index >= mList.length) {
            System.out.println("Index out of range");
            return false;
        }
        if (mList[index] != null) {
            System.out.println("There is another object in that spot");
            return false;

        } else {
            if (index < arrayIndex) {
                mList[index] = o;
                return true;
            } else
                mList[index] = o;
            arrayIndex = index + 1;
            return true;
        }
    }

    //clears all data from the list

    public void clear() {
        for (int i = 0; i < mList.length; i++) {
            mList[i] = null;
        }
    }

    //returns true if the list contains the given object

    public boolean contains(Object o) {
        boolean hasIt = false;
        for (int i = 0; i < mList.length; i++) {
            if (mList[i] != null && mList[i].equals(o)) {
                hasIt = true;
            }
        }
        return hasIt;
    }

    //returns the object at the given index

    public Object get(int index) {
        if (index < 0 || index >= mList.length) { // index out of range
            System.out.println("Index out of range");
            return null;
        } else {
            return mList[index];
        }
    }


    //returns the index of the given object

    public int indexOf(Object o) {
        int oIndex = -1;
        for (int i = 0; i < mList.length; i++) {
            if (mList[i] != null && mList[i].equals(o)) {
                oIndex = i;
            }
        }
        if (oIndex < 0) {
            System.out.println("Object not in array");
        }
        return oIndex;
    }

    //returns true if the list is empty, false if otherwise

    public boolean isEmpty() {
        boolean nothing = true;
        for (int i = 0; i < mList.length; i++) {
            if (mList[i] != null) {
                nothing = false;
            }
        }
        return nothing;
    }

    //removes and returns the object at the given index

    public Object remove(int index) {
        if (index < 0 || index >= mList.length) {
            System.out.println("Index out of range");
            return null;
        } else {
            return mList[index];
        }
    }

    //removes the first instance of the object in the list.
    //returns true if an object is successfully removed, false if otherwise

    public boolean remove(Object o) {
        boolean sRemoved = false;

        for (int i = 0; i < mList.length; i++) {
            if (mList[i] != null && mList[i].equals(o)) {
                mList[i] = null;
                sRemoved = true;
            }
        }
        return sRemoved;
    }

    //replaces the object at the given index with the given object

    public void set(int index, Object o) {
        if (index < 0 || index >= mList.length) {
            System.out.println("Index out of range");
        } else {
            mList[index] = o;
        }
    }

    //returns the number of elements in the list

    public int size() {
        int countItems = 0;
        for (int i = 0; i < mList.length; i++) {
            if (mList[i] != null) {
                countItems++;
            }
        }
        return countItems;
    }

    public static void main(String[] args) {

        MyArrayList mL = new MyArrayList();
        mL.add("Hello");
        mL.add("How");
        mL.add("Are");
        mL.add("You?");
        mL.add("Hello");
        mL.add("How");
        mL.add("Are");
        mL.add("You?");
        mL.add("?");
        System.out.println(mL.contains("Hello"));
        System.out.println(mL.get(0));
        System.out.println(mL.get(1));
        System.out.println(mL.get(2));
        System.out.println(mL.get(3));
        System.out.println(mL.isFull());
        mL.set(0, "Hi");
        System.out.println(mL.get(0));
        System.out.println(mL.contains("Hello"));
        mL.remove(2);
        mL.remove("You?");
        mL.insert(5, "^_^");
        System.out.println(mL.get(5));
        mL.insert(9, "^_^");
        System.out.println(mL.get(9));
        System.out.println(mL.indexOf("^_^"));
        mL.clear();
        System.out.println(mL.isEmpty());
    }
}
