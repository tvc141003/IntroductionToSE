package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Toolkit;

public class ForgotPassWord extends JFrame {

	private JPanel contentPane;
	private JButton btnLogin;
	
	int x = 1100;
	int y = 600;
	private JTextField UserName;
	private JTextField Mail;
	

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { ForgotPassWord frame = new
	 * ForgotPassWord(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public ForgotPassWord() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ForgotPassWord.class.getResource("/Icon/7.png")));
		setTitle("Forgot PassWord");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, x, y);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panelLogo = new JPanel();
		panelLogo.setBackground(new Color(255, 255, 255));
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(new Color(0, 255, 128));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(panelLogo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelLogin, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panelLogin, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
				.addComponent(panelLogo, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
		);
		
		JLabel Title = new JLabel("FORGOT PASSWORD");
		Title.setFont(new Font("Segoe UI", Font.BOLD, 30));
		
		btnLogin = new JButton("Login");
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ForgotPassWord.class.getResource("/Icon/iconUser.png")));
		
		UserName = new JTextField();
		UserName.setOpaque(false);
		UserName.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		UserName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ForgotPassWord.class.getResource("/Icon/iconMail.png")));
		
		Mail = new JTextField();
		Mail.setOpaque(false);
		Mail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		Mail.setColumns(10);
		GroupLayout gl_panelLogin = new GroupLayout(panelLogin);
		gl_panelLogin.setHorizontalGroup(
			gl_panelLogin.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelLogin.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_panelLogin.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelLogin.createSequentialGroup()
							.addGroup(gl_panelLogin.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelLogin.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(18)
									.addComponent(UserName, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
								.addComponent(Title))
							.addContainerGap(170, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panelLogin.createSequentialGroup()
							.addGroup(gl_panelLogin.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelLogin.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(Mail, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
									.addGap(9)))
							.addGap(43))))
		);
		gl_panelLogin.setVerticalGroup(
			gl_panelLogin.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelLogin.createSequentialGroup()
					.addGap(127)
					.addComponent(Title, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
					.addGap(43)
					.addGroup(gl_panelLogin.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelLogin.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
							.addGap(26))
						.addGroup(gl_panelLogin.createSequentialGroup()
							.addComponent(UserName, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
							.addGap(34)))
					.addGroup(gl_panelLogin.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(Mail, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(150))
		);
		panelLogin.setLayout(gl_panelLogin);
		ImageIcon icon1 = new ImageIcon(Login.class.getResource("/Icon/logo.png"));
		Image img = icon1.getImage();
        Image newImg = img.getScaledInstance(700,500, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel("");
		lblLogo.setBackground(new Color(64, 0, 64));
		lblLogo.setIcon(new ImageIcon(newImg));
		panelLogo.add(lblLogo);
		contentPane.setLayout(gl_contentPane);
		
   
		//ImageIcon icon = new ImageIcon("User(1).png");
	}

}
