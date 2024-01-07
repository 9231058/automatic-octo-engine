package menchclient.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 4543629293781346075L;
	JFrame loginFrame;
	JFrame signUpFrame;
	JButton login;
	JButton signUp;

	public MainFrame() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 300);
		setLayout(null);

		JLabel welcome = new JLabel("WELCOME");
		welcome.setBounds(25, 10, 210, 30);
		add(welcome);
		JLabel jl1 = new JLabel("if you have an account please login");
		jl1.setBounds(25, 50, 250, 30);
		add(jl1);
		JLabel jl2 = new JLabel("if you don't please signup");
		jl2.setBounds(25, 90, 250, 30);
		add(jl2);

		login = new JButton("Login");
		signUp = new JButton("SignUp");

		login.setBounds(50, 210, 80, 40);
		login.addActionListener(new LoginButtom());
		add(login);

		signUp.setBounds(170, 210, 80, 40);
		signUp.addActionListener(new SignUpButtom());
		add(signUp);

		setVisible(true);
	}

	class LoginButtom implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			loginFrame = new LoginFrame();
			loginFrame.setVisible(true);
		}
	}

	class SignUpButtom implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			signUpFrame = new SignupFrame();
			signUpFrame.setVisible(true);
		}
	}
}