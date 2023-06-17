package viewLogin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import viewLogin.ForgotPassword;



public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField UserName;
	private JPasswordField PassWord;
	private JButton btnForgotPassword;
	private JButton btnLogin;
	
	private static viewLogin.ForgotPassword ForgotPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		initComponents();
	}
	public void initComponents()
	{
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_Logo = new JPanel();
		panel_Logo.setBackground(new Color(147, 255, 147));
		panel_Logo.setForeground(new Color(0, 255, 128));
		panel_Logo.setBounds(0, 0, 400, 500);
		panel_Logo.setLayout(null);
		contentPane.add(panel_Logo);
		
		JPanel panel_Login = new JPanel();
		panel_Login.setBackground(new Color(255, 255, 255));
		panel_Login.setBounds(400, 0, 400, 500);
		contentPane.add(panel_Login);
		panel_Login.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 46));
		lblNewLabel.setBounds(120, 81, 146, 85);
		panel_Login.add(lblNewLabel);
		
		UserName = new JTextField();
		UserName.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		UserName.setBounds(76, 187, 266, 26);
		panel_Login.add(UserName);
		UserName.setColumns(10);
		
		btnForgotPassword = new JButton("Forgot password?");
		btnForgotPassword.setFont(new Font("Arial", Font.ITALIC, 12));
		btnForgotPassword.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnForgotPassword.setContentAreaFilled(false);
		btnForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnForgotPassword.setBounds(65, 311, 113, 21);
		panel_Login.add(btnForgotPassword);
		
		PassWord = new JPasswordField();
		PassWord.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		PassWord.setBounds(76, 249, 266, 19);
		panel_Login.add(PassWord);
		
		btnLogin = new JButton("Login");
		btnLogin.setContentAreaFilled(false);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(252, 304, 90, 35);
		panel_Login.add(btnLogin);
		
   
		
		ImageIcon icon1 = new ImageIcon((Login.class.getResource("/icon/user.png")));
		Image img = icon1.getImage();
        Image newImg = img.getScaledInstance(24,24, Image.SCALE_SMOOTH);
        JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(newImg));
		lblNewLabel_1.setBounds(45, 187, 34, 35);
		panel_Login.add(lblNewLabel_1);
		
		ImageIcon icon11 = new ImageIcon((Login.class.getResource("/icon/password.png")));
		Image img1 = icon11.getImage();
        Image newImg1 = img1.getScaledInstance(24,24, Image.SCALE_SMOOTH);
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(newImg1));
		lblNewLabel_1_1.setBounds(45, 244, 34, 35);
		panel_Login.add(lblNewLabel_1_1);
		
	}

}
