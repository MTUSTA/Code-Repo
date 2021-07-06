import java.util.Date;

public class Customer {
	public String customer_name,surname,type_of_membership;
	public Date shopping_date;
	public String[] product_list;
	
	public Customer(String customer_name, String surname, String type_of_membership, Date shopping_date,String[] product_list) {
		this.customer_name = customer_name;
		this.surname = surname;
		this.type_of_membership = type_of_membership;
		this.shopping_date = shopping_date;
		this.product_list = product_list;
	}

}
