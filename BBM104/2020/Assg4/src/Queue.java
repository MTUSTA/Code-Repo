import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Queue {

	private int front, rear, size, capacity;
	private int[] array = null;

	public Queue(int capacity) {
		this.array = new int[capacity];
		this.capacity = capacity;
		this.front = this.size = 0;
		this.rear = capacity - 1;
	}

	// Queue is full when size becomes equal to the capacity
	boolean isFull() {
		return (this.size == this.capacity);
	}

	// Queue is empty when size is 0
	boolean isEmpty() {
		return (this.size == 0);
	}

	// Method to add an item to the queue.It changes rear and size
	public void enqueue(int item) {
		if (isFull()) {
			return;
		}
		this.rear = (this.rear + 1) % this.capacity;
		this.array[this.rear] = item;
		this.size = this.size + 1;
	}

	// Method to remove an item from queue. It changes front and size
	public int dequeue() {
		if (isEmpty()) {
			return Integer.MIN_VALUE;
		}
		int item = this.array[this.front];
		this.front = (this.front + 1) % this.capacity;
		this.size = this.size - 1;
		return item;
	}

	// Method to get front of queue
	public int front() {
		if (isEmpty()) {
			return Integer.MIN_VALUE;
		}

		return this.array[this.front];
	}

	// Method to get rear of queue
	public int rear() {
		if (isEmpty()) {
			return Integer.MIN_VALUE;
		}

		return this.array[this.rear];
	}
	// create copy queue without reference
	public Queue copy() {
		Queue copy = new Queue(this.capacity);
				
		// reload original queue
		copy.array = Arrays.copyOf( this.array,  this.array.length);
		copy.capacity = this.capacity;
		copy.front = this.front;
		copy.rear = this.rear;	
		copy.size = this.size;


		// return copy queue
		return copy;
	}
	// sort queue and return sorted queue
	public Queue sort() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		Queue q = new Queue(this.capacity);
		while(!isEmpty()) {
			temp.add(dequeue());
		}
		Collections.sort(temp);
		for (Integer integer : temp) {
			q.enqueue(integer);
		}
		return q;
	}

}
