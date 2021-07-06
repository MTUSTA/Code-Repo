import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Stack {
	private int[] array = null;
	private int maxSize;
	private int top;

	public Stack(int size) {
		this.array = new int[size];
		this.maxSize = size;
		top = -1;
	}
	//adding element in stack
	public void push(int element) {
		// if not full, add element
		if (!isFull()) {
			this.array[++top] = element;
		} else {
			// if full special print
			System.out.println("full , no pushing");
		}

	}
	// remove element and return it 
	public int pop() {
		if (!isEmpty()) {
			return this.array[top--];

		} else {
			return 0;
		}

	}
	// if stack is not empty , return top value
	public int peek() {
		if (!isEmpty()) {
			return this.array[top];

		} else {
			return 0;
		}

	}
	// if stack is empty return true
	public boolean isEmpty() {
		return (top == -1);
	}
	// if stack is full return true
	public boolean isFull() {
		if (top >= maxSize - 1) {
			return true;
		}
		return false;
	}
	
	// copy stack
	public Stack copy() {

		Stack copy = new Stack(this.maxSize);

		// copy without reference
		copy.array = Arrays.copyOf(this.array,this.array.length);
		copy.maxSize = this.maxSize;
		copy.top = this.top;
		// return copy stack
		return copy;

	}
	// Sorting array
	public Stack sort() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		Stack s = new Stack(this.maxSize);
		while(!isEmpty()) {
			temp.add(pop());
		}
		//sorting
		Collections.sort(temp);
		
		// fills new stack
		for (int i = temp.size()-1; i >= 0; i--) {
			s.push(temp.get(i));
		}
		return s;
	}
}
