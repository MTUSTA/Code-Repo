import java.util.ArrayList;

public class Bill {

	public void create_bill(ArrayList<Customer> customers, ArrayList<Item> items) {
		// foreach for customer arraylist
		for (Customer custo : customers) {
			System.out.println("---" + custo.customer_name + " " + custo.surname + "---");
			// for total cost
			double total = 0;
			// for customer product list
			for (int i = 0; i < custo.product_list.length; i = i + 2) {
				// foreach for items arraylist
				for (Item item : items) {
					// compare name and compare type of membership
					if (item.product_name.compareTo(custo.product_list[i]) == 0	&& item.type_of_membership.compareTo(custo.type_of_membership) == 0) {
						// compare valid date
						if (custo.shopping_date.compareTo(item.start_date) >= 0 && custo.shopping_date.compareTo(item.end_date) <= 0) {
							//print product name
							System.out.print(custo.product_list[i] + "\t");
							// parse integer
							int piece = Integer.parseInt(custo.product_list[i + 1]);
							// compute price and print
							total += item.price * piece;
							System.out.println(item.price + "\t" + piece + "\t" + (item.price * piece));
						}
					}
				}
			}
			// print total
			System.out.println("Total" + "\t" + total);
		}
	}

}
