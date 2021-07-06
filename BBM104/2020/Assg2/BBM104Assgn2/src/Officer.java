import java.util.Calendar;

public class Officer extends Personnel {
	private double BaseSalary = 2600;
	private double special_service_benefits;
	private double severancePay=(Calendar.getInstance().get(Calendar.YEAR)-getYearOfStart())*20*8/10;
	private double overWorkSalary = 0;
	public Officer(String name, String surname, String registerNumber, String position, int yearOfStart) {
		super(name, surname, registerNumber, position, yearOfStart);
		this.special_service_benefits = ((this.BaseSalary/100)*65);
	}
	//	Officers, work 40 hours per week, excluding the working hours to gain overwork salary and additional course fee.But, he/she will be paid up to 10 hours a week.
	public void calculate_overWorkSalary(int hours) {
		hours -= 40;
		//less than 10
		if(hours > 0 && hours < 11) {
			this.overWorkSalary += hours*20;
		}
		//10 or more
		else if(hours>10){
			this.overWorkSalary += 10*20;
		}		
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
	public double getOverWorkSalary() {
		return overWorkSalary;
	}
	public void setOverWorkSalary(double overWorkSalary) {
		this.overWorkSalary = overWorkSalary;
	}
	public double getBaseSalary() {
		return BaseSalary;
	}
	public void setBaseSalary(double baseSalary) {
		BaseSalary = baseSalary;
	}
}
