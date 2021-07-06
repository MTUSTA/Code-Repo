
public class Chief extends FullTimeEmployee {
	
	//while Chiefs can work a maximum of 8 hours a week and are paid 15 TL per hour to gain overwork salary. 
	
	public Chief(String name, String surname, String registerNumber, String position, int yearOfStart) {
		super(name, surname, registerNumber, position, yearOfStart);
		//Chiefs are paid 125 TL per day
		this.setDayofwork(125);
	}
	//*	Chiefs work 40 hours per week, excluding the working hours to gain overwork salary and additional course fee.
	//(If they work more than their maximum hours, they will not be paid additional money for these extra hours).
	// method overriding
	public void calculate_overWorkSalary(int weekly_hours) {
		weekly_hours -= 40;
		//Workers can work a maximum of 8 hours a week and are paid 15 TL per hour
		//more than 0 and less than 9  ---> max 8
		if(weekly_hours > 0 && weekly_hours < 9) {
			setOverWorkSalary(getOverWorkSalary()+(weekly_hours*15));
		}
		else if(weekly_hours>8) {
			setOverWorkSalary(getOverWorkSalary()+(8*15));
		}
	}
	
}
