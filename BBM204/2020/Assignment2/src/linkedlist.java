import java.io.FileWriter;
import java.io.IOException;

/* Linked list Node */
class Node {
	// The linked lists will hold EmployeeData objects
	Employee data;

	Node next;

	// Constructor to create a new node Next is by default initialized as null
	Node(Employee d) {
		data = d;
	}
}

public class linkedlist {
	Node head; // head of list

	// Method to insert a new node
	public void insert(Employee data) {
		Node currNode = this.head;
		// Create a new node with given data
		Node new_node = new Node(data);
		new_node.next = null;

		// If the Linked List is empty, then make the new node as head
		if (currNode == null) {
			currNode = new_node;
		} else {
			// Else traverse till the last node and insert the new_node there
			Node last = this.head;
			while (last.next != null) {
				last = last.next;
			}
			// Insert the new_node at last node
			last.next = new_node;
		}
		this.head = currNode;
	}

	// Method to print the LinkedList.
	public void printList(FileWriter writer) throws IOException {
		Node currNode = this.head;

		// Traverse through the LinkedList
		if (currNode == null) {
			writer.write("Null");

		} else {
			while (currNode != null) {
				// Print the data at current node
				
				writer.write(Integer.toString(currNode.data.phoneNumber));
				// Go to next node
				currNode = currNode.next;
				if (currNode != null) {
					writer.write("---->");
				}
			}
		}
		writer.write("\n");
	}

	// Checks whether the value x is present in linked list
	public Employee search(int phonenumber) throws IOException {

		int comp = 0;
		Node current = this.head; // Initialize current
		while (current != null) {
			if (current.data.phoneNumber == phonenumber) {
				// retrieve (return) the full Employee object
				main.writer.write(Integer.toString(comp+1));
				return current.data; // data found
			}
			comp++;
			current = current.next;
		}
		return null; // data not found

	}
}
