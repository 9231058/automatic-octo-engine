package menchclient.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import menchclient.domain.Account;
import menchclient.network.MessageServer;
import menchclient.network.NetworkReader;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 3209173314811131115L;

	private JTextField username;
	private JPasswordField password;
	private JButton submit;

	public LoginFrame() {
		super("Login");
		setSize(new Dimension(300, 180));
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		JLabel username_label = new JLabel("Username:");
		sl_contentPane.putConstraint(SpringLayout.WEST, username_label, 5,
				SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, username_label, 20,
				SpringLayout.NORTH, contentPane);
		add(username_label);

		JLabel password_label = new JLabel("Password:");
		sl_contentPane.putConstraint(SpringLayout.WEST, password_label, 5,
				SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, password_label, 50,
				SpringLayout.NORTH, username_label);
		add(password_label);

		username = new JTextField(15);
		sl_contentPane.putConstraint(SpringLayout.WEST, username, 0,
				SpringLayout.EAST, username_label);
		sl_contentPane.putConstraint(SpringLayout.NORTH, username, 0,
				SpringLayout.NORTH, username_label);
		add(username);

		password = new JPasswordField(15);
		sl_contentPane.putConstraint(SpringLayout.WEST, password, 0,
				SpringLayout.EAST, password_label);
		sl_contentPane.putConstraint(SpringLayout.NORTH, password, 0,
				SpringLayout.NORTH, password_label);
		add(password);

		submit = new JButton("Submit");
		sl_contentPane.putConstraint(SpringLayout.WEST, submit, 10,
				SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, submit, 50,
				SpringLayout.NORTH, password_label);
		add(submit);
		submit.addActionListener(new SubmitButton());
	}

	private class SubmitButton implements ActionListener {

		private String accountUsername;
		private String accountPassword;

		@Override
		public void actionPerformed(ActionEvent event) {

			sendToServer();

			String receiveMessage = getFromServer();
			String[] rm = receiveMessage.split(" ");

			if (rm[0].equals("true")) {
				String accountName = rm[2];
				LoginFrame.this.dispose();
				new AccountManagerFrame(new Account(accountName,
						accountUsername, accountPassword)).setVisible(true);
			}

			if (rm[0].equals("false")) {

				LoginFrame.this.dispose();
				accountUsername = "";
				accountPassword = "";
				JOptionPane.showMessageDialog(null,
						"incorrect username or password");
			}

		}

		private void sendToServer() {

			accountUsername = username.getText();
			accountPassword = new String(password.getPassword());
			MessageServer.getInstance().sendMessage(
					"login u " + accountUsername + " p " + accountPassword);
		}

		private String getFromServer() {
			// <<true " " "account name">> OR <<false>>
			NetworkReader reader = new NetworkReader();
			return reader.receiveMessage();
		}
	}

}
