/**
 * @author Mehmet Taha Usta
 * @version 1.0, 28 April 2017
 **/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
	/**
	 * <p>Main is reads files ,runs progress command line</p>
	 * @throws Exception if convert false for date convert,this throws Exception 
	 * */
	public static void main(String[] args) throws Exception{
		try {
			Scanner user = new Scanner(new File(args[0]));
			Scanner command = new Scanner(new File(args[1]));
			while(user.hasNextLine()){
				String user_lines = user.nextLine();
				String[] alinan_user_lines=user_lines.split("\t");
				UserCollection.addUser(alinan_user_lines);
			}
			while(command.hasNextLine()){
				String command_lines = command.nextLine();
				String[] alinan_command_lines= command_lines.split("\t");
				if(alinan_command_lines[0].equals("ADDUSER")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					int bastan_silme;
					for (bastan_silme = 0; bastan_silme < alinan_command_lines.length- 1; bastan_silme++) {
						alinan_command_lines[bastan_silme] = alinan_command_lines[bastan_silme+ 1];
					}
					UserCollection.addUser(alinan_command_lines);
					System.out.println(alinan_command_lines[0]+" has been successfully added.");
					System.out.println("-----------------------");
				}
				if(alinan_command_lines[0].equals("REMOVEUSER")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					UserCollection.remove(alinan_command_lines);
					System.out.println("-----------------------");
					
				}
				if(alinan_command_lines[0].equals("SIGNIN")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					UserCollection.userSignIn(alinan_command_lines);
					System.out.println("-----------------------");
				}
				if(alinan_command_lines[0].equals("LISTUSERS")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					UserCollection.kullanici.listUser();
				}
				if(alinan_command_lines[0].equals("UPDATEPROFILE")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					UserCollection.kullanici.updateProfile(alinan_command_lines);
				}
				if(alinan_command_lines[0].equals("CHPASS")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					UserCollection.kullanici.chPass(alinan_command_lines);
					
				}
				if(alinan_command_lines[0].equals("ADDFRIEND")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					UserCollection.kullanici.addFriend(alinan_command_lines);

				}
				if(alinan_command_lines[0].equals("REMOVEFRIEND")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					UserCollection.kullanici.removeFriend(alinan_command_lines);
				}
				if(alinan_command_lines[0].equals("LISTFRIENDS")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();	
					UserCollection.kullanici.listFriends();
				}
				if(alinan_command_lines[0].equals("ADDPOST-TEXT")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					UserCollection.kullanici.addPost_TXT(alinan_command_lines);
				}
				if(alinan_command_lines[0].equals("ADDPOST-IMAGE")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					UserCollection.kullanici.addPost_IMAGE(alinan_command_lines);
				}
				if(alinan_command_lines[0].equals("ADDPOST-VIDEO")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();	
					UserCollection.kullanici.addPost_VIDEO(alinan_command_lines);
				}
				if(alinan_command_lines[0].equals("REMOVELASTPOST")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					UserCollection.kullanici.removeLastPost();
				}
				if(alinan_command_lines[0].equals("SHOWPOSTS")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					UserCollection.ShowPosts(alinan_command_lines);
				}
				if(alinan_command_lines[0].equals("BLOCK")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					UserCollection.kullanici.blockUser(alinan_command_lines);
				}
				if(alinan_command_lines[0].equals("SHOWBLOCKEDFRIENDS")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					UserCollection.kullanici.showBlockedFriends();
				}
				if(alinan_command_lines[0].equals("UNBLOCK")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();
					UserCollection.kullanici.unblockUser(alinan_command_lines);
				}
				if(alinan_command_lines[0].equals("SHOWBLOCKEDUSERS")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					System.out.println();	
					UserCollection.kullanici.showBlockedUsers();
				}
				if(alinan_command_lines[0].equals("SIGNOUT")){
					System.out.println("-----------------------");
					for(String kelime:alinan_command_lines){
						System.out.print("Command: "+ kelime+" ");
					}
					System.out.println();
					UserCollection.kullanici.userSignOut();
				}						
			}
			user.close();
			command.close();	
			} catch (FileNotFoundException ex) {
			System.out.println("No File Found!");
			return;
			}
	}
}