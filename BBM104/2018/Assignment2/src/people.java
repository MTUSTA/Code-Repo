public class people {
	private int personID, weight, height, dateOfBirth,caloriestaken=0,calorisburned=0,kullanilan=0;
	private String name;
	private String gender;

	public people(int personid,String name ,String gender,int weight,int height,int dateOfBirth) {
		this.setPersonID(personid);
		this.setName(name);
		this.setGender(gender);
		this.setWeight(weight);		
		this.setHeight(height);
		this.setDateOfBirth(2018-dateOfBirth);					
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public int getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(int dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getCaloriestaken() {
		return caloriestaken;
	}

	public void setCaloriestaken(int caloriestaken) {
		this.caloriestaken = caloriestaken;
	}

	public int getCalorisburned() {
		return calorisburned;
	}

	public void setCalorisburned(int calorisburned) {
		this.calorisburned = calorisburned;
	}

	public int getKullanilan() {
		return kullanilan;
	}

	public void setKullanilan(int kullanilan) {
		this.kullanilan = kullanilan;
	}
}
