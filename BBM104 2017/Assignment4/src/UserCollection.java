/**
 * @author Mehmet Taha USTA
 * @version 1.0, 12 April 2017
 */

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;



public class UserCollection{
	/**
	 * <p>This list keeps users</p>
	 * */
	public static List<User> users = new ArrayList<User>();
	/**
	 * <p>This integer variable gives id number for users</p>
	 * */
	public static int user_id_verilen=1;
	/**
	 * <p>This User variable keeps the user entering the system in memory</p>
	 * */
	public static User kullanici;
	/**
	 * <p>This integer variable determines the presence of the person entering the system</p>
	 * */
	public static int giren_kisi=0;
	/**
	 * @param alinan_lines	command line
	 * <p>this method provides add user</p>
	 * @throws ParseException	if convert false for date convert,this throws ParseException 
	 * */
	public static void addUser(String[] alinan_lines) throws ParseException{

		users.add(new User(user_id_verilen,alinan_lines[0],alinan_lines[1],alinan_lines[2],alinan_lines[3],alinan_lines[4],alinan_lines[5]));
		user_id_verilen++;
	}
	/**
	 * @param alinan_lines command line
	 * <p>this method provides remove user</p>
	 * */
	public static void remove(String[] alinan_lines){
		int silememe=0;
		int remove_id=Integer.parseInt(alinan_lines[1]);
		for(User users_remove:users){
			if(users_remove.getUser_id()==remove_id){
				silememe++;
				users.remove(users_remove);	
				System.out.println("User has been successfully removed.");
				break;
			}
		}
		if(silememe==0){
			System.out.println("No such user!");
		}		
	}
	/**
	 * @param alinan_lines command line
	 * <p>this method provides user sign in</p>
	 * */
	public static void userSignIn(String[] alinan_lines){
		int usersizlik=0;
		for(User user_sign_in: users){
			if(alinan_lines[1].equals(user_sign_in.getUsername())){
				usersizlik++;
				kullanici=user_sign_in;
				kullanici.sifrekontrol(alinan_lines[2]);
			}
		}
		if(usersizlik==0&&giren_kisi==0){
			System.out.println("No such user!");
		}	

	
	}
	
}