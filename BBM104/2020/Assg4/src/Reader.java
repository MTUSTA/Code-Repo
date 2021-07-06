
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Reader {
	Queue queue_structure = new Queue(100);
	Stack stack_structure = new Stack(100);
	FileWriter Qwriter = null;
	FileWriter Swriter = null;
	
	// line reader
	public String[] readfile(String path) {

		try {
			int i = 0;
			int len = Files.readAllLines(Paths.get(path)).size();
			String[] results = new String[len];
			for (String line : Files.readAllLines(Paths.get(path))) {
				// delete newline character
				line = line.replace("\n", "").replace("\r", "");
				results[i++] = line;
			}
			return results;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void print_Stack() throws IOException {
		Stack s = stack_structure.copy();
		while(!s.isEmpty()) {
			Swriter.write(s.pop()+ " ");
		}
		Swriter.write("\n");
	}
	

	public void print_Queue() throws IOException {
		Queue q = queue_structure.copy();
		
		while(!q.isEmpty()) {
			Qwriter.write(q.dequeue()+ " ");
		}		
		Qwriter.write("\n");
	}

	public void process(String[] lines) throws IOException {
		Qwriter = new FileWriter(new File("queueOut.txt"));
		Swriter = new FileWriter(new File("stackOut.txt"));
		
		for (String line : lines) {
			String[] splitted_s = line.split(" ");
			if (splitted_s[0].compareTo("Q") == 0) {
				// remove gretar
				if (splitted_s[1].compareTo("removeGreater") == 0) {
					//empty queue
					Queue temp = new Queue(100);
					// value to compare
					int compvalue = Integer.parseInt(splitted_s[2]);
					// clear queue
					while (!queue_structure.isEmpty()) {
						int dequeued = queue_structure.dequeue();
						//compare 2 value
						if (dequeued <= compvalue) {
							//addign temp
							temp.enqueue(dequeued);
						}

					}
					// save result queue
					queue_structure = temp;
					Qwriter.write("After removeGreater "+compvalue+":\n");
					print_Queue();

				} else if (splitted_s[1].compareTo("calculateDistance") == 0) {
					int total_distance = 0;
					// copy queue
					Queue temp = queue_structure.copy();
					while (!temp.isEmpty()) {
						// copy queue for calculate distance
						int x = temp.dequeue();
						Queue temp2 = temp.copy();
						//calculate distance every elements
						while (!temp2.isEmpty()) {
							int element = temp2.dequeue();
							int result = x - element;
							if (result < 0) {
								result *= -1;
							}
							total_distance += result;
						}
					}
					Qwriter.write("After calculateDistance:\n" + "Total distance="+total_distance+"\n");

				} else if (splitted_s[1].compareTo("addOrRemove") == 0) {
					int value = Integer.parseInt(splitted_s[2]);
					if (value < 0) {
						//remove value
						for (int i = 0; i < value * -1; i++) {
							queue_structure.dequeue();
						}
					} else if (value > 0) {
						for (int i = 0; i < value; i++) {
							// add value random (between 0-50)
							queue_structure.enqueue(new Random().nextInt(51));
						}
					}
					Qwriter.write("After addOrRemove "+ value+"\n");
					print_Queue();

				} else if (splitted_s[1].compareTo("reverse") == 0) {
					Stack temp = new Stack(100);
					Queue q = new Queue(100);
					// reverse number
					int number = Integer.parseInt(splitted_s[2]);
					//dequeue numbers according to input
					for (int i = 0; i < number; i++) {
						temp.push(queue_structure.dequeue());
					}
					//enqueue from stack with pop method
					while(!temp.isEmpty()) {
						q.enqueue(temp.pop());
					}
					// adding end of the new queue
					while(!queue_structure.isEmpty()) {
						q.enqueue(queue_structure.dequeue());
					}
					//save data
					queue_structure = q;
					Qwriter.write("After reverse " + number+ "\n");
					print_Queue();
					
				} else if (splitted_s[1].compareTo("sortElements") == 0) {
					// self function for sorting queue
					queue_structure = queue_structure.sort();
					Qwriter.write("After sortElements\n");
					print_Queue();

				} else if (splitted_s[1].compareTo("distinctElements") == 0) {
					// helper object -> no duplicate elements
					HashSet<Integer> hash = new HashSet<Integer>();
					Queue temp = queue_structure.copy();
					while (!temp.isEmpty()) {
						hash.add(temp.dequeue());
					}
					Qwriter.write("After distinctElements:\n" + "Total distinct element="+hash.size()+"\n");

				}
			} 
			
			
			
			
			else if (splitted_s[0].compareTo("S") == 0) {
				if (splitted_s[1].compareTo("removeGreater") == 0) {
					// new stack
					Stack temp = new Stack(100);
					// value to compare
					int compvalue = Integer.parseInt(splitted_s[2]);
					// clear stack
					while (!stack_structure.isEmpty()) {
						int popped = stack_structure.pop();
						
						// compare 2 value
						if (popped <= compvalue) {
							temp.push(popped);
						}
					}
					Stack new1 = new Stack(100);
					while(!temp.isEmpty()) {
						new1.push(temp.pop());
					}
					
					
					// save temp stack
					stack_structure = new1;
					Swriter.write("After removeGreater " + compvalue+":\n");
					print_Stack();
				} else if (splitted_s[1].compareTo("calculateDistance") == 0) {
					int total_distance = 0;
					Stack temp = stack_structure.copy();

					while (!temp.isEmpty()) {
						// copy queue for calculate distance

						int x = temp.pop();
						Stack temp2 = temp.copy();

						while (!temp2.isEmpty()) {
							int element = temp2.pop();
							int result = x - element;
							if (result < 0) {
								result *= -1;
							}
							total_distance += result;
						}
					}
					Swriter.write("After calculateDistance:\n");
					Swriter.write("Total distance="+total_distance+"\n");

				} else if (splitted_s[1].compareTo("addOrRemove") == 0) {
					int value = Integer.parseInt(splitted_s[2]);
					if (value < 0) {
						for (int i = 0; i < value * -1; i++) {
							stack_structure.pop();
						}
					} else if (value > 0) {
						for (int i = 0; i < value; i++) {
							// (between 0-50)
							stack_structure.push(new Random().nextInt(51));
						}
					}
					Swriter.write("After addOrRemove "+ value+":\n");
					print_Stack();

				} else if (splitted_s[1].compareTo("reverse") == 0) {
					int value = Integer.parseInt(splitted_s[2]);
					//final stack
					Stack newstack = new Stack(100);
					//reversed
					ArrayList<Integer> temp = new ArrayList<Integer>();
					
					for (int i = 0; i < value; i++) {
						temp.add(0, stack_structure.pop());
					}

					while(!stack_structure.isEmpty()) {
						temp.add(stack_structure.pop());
					}
					for (int i = temp.size()-1; i >= 0 ; i--) {
						newstack.push(temp.get(i));
					}

					stack_structure = newstack;
					Swriter.write("After reverse "+value+":\n");
					print_Stack();
					

				} else if (splitted_s[1].compareTo("sortElements") == 0) {
					stack_structure = stack_structure.sort();
					Swriter.write("After sortElements:\n");
					print_Stack();

				} else if (splitted_s[1].compareTo("distinctElements") == 0) {
					HashSet<Integer> hash = new HashSet<Integer>();
					Stack temp = stack_structure.copy();
					while (!temp.isEmpty()) {
						hash.add(temp.pop());
					}
					Swriter.write("After distinctElements:\nTotal distinct element=");
					Swriter.write(hash.size()+"\n");					
				}
			}
		}
		
		Qwriter.close();
		Swriter.close();

	}

	public void read_queue() {
		String[] q = readfile("queue.txt");
		for (int i = 0; i < q.length; i++) {
			String[] split_q = q[i].split(" ");
			for (int j = 0; j < split_q.length; j++) {
				// filling queue
				queue_structure.enqueue(Integer.parseInt(split_q[j]));
			}
		}
	}

	public void read_stack() {
		String[] s = readfile("stack.txt");
		for (int i = 0; i < s.length; i++) {

			String[] split_s = s[i].split(" ");
			for (int j = split_s.length-1; j >= 0 ; j--) {
				// filling stack
				stack_structure.push(Integer.parseInt(split_s[j]));
			}

		}
	}

}
