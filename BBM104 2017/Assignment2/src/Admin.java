/**
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017
 * @param alinan_name	the admin's name
 * @param alinan_e_mail	the admin's email
 * @param alinan_birthday the admin's birthday
 * @param alinan_salary	the admin's salary
 * @param alinan_password the admin's password
 * @see Employees_Person#Employees_Person(String, String, String, double)
 */
public class Admin extends Employees_Person{
	public String password;
	public Admin(String alinan_name, String alinan_e_mail, String alinan_birthday, double alinan_salary,String alinan_password) {
		super(alinan_name, alinan_e_mail, alinan_birthday, alinan_salary);
		password = alinan_password;
	}

}
