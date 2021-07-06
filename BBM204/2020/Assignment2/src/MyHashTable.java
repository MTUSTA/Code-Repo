import java.io.FileWriter;
import java.io.IOException;


public class MyHashTable {
	// The size of the linked lists depends on load factor --> input size/ LF
	private linkedlist[] seperatechaining = null;
	private int table_size;
	// seperate chaining --> 0 or lineer probing --> 1 or double hashing --> 2
	private int type;

	public MyHashTable(int input_size, double LF, int type) {
		// exp: 20 / 0,5 = 40.0 --> it will be 40 type casting
		this.table_size = (int) (input_size / LF);
		this.seperatechaining = new linkedlist[this.table_size];// declaration and instantiation

		for (int i = 0; i < seperatechaining.length; i++) {
			seperatechaining[i] = new linkedlist();
		}
		this.type = type;
	}

	// This method will take a key (a phoneNumber) and generate a number (hashvalue)
	// between 0 and TABLE_SIZE
	private int hash1(int phonenumber) {
		return (phonenumber % this.table_size);
	}

	// Second hash function for double hashing
	public int doubleHashFunction(int phonenumber) {
		return 1 + (phonenumber % (this.table_size - 1));
	}

	// take a key (a phoneNumber) seperate chaining
	public void put(Employee emp, int Phonenumber) {
		// use of hash() to decide which table index to insert the object into.
		if (this.type == 0) {
			int position = hash1(Phonenumber);
			this.seperatechaining[position].insert(emp);
		}
		// linear probing
		else if (this.type == 1) {
			int i;
			for (i = hash1(Phonenumber); seperatechaining[i].head != null; i = (i + 1) % seperatechaining.length) {
				if (seperatechaining[i].head.data.phoneNumber == emp.phoneNumber) {
					return;
				}
			}
			seperatechaining[i].insert(emp);

		}
		// double hashing
		else if (this.type == 2) {
			int i = hash1(Phonenumber);
			int j = doubleHashFunction(Phonenumber);
			int increment = 0;
			
			for (; seperatechaining[i].head != null; i = (i + increment * j) % seperatechaining.length) {
				if (seperatechaining[i].head.data.phoneNumber == emp.phoneNumber) {
					return;
				}
				increment++;
			}
			seperatechaining[i].insert(emp);
		}
	}

	// (a phoneNumber) and retrieve (return) the sfull Employee object
	public Employee get(int phoneNumber) throws IOException{
		// seperate chaining
		if (this.type == 0) {
			int position = hash1(phoneNumber);

			Employee emp = this.seperatechaining[position].search(phoneNumber);
			// data found return it
			if (emp != null) {
				return emp;
			}
		}
		// linear probing
		else if (this.type == 1) {
			int comparisons = 0;
			for (int i = hash1(phoneNumber); seperatechaining[i].head != null; i = (i + 1) % seperatechaining.length) {
				if (seperatechaining[i].head.data.phoneNumber == phoneNumber) {
					main.writer.write(Integer.toString(comparisons+1));
					return seperatechaining[i].head.data;
				}
				comparisons++;
			}

		}
		// double hashing
		else if (this.type == 2) {
			int j = doubleHashFunction(phoneNumber);
			int increment = 0;
			for (int i = hash1(phoneNumber); seperatechaining[i].head != null; i = (i + increment * j) % seperatechaining.length) {
				if (seperatechaining[i].head.data.phoneNumber == phoneNumber) {
					main.writer.write(Integer.toString(increment+1));
					return seperatechaining[i].head.data;
				}
				increment++;
			}
		}
		// data not found
		return null;
	}

	public void output(FileWriter writer) throws IOException {
		if(this.type == 0) {
			for (int i = 0; i < seperatechaining.length; i++) {
				writer.write("[Chain " + i + "]: ");
				this.seperatechaining[i].printList(writer);
			}			
		}
		else if(this.type != 0) {
			for (int i = 0; i < seperatechaining.length; i++) {
				writer.write("[" + i + "]--->");
				this.seperatechaining[i].printList(writer);
			}
		}

	}

}
