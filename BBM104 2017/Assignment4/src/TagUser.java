import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TagUser extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void taguser() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TagUser frame = new TagUser();
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
	public TagUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 304, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel yazi_öylesine = new JLabel("Taggable Friends");
		yazi_öylesine.setFont(new Font("Tahoma", Font.PLAIN, 15));
		yazi_öylesine.setBounds(12, 13, 136, 32);
		contentPane.add(yazi_öylesine);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(12, 49, 262, 215);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		list.setModel(ProfilePage.DLM_tag);
		scrollPane.setViewportView(list);
		
		JButton taglama = new JButton("Tag Friend");
		taglama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int selectedIndex = list.getSelectedIndex();
				ProfilePage.DLM_tag.remove(selectedIndex);

				
				
			}
		});
		taglama.setBounds(12, 282, 262, 25);
		contentPane.add(taglama);
	}
}
