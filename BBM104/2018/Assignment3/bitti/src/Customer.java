public class Customer{
	
	private int Customer_id;
	private String Customer_Surname,Customer_Name,Customer_Phone_Number,Customer_Address;
	
	public Customer(int Customer_id,String Customer_Name ,String Customer_Surname,String Customer_Phone_Number,String Customer_Address) {
		this.Customer_id=Customer_id;
		this.Customer_Name=Customer_Name;
		this.Customer_Surname=Customer_Surname;
		this.Customer_Phone_Number=Customer_Phone_Number;
		this.Customer_Address=Customer_Address;	
	}
	public int getCustomer_id() {
		return Customer_id;
	}
	public void setCustomer_id(int customer_id) {
		Customer_id = customer_id;
	}
	public String getCustomer_Surname() {
		return Customer_Surname;
	}
	public void setCustomer_Surname(String customer_Surname) {
		Customer_Surname = customer_Surname;
	}
	public String getCustomer_Name() {
		return Customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		Customer_Name = customer_Name;
	}
	public String getCustomer_Phone_Number() {
		return Customer_Phone_Number;
	}
	public void setCustomer_Phone_Number(String customer_Phone_Number) {
		Customer_Phone_Number = customer_Phone_Number;
	}
	public String getCustomer_Address() {
		return Customer_Address;
	}
	public void setCustomer_Address(String customer_Address) {
		Customer_Address = customer_Address;
	}
}
