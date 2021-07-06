
public class Worker extends FullTimeEmployee {
	
	public Worker(String name, String surname, String registerNumber, String position, int yearOfStart) {
		super(name, surname, registerNumber, position, yearOfStart);
		//Workers are paid 105 per day
		this.setDayofwork(105);
	}
	//*	Workers work 40 hours per week, excluding the working hours to gain overwork salary and additional course fee.
	//(If they work more than their maximum hours, they will not be paid additional money for these extra hours).
	// method overriding
	public void calculate_overWorkSalary(int weekly_hours) {
		weekly_hours -= 40;
		
		//Workers can work a maximum of 10 hours a week and are paid 11 TL per hour
		//more than 0 and less than 11  ---> max 10
		if(weekly_hours > 0 && weekly_hours < 11) {
			setOverWorkSalary(getOverWorkSalary()+(weekly_hours*11));
		}
		else if(weekly_hours>10) {
			setOverWorkSalary(getOverWorkSalary()+(10*11));
		}
	}

}
