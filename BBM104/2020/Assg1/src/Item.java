import java.util.Date;

public class Item {
	//object variable
	public String product_name,type_of_membership;
	public Date start_date,end_date;
	public double price;
	// constructer
	public Item(String product_name, String type_of_membership, Date start_date, Date end_date, double price) {
		this.product_name = product_name;
		this.type_of_membership = type_of_membership;
		this.start_date = start_date;
		this.end_date = end_date;
		this.price = price;
	}
	
}
