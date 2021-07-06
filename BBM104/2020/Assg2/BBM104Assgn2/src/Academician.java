import java.util.Calendar;

public class Academician extends Personnel {
	private double BaseSalary = 2600;
	private double special_service_benefits;
	// (currentyear - yearofstart)*20*(0,8) 
	private double severancePay = (Calendar.getInstance().get(Calendar.YEAR)-getYearOfStart())*20*8/10;
	//special service benefits
	public Academician(String name, String surname, String registerNumber, String position, int yearOfStart) {
		super(name, surname, registerNumber, position, yearOfStart);
	}
	//*	Academicians, work 40 hours per week, excluding the working hours to gain overwork salary and additional course fee.
	public double getBaseSalary() {
		return BaseSalary;
	}
	public void setBaseSalary(double baseSalary) {
		BaseSalary = baseSalary;
	}
	public double getSpecial_service_benefits() {
		return special_service_benefits;
	}
	public void setSpecial_service_benefits(double special_service_benefits) {
		this.special_service_benefits = special_service_benefits;
	}
	public double getSeverancePay() {
		return severancePay;
	}
	public void setSeverancePay(double severancePay) {
		this.severancePay = severancePay;
	}


}
