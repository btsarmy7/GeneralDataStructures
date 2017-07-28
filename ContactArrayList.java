import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class ContactArrayList extends MyArrayList implements ContactList, Serializable {
	
	static final long serialVersionUID = 1L; 
	private ObjectInputStream i = null;
	private ObjectOutputStream o = null;
	
	public boolean addContact(Contact entry){
		if (isFull())
			return false;
		else{
			mList[arrayIndex] = entry;
			arrayIndex++;
			return true;			
		}
	}
	
	
	public Contact find(String n){
		
		int i = 0;
		
		while( i < mList.length ) {	
			if( mList[i] != null ) {
				Contact c = (Contact) mList[i];
//				System.out.println(" first spot " + mList[i] + size());
			
				if( c.getName() != null ) {	
					if(c.getName().contains(n)) {
						break;				
					}
				}
			}
			i++;
		}
		if( i == mList.length ) {	// already search all list elements but not find
				return( null );
		} else {
				return( (Contact) mList[i] );
		}
	}
	
	public boolean isFull(Contact[] list){
		if (arrayIndex == (list.length))	// remember java array start from 0 to n-1
			return true;
		else 
			return false;
	}
	
		  
	
	
	public boolean read(String fileName){
		
		try {   // test for Exception in call to File constructor
			
			FileInputStream fin = new FileInputStream(fileName);
			i = new ObjectInputStream(fin);
			
			while( true ) { // loop until the file stream is at the end
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

	public boolean write(String fileName){
		try {
			o = new ObjectOutputStream(new FileOutputStream(fileName));
			for( int i = 0; i < mList.length; i++ ) {
				if( mList[i] != null ){
//					System.out.println("the list => " + mList[i].toString());
					o.writeObject(mList[i]); // where x is your Contact
				}
			}
			o.flush();
			}
			catch (IOException e) {
			System.out.println("File write problem to fix");
			} 
		
		return true;		
	}	
			
		  
    public void sort(){
    	 for (int i = mList.length - 1; i > 0; i--) {
             for (int j = 0; j < i; j++) {
             	// swap if a[j] > a[j+1]
            	 if( mList[j] != null && mList[j+1] != null ) {
            		 Contact t1 = (Contact) mList[j];
            		 Contact t2 = (Contact) mList[j+1];
            	 
            		 if (t1.getName().compareTo( t2.getName()) > 0) {
            			 Contact T = t1;
            			 t1 = t2;
            			 t2 = T;
            		 } 
            	 }
             }
         }
    }
    
    public Contact remove(){
    	if( mList[arrayIndex] != null ) {
    		Contact c = (Contact) mList[arrayIndex]; 	// get current contact object
    		mList[arrayIndex] = null;
    		return( c );				// return current contact object
    	} else {
    		return null;
    	}
	}

	public boolean addInOrder(Contact c){
		boolean added = false;
		System.out.println(arrayIndex);
		for(int i = 0; i < mList.length; i++){	
			
			if (mList[i] != null ) {
				
				Contact t = (Contact) mList[i];
			
				if (t.getName().compareTo(c.getName()) > 0){
				
					if (isFull()){
						System.out.println("full");
						Object[] newCList = new Object[mList.length + 1];
						for(int j = 0; j < mList.length; j++){
							if(j < i){
								newCList[j] = mList[j];
							}else{
								if( j == i ){
									newCList[j] = c;
									added = true;
									newCList[ j + 1 ] = mList[i];
								}else{
									newCList[ i + 1 ] = mList[i];
								}
							
							}
					
						}
						mList = newCList;
						arrayIndex = mList.length;
					}else{
						for(int j = (mList.length - 1); j > i; j--){
							mList[j] = mList[ j - 1 ];
						}
						mList[i] = c;
						added = true;	
						arrayIndex++;
					}
				}
		}
	}
		if (!added){
			if (isFull()){
				Object[] newCList = new Object[mList.length + 1];
				for(int j = 0; j < mList.length; j++){
					newCList[j] = mList[j];
				}
				newCList[mList.length] = c;
				added = true;
				arrayIndex = newCList.length;
				mList = newCList;
			} else{
			mList[arrayIndex] = c;
			arrayIndex++;
			}
		}
		 return added;
	}

    public static void main(String[] args){
    	
    	ContactArrayList mL = new ContactArrayList();
    	  Contact newContact = new Contact();
			newContact.setName("A");
			newContact.setPhone(1234567);
			newContact.setAddress("901 24th ST SE ");
			newContact.setComments("comments for A");
		  mL.add(newContact);
		  
		  Contact newContact2 = new Contact();
			newContact2.setName("B");
			newContact2.setPhone(1234567);
			newContact2.setAddress("700 12th ST NW ");
			newContact2.setComments("jkhk");
		  mL.add(newContact2);
		  
		  Contact newContact3 = new Contact();
			newContact3.setName("C");
			newContact3.setPhone(3214213);
			newContact3.setAddress("220 3rd ST SW ");
			newContact3.setComments("Comments for C");			
		 mL.add(newContact3);
		 
    	System.out.println(mL.contains(newContact));
    	System.out.println(mL.get(0));
    	System.out.println(mL.get(1));
    	System.out.println(mL.get(2));
    	System.out.println(mL.isFull());
    	
    	Contact newContact4 = new Contact();
			newContact4.setName("D");
			newContact4.setPhone(3214213);
			newContact4.setAddress("220 3rd ST SW ");
			newContact4.setComments("Comments for D");	
    	
    	mL.set(0, newContact4);
    	System.out.println(" mL [0] = " + mL.get(0));
    	
    	System.out.println("Contains A: " + mL.contains("A"));
    	
    	mL.remove(2);
    	mL.remove(newContact2);
    	
    	Contact newContact5 = new Contact();
    	newContact5.setName("E");
		newContact5.setPhone(3214213);
		newContact5.setAddress("220 3rd ST SW ");
		newContact5.setComments("Comments for E");	
    	mL.insert(3, newContact5);
    	System.out.println(mL.get(3));
    	System.out.println(mL.indexOf(newContact5)); // returns -1 if not there
    	//mL.clear();
    	System.out.println(mL.isEmpty());
    	System.out.println("Size =  " + mL.size());
    	System.out.println("Find A = " + mL.find("A"));
    	
    	mL.sort();
		mL.addInOrder(newContact5);
		
		Contact fd2 = mL.find("E");
		if( fd2 != null ) {
			 System.out.println(" Find = " + fd2.toString());
		 } else {
			 System.out.println("\nE" + " is not in the list");
		 }
		 
//		 System.out.println( "E".compareTo("A") );
		 
		 mL.write("data.txt");
		 mL.mList = new Object[5];	// create new object list
		 mL.arrayIndex = 0;			// reset ptr to first
		 mL.read("data.txt");
		 
		 System.out.println("read in object => \n" + mL.find("C"));    	
    }
}
