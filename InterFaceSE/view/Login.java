package view;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField UserName;
	private JPasswordField PassWord;
	private JButton btnLogin;
	private JButton btnForgotPassWord;
	
	private static view.ForgotPassWord ForgotPassword;
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Login frame = new Login();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */
	int x = 1100;
	int y = 600;
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Icon/7.png")));
		setTitle("Login");
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
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelLogo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panelLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelLogo, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/Icon/iconUser.png")));
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(Login.class.getResource("/Icon/iconPassword.png")));
		
		UserName = new JTextField();
		UserName.setOpaque(false);
		UserName.setCursor(new Cursor(Cursor.HAND_CURSOR));
		UserName.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		UserName.setColumns(10);
		
		PassWord = new JPasswordField();
		PassWord.setOpaque(false);
		PassWord.setCursor(new Cursor(Cursor.HAND_CURSOR));
		PassWord.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		
		btnLogin = new JButton("Login");
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		btnForgotPassWord = new JButton("Forgot password?");
		btnForgotPassWord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ForgotPassword = new view.ForgotPassWord();
				setVisible(false);
				ForgotPassword.setVisible(true);
			}
		});
		btnForgotPassWord.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnForgotPassWord.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		btnForgotPassWord.setOpaque(false);
		btnForgotPassWord.setBackground(new Color(255, 255, 255));
		btnForgotPassWord.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		GroupLayout gl_panelLogin = new GroupLayout(panelLogin);
		gl_panelLogin.setHorizontalGroup(
			gl_panelLogin.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelLogin.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_panelLogin.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelLogin.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(UserName, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
						.addGroup(gl_panelLogin.createSequentialGroup()
							.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelLogin.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelLogin.createSequentialGroup()
									.addComponent(btnForgotPassWord, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
									.addGap(38)
									.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
								.addComponent(PassWord, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))))
					.addGap(42))
				.addGroup(gl_panelLogin.createSequentialGroup()
					.addGap(122)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(113))
		);
		gl_panelLogin.setVerticalGroup(
			gl_panelLogin.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelLogin.createSequentialGroup()
					.addGap(89)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
					.addGap(68)
					.addGroup(gl_panelLogin.createParallelGroup(Alignment.TRAILING)
						.addComponent(UserName, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(33)
					.addGroup(gl_panelLogin.createParallelGroup(Alignment.TRAILING)
						.addComponent(PassWord, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
					.addGap(37)
					.addGroup(gl_panelLogin.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
						.addComponent(btnForgotPassWord))
					.addGap(169))
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
