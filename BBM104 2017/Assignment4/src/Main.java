import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JPasswordField;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.SwingConstants;
import java.awt.Window.Type;


public class Main {

	public static JFrame frmFacebookLoginPage;
	private JTextField kullanicitextField;
	private JPasswordField passwordField;
	private String value;
	public static JList<String> listkullanicilar = new JList<String>();
	public static DefaultListModel<String> DLM= new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
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
					for(User kullanici_bulma :UserCollection.users){
						if(kullanici_bulma.getUsername().equals(alinan_command_lines[1])){
							UserCollection.kullanici=kullanici_bulma;
							UserCollection.giren_kisi=1;
						}
						
					}
					UserCollection.kullanici.addFriend(alinan_command_lines);
					UserCollection.giren_kisi=0;
					UserCollection.kullanici=null;
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
					for(User kullanici_bulma :UserCollection.users){
						if(kullanici_bulma.getUsername().equals(alinan_command_lines[1])){
							UserCollection.kullanici=kullanici_bulma;
							UserCollection.giren_kisi=1;
						}					
					}
					System.out.println();
					UserCollection.kullanici.addPost_TXT(alinan_command_lines);
					UserCollection.giren_kisi=0;
					UserCollection.kullanici=null;
				}
				if(alinan_command_lines[0].equals("ADDPOST-IMAGE")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					for(User kullanici_bulma :UserCollection.users){
						if(kullanici_bulma.getUsername().equals(alinan_command_lines[1])){
							UserCollection.kullanici=kullanici_bulma;
							UserCollection.giren_kisi=1;
						}					
					}
					System.out.println();
					UserCollection.kullanici.addPost_IMAGE(alinan_command_lines);
					UserCollection.giren_kisi=0;
					UserCollection.kullanici=null;
				}
				if(alinan_command_lines[0].equals("ADDPOST-VIDEO")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					for(User kullanici_bulma :UserCollection.users){
						if(kullanici_bulma.getUsername().equals(alinan_command_lines[1])){
							UserCollection.kullanici=kullanici_bulma;
							UserCollection.giren_kisi=1;
						}					
					}
					System.out.println();	
					UserCollection.kullanici.addPost_VIDEO(alinan_command_lines);
					UserCollection.giren_kisi=0;
					UserCollection.kullanici=null;
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
					User.ShowPosts(alinan_command_lines);
				}
				if(alinan_command_lines[0].equals("BLOCKFRIEND")){
					System.out.println("-----------------------");
					System.out.print("Command: ");
					for(String kelime:alinan_command_lines){
						System.out.print(kelime+" ");
					}
					for(User kullanici_bulma :UserCollection.users){
						if(kullanici_bulma.getUsername().equals(alinan_command_lines[1])){
							UserCollection.kullanici=kullanici_bulma;
							UserCollection.giren_kisi=1;
						}
						
					}
					System.out.println();
					UserCollection.kullanici.blockUser(alinan_command_lines);
					UserCollection.giren_kisi=0;
					UserCollection.kullanici=null;
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
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmFacebookLoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFacebookLoginPage = new JFrame();
		frmFacebookLoginPage.setTitle("Facebook Login Page");
		frmFacebookLoginPage.getContentPane().setBackground(Color.WHITE);
		frmFacebookLoginPage.setBounds(100, 100, 594, 517);
		frmFacebookLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFacebookLoginPage.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Remove User");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int havet = JOptionPane.showConfirmDialog(frmFacebookLoginPage, "are you sure","Delete", JOptionPane.YES_NO_CANCEL_OPTION);
				if(havet==0){
					User tüm_veri_silme_kullanýcý =null;
					for(User userid_bulma:UserCollection.users){
						if(value.equals(userid_bulma.getUsername())){
							tüm_veri_silme_kullanýcý=userid_bulma;
							String komuta_index = Integer.toString(userid_bulma.getUser_id());
							String[] komuta = {"REMOVEUSER",komuta_index};
							int index = listkullanicilar.getSelectedIndex();
							DLM.remove(index);
							listkullanicilar.setModel(DLM);
							UserCollection.remove(komuta);
							break;
						}	
					}
					for(User tüm_veri_silme:UserCollection.users){
						for(String block_kaldýrma:tüm_veri_silme.getCollection_of_blocked_users()){
							if(tüm_veri_silme_kullanýcý.getUsername().equals(block_kaldýrma)){
								tüm_veri_silme.getCollection_of_blocked_users().remove(block_kaldýrma);
								break;
							}
						}	
						for(String arkadas_silme:tüm_veri_silme.getCollection_of_friends()){
							if(tüm_veri_silme_kullanýcý.getUsername().equals(arkadas_silme)){
								
								tüm_veri_silme.getCollection_of_friends().remove(arkadas_silme);
								break;
							}	
						}
					}
				}

				
			}
		});
		btnNewButton.setBounds(12, 414, 119, 25);
		frmFacebookLoginPage.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New User...");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateUser kullanici_olustur = new CreateUser();
				kullanici_olustur.NewScreen_create_user();		
			}
		});
		btnNewButton_1.setBounds(445, 414, 119, 25);
		frmFacebookLoginPage.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Login");
		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String gelen_user_name =kullanicitextField.getText();
				String gelen_password = passwordField.getText();
				String[] komuta = {"SIGNIN",gelen_user_name,gelen_password};
				UserCollection.userSignIn(komuta);
				if(UserCollection.giren_kisi==1){
					JOptionPane.showMessageDialog(frmFacebookLoginPage, "You have successfully signed in.");
					ProfilePage profil_sayfa = new ProfilePage();
					profil_sayfa.NewScreen_ProfilePage();
					frmFacebookLoginPage.setVisible(false);
					
				}
				if(UserCollection.giren_kisi==0){
					JOptionPane.showMessageDialog(frmFacebookLoginPage, "Invalid username or password! Please try again.");
				}
			}
		});
		btnNewButton_2.setBounds(430, 177, 134, 25);
		frmFacebookLoginPage.getContentPane().add(btnNewButton_2);
		
		kullanicitextField = new JTextField();
		kullanicitextField.setBounds(430, 127, 131, 22);
		frmFacebookLoginPage.getContentPane().add(kullanicitextField);
		kullanicitextField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(347, 130, 71, 16);
		frmFacebookLoginPage.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(347, 152, 71, 16);
		frmFacebookLoginPage.getContentPane().add(lblPassword);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(12, 342, 552, 59);
		frmFacebookLoginPage.getContentPane().add(scrollPane);
		
		
		listkullanicilar.setVisibleRowCount(2);
		listkullanicilar.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		scrollPane.setViewportView(listkullanicilar);
		
		for(User jlist_ekleme:UserCollection.users){
			DLM.addElement(jlist_ekleme.getUsername());
		}
		listkullanicilar.setModel(DLM);
		listkullanicilar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				value = (String)listkullanicilar.getModel().getElementAt(listkullanicilar.locationToIndex(arg0.getPoint()));
				kullanicitextField.setText(value);
				for(User sifrebulma:UserCollection.users){
					if(value.equals(sifrebulma.getUsername())){
						passwordField.setText(sifrebulma.getPassword());
					}
				}	
			}
		});		
		
		JLabel lblNewLabel_1 = new JLabel("Registered Users");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(12, 304, 186, 25);
		frmFacebookLoginPage.getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(430, 149, 131, 22);
		frmFacebookLoginPage.getContentPane().add(passwordField);
		
		JLabel lblfacebookicon = new JLabel("");
		Image imgfaceicon = new ImageIcon(this.getClass().getResource("/Facebooklogo1.png")).getImage();
		lblfacebookicon.setIcon(new ImageIcon(imgfaceicon));
		lblfacebookicon.setBounds(12, 13, 323, 263);
		frmFacebookLoginPage.getContentPane().add(lblfacebookicon);
		
		JMenuBar menuBar = new JMenuBar();
		frmFacebookLoginPage.setJMenuBar(menuBar);
		
		JMenu mnSystem = new JMenu("System");
		menuBar.add(mnSystem);
	}
}
