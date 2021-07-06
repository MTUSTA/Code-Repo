import java.util.Calendar;

public class Employee extends Personnel{
	private double severancePay = (Calendar.getInstance().get(Calendar.YEAR)-getYearOfStart())*20*8/10;
	public Employee(String name, String surname, String registerNumber, String position, int yearOfStart) {
		super(name, surname, registerNumber, position, yearOfStart);
	}
	public double getSeverancePay() {
		return severancePay;
	}
	public void setSeverancePay(double severancePay) {
		this.severancePay = severancePay;
	}

}
