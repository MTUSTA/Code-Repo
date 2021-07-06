import java.util.Calendar;

public class FullTimeEmployee extends Employee {
	//Full-time Employees do not work at weekends. 
	private double dayOfWork;
	private double overWorkSalary=0;
	

	public FullTimeEmployee(String name, String surname, String registerNumber, String position, int yearOfStart) {
		super(name, surname, registerNumber, position, yearOfStart);
		// TODO Auto-generated constructor stub
	}
	
	//fulltimeemployee overworkSalary function ---> no purpose, only for inheritance
	public void calculate_overWorkSalary(int salary) {
		this.overWorkSalary = salary;
	}

	public double getDayofwork() {
		return dayOfWork;
	}

	public void setDayofwork(double dayOfWork) {
		this.dayOfWork = dayOfWork;
	}

	public double getOverWorkSalary() {
		return overWorkSalary;
	}

	public void setOverWorkSalary(double overWorkSalary) {
		this.overWorkSalary = overWorkSalary;
	}

}
