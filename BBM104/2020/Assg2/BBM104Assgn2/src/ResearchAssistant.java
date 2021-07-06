
public class ResearchAssistant extends Academician {
	/*no change*/
	private double addCourseFee = 0;
	public ResearchAssistant(String name, String surname, String registerNumber, String position, int yearOfStart) {
		super(name, surname, registerNumber, position, yearOfStart);
		// set SSbenefit base_salary % 105
		setSpecial_service_benefits((getBaseSalary()/100)*105);
	}
	//*	If a research assistant works more than 40 hours in a week, they will not be paid additional money.
	public void calculate_addCourseFee(int hours) {
		// this function do nothing
	}
}
