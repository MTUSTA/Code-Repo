
public class Personnel {
	private String Name, Surname, RegisterNumber, Position;
	private int YearOfStart;

	// constructor
	public Personnel(String name, String surname, String registerNumber, String position, int yearOfStart) {
		super();
		this.Name = name;
		this.Surname = surname;
		this.RegisterNumber = registerNumber;
		this.Position = position;
		this.YearOfStart = yearOfStart;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getRegisterNumber() {
		return RegisterNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		RegisterNumber = registerNumber;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public int getYearOfStart() {
		return YearOfStart;
	}

	public void setYearOfStart(int yearOfStart) {
		YearOfStart = yearOfStart;
	}

}
