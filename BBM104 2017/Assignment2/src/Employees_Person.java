/**
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_name	the person's name
 * @param alinan_e_mail	the person's email
 * @param alinan_birthday	the person's birthday
 * @param alinan_salary	the person's salary
 */
public class Employees_Person extends User{
	public double salary;
	
	public Employees_Person(String alinan_name, String alinan_e_mail, String alinan_birthday,double alinan_salary) {
		super(alinan_name, alinan_e_mail, alinan_birthday);
		salary = alinan_salary;
	}	

}
