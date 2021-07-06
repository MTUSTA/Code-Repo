
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		// object item list for pricelist.txt
		ArrayList<Item> items = new ArrayList<Item>();
		// object customer list for shoppinglist.txt
		ArrayList<Customer> customers = new ArrayList<Customer>();
		// reader object
		ReadFile reader = new ReadFile();
		// Bill object
		Bill pay = new Bill();
		// reads pricelist.txt
		String[] price_list_file_lines2 = reader.readfile(args[1]);
		// reads shoppinglist.txt
		String[] shopping_file_lines = reader.readfile(args[0]);
		// creates objects and add arraylist
		reader.create_object(customers, items, price_list_file_lines2, shopping_file_lines);
		// calculating and print on console
		pay.create_bill(customers, items);
	}

}
