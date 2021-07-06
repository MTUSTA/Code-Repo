import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class ReadFile {
	//file line reader method
	public String[] readfile(String path) {
		try {
			int i =0;
			int len = Files.readAllLines(Paths.get(path)).size();
			String[] results = new String[len];
			for (String line : Files.readAllLines(Paths.get(path))) {
				//delete newline character
				line = line.replace("\n", "").replace("\r", "");
				results[i++]= line;
			}
			return results;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//creates objects and add arraylist
	public void create_object(ArrayList<Customer> customers, ArrayList<Item> items,String[] price_list_file_lines2, String[] shopping_file_lines) {

		for (String string : price_list_file_lines2) {
			// split string according to the tab key
			String[] splitted_string = string.split("\t");

			// dates objects
			Date start_date = null;
			Date end_date = null;
			try {
				// convert string to date
				start_date = new SimpleDateFormat("dd.MM.yyyy").parse(splitted_string[2]);
				end_date = new SimpleDateFormat("dd.MM.yyyy").parse(splitted_string[3]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// convert string to double
			double price = Double.parseDouble(splitted_string[4]);
			// create object
			Item objt = new Item(splitted_string[0], splitted_string[1], start_date, end_date, price);
			// add object in items arraylist
			items.add(objt);

		}

		for (String string : shopping_file_lines) {
			// split string according to the tab key
			String[] splitted_string = string.split("\t");
			// date objects
			Date shopping_date = null;

			try {
				// convert string to date
				shopping_date = new SimpleDateFormat("dd.MM.yyyy").parse(splitted_string[2]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// split string --> name and surname
			String[] splitted_name = splitted_string[0].split(" ");
			// products array
			String[] product_array = Arrays.copyOfRange(splitted_string, 3, splitted_string.length);
			// creates customer object
			Customer objt = new Customer(splitted_name[0], splitted_name[1], splitted_string[1], shopping_date,	product_array);
			// add customer object in arraylist
			customers.add(objt);
		}

	}
}
