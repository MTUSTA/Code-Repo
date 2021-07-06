/**
 * @author Mehmet Taha USTA
 * @version 1.0, 12 April 2017
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

		users.add(new User(user_id_verilen,alinan_lines[0],alinan_lines[1],alinan_lines[2],alinan_lines[3],alinan_lines[4]));
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
	/**
	 * @param alinan_lines command line
	 * <p>this method provides user show posts</p>
	 * */
	public static void ShowPosts(String[] alinan_lines){
		int show_post_usersizlik=0;
		for(User user_showpost:UserCollection.users){
			if(user_showpost.getUsername().equals(alinan_lines[1])){
				show_post_usersizlik++;
				if(user_showpost.getCollection_of_posts().size()==0){
					System.out.println(alinan_lines[1]+" does not have any posts yet.");
					break;
				}
				if(user_showpost.getCollection_of_posts().size()!=0){
					System.out.println("**************");
					System.out.println(alinan_lines[1]+"'s Posts");
					System.out.println("**************");
					DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
					for(TextPost cikan_post:UserCollection.kullanici.getCollection_of_posts()){
						String startDateString2 = df2.format(cikan_post.getDate());
						if(cikan_post.getClass()==TextPost.class){
							System.out.println(cikan_post.getText());
							System.out.println("Date: "+startDateString2);
							cikan_post.show_post_location(cikan_post.getKonum());
							cikan_post.show_tagged_users(cikan_post.getCollection_of_tagged_friends());
							System.out.println("----------------------");
						}
						if(cikan_post.getClass()==ImagePost.class){
							System.out.println(cikan_post.getText());
							System.out.println("Date: "+startDateString2);
							cikan_post.show_post_location(cikan_post.getKonum());
							System.out.println("Image: "+ ((ImagePost)cikan_post).getImage_filename());
							System.out.print("Image resolution: "+((ImagePost)cikan_post).getImage_resolution()[0]);
							System.out.println(" x "+((ImagePost)cikan_post).getImage_resolution()[1]);
							cikan_post.show_tagged_users(cikan_post.getCollection_of_tagged_friends());
							System.out.println("----------------------");
						}

						if(cikan_post.getClass()==VideoPost.class){
							System.out.println(cikan_post.getText());
							System.out.println("Date: "+startDateString2);
							cikan_post.show_post_location(cikan_post.getKonum());
							System.out.println("Video: "+((VideoPost)cikan_post).getVideo_file_name());
							System.out.println("Video duration: "+((VideoPost)cikan_post).getVideo_duration()+" minutes");
							cikan_post.show_tagged_users(cikan_post.getCollection_of_tagged_friends());	
							System.out.println("----------------------");								
						}
						
					}

				}
			}
		}
		if(show_post_usersizlik==0){
			System.out.println("No such user!");
		}
	
	}

}