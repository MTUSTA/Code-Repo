import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.EtchedBorder;


public class ProfilePage extends JFrame {

	private JPanel contentPane;
	private ButtonGroup bg = new ButtonGroup();
	private JList list = new JList();
	private JTextField searchbar;
	private User ziyaret_edilen_sayfa_kullanici;
	private int ziyaretci=0;
	public static DefaultListModel<String> DLM_tag= new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
	public static void NewScreen_ProfilePage() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfilePage frame = new ProfilePage();
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
	public ProfilePage() {

		setTitle("Profil Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 912, 882);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel profil_resim = new JLabel("");
		profil_resim.setHorizontalAlignment(SwingConstants.CENTER);
		Image imgfaceicon = new ImageIcon(this.getClass().getResource("/personicon8.png")).getImage();
		profil_resim.setIcon(new ImageIcon(imgfaceicon));
		profil_resim.setBounds(12, 78, 206, 205);
		contentPane.add(profil_resim);
		
		JLabel giren_kisinin_adsoyad = new JLabel(UserCollection.kullanici.getName());
		giren_kisinin_adsoyad.setFont(new Font("Tahoma", Font.BOLD, 20));
		giren_kisinin_adsoyad.setBounds(230, 215, 206, 37);
		contentPane.add(giren_kisinin_adsoyad);
		JTextField giren_kisinin_adsoyad_update = new JTextField();
		giren_kisinin_adsoyad_update.setBounds(230, 215, 206, 37);
		
		DefaultListModel<String> önceden_secim= new DefaultListModel<String>();
		for(String arkadas_gösterim:UserCollection.kullanici.getCollection_of_friends()){
			önceden_secim.addElement(arkadas_gösterim);
		}
		list.setModel(önceden_secim);
		
		JRadioButton normal_arkadas = new JRadioButton("Normal");
		
		normal_arkadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel<String> DLM_arkadas= new DefaultListModel<String>();
				if(ziyaretci==0){
					for(String arkadas_gösterim:UserCollection.kullanici.getCollection_of_friends()){
						DLM_arkadas.addElement(arkadas_gösterim);
					}
					list.setModel(DLM_arkadas);
				}
				if(ziyaretci==1){
					list.removeAll();
					DLM_arkadas.removeAllElements();
					for(String arkadas_gösterim2:ziyaret_edilen_sayfa_kullanici.getCollection_of_friends()){
						DLM_arkadas.addElement(arkadas_gösterim2);
					}
					list.setModel(DLM_arkadas);
				}
			}
		});
		normal_arkadas.setBounds(66, 551, 76, 25);
		contentPane.add(normal_arkadas);
		normal_arkadas.setSelected(true);
		
		
		JRadioButton engellenmis_Arkadas = new JRadioButton("Blocked");
		engellenmis_Arkadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> DLM_blocked= new DefaultListModel<String>();
				if(ziyaretci==0){
					for(String blocked_gösterim:UserCollection.kullanici.getCollection_of_blocked_users()){
						DLM_blocked.addElement(blocked_gösterim);
					}
					list.setModel(DLM_blocked);
					
				}
				if(ziyaretci==1){
					list.removeAll();
					DLM_blocked.removeAllElements();
					for(String arkadas_gösterim2:ziyaret_edilen_sayfa_kullanici.getCollection_of_blocked_users()){
						DLM_blocked.addElement(arkadas_gösterim2);
					}
					list.setModel(DLM_blocked);
				}
			}
		});
		engellenmis_Arkadas.setBounds(147, 551, 76, 25);
		contentPane.add(engellenmis_Arkadas);
		bg.add(normal_arkadas);
		bg.add(engellenmis_Arkadas);
	
		JPanel panel_bilgi = new JPanel();
		panel_bilgi.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_bilgi.setBounds(12, 297, 233, 245);
		contentPane.add(panel_bilgi);
		panel_bilgi.setLayout(null);
		
		JLabel sadeceyazi = new JLabel("Date of Birth");
		sadeceyazi.setBounds(12, 31, 125, 25);
		panel_bilgi.add(sadeceyazi);
		sadeceyazi.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel kisi_dg_gelcek = new JLabel(UserCollection.kullanici.getDate_of_birth());
		kisi_dg_gelcek.setBounds(12, 57, 125, 25);
		panel_bilgi.add(kisi_dg_gelcek);
		
		
		JLabel yazi_oylesine = new JLabel("School Graduated");
		yazi_oylesine.setBounds(12, 79, 140, 25);
		panel_bilgi.add(yazi_oylesine);
		yazi_oylesine.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel kisi_okul_gelcek = new JLabel(UserCollection.kullanici.getSchool_or_educate());
		kisi_okul_gelcek.setBounds(12, 106, 165, 25);
		panel_bilgi.add(kisi_okul_gelcek);
		
		JLabel oylesine_yazicik = new JLabel("Relationship Status");
		oylesine_yazicik.setBounds(12, 134, 140, 25);
		panel_bilgi.add(oylesine_yazicik);
		oylesine_yazicik.setFont(new Font("Tahoma", Font.BOLD, 14));
		

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 172, 165, 22);
		panel_bilgi.add(comboBox);
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {UserCollection.kullanici.getRelationshipstatus()}));
		JButton btnNewButton = new JButton("Update");
		btnNewButton.setBounds(43, 207, 97, 25);
		panel_bilgi.add(btnNewButton);
		
		JLabel lblInformation = new JLabel("Information");
		lblInformation.setBounds(12, 2, 66, 16);
		panel_bilgi.add(lblInformation);
		JTextField kisi_dg_gelcek_update = new JTextField();
		kisi_dg_gelcek_update.setBounds(12, 57, 125, 25);
		JTextField kisi_okul_gelcek_update = new JTextField();
		kisi_okul_gelcek_update.setBounds(12, 106, 165, 25);
		JButton btnNewButton2_save = new JButton("Save");
	
		btnNewButton2_save.setBounds(43, 207, 97, 25);
		btnNewButton2_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				UserCollection.kullanici.setRelationshipstatus((String) comboBox.getSelectedItem());
				comboBox.setModel(new DefaultComboBoxModel(new String[] {UserCollection.kullanici.getRelationshipstatus()}));
				UserCollection.kullanici.setDate_of_birth(kisi_dg_gelcek_update.getText());
				UserCollection.kullanici.setName(giren_kisinin_adsoyad_update.getText());
				UserCollection.kullanici.setSchool_or_educate(kisi_okul_gelcek_update.getText());
				giren_kisinin_adsoyad.setText(UserCollection.kullanici.getName());
				kisi_dg_gelcek.setText(UserCollection.kullanici.getDate_of_birth());
				kisi_okul_gelcek.setText(UserCollection.kullanici.getSchool_or_educate());
								
				panel_bilgi.add(btnNewButton);
				panel_bilgi.remove(btnNewButton2_save);
				contentPane.remove(giren_kisinin_adsoyad_update);
				contentPane.add(giren_kisinin_adsoyad);
				panel_bilgi.remove(kisi_dg_gelcek_update);
				panel_bilgi.add(kisi_dg_gelcek);
				panel_bilgi.remove(kisi_okul_gelcek_update);
				panel_bilgi.add(kisi_okul_gelcek);
				SwingUtilities.updateComponentTreeUI(contentPane);
			}
		});
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(257, 265, 625, 565);
		contentPane.add(scrollPane_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setViewportView(scrollPane_1);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		scrollPane_1.setViewportView(tabbedPane);
		
		
		JPanel mesajlar = new JPanel();
		mesajlar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mesajlar.setToolTipText("");		
		tabbedPane.addTab("Posts", null, mesajlar, null);
		mesajlar.setLayout(null);
		int y_ekseni = 0;
		for (Post postlar : UserCollection.kullanici.getCollection_of_posts()){
			JPanel panel_ilk = new JPanel();
			panel_ilk.setLayout(new BorderLayout());
			panel_ilk.setBounds(0, y_ekseni, 595, 80);
			if(postlar.getClass()==TextPost.class){
				JLabel sol_label = new JLabel("T");
				sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
				sol_label.setVerticalAlignment(SwingConstants.TOP);
				panel_ilk.add(sol_label,BorderLayout.WEST);
				
				JLabel üst_label = new JLabel(postlar.getText());
				üst_label.setForeground(Color.BLUE);
				üst_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));		
				panel_ilk.add(üst_label,BorderLayout.NORTH);
				
				
				String tutan_beyin="";
				for(String adi_bulcam:postlar.getCollection_of_tagged_friends()){
					for(User kisiler:UserCollection.users){
						if(adi_bulcam.equals(kisiler.getUsername())){
							tutan_beyin=tutan_beyin+kisiler.getName()+" ";
							if((postlar.getCollection_of_tagged_friends().size()-1)!=postlar.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
								tutan_beyin=tutan_beyin+",";
							}
						}
					}
				}

				JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
				alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
				panel_ilk.add(alt_label, BorderLayout.SOUTH);
				
				JButton btnTagFriend = new JButton("Tag Friends");
				btnTagFriend.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						DLM_tag.removeAllElements();
						for(String arkadaslar:UserCollection.kullanici.getCollection_of_friends()){
							for(User kisiler:UserCollection.users){
								if(arkadaslar.equals(kisiler.getUsername())){
									DLM_tag.addElement(kisiler.getName());	
								}
								
							}
						}
						for(String arkadaslar2:postlar.getCollection_of_tagged_friends()){
							for(User kisiler:UserCollection.users){
								if(arkadaslar2.equals(kisiler.getUsername())){
									int yer = DLM_tag.indexOf(kisiler.getName());
									DLM_tag.remove(yer);		
								}
							}
						}
						TagUser.taguser();
					}
				});
				panel_ilk.add(btnTagFriend, BorderLayout.EAST);
				
				
				
			}
			if(postlar.getClass()==ImagePost.class){
				
				JLabel sol_label = new JLabel("I");
				sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
				sol_label.setVerticalAlignment(SwingConstants.TOP);
				panel_ilk.add(sol_label,BorderLayout.WEST);

				JLabel üst_label = new JLabel(postlar.getText());
				üst_label.setForeground(Color.BLUE);
				üst_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
				panel_ilk.add(üst_label,BorderLayout.NORTH);
				

				String tutan_beyin="";
				for(String adi_bulcam:postlar.getCollection_of_tagged_friends()){
					for(User kisiler:UserCollection.users){
						if(adi_bulcam.equals(kisiler.getUsername())){
							tutan_beyin=tutan_beyin+kisiler.getName()+" ";
							if((postlar.getCollection_of_tagged_friends().size()-1)!=postlar.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
								tutan_beyin=tutan_beyin+",";
							}
						}
					}
				}

				JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
				alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
				panel_ilk.add(alt_label, BorderLayout.SOUTH);
				
				JButton btnTagFriend = new JButton("Tag Friends");
				btnTagFriend.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						DLM_tag.removeAllElements();
						for(String arkadaslar:UserCollection.kullanici.getCollection_of_friends()){
							for(User kisiler:UserCollection.users){
								if(arkadaslar.equals(kisiler.getUsername())){
									DLM_tag.addElement(kisiler.getName());	
								}
								
							}
						}
						for(String arkadaslar2:postlar.getCollection_of_tagged_friends()){
							for(User kisiler:UserCollection.users){
								if(arkadaslar2.equals(kisiler.getUsername())){
									int yer = DLM_tag.indexOf(kisiler.getName());
									DLM_tag.remove(yer);		
								}
							}
						}
						TagUser.taguser();
					}
				});
				panel_ilk.add(btnTagFriend, BorderLayout.EAST);

			}
			if(postlar.getClass()==VideoPost.class){
				
				JLabel sol_label = new JLabel("V");
				sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
				sol_label.setVerticalAlignment(SwingConstants.TOP);
				panel_ilk.add(sol_label,BorderLayout.CENTER);
				
				JLabel üst_label = new JLabel(postlar.getText());
				üst_label.setForeground(Color.BLUE);
				üst_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
				panel_ilk.add(üst_label,BorderLayout.NORTH);
				
				String tutan_beyin="";
				for(String adi_bulcam:postlar.getCollection_of_tagged_friends()){
					for(User kisiler:UserCollection.users){
						if(adi_bulcam.equals(kisiler.getUsername())){
							tutan_beyin=tutan_beyin+kisiler.getName()+" ";
							if((postlar.getCollection_of_tagged_friends().size()-1)!=postlar.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
								tutan_beyin=tutan_beyin+",";
							}
						}
					}
				}

				JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
				alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
				panel_ilk.add(alt_label, BorderLayout.SOUTH);
				
				JButton btnTagFriend = new JButton("Tag Friends");
				btnTagFriend.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						DLM_tag.removeAllElements();
						for(String arkadaslar:UserCollection.kullanici.getCollection_of_friends()){
							for(User kisiler:UserCollection.users){
								if(arkadaslar.equals(kisiler.getUsername())){
									DLM_tag.addElement(kisiler.getName());	
								}
								
							}
						}
						for(String arkadaslar2:postlar.getCollection_of_tagged_friends()){
							for(User kisiler:UserCollection.users){
								if(arkadaslar2.equals(kisiler.getUsername())){
									int yer = DLM_tag.indexOf(kisiler.getName());
									DLM_tag.remove(yer);		
								}
							}
						}
						TagUser.taguser();
					}
				});
				panel_ilk.add(btnTagFriend, BorderLayout.EAST);
				
			}
			y_ekseni+=80;
			panel_ilk.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
			mesajlar.add(panel_ilk);
			
		}
		
		JPanel arkadas_mesajlar = new JPanel();
		arkadas_mesajlar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    arkadas_mesajlar.setToolTipText("");
		tabbedPane.addTab("Friends' Posts", null, arkadas_mesajlar, null);
		arkadas_mesajlar.setLayout(null);
		

		int y_ekseni3=0;
		for(User kullanici_disi:UserCollection.users){
			if(!kullanici_disi.equals(UserCollection.kullanici) && kullanici_disi.getCollection_of_posts().size()!=0){
				System.out.println(kullanici_disi.getUsername());
				for (Post postlar_ark : kullanici_disi.getCollection_of_posts()){
					JPanel panel2 = new JPanel();
					panel2.setLayout(new BorderLayout());
					panel2.setBounds(0, y_ekseni3, 595, 80);
					if(postlar_ark.getClass()==TextPost.class){
						JLabel üst_label = new JLabel(kullanici_disi.getName()+" has shared");
						üst_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel2.add(üst_label,BorderLayout.NORTH);
						
						JLabel sol_label = new JLabel("T");
						sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
						sol_label.setVerticalAlignment(SwingConstants.TOP);
						panel2.add(sol_label,BorderLayout.WEST);
						
						JLabel merkez_label = new JLabel(postlar_ark.getText());
						merkez_label.setForeground(Color.BLUE);
						merkez_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));		
						panel2.add(merkez_label,BorderLayout.CENTER);
						
						
						String tutan_beyin="";
						for(String adi_bulcam:postlar_ark.getCollection_of_tagged_friends()){
							for(User kisiler:UserCollection.users){
								if(adi_bulcam.equals(kisiler.getUsername())){
									tutan_beyin=tutan_beyin+kisiler.getName()+" ";
									if((postlar_ark.getCollection_of_tagged_friends().size()-1)!=postlar_ark.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
										tutan_beyin=tutan_beyin+",";
									}
								}
							}
						}

						JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
						alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
						panel2.add(alt_label, BorderLayout.SOUTH);
						
						
					}
					if(postlar_ark.getClass()==ImagePost.class){
						JLabel üst_label = new JLabel(kullanici_disi.getName()+" has shared");
						üst_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel2.add(üst_label,BorderLayout.NORTH);
						
						JLabel sol_label = new JLabel("I");
						sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
						sol_label.setVerticalAlignment(SwingConstants.TOP);
						panel2.add(sol_label,BorderLayout.WEST);

						JLabel merkez_label = new JLabel(postlar_ark.getText());
						merkez_label.setForeground(Color.BLUE);
						merkez_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
						panel2.add(merkez_label,BorderLayout.CENTER);
						

						String tutan_beyin="";
						for(String adi_bulcam:postlar_ark.getCollection_of_tagged_friends()){
							for(User kisiler:UserCollection.users){
								if(adi_bulcam.equals(kisiler.getUsername())){
									tutan_beyin=tutan_beyin+kisiler.getName()+" ";
									if((postlar_ark.getCollection_of_tagged_friends().size()-1)!=postlar_ark.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
										tutan_beyin=tutan_beyin+",";
									}
								}
							}
						}

						JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
						alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
						panel2.add(alt_label, BorderLayout.SOUTH);
						
					}
					if(postlar_ark.getClass()==VideoPost.class){
						JLabel üst_label = new JLabel(kullanici_disi.getName()+" has shared");
						üst_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel2.add(üst_label,BorderLayout.NORTH);
						
						JLabel sol_label = new JLabel("V");
						sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
						sol_label.setVerticalAlignment(SwingConstants.TOP);
						panel2.add(sol_label,BorderLayout.CENTER);
						
						JLabel merkez_label = new JLabel(postlar_ark.getText());
						merkez_label.setForeground(Color.BLUE);
						merkez_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
						panel2.add(merkez_label,BorderLayout.CENTER);
						
						String tutan_beyin="";
						for(String adi_bulcam:postlar_ark.getCollection_of_tagged_friends()){
							for(User kisiler:UserCollection.users){
								if(adi_bulcam.equals(kisiler.getUsername())){
									tutan_beyin=tutan_beyin+kisiler.getName()+" ";
									if((postlar_ark.getCollection_of_tagged_friends().size()-1)!=postlar_ark.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
										tutan_beyin=tutan_beyin+",";
									}
								}
							}
						}

						JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
						alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
						panel2.add(alt_label, BorderLayout.SOUTH);
					}
				
					y_ekseni3+=80;
					panel2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
					arkadas_mesajlar.add(panel2);
					SwingUtilities.updateComponentTreeUI(tabbedPane);
				}
				
			}		
		}

		JPanel jlist_paneli = new JPanel();
		jlist_paneli.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jlist_paneli.setBounds(12, 585, 233, 245);
		contentPane.add(jlist_paneli);
		jlist_paneli.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 13, 221, 191);
		jlist_paneli.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrollPane.setViewportView(list);
		
		JButton silme_buton = new JButton("Remove Selected User");
		silme_buton.setBounds(6, 207, 220, 25);
		jlist_paneli.add(silme_buton);
		silme_buton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"In a relationship", "Divorced", "Complicated", "Single"}));
			panel_bilgi.add(btnNewButton2_save);
			panel_bilgi.remove(btnNewButton);
			contentPane.add(giren_kisinin_adsoyad_update);
			contentPane.remove(giren_kisinin_adsoyad);
			panel_bilgi.add(kisi_dg_gelcek_update);
			panel_bilgi.remove(kisi_dg_gelcek);
			panel_bilgi.add(kisi_okul_gelcek_update);
			panel_bilgi.remove(kisi_okul_gelcek);
			SwingUtilities.updateComponentTreeUI(contentPane);
			}
		});
		
		JLabel arkadas_öylesine = new JLabel("Friends");
		arkadas_öylesine.setBounds(12, 553, 66, 21);
		contentPane.add(arkadas_öylesine);
		
		JPanel mavi_panel = new JPanel();
		mavi_panel.setBackground(Color.BLUE);
		mavi_panel.setBounds(0, 0, 894, 65);
		contentPane.add(mavi_panel);
		mavi_panel.setLayout(null);
		
		JButton cıkıs = new JButton("Logout");
		cıkıs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				UserCollection.kullanici.userSignOut();
				Main.frmFacebookLoginPage.setVisible(true);
			}
		});
		cıkıs.setBounds(785, 13, 97, 25);
		mavi_panel.add(cıkıs);
		
		JButton mesaj_butonu = new JButton("Create a Post");
		mesaj_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddPost.addpost_acma();
			}
			
		});
		mesaj_butonu.setBounds(642, 13, 131, 25);
		mavi_panel.add(mesaj_butonu);
		mesaj_butonu.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton block_misafir = new JButton("Block this person");
		block_misafir.setBounds(738, 224, 144, 25);
		block_misafir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] kullanici_block = {"BLOCKFRIEND",UserCollection.kullanici.getUsername(),ziyaret_edilen_sayfa_kullanici.getUsername()};	
				UserCollection.kullanici.blockUser(kullanici_block);
				String[] kullanici_silme ={"REMOVEFRIEND",ziyaret_edilen_sayfa_kullanici.getUsername()};
				UserCollection.kullanici.removeFriend(kullanici_silme);
				contentPane.remove(block_misafir);
				SwingUtilities.updateComponentTreeUI(contentPane);

			}
		});
		
		JButton add_misafir = new JButton("Add Friend");
		add_misafir.setBounds(785, 224, 97, 25);
		
		
		JButton eve_donus = new JButton("Home");
		eve_donus.setBounds(12, 13, 97, 25);
		eve_donus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				giren_kisinin_adsoyad.setText(UserCollection.kullanici.getName());
				kisi_dg_gelcek.setText(UserCollection.kullanici.getDate_of_birth());
				kisi_okul_gelcek.setText(UserCollection.kullanici.getSchool_or_educate());
				comboBox.setModel(new DefaultComboBoxModel(new String[] {UserCollection.kullanici.getRelationshipstatus()}));
				mavi_panel.remove(eve_donus);
				contentPane.remove(block_misafir);
				contentPane.remove(add_misafir);
				mavi_panel.add(mesaj_butonu);
				jlist_paneli.add(silme_buton);
				ziyaretci=0;
				mesajlar.removeAll();
				arkadas_mesajlar.removeAll();
				
				int y_ekseni3 = 0;
				for (Post postlar : UserCollection.kullanici.getCollection_of_posts()){
					JPanel panel = new JPanel();
					panel.setLayout(new BorderLayout());
					panel.setBounds(0, y_ekseni3, 595, 80);
					if(postlar.getClass()==TextPost.class){
						JLabel sol_label = new JLabel("T");
						sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
						sol_label.setVerticalAlignment(SwingConstants.TOP);
						panel.add(sol_label,BorderLayout.WEST);
						
						JLabel üst_label = new JLabel(postlar.getText());
						üst_label.setForeground(Color.BLUE);
						üst_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));		
						panel.add(üst_label,BorderLayout.NORTH);
						
						
						String tutan_beyin="";
						for(String adi_bulcam:postlar.getCollection_of_tagged_friends()){
							for(User kisiler:UserCollection.users){
								if(adi_bulcam.equals(kisiler.getUsername())){
									tutan_beyin=tutan_beyin+kisiler.getName()+" ";
									if((postlar.getCollection_of_tagged_friends().size()-1)!=postlar.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
										tutan_beyin=tutan_beyin+",";
									}
								}
							}
						}

						JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
						alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
						panel.add(alt_label, BorderLayout.SOUTH);
						
						JButton btnTagFriend = new JButton("Tag Friends");
						panel.add(btnTagFriend, BorderLayout.EAST);
						btnTagFriend.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
							
							SwingUtilities.updateComponentTreeUI(contentPane);
							}
						});
					}
					if(postlar.getClass()==ImagePost.class){
						
						JLabel sol_label = new JLabel("I");
						sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
						sol_label.setVerticalAlignment(SwingConstants.TOP);
						panel.add(sol_label,BorderLayout.WEST);

						JLabel üst_label = new JLabel(postlar.getText());
						üst_label.setForeground(Color.BLUE);
						üst_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
						panel.add(üst_label,BorderLayout.NORTH);
						
						String tutan_beyin="";
						for(String adi_bulcam:postlar.getCollection_of_tagged_friends()){
							for(User kisiler:UserCollection.users){
								if(adi_bulcam.equals(kisiler.getUsername())){
									tutan_beyin=tutan_beyin+kisiler.getName()+" ";
									if((postlar.getCollection_of_tagged_friends().size()-1)!=postlar.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
										tutan_beyin=tutan_beyin+",";
									}
								}
							}
						}
						JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
						alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
						panel.add(alt_label, BorderLayout.SOUTH);
						
						JButton btnTagFriend = new JButton("Tag Friends");
						panel.add(btnTagFriend, BorderLayout.EAST);
					}
					if(postlar.getClass()==VideoPost.class){
						
						JLabel sol_label = new JLabel("V");
						sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
						sol_label.setVerticalAlignment(SwingConstants.TOP);
						panel.add(sol_label,BorderLayout.CENTER);
						
						JLabel üst_label = new JLabel(postlar.getText());
						üst_label.setForeground(Color.BLUE);
						üst_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
						panel.add(üst_label,BorderLayout.NORTH);
						
						String tutan_beyin="";
						for(String adi_bulcam:postlar.getCollection_of_tagged_friends()){
							for(User kisiler:UserCollection.users){
								if(adi_bulcam.equals(kisiler.getUsername())){
									tutan_beyin=tutan_beyin+kisiler.getName()+" ";
									if((postlar.getCollection_of_tagged_friends().size()-1)!=postlar.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
										tutan_beyin=tutan_beyin+",";
									}
								}
							}
						}
						JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
						alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
						panel.add(alt_label, BorderLayout.SOUTH);
						JButton btnTagFriend = new JButton("Tag Friends");
						panel.add(btnTagFriend, BorderLayout.EAST);
					}
					y_ekseni3+=80;
					panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
					mesajlar.add(panel);
				}
				SwingUtilities.updateComponentTreeUI(contentPane);
				int y_ekseni4=0;
				for(User kullanici_disi:UserCollection.users){
					if(!kullanici_disi.equals(UserCollection.kullanici) && kullanici_disi.getCollection_of_posts().size()!=0){
						System.out.println(kullanici_disi.getUsername());
						for (Post postlar_ark : kullanici_disi.getCollection_of_posts()){
							JPanel panel2 = new JPanel();
							panel2.setLayout(new BorderLayout());
							panel2.setBounds(0, y_ekseni4, 595, 80);
							if(postlar_ark.getClass()==TextPost.class){
								JLabel üst_label = new JLabel(kullanici_disi.getName()+" has shared");
								üst_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
								panel2.add(üst_label,BorderLayout.NORTH);
								
								JLabel sol_label = new JLabel("T");
								sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
								sol_label.setVerticalAlignment(SwingConstants.TOP);
								panel2.add(sol_label,BorderLayout.WEST);
								
								JLabel merkez_label = new JLabel(postlar_ark.getText());
								merkez_label.setForeground(Color.BLUE);
								merkez_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));		
								panel2.add(merkez_label,BorderLayout.CENTER);
								
								
								String tutan_beyin="";
								for(String adi_bulcam:postlar_ark.getCollection_of_tagged_friends()){
									for(User kisiler:UserCollection.users){
										if(adi_bulcam.equals(kisiler.getUsername())){
											tutan_beyin=tutan_beyin+kisiler.getName()+" ";
											if((postlar_ark.getCollection_of_tagged_friends().size()-1)!=postlar_ark.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
												tutan_beyin=tutan_beyin+",";
											}
										}
									}
								}

								JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
								alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
								panel2.add(alt_label, BorderLayout.SOUTH);
								
								
							}
							if(postlar_ark.getClass()==ImagePost.class){
								JLabel üst_label = new JLabel(kullanici_disi.getName()+" has shared");
								üst_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
								panel2.add(üst_label,BorderLayout.NORTH);
								
								JLabel sol_label = new JLabel("I");
								sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
								sol_label.setVerticalAlignment(SwingConstants.TOP);
								panel2.add(sol_label,BorderLayout.WEST);

								JLabel merkez_label = new JLabel(postlar_ark.getText());
								merkez_label.setForeground(Color.BLUE);
								merkez_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
								panel2.add(merkez_label,BorderLayout.CENTER);
								

								String tutan_beyin="";
								for(String adi_bulcam:postlar_ark.getCollection_of_tagged_friends()){
									for(User kisiler:UserCollection.users){
										if(adi_bulcam.equals(kisiler.getUsername())){
											tutan_beyin=tutan_beyin+kisiler.getName()+" ";
											if((postlar_ark.getCollection_of_tagged_friends().size()-1)!=postlar_ark.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
												tutan_beyin=tutan_beyin+",";
											}
										}
									}
								}

								JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
								alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
								panel2.add(alt_label, BorderLayout.SOUTH);
								
							}
							if(postlar_ark.getClass()==VideoPost.class){
								JLabel üst_label = new JLabel(kullanici_disi.getName()+" has shared");
								üst_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
								panel2.add(üst_label,BorderLayout.NORTH);
								
								JLabel sol_label = new JLabel("V");
								sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
								sol_label.setVerticalAlignment(SwingConstants.TOP);
								panel2.add(sol_label,BorderLayout.CENTER);
								
								JLabel merkez_label = new JLabel(postlar_ark.getText());
								merkez_label.setForeground(Color.BLUE);
								merkez_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
								panel2.add(merkez_label,BorderLayout.CENTER);
								
								String tutan_beyin="";
								for(String adi_bulcam:postlar_ark.getCollection_of_tagged_friends()){
									for(User kisiler:UserCollection.users){
										if(adi_bulcam.equals(kisiler.getUsername())){
											tutan_beyin=tutan_beyin+kisiler.getName()+" ";
											if((postlar_ark.getCollection_of_tagged_friends().size()-1)!=postlar_ark.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
												tutan_beyin=tutan_beyin+",";
											}
										}
									}
								}

								JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
								alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
								panel2.add(alt_label, BorderLayout.SOUTH);
							}
						
							y_ekseni4+=80;
							panel2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
							arkadas_mesajlar.add(panel2);
							SwingUtilities.updateComponentTreeUI(tabbedPane);
						}
						
					}
				}
			}
		});
		



		
		
		DefaultListModel search = new DefaultListModel();
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_3.setBounds(342, 36, 153, 25);
		contentPane.add(scrollPane_3);
		JList search_list =new JList();
		scrollPane_3.setViewportView(search_list);
		search_list.setModel(search);
		search_list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt){
				JList listecik = (JList)evt.getSource();

				if(evt.getClickCount()== 2){
					String value = (String)search_list.getModel().getElementAt(search_list.locationToIndex(evt.getPoint()));
					for(User kisi_bul:UserCollection.users){
						
						if(value.equals(kisi_bul.getName())){
							giren_kisinin_adsoyad.setText(kisi_bul.getName());
							kisi_dg_gelcek.setText(kisi_bul.getDate_of_birth());
							kisi_okul_gelcek.setText(kisi_bul.getSchool_or_educate());
							comboBox.setModel(new DefaultComboBoxModel(new String[] {kisi_bul.getRelationshipstatus()}));
							jlist_paneli.remove(silme_buton);
							ziyaretci=1;
							ziyaret_edilen_sayfa_kullanici=kisi_bul;
						}
					}
					mavi_panel.add(eve_donus);
					mavi_panel.remove(mesaj_butonu);
					mesajlar.removeAll();
					arkadas_mesajlar.removeAll();
					
					int y_ekseni2 = 0;
					for (Post postlar : ziyaret_edilen_sayfa_kullanici.getCollection_of_posts()){
						JPanel panel = new JPanel();
						panel.setLayout(new BorderLayout());
						panel.setBounds(0, y_ekseni2, 595, 80);
						if(postlar.getClass()==TextPost.class){
							JLabel sol_label = new JLabel("T");
							sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
							sol_label.setVerticalAlignment(SwingConstants.TOP);
							panel.add(sol_label,BorderLayout.WEST);
							
							JLabel üst_label = new JLabel(postlar.getText());
							üst_label.setForeground(Color.BLUE);
							üst_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));		
							panel.add(üst_label,BorderLayout.NORTH);
							
							
							String tutan_beyin="";
							for(String adi_bulcam:postlar.getCollection_of_tagged_friends()){
								for(User kisiler:UserCollection.users){
									if(adi_bulcam.equals(kisiler.getUsername())){
										tutan_beyin=tutan_beyin+kisiler.getName()+" ";
										if((postlar.getCollection_of_tagged_friends().size()-1)!=postlar.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
											tutan_beyin=tutan_beyin+",";
										}
									}
								}
							}
							JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
							alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
							panel.add(alt_label, BorderLayout.SOUTH);
							
							JButton btnTagFriend = new JButton("Tag Friends");
							panel.add(btnTagFriend, BorderLayout.EAST);
							btnTagFriend.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
								
								SwingUtilities.updateComponentTreeUI(contentPane);
								}
							});
						}
						if(postlar.getClass()==ImagePost.class){
							
							JLabel sol_label = new JLabel("I");
							sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
							sol_label.setVerticalAlignment(SwingConstants.TOP);
							panel.add(sol_label,BorderLayout.WEST);

							JLabel üst_label = new JLabel(postlar.getText());
							üst_label.setForeground(Color.BLUE);
							üst_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
							panel.add(üst_label,BorderLayout.NORTH);
							
							String tutan_beyin="";
							for(String adi_bulcam:postlar.getCollection_of_tagged_friends()){
								for(User kisiler:UserCollection.users){
									if(adi_bulcam.equals(kisiler.getUsername())){
										tutan_beyin=tutan_beyin+kisiler.getName()+" ";
										if((postlar.getCollection_of_tagged_friends().size()-1)!=postlar.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
											tutan_beyin=tutan_beyin+",";
										}
									}
								}
							}
							JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
							alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
							panel.add(alt_label, BorderLayout.SOUTH);
							
							JButton btnTagFriend = new JButton("Tag Friends");
							panel.add(btnTagFriend, BorderLayout.EAST);
						}
						if(postlar.getClass()==VideoPost.class){
							
							JLabel sol_label = new JLabel("V");
							sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
							sol_label.setVerticalAlignment(SwingConstants.TOP);
							panel.add(sol_label,BorderLayout.CENTER);
							
							JLabel üst_label = new JLabel(postlar.getText());
							üst_label.setForeground(Color.BLUE);
							üst_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
							panel.add(üst_label,BorderLayout.NORTH);
							
							String tutan_beyin="";
							for(String adi_bulcam:postlar.getCollection_of_tagged_friends()){
								for(User kisiler:UserCollection.users){
									if(adi_bulcam.equals(kisiler.getUsername())){
										tutan_beyin=tutan_beyin+kisiler.getName()+" ";
										if((postlar.getCollection_of_tagged_friends().size()-1)!=postlar.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
											tutan_beyin=tutan_beyin+",";
										}
									}
								}
							}
							JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
							alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
							panel.add(alt_label, BorderLayout.SOUTH);
							JButton btnTagFriend = new JButton("Tag Friends");
							panel.add(btnTagFriend, BorderLayout.EAST);
						}
						y_ekseni2+=80;
						panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
						mesajlar.add(panel);
					}
									
					int y_ekseni3=0;
					for(User kullanici_disi:UserCollection.users){
						if(!kullanici_disi.equals(ziyaret_edilen_sayfa_kullanici) && kullanici_disi.getCollection_of_posts().size()!=0){
							System.out.println(kullanici_disi.getUsername());
							for (Post postlar_ark : kullanici_disi.getCollection_of_posts()){
								JPanel panel2 = new JPanel();
								panel2.setLayout(new BorderLayout());
								panel2.setBounds(0, y_ekseni3, 595, 80);
								if(postlar_ark.getClass()==TextPost.class){
									JLabel üst_label = new JLabel(kullanici_disi.getName()+" has shared");
									üst_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
									panel2.add(üst_label,BorderLayout.NORTH);
									
									JLabel sol_label = new JLabel("T");
									sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
									sol_label.setVerticalAlignment(SwingConstants.TOP);
									panel2.add(sol_label,BorderLayout.WEST);
									
									JLabel merkez_label = new JLabel(postlar_ark.getText());
									merkez_label.setForeground(Color.BLUE);
									merkez_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));		
									panel2.add(merkez_label,BorderLayout.CENTER);
									
									
									String tutan_beyin="";
									for(String adi_bulcam:postlar_ark.getCollection_of_tagged_friends()){
										for(User kisiler:UserCollection.users){
											if(adi_bulcam.equals(kisiler.getUsername())){
												tutan_beyin=tutan_beyin+kisiler.getName()+" ";
												if((postlar_ark.getCollection_of_tagged_friends().size()-1)!=postlar_ark.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
													tutan_beyin=tutan_beyin+",";
												}
											}
										}
									}

									JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
									alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
									panel2.add(alt_label, BorderLayout.SOUTH);
									
									
								}
								if(postlar_ark.getClass()==ImagePost.class){
									JLabel üst_label = new JLabel(kullanici_disi.getName()+" has shared");
									üst_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
									panel2.add(üst_label,BorderLayout.NORTH);
									
									JLabel sol_label = new JLabel("I");
									sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
									sol_label.setVerticalAlignment(SwingConstants.TOP);
									panel2.add(sol_label,BorderLayout.WEST);

									JLabel merkez_label = new JLabel(postlar_ark.getText());
									merkez_label.setForeground(Color.BLUE);
									merkez_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
									panel2.add(merkez_label,BorderLayout.CENTER);
									

									String tutan_beyin="";
									for(String adi_bulcam:postlar_ark.getCollection_of_tagged_friends()){
										for(User kisiler:UserCollection.users){
											if(adi_bulcam.equals(kisiler.getUsername())){
												tutan_beyin=tutan_beyin+kisiler.getName()+" ";
												if((postlar_ark.getCollection_of_tagged_friends().size()-1)!=postlar_ark.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
													tutan_beyin=tutan_beyin+",";
												}
											}
										}
									}

									JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
									alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
									panel2.add(alt_label, BorderLayout.SOUTH);
									
								}
								if(postlar_ark.getClass()==VideoPost.class){
									JLabel üst_label = new JLabel(kullanici_disi.getName()+" has shared");
									üst_label.setFont(new Font("Tahoma", Font.PLAIN, 15));
									panel2.add(üst_label,BorderLayout.NORTH);
									
									JLabel sol_label = new JLabel("V");
									sol_label.setFont(new Font("Tahoma", Font.BOLD, 30));
									sol_label.setVerticalAlignment(SwingConstants.TOP);
									panel2.add(sol_label,BorderLayout.CENTER);
									
									JLabel merkez_label = new JLabel(postlar_ark.getText());
									merkez_label.setForeground(Color.BLUE);
									merkez_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
									panel2.add(merkez_label,BorderLayout.CENTER);
									
									String tutan_beyin="";
									for(String adi_bulcam:postlar_ark.getCollection_of_tagged_friends()){
										for(User kisiler:UserCollection.users){
											if(adi_bulcam.equals(kisiler.getUsername())){
												tutan_beyin=tutan_beyin+kisiler.getName()+" ";
												if((postlar_ark.getCollection_of_tagged_friends().size()-1)!=postlar_ark.getCollection_of_tagged_friends().indexOf(adi_bulcam)){
													tutan_beyin=tutan_beyin+",";
												}
											}
										}
									}

									JLabel alt_label = new JLabel("Tagged User: "+tutan_beyin);
									alt_label.setFont(new Font("Times New Roman", Font.PLAIN, 13));	
									panel2.add(alt_label, BorderLayout.SOUTH);
								}
							
								y_ekseni3+=80;
								panel2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
								arkadas_mesajlar.add(panel2);
								SwingUtilities.updateComponentTreeUI(tabbedPane);
							}
							
						}
					}

					int belirleme=0;
					for(String arkadas_yada_degil:UserCollection.kullanici.getCollection_of_friends()){
						if(ziyaret_edilen_sayfa_kullanici.getUsername().equals(arkadas_yada_degil)){
							belirleme++;
							contentPane.add(block_misafir);
							contentPane.remove(add_misafir);
						}
					}
					if(belirleme==0){
						contentPane.add(add_misafir);
						contentPane.remove(block_misafir);
						
					}
					SwingUtilities.updateComponentTreeUI(contentPane);
				}
			}
		});
		search_list.setVisible(false);;
			
		searchbar = new JTextField();
		searchbar.setBounds(342, 14, 145, 23);
		searchbar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				search.removeAllElements();
				for(User kisi_bulma:UserCollection.users){
					if(kisi_bulma.getName().toLowerCase().startsWith(searchbar.getText().toLowerCase())&&!kisi_bulma.getName().equals(UserCollection.kullanici.getName())){
						search.addElement(kisi_bulma.getName());
					}
					
				}
				search_list.setModel(search);
				search_list.show();
				
			}
		});
		
		contentPane.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event){
				search_list.hide();
				searchbar.setText("");
			}
		});
		mavi_panel.add(searchbar);
		searchbar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search Friends");
		lblNewLabel.setBounds(230, 14, 100, 20);
		mavi_panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
	}
}
