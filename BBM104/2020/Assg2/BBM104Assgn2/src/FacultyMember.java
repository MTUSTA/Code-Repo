

public class FacultyMember extends Academician {
	private double addCourseFee = 0;
	public FacultyMember(String name, String surname, String registerNumber, String position, int yearOfStart) {
		super(name, surname, registerNumber, position, yearOfStart);
		// set SSbenefit base_salary % 135
		setSpecial_service_benefits((getBaseSalary()/100)*135);
	}
	//*	If a faculty member worked more than 40 hours a week, it means that he gives additional course that week. But, he/she will be paid up to 8 hours a week.
	public void calculate_addCourseFee(int hours) {
		hours -=40;
		//less than 8 and more than 0
		if(hours > 0 && hours < 9) {
			this.addCourseFee += hours*20;
		}
		//more 8
		else if(hours>8){
			this.addCourseFee += 8*20;
		}		
	}
	public double getAddCourseFee() {
		return addCourseFee;
	}
	public void setAddCourseFee(double addCourseFee) {
		this.addCourseFee = addCourseFee;
	}


}
