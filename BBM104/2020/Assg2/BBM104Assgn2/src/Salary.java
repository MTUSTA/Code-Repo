import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Salary {
	
	// write the information of personnel in arraylist to the file
	public void calculate_write(ArrayList<Personnel> personel_objts) {
		for (Personnel personnel : personel_objts) {
			try {
				FileWriter writer = new FileWriter(personnel.getRegisterNumber() + ".txt");
				writer.write("Name : " + personnel.getName() + "\n");
				writer.write("Surname : " + personnel.getSurname() + "\n");
				writer.write("Registration Number : " + personnel.getRegisterNumber() + "\n");
				writer.write("Position : " + personnel.getPosition() + "\n");
				writer.write("Year of Start : " + personnel.getYearOfStart() + "\n");
				writer.write("Total Salary : " + calculate_all_money(personnel) + "0 TL");
				writer.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// According to the input, each person's salary is calculated with this function
	public double calculate_all_money(Personnel personnel) {
		double total=0;
			
			if(personnel.getClass() == FacultyMember.class) {
				//polimorf
				FacultyMember var1 = ((FacultyMember)personnel);				
				total += var1.getBaseSalary();
				total += var1.getSeverancePay();
				total += var1.getSpecial_service_benefits();
				total += var1.getAddCourseFee();
			}
			else if(personnel.getClass() == ResearchAssistant.class) {
				ResearchAssistant var1 = ((ResearchAssistant)personnel);
				total += var1.getBaseSalary();
				total += var1.getSeverancePay();
				total += var1.getSpecial_service_benefits();
			}
			else if(personnel.getClass() == Officer.class) {
				Officer var1 = ((Officer)personnel);
				total += var1.getBaseSalary();
				total += var1.getSeverancePay();
				total += var1.getSpecial_service_benefits();
				total += var1.getOverWorkSalary();
			}
			else if(personnel.getClass() == Security.class) {
				Security var1 = ((Security)personnel);
				total += var1.getHourOfWork();
				total += var1.getSeverancePay();
				//They do not work one day of the week
				// so 4 weeks and every week - 6 days --> 24 days
				total += var1.getFoodMoney()*24;
				total += var1.getTransMoney()*24;

			}
			else if(personnel.getClass() == PartTimeEmployee.class) {
				PartTimeEmployee var1 = ((PartTimeEmployee)personnel);
				total += var1.getHourOfWork();
				total += var1.getSeverancePay();
			}
			else if(personnel.getClass() == Worker.class) {
				Worker var1 = ((Worker)personnel);
				total += var1.getSeverancePay();
				// Dayofwork* 5 days in 1 week * 4 weeks
				total += var1.getDayofwork()*5*4;
				total += var1.getOverWorkSalary();

			}
			else if(personnel.getClass() == Chief.class) {
				Chief var1 = ((Chief)personnel);
				total += var1.getSeverancePay();
				// Dayofwork* 5 days in 1 week * 4 weeks
				total += var1.getDayofwork()*5*4;
				total += var1.getOverWorkSalary();

			}
		return total;
	}

}
