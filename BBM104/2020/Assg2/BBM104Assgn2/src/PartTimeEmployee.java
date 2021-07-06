
public class PartTimeEmployee extends Employee {
	private double hourOfWork;
		
	//Part-time Employees can work a minimum of 10 hours and a maximum of 20 hours a week and they are paid 18 TL per hour.
	public PartTimeEmployee(String name, String surname, String registerNumber, String position, int yearOfStart) {
		super(name, surname, registerNumber, position, yearOfStart);
	}
	//Part-time Employees can work a minimum of 10 hours and a maximum of 20 hours a week and they are paid 18 TL per hour.
	public void calculate_overWorkSalary(int weekly_hours) {
		//Workers can work a maximum of 10 hours a week and are paid 18 TL per hour
		//more than 9 and less than 21  ---> max 20
		if(weekly_hours > 9 && weekly_hours < 21) {
			this.hourOfWork += (weekly_hours*18);
		}
		else if(weekly_hours>20) {
			this.hourOfWork += (20*18);
		}
	}
	/**
	 * @return the hourOfWork
	 */
	public double getHourOfWork() {
		return hourOfWork;
	}
	/**
	 * @param hourOfWork the hourOfWork to set
	 */
	public void setHourOfWork(double hourOfWork) {
		this.hourOfWork = hourOfWork;
	}
}
