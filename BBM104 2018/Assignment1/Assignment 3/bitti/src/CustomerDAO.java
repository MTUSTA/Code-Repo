import java.util.ArrayList;

public class CustomerDAO implements IDataAccessObject {
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	
	/*returns customer object*/
	@Override
	public Object getByID(int ID) {
		for(Customer customer1:customers) {
			if(customer1.getCustomer_id()==ID) {		
				return customer1;
			}
		}
		return null;
	}
	/*delete customer object in customers arraylist*/
	@Override
	public boolean deleteByID(int ID) {
		
		for(Customer customer1:customers) {
			if(customer1.getCustomer_id()==ID) {
				customers.remove(customer1);
				return true;
			}
		}
		return false;
	}
	/*create customer object and add in customers arraylist */
	@Override
	public void add(Object musteri) {
		String[] line2 = ((String)musteri).split(":");
		String[] line3 = line2[0].split(" ");
		int cusid = Integer.parseInt(line3[0]);
		customers.add(new Customer(cusid, line3[1], line3[2], line3[3], line2[1]));
	}
	@Override
	public void update(Object object) {	
	}
	/*returns customers arraylist sorted by name*/
	@Override
	public ArrayList<Customer> getALL() {
		/*Sort the items in the arraylist by name*/
		int a, b;
		Customer temp;
		int sortTheNumbers = customers.size() - 1;
		for (a = 0; a < sortTheNumbers; ++a) {
		    for (b = 0; b < sortTheNumbers; ++b) {
		        if (customers.get(b).getCustomer_Name().compareTo(customers.get(b+1).getCustomer_Name())>0 ) {
		            temp = customers.get(b);
		            customers.set(b, customers.get(b+1));
		            customers.set(b+1, temp);
		        }
		    }
		}
		return customers;
	}
	/*returns customers arraylist sorted by id*/
	@Override
	public ArrayList<Customer> getALL2() {
		/*Sort the items in the arraylist by id*/
		int a, b;
		Customer temp;
		int sortTheNumbers = customers.size() - 1;
		for (a = 0; a < sortTheNumbers; ++a) {
		    for (b = 0; b < sortTheNumbers; ++b) {
		        if (customers.get(b).getCustomer_id()>customers.get(b+1).getCustomer_id() ) {
		            temp = customers.get(b);
		            customers.set(b, customers.get(b+1));
		            customers.set(b+1, temp);
		        }
		    }
		}
		return customers;
	}
}
