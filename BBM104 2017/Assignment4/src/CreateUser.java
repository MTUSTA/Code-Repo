import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class CreateUser extends JFrame {

	private JPanel contentPane;
	private JTextField kayit_username;
	private JPasswordField kayit_password;
	private JPasswordField kayit_password_re;
	private JTextField kayit_name_surname;
	private JTextField kayit_okul;
	private JTextField kayit_birth;

	/**
	 * Launch the application.
	 */
	public static void NewScreen_create_user() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUser frame = new CreateUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 393, 588);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel facebookiconn = new JLabel("");
		facebookiconn.setHorizontalAlignment(SwingConstants.CENTER);
		Image imgfaceicon = new ImageIcon(this.getClass().getResource("/Facebooklogo1.png")).getImage();
		facebookiconn.setIcon(new ImageIcon(imgfaceicon));
		facebookiconn.setBounds(12, 13, 351, 191);
		contentPane.add(facebookiconn);
		
		JLabel lblCreateUser = new JLabel("Create User");
		lblCreateUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateUser.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCreateUser.setBounds(132, 160, 111, 44);
		contentPane.add(lblCreateUser);
		
		JLabel Username = new JLabel("Username");
		Username.setBounds(12, 217, 80, 26);
		contentPane.add(Username);
		
		JLabel Password = new JLabel("Password");
		Password.setBounds(12, 244, 80, 26);
		contentPane.add(Password);
		
		JLabel Passwordre = new JLabel("Password (re-type)");
		Passwordre.setBounds(12, 273, 123, 26);
		contentPane.add(Passwordre);
		
		JLabel adsoyad = new JLabel("Name Surname");
		adsoyad.setBounds(12, 302, 96, 26);
		contentPane.add(adsoyad);
		
		JLabel okul = new JLabel("School graduated");
		okul.setBounds(12, 331, 111, 26);
		contentPane.add(okul);
		
		JLabel dogma = new JLabel("Birth Date");
		dogma.setBounds(12, 360, 80, 26);
		contentPane.add(dogma);
		
		JLabel iliski = new JLabel("Relationship Status");
		iliski.setBounds(12, 400, 123, 26);
		contentPane.add(iliski);
		
		kayit_username = new JTextField();
		kayit_username.setBounds(127, 219, 128, 22);
		contentPane.add(kayit_username);
		kayit_username.setColumns(10);
		
		kayit_password = new JPasswordField();
		kayit_password.setBounds(127, 246, 128, 22);
		contentPane.add(kayit_password);
		
		kayit_password_re = new JPasswordField();
		kayit_password_re.setBounds(127, 275, 128, 22);
		contentPane.add(kayit_password_re);
		
		kayit_name_surname = new JTextField();
		kayit_name_surname.setBounds(127, 304, 223, 22);
		contentPane.add(kayit_name_surname);
		kayit_name_surname.setColumns(10);
		
		kayit_okul = new JTextField();
		kayit_okul.setBounds(127, 333, 223, 22);
		contentPane.add(kayit_okul);
		kayit_okul.setColumns(10);
		
		kayit_birth = new JTextField();
		kayit_birth.setBounds(127, 362, 128, 22);
		contentPane.add(kayit_birth);
		kayit_birth.setColumns(10);
		
		//setrelationship kýsmýný sor
		
		JComboBox comboBox_iliski = new JComboBox();
		comboBox_iliski.setModel(new DefaultComboBoxModel(new String[] {"In a relationship", "Divorced", "Complicated", "Single"}));
		comboBox_iliski.setBounds(127, 402, 128, 22);
		contentPane.add(comboBox_iliski);

		
		JButton kullanici_olusturma = new JButton("Create");
		kullanici_olusturma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String kayýt_user_name =kayit_username.getText();
				String kayýt_sifre =kayit_password.getText();
				String kayýt_sifre_re =kayit_password_re.getText();
				String kayýt_ad_soyad =kayit_name_surname.getText();
				String kayýt_okulu =kayit_okul.getText();
				String kayýt_dogma =kayit_birth.getText();
				String kayýt_iliski_durumu =(String) comboBox_iliski.getSelectedItem();
				String[] kullanici_kayit = {kayýt_user_name,kayýt_sifre,kayýt_sifre_re,kayýt_ad_soyad,kayýt_okulu,kayýt_dogma,kayýt_iliski_durumu};
				//System.out.println(UserCollection.users);
				int aynýlýk_var=0;
				for(User farklý_olmalý:UserCollection.users){
					if(kayýt_user_name.equals(farklý_olmalý.getUsername())){
						aynýlýk_var++;
					}
					
				}
				if(aynýlýk_var==0){
					if(kayýt_sifre.equals(kayýt_sifre_re)){
						if(kayýt_user_name.length()!=0&&kayýt_sifre.length()!=0 && kayýt_sifre_re.length()!=0 && kayýt_ad_soyad.length()!=0&&kayýt_okulu.length()!=0&&kayýt_dogma.length()!=0&&kayýt_iliski_durumu.length()!=0){
							try {
								UserCollection.addUser(kullanici_kayit);
								User jliste_eklenecek=UserCollection.users.get(UserCollection.users.size()-1);
								System.out.println(jliste_eklenecek.getUsername());//silincek
								Main.DLM.addElement(jliste_eklenecek.getUsername());
								Main.listkullanicilar.setModel(Main.DLM);
								//System.out.println(UserCollection.users);
								JOptionPane.showMessageDialog(kullanici_olusturma, "Success!");

							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
					}
					else{
						JOptionPane.showMessageDialog(kullanici_olusturma, "Password (re-type) is not equal password");
					}
					
				}
				if(aynýlýk_var==1){
					JOptionPane.showMessageDialog(kullanici_olusturma, "Please change username");
				}
	
			}
		});
		kullanici_olusturma.setBounds(127, 454, 141, 25);
		contentPane.add(kullanici_olusturma);
	}
}
