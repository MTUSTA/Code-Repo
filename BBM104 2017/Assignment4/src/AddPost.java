import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddPost extends JFrame {

	private JPanel contentPane;
	private JTextField girilen_txt;
	private JTextField girilen_latitude;
	private JTextField girilen_longitude;
	private JTextField dosyadiicin;
	private JTextField durationicin;
	private JTextField genisliktext;
	private JTextField yüksekliktext;



	/**
	 * Launch the application.
	 */
	public static void addpost_acma() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPost frame = new AddPost();
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
	public AddPost() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 596, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblPostType = new JLabel("Post Type");
		lblPostType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPostType.setBounds(12, 31, 73, 30);
		contentPane.add(lblPostType);
		
		
		JLabel yazi = new JLabel("Text:");
		yazi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		yazi.setBounds(12, 94, 73, 30);
		contentPane.add(yazi);
		
		girilen_txt = new JTextField();
		girilen_txt.setBounds(97, 99, 469, 22);
		contentPane.add(girilen_txt);
		girilen_txt.setColumns(10);
		
		JButton btnNewButton = new JButton("Add Post");

		btnNewButton.setBounds(469, 35, 97, 25);
		contentPane.add(btnNewButton);
		
		JLabel Latitude = new JLabel("Latitude");
		Latitude.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Latitude.setBounds(12, 137, 73, 30);
		contentPane.add(Latitude);
		
		girilen_latitude = new JTextField();
		girilen_latitude.setBounds(97, 142, 116, 22);
		contentPane.add(girilen_latitude);
		girilen_latitude.setColumns(10);
		
		JLabel Longitude = new JLabel("Longitude");
		Longitude.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Longitude.setBounds(225, 141, 73, 22);
		contentPane.add(Longitude);
		
		girilen_longitude = new JTextField();
		girilen_longitude.setBounds(310, 142, 116, 22);
		contentPane.add(girilen_longitude);
		girilen_longitude.setColumns(10);

		JLabel dosya_adi = new JLabel("Filename:");
		dosya_adi.setBounds(12, 180, 73, 22);
		dosya_adi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		JLabel uzunluk = new JLabel("Duration:");
		uzunluk.setBounds(12, 215, 73, 22);
		uzunluk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		dosyadiicin = new JTextField();
		dosyadiicin.setBounds(97, 180, 201, 22);	
		dosyadiicin.setColumns(10);
		
		durationicin = new JTextField();
		durationicin.setBounds(97, 215, 80, 22);
		durationicin.setColumns(10);
		
		JLabel genislik = new JLabel("Width:");
		genislik.setBounds(12, 215, 73, 22);
		genislik.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
		
		genisliktext = new JTextField();
		genisliktext.setBounds(97, 215, 56, 22);
		genisliktext.setColumns(10);
		
		JLabel yukseklik = new JLabel("Height:");
		yukseklik.setFont(new Font("Tahoma", Font.PLAIN, 15));
		yukseklik.setBounds(175, 215, 49, 22);
		
		
		yüksekliktext = new JTextField();
		yüksekliktext.setBounds(225, 215, 73, 22);
		yüksekliktext.setColumns(10);

		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((boolean)comboBox.getSelectedItem().equals("TextPost")){
					contentPane.remove(dosya_adi);
					contentPane.remove(dosyadiicin);
					contentPane.remove(uzunluk);
					contentPane.remove(durationicin);
					contentPane.remove(genislik);
					contentPane.remove(genisliktext);
					contentPane.remove(yukseklik);
					contentPane.remove(yüksekliktext);
					SwingUtilities.updateComponentTreeUI(contentPane);					
				}
				if((boolean)comboBox.getSelectedItem().equals("ImagePost")){
					contentPane.add(dosya_adi);
					contentPane.add(dosyadiicin);
					contentPane.add(genislik);
					contentPane.add(genisliktext);
					contentPane.add(yukseklik);
					contentPane.add(yüksekliktext);
					contentPane.remove(uzunluk);
					contentPane.remove(durationicin);
					SwingUtilities.updateComponentTreeUI(contentPane);
				}
				if((boolean)comboBox.getSelectedItem().equals("VideoPost")){
					contentPane.add(dosya_adi);
					contentPane.add(dosyadiicin);
					contentPane.add(uzunluk);
					contentPane.add(durationicin);
					contentPane.remove(genislik);
					contentPane.remove(genisliktext);
					contentPane.remove(yukseklik);
					contentPane.remove(yüksekliktext);
					SwingUtilities.updateComponentTreeUI(contentPane);	
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"TextPost", "ImagePost", "VideoPost"}));
		comboBox.setBounds(97, 36, 177, 22);
		contentPane.add(comboBox);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((boolean) comboBox.getSelectedItem().equals("TextPost")){
					String[] txt_post_ekleme = {"ADDPOST-TEXT",UserCollection.kullanici.getUsername(),girilen_txt.getText(),girilen_longitude.getText(),girilen_latitude.getText(),""};
					UserCollection.kullanici.addPost_TXT(txt_post_ekleme);

				}
				if((boolean) comboBox.getSelectedItem().equals("ImagePost")){
					String reso_top=genisliktext.getText()+"<x>"+yüksekliktext.getText();
					String[] txt_post_ekleme = {"ADDPOST-IMAGE",UserCollection.kullanici.getUsername(),girilen_txt.getText(),girilen_longitude.getText(),girilen_latitude.getText(),"",dosyadiicin.getText(),reso_top};
					UserCollection.kullanici.addPost_IMAGE(txt_post_ekleme);

				}
				if((boolean) comboBox.getSelectedItem().equals("VideoPost")){
					String[] txt_post_ekleme = {"ADDPOST-VIDEO",UserCollection.kullanici.getUsername(),girilen_txt.getText(),girilen_longitude.getText(),girilen_latitude.getText(),"",dosyadiicin.getText(),durationicin.getText()};
					UserCollection.kullanici.addPost_VIDEO(txt_post_ekleme);

					
				}
				}
		});
	}
}
