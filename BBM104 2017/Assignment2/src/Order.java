import java.util.Date;
/**
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_orderdate if item sell,orderdate take current time
 * @param alinan_totalcost item total cost
 * @param alinan_purchased_items Number of items sold
 * @param alinan_customerid the computer's id
 **/

public class Order {
	public Date orderDate;
	public int totalCost,customerid,purchased_items;
	public Order(Date alinan_orderdate,int alinan_totalcost,int alinan_purchased_items,int alinan_customerid){
		orderDate=alinan_orderdate;
		totalCost=alinan_totalcost;
		customerid=alinan_customerid;
		purchased_items=alinan_purchased_items;
		
	}
}