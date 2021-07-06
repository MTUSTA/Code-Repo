import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Reader { // Read Class

	public ArrayList<Employee> readfile(String Argument) {

		ArrayList<Employee> employees = new ArrayList<Employee>();

		try { 
			//Scanner object is created & take argument
			Scanner scan = new Scanner(new File(Argument));
			String firstline = scan.nextLine(); // read line for ignore first line to adding in arraylist [E_Code, NRIC, Phone]
			// Checking line
			while (scan.hasNextLine()) {
				String line = scan.nextLine(); // read line
				String[] split_line = line.split(" "); // split line

				if(split_line.length == 3) {
					// create employee data object
					Employee emp = new Employee(split_line[0], split_line[1], Integer.parseInt(split_line[2]));
					employees.add(emp); // adding array list
					//System.out.println(Arrays.toString(split_line)+" "+split_line.length);
				}
			}
			scan.close();
			return employees;

		} catch (FileNotFoundException ex) {
			System.out.println("No File Found!");
			return null;

		}
	}

}
