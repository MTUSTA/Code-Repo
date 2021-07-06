import java.util.ArrayList;
import java.util.List;
/**
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_name	the customer's name
 * @param alinan_e_mail	the customer's email
 * @param alinan_birthday	the customer's birthday
 * @param alinan_customer_id	the customer's customer id
 * @param alinan_sifre	the customer's password
 * @param alinan_bakiye	the customer's balance
 * @param alinan_status all customers have CLASSIC status but if customer spends enough money,customer statu change
 * @param alinan_shopping_cart the customer's shopping cart
 * @param alinan_order_history the customer's order history
 * @see User#User(String, String, String)
 */

public class Customer extends User{
	public double bakiye;
	public String sifre,Status;
	
	int customer_id;
	public List<Object> shopping_cart = new ArrayList<Object>();
	public List<Object> order_history = new ArrayList<Object>();
	
	
	public Customer(String alinan_name, String alinan_e_mail, String alinan_birthday,int alinan_customer_id,String alinan_sifre,double alinan_bakiye,String alinan_status,Object alinan_shopping_cart,Object alinan_order_history) {
		super(alinan_name, alinan_e_mail, alinan_birthday);
		bakiye = alinan_bakiye;
		sifre = alinan_sifre;
		Status= alinan_status;
		customer_id = alinan_customer_id;
		order_history.add(alinan_order_history);	
		shopping_cart.add(alinan_shopping_cart);
		shopping_cart.clear();
		order_history.clear();

	}
}
