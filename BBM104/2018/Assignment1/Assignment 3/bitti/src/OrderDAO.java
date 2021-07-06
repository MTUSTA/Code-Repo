import java.util.ArrayList;

public class OrderDAO implements IDataAccessObject2{
	private ArrayList<Order>orderlar = new ArrayList<Order>();

	@Override
	public Object getByID(int ID) {
		Object donme = null;
		for (Order emir : orderlar) {
			if ( emir.getSira() == ID) {
				donme=emir;
				break;
			}
		}
		return donme;
	}

	@Override
	public boolean deleteByID(int ID) {
		return false;
	}

	@Override
	public void add(int deger1,int deger2) {
		orderlar.add(new Order(deger1, deger2));
	}

	@Override
	public void update(Object object) {
		for (Order emir : orderlar) {
			if ( emir.getCusid() == ((Integer)object)) {
				orderlar.remove(emir);
				break;
			}
		}
		
	}

	@Override
	public ArrayList<Order> getALL() {
		return orderlar;
	}


}
