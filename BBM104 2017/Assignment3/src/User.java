/**
 * @author Mehmet Taha USTA
 * @version 1.0, 12 April 2017
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	private int user_id;
	private String password,name,username,school_or_educate,date_of_birth;
	public String last_login_date;
	private List<String> collection_of_friends = new ArrayList<String>();
	private List<String> collection_of_blocked_users = new ArrayList<String>();
	private List<TextPost> collection_of_posts = new ArrayList<TextPost>();
	/**
	 * @param user_id	the user's id
	 * @param alinan_name 	the user's name
	 * @param alinan_username	the user's username
	 * @param alinan_password	the user's password
	 * @param alinan_date_of_birth	the user's birthday
	 * @param alinan_school_or_educate	the user's school
	 * */

	public User(int user_id ,String alinan_name,String alinan_username,String alinan_password,String alinan_date_of_birth,String alinan_school_or_educate){
		setUser_id(user_id);
		setName(alinan_name);
		setUsername(alinan_username);
		setPassword(alinan_password);
		setSchool_or_educate(alinan_school_or_educate);
		setDate_of_birth(alinan_date_of_birth);
		
	}
	/**
	 * @return	the user's school
	 * */
	public String getSchool_or_educate() {
		return school_or_educate;
	}
	/**
	 * </p>this method sets school </p>
	 * */
	public void setSchool_or_educate(String school_or_educate) {
		this.school_or_educate = school_or_educate;
	}
	/**
	 * @return	the user's birthday
	 * */
	public String getDate_of_birth() {
		return date_of_birth;
	}
	/**
	 * </p>this method sets birthday </p>
	 * */
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	/**
	 * @return	the user's name
	 * */
	public String getName() {
		return name;
	}
	/**
	 * </p>this method sets name </p>
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return	the user's username
	 * */
	public String getUsername() {
		return username;
	}
	/**
	 * </p>this method sets username </p>
	 * */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * </p>this method sets password </p>
	 * */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return	the user's user id
	 * */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * </p>this method sets user id </p>
	 * */
	public void setUser_id(int user_id) {
		this.user_id = user_id;	
	}
	/**
	 * @return	the user's friends
	 * */
	public List<String> getCollection_of_friends() {
		return collection_of_friends;
	}
	/**
	 * </p>this method sets friends </p>
	 * */
	public void setCollection_of_friends(List<String> collection_of_friends) {
		this.collection_of_friends = collection_of_friends;
	}
	/**
	 * @return	the user's blocked users
	 * */
	public List<String> getCollection_of_blocked_users() {
		return collection_of_blocked_users;
	}
	/**
	 * </p>this method sets blocked user </p>
	 * */
	public void setCollection_of_blocked_users(List<String> collection_of_blocked_users) {
		this.collection_of_blocked_users = collection_of_blocked_users;
	}	
	/**
	 * @return	the user's posts
	 * */
	public List<TextPost> getCollection_of_posts() {
		return collection_of_posts;
	}
	/**
	 * </p>this method sets posts </p>
	 * */
	public void setCollection_of_posts(List<TextPost> collection_of_posts) {
		this.collection_of_posts = collection_of_posts;
	}
	/**
	 * </p>this method provides user sign out </p>
	 * */
	public void userSignOut(){
		UserCollection.giren_kisi=0;
		System.out.println("You have successfully signed out."); 
	}
	/**
	 * </p>this method provides user's check password.if password is true,user sign in </p>
	 * @param gelen_lines command line
	 * */
	public void sifrekontrol(String gelen_lines){
		int eslesmeme = 0;
		if(gelen_lines.equals(UserCollection.kullanici.password)){
			System.out.println("You have successfully signed in.");
			eslesmeme++;
			UserCollection.giren_kisi++;	
			
			DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			Date date= new Date();
			last_login_date=dateformat.format(date);
		}
		if(eslesmeme==0 && UserCollection.giren_kisi==0){
		System.out.println("Invalid username or password! Please try again.");
		}
		
	}
	/**
	 * </p>this method provides user can update your own profile </p>
	 * @param gelen_lines command line
	 * @throws ParseException if convert false for date convert,this throws ParseException 
	 * */
	public void updateProfile(String[] alinan_lines) throws ParseException {
		if(UserCollection.giren_kisi==1){
			for(User user_list_update:UserCollection.users){
				if(user_list_update.user_id==UserCollection.kullanici.user_id){		
					user_list_update.setName(alinan_lines[1]);
					user_list_update.setDate_of_birth(alinan_lines[2]);
					user_list_update.setSchool_or_educate(alinan_lines[3]);
					System.out.println("Your user profile has been successfully updated.");
					break;
				}
			}
		}
		if(UserCollection.giren_kisi==0){
			System.out.println("Error: Please sign in and try again.");		 
		}	
	}
	/**
	 * </p>this method provides user can change your own password</p>
	 * @param gelen_lines command line
	 * 
	 * */
	public void chPass(String[] alinan_lines) {	
		int uyusmazlik=0;
		if(UserCollection.giren_kisi==1){
			if(UserCollection.kullanici.password.equals(alinan_lines[1])){
				UserCollection.kullanici.setPassword(alinan_lines[2]);
				uyusmazlik++;
			}
		}
		if(uyusmazlik==0&&UserCollection.giren_kisi==1){
			System.out.println("Password mismatch! Please, try again.");		 
		}
		if(UserCollection.giren_kisi==0){
			System.out.println("Error: Please sign in and try again. ");
		}
	}
/**
 *  </p>this method provides list users or user</p>
 * @throws Exception	df.parse(list_user.date_of_birth) is throwing unparseable date exception: 	
 * */	
	public void listUser() throws Exception{
		if(UserCollection.giren_kisi==1){
			 DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			 DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy"); 
			for(User list_user:UserCollection.users){
				Date startDate = df.parse(list_user.date_of_birth);
				String startDateString2 = df2.format(startDate);
			
				System.out.println("Name: "+list_user.name);
				System.out.println("Username: "+list_user.username);
				System.out.println("Date of Birth: "+startDateString2);
				System.out.println("School: "+list_user.school_or_educate);
				System.out.println("---------------------------");
			}
		}
		if(UserCollection.giren_kisi==0){
			System.out.println("Error: Please sign in and try again.");
		}
	}
	/**
	 *  </p>this method provides user adds friend in own list</p>
	 * @param alinan_lines command line
	 * */	
	public void addFriend(String[] alinan_lines){
		int fazlalik=0;
		int user_bulamama=0;
		if(UserCollection.giren_kisi==1){
			for(User such_user_add_friend:UserCollection.users){
				if(such_user_add_friend.username.equals(alinan_lines[1])){
					user_bulamama++;
					break;		
				}
			}
			for(String arkadas_liste:((User)UserCollection.kullanici).collection_of_friends){
				if(arkadas_liste.equals(alinan_lines[1])&&user_bulamama==1){
					fazlalik++;
					System.out.println("This user is already in your friend list!");
					break;
				}
			}
			if(fazlalik==0&&user_bulamama==1){
				UserCollection.kullanici.collection_of_friends.add(alinan_lines[1]);
				System.out.println(alinan_lines[1]+" has been successfully added to your friend list.");
			}
			if(user_bulamama==0){
				System.out.println("No such user!");
			}
		}	
		if(UserCollection.giren_kisi==0){
			System.out.println("Error: Please sign in and try again.");
		}
	}	
	/**
	 *  </p>this method provides user removes friend in own list</p>
	 * @param alinan_lines command line
	 * */	
	public void removeFriend(String[] alinan_lines){
		int no_friend=0;
		if(UserCollection.giren_kisi==1){
			for(String remove_friend:UserCollection.kullanici.collection_of_friends){
				int yer=UserCollection.kullanici.collection_of_friends.indexOf(remove_friend);
				if(remove_friend.equals(alinan_lines[1])){
					no_friend++;
					UserCollection.kullanici.collection_of_friends.remove(yer);
					System.out.println(remove_friend+" has been successfully removed from your friend list.");
					break;					
				}	
			}
		}
		if(UserCollection.giren_kisi==1&&no_friend==0){
			System.out.println("No such friend!");
		}
		if(UserCollection.giren_kisi==0){
			System.out.println("Error: Please sign in and try again.");		 
		}
	}
	/**
	 * @throws Exception	df.parse(ana_oge.date_of_birth) is throwing unparseable date exception: 	
	 * </p>this method provides list user friends</p>
	 * */	
	public void listFriends() throws Exception {
		 DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		 DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy"); 
		for(String listele:UserCollection.kullanici.collection_of_friends){
			for(User ana_oge:UserCollection.users){
				if(listele.equals(ana_oge.username)){
					Date startDate = df.parse(ana_oge.date_of_birth);
					String startDateString2 = df2.format(startDate);
					System.out.println("Name: "+ana_oge.name);
					System.out.println("Username: "+ana_oge.username);
					System.out.println("Date of Birth: "+startDateString2);
					System.out.println("School: "+ana_oge.school_or_educate);
					System.out.println("---------------------------");
				}
			}
		}	
	}
	/**
	 *  </p>this method provides user adds text post in own list</p>
	 * @param alinan_lines command line
	 * */
	public void addPost_TXT(String[] alinan_lines){
		double girecek_longitude= Double.parseDouble(alinan_lines[2]);
		double girecek_latitude=Double.parseDouble(alinan_lines[3]);
		String[] gidecek_tagged_user = alinan_lines[4].split(":");
		List<String> eklenecekler_txt = new ArrayList<String>();		
		if(UserCollection.giren_kisi==1){
			for(String tagged_user:gidecek_tagged_user){
				
				int kisi_var=0;
				for(String tagg_compara:UserCollection.kullanici.collection_of_friends){
					if(tagg_compara.equals(tagged_user)){
						kisi_var++;
						eklenecekler_txt.add(tagged_user);
						break;
					}
				}
				if(kisi_var==0){
					System.out.println(tagged_user+" is not your friend, and will not be tagged!");		
				}
			}
		System.out.println("The post has been successfully added.");
		UserCollection.kullanici.collection_of_posts.add(new TextPost(alinan_lines[1], girecek_longitude, girecek_latitude, eklenecekler_txt));
		}
		if(UserCollection.giren_kisi==0){
			System.out.println("Error: Please sign in and try again.");
		}		
	}
	/**
	 *  </p>this method provides user adds image post in own list</p>
	 * @param alinan_lines command line
	 * */
	public void addPost_IMAGE(String[] alinan_lines){
		double girecek_longitude= Double.parseDouble(alinan_lines[2]);
		double girecek_latitude=Double.parseDouble(alinan_lines[3]);
		String[] gidecek_tagged_user = alinan_lines[4].split(":");
		List<String> eklenecekler_image = new ArrayList<String>();
		String[] array_resolution= alinan_lines[6].split("x");
		int array_resolution_width=Integer.parseInt(array_resolution[0]);
		int array_resolution_height=Integer.parseInt(array_resolution[1]);
		
		if(UserCollection.giren_kisi==1){
			
			for(String tagged_user:gidecek_tagged_user){
				int kisi_var=0;
				for(String tagg_compara:UserCollection.kullanici.collection_of_friends){
					if(tagg_compara.equals(tagged_user)){
						kisi_var++;
						eklenecekler_image.add(tagged_user);
						break;
					}
				}
				if(kisi_var==0){
					System.out.println(tagged_user+" is not your friend, and will not be tagged!");
				}
			}
		System.out.println("The post has been successfully added.");
		UserCollection.kullanici.collection_of_posts.add(new ImagePost(alinan_lines[1], girecek_longitude, girecek_latitude, eklenecekler_image, alinan_lines[5], array_resolution_width, array_resolution_height));
		}
		if(UserCollection.giren_kisi==0){
			System.out.println("Error: Please sign in and try again.");	
		}
	}
	/**
	 *  </p>this method provides user adds video post in own list</p>
	 * @param alinan_lines command line
	 * */
	public void addPost_VIDEO(String[] alinan_lines){
		double girecek_longitude= Double.parseDouble(alinan_lines[2]);
		double girecek_latitude=Double.parseDouble(alinan_lines[3]);
		String[] gidecek_tagged_user = alinan_lines[4].split(":");
		List<String> eklenecekler_video = new ArrayList<String>();
		int girecek_video_dk= Integer.parseInt(alinan_lines[6]);
		int hata_video_min=0;
		if(girecek_video_dk>10&&UserCollection.giren_kisi==1){
			System.out.println("Error: Your video exceeds maximum allowed duration	of	10	minutes.");
			hata_video_min++;
		}
		
		if(UserCollection.giren_kisi==1&&hata_video_min==0){
			for(String tagged_user:gidecek_tagged_user){
				int kisi_var=0;
				for(String tagg_compara:UserCollection.kullanici.collection_of_friends){
					if(tagg_compara.equals(tagged_user)){
						kisi_var++;
						eklenecekler_video.add(tagged_user);
						break;
					}
				}
				if(kisi_var==0){
					System.out.println(tagged_user+" is not your friend, and will not be tagged!");
				}
			}
		System.out.println("The post has been successfully added.");
		UserCollection.kullanici.collection_of_posts.add(new VideoPost(alinan_lines[1], girecek_longitude, girecek_latitude, eklenecekler_video, alinan_lines[5], girecek_video_dk));
		}
		if(UserCollection.giren_kisi==0){
			System.out.println("Error: Please sign in and try again.");	
		}
	}
	/**
	 *  </p>this method provides user removes last post in own post list</p>
	 * */
	public void removeLastPost(){
		if(UserCollection.giren_kisi==1){
			if(UserCollection.kullanici.collection_of_posts.size()==0){
				System.out.println("Error: You don't have any posts.");
			}
			if(UserCollection.kullanici.collection_of_posts.size()!=0){
				UserCollection.kullanici.collection_of_posts.remove(UserCollection.kullanici.collection_of_posts.size()-1);
				System.out.println("Your last post has been successfully removed.");
			}
		}
		if(UserCollection.giren_kisi==0){
			System.out.println("Error: Please sign in and try again.");	 
		}
	}
	/**
	 *  </p>this method provides user blocks other user</p>
	 * @param alinan_lines command line
	 * */
	public void blockUser(String[] alinan_lines){
		if(UserCollection.giren_kisi==1){
			int kullanici_bulmama=0;
			for(User bloklama:UserCollection.users){
				if(bloklama.username.equals(alinan_lines[1])){
					kullanici_bulmama++;
					UserCollection.kullanici.collection_of_blocked_users.add(alinan_lines[1]);
					System.out.println(alinan_lines[1]+" has been successfully blocked.");
					 
					break;
				}
			}
			if(kullanici_bulmama==0){
				System.out.println("No such user!");	 
			}	
		}
		if(UserCollection.giren_kisi==0){
			System.out.println("Error: Please sign in and try again.");	 
		}
	}
	/**
	 *  </p>this method provides user unblocks user in your block list</p>
	 * @param alinan_lines command line
	 * */
	public void unblockUser(String[] alinan_lines){
		if(UserCollection.giren_kisi==1){
			int kullanici_bulmama=0;
			for(String unblock:UserCollection.kullanici.collection_of_blocked_users){
				if(unblock.equals(alinan_lines[1])){
					kullanici_bulmama++;
					UserCollection.kullanici.collection_of_blocked_users.remove(UserCollection.kullanici.collection_of_blocked_users.indexOf(alinan_lines[1]));
					System.out.println(alinan_lines[1]+" has been successfully unblocked.");
					break;
				}
			}
			if(kullanici_bulmama==0){
				System.out.println("No such user in your blocked users list!");	 
			}	
		}
		if(UserCollection.giren_kisi==0){
			System.out.println("Error: Please sign in and try again.");
		}
	}
	/**
	 * @throws Exception	df.parse(bilgi_cekme.date_of_birth); is throwing unparseable date exception: 	
	 * <p>this method provides user can see blocked friends</p>
	 * */	
	public void showBlockedFriends() throws Exception{
		if(UserCollection.giren_kisi==1){
			int empty_blocked_friend=0;
			int list_bos=0;
			if(UserCollection.kullanici.collection_of_blocked_users.size()==0){
				list_bos++;
				System.out.println("You have not blocked any users yet!");
				System.out.println("-----------------------");
				
			}
			if(UserCollection.kullanici.collection_of_blocked_users.size()!=0){
				 DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				 DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy"); 
				for(String blokdaki_uyeler:UserCollection.kullanici.collection_of_blocked_users){
					for(String arkadas_uye:UserCollection.kullanici.collection_of_friends){
						if(blokdaki_uyeler.equals(arkadas_uye)){
							empty_blocked_friend++;
							for(User bilgi_cekme:UserCollection.users){
								if(arkadas_uye.equals(bilgi_cekme.username)){
									Date startDate = df.parse(bilgi_cekme.date_of_birth);
									String startDateString2 = df2.format(startDate);
									System.out.println("Name: "+bilgi_cekme.name);
									System.out.println("User Name: "+bilgi_cekme.username);
									System.out.println("Date of Birth: "+startDateString2);
									System.out.println("School: "+bilgi_cekme.school_or_educate);
									System.out.println("-----------------------");
								}			
							}
						}
					}
				}
			}
			if(empty_blocked_friend==0&&list_bos==0){
				System.out.println("You	have not blocked any friends yet!");	 
			}	
		}
		if(UserCollection.giren_kisi==0){
			System.out.println("Error: Please sign in and try again.");
		}
	}
	/**
	 * @throws Exception	df.parse(list_user.date_of_birth) is throwing unparseable date exception: 	
	 * <p>this method provides user can see blocked users  </p>
	 * */	
	public void showBlockedUsers() throws Exception{
		if(UserCollection.giren_kisi==1){
			 DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			 DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy"); 
			int empty_blocked_user=0;
			for(String blokdaki_uyeler:UserCollection.kullanici.collection_of_blocked_users){
				for(User kullanicilar:UserCollection.users){
					if(blokdaki_uyeler.equals(kullanicilar.username)){
						empty_blocked_user++;
						Date startDate = df.parse(kullanicilar.date_of_birth);
						String startDateString2 = df2.format(startDate);
						System.out.println("Name: "+kullanicilar.name);
						System.out.println("User Name: "+kullanicilar.username);
						System.out.println("Date of Birth: "+startDateString2);
						System.out.println("School: "+kullanicilar.school_or_educate);
						}
					}
				}		
			if(empty_blocked_user==0){
				System.out.println("You	have not blocked any friends yet!");
			}
		}	

		if(UserCollection.giren_kisi==0){
			System.out.println("Error: Please sign in and try again.");			
		}
		
	}

	

}	