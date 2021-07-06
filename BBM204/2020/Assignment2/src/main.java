import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class main {
	public static FileWriter writer;
	
	public static void main(String[] args) throws IOException {
		// reader file object
		Reader rd = new Reader();
		// generate output.txt
		writer = new FileWriter("output.txt");
		// data objects
		ArrayList<Employee> employees = rd.readfile(args[0]);
		// parsing argument for load factor
		double load_factor1 = Double.parseDouble(args[1]);
		// hash table object for seperate chaining
		MyHashTable hashtable = new MyHashTable(employees.size(), load_factor1, 0);
		// insert all data object for seperate chaining
		writer.write(args[0].split("\\.")[0] + ",LF=" + args[1] + ",LF2=" + args[2] + "," + args[3]+"\n");
		writer.write("PART1\n");
		for (int i = 0; i < employees.size(); i++) {
			hashtable.put(employees.get(i), employees.get(i).phoneNumber);
		}
		//seperate chaining output
		hashtable.output(writer);
		
		writer.write("PART2\nHashtable for Linear Probing\n");
		double load_factor2 = Double.parseDouble(args[2]);
		MyHashTable hashtable2 = new MyHashTable(employees.size(), load_factor2, 1);
		for (int i = 0; i < employees.size(); i++) {
			hashtable2.put(employees.get(i), employees.get(i).phoneNumber);
		}
		//linear probing
		hashtable2.output(writer);
		writer.write("Hashtable for Double Hashing\n");
		MyHashTable hashtable3 = new MyHashTable(employees.size(), load_factor2, 2);
		for (int i = 0; i < employees.size(); i++) {
			hashtable3.put(employees.get(i), employees.get(i).phoneNumber);
		}
		// double hashing
		hashtable3.output(writer);
		
		// parse input
		int search_key = Integer.parseInt(args[3]);
		
		writer.write("SEPARATE CHAINING:\n");
		long nano_startTime = System.nanoTime();
		writer.write("Key found with ");
		hashtable.get(search_key);
		writer.write(" comparisons\n");
		long nano_endTime = System.nanoTime();
		writer.write("CPU time taken to search = " + Long.toString(nano_endTime - nano_startTime)+".0 ns\n");
		
		writer.write("LINEAR PROBING:\n");
		nano_startTime = System.nanoTime();
		writer.write("Key found with ");
		hashtable2.get(search_key);
		writer.write(" comparisons\n");
		nano_endTime = System.nanoTime();
		writer.write("CPU time taken to search = " + Long.toString(nano_endTime - nano_startTime)+".0 ns\n");
		
		writer.write("DOUBLE HASHING:\n");
		nano_startTime = System.nanoTime();
		writer.write("Key found with ");
		hashtable3.get(search_key);
		writer.write(" comparisons\n");
		nano_endTime = System.nanoTime();
		writer.write("CPU time taken to search = " + Long.toString(nano_endTime - nano_startTime)+".0 ns\n");
		
		writer.close();
	}
}
