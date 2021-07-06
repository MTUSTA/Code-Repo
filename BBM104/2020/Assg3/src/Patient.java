
public class Patient {
	private int patiend_id;
	private String name,surname,address,phone_number;
	
	public Patient(int patiend_id, String name, String surname, String phone_number,String address) {
		this.patiend_id = patiend_id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone_number = phone_number;
	}
	public int getPatiend_id() {
		return patiend_id;
	}
	public void setPatiend_id(int patiend_id) {
		this.patiend_id = patiend_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

}
