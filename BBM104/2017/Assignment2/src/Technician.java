/** 
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_name the technician's name
 * @param alinan_e_mail	the technician's email
 * @param alinan_birthday the technician's birthday
 * @param alinan_salary	the technician's salary
 * @param alinan_senior the technician is senior or not
 * @see Employees_Person#Employees_Person(String, String, String, double)
 */
public class Technician extends Employees_Person{
	public int senior;
	public Technician(String alinan_name, String alinan_e_mail, String alinan_birthday, double alinan_salary,int alinan_senior) {
		super(alinan_name, alinan_e_mail, alinan_birthday, alinan_salary);
		senior = alinan_senior;
	}
}
