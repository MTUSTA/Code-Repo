import java.util.ArrayList;

public class Order {
	private ArrayList<Object>orders = new ArrayList<Object>();
	private int sira,cusid;

	public Order(int id,int Customer_id) {
		setSira(id);
		setCusid(Customer_id);
	}

	public int getSira() {
		return sira;
	}

	public void setSira(int sira) {
		this.sira = sira;
	}

	public int getCusid() {
		return cusid;
	}

	public void setCusid(int cusid) {
		this.cusid = cusid;
	}

	public ArrayList<Object> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Object> orders) {
		this.orders = orders;
	}

}
