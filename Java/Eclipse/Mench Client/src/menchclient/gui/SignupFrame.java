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

import menchclient.network.MessageServer;
import menchclient.network.NetworkReader;

public class SignupFrame extends JFrame {

	private static final long serialVersionUID = -4416351093988069508L;

	private JButton create;
	private JTextField name;
	private JTextField username;
	private JPasswordField password;

	public SignupFrame() {
		super("Sign up");
		setSize(new Dimension(300, 400));
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		JLabel name_label = new JLabel("name:");
		sl_contentPane.putConstraint(SpringLayout.WEST, name_label, 5,
				SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, name_label, 20,
				SpringLayout.NORTH, contentPane);
		add(name_label);

		JLabel username_label = new JLabel("username:");
		sl_contentPane.putConstraint(SpringLayout.WEST, username_label, 5,
				SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, username_label, 20,
				SpringLayout.NORTH, name_label);
		add(username_label);

		JLabel password_label = new JLabel("password:");
		sl_contentPane.putConstraint(SpringLayout.WEST, password_label, 5,
				SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, password_label, 20,
				SpringLayout.NORTH, username_label);
		add(password_label);

		name = new JTextField(15);
		sl_contentPane.putConstraint(SpringLayout.WEST, name, 0,
				SpringLayout.EAST, name_label);
		sl_contentPane.putConstraint(SpringLayout.NORTH, name, 0,
				SpringLayout.NORTH, name_label);
		add(name);

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

		create = new JButton("Create");
		sl_contentPane.putConstraint(SpringLayout.WEST, create, 10,
				SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, create, 50,
				SpringLayout.NORTH, password_label);
		add(create);
		create.addActionListener(new CreateButtom());
	}

	private class CreateButtom implements ActionListener {

		private String accountName;
		private String accountUsername;
		private String accountPassword;

		@Override
		public void actionPerformed(ActionEvent e) {

			sendToServer();

			String receiveMessage = getFromServer();

			if (receiveMessage.equals("true")) {
				SignupFrame.this.dispose();
				JOptionPane.showMessageDialog(null, "signed up succesfully!");
			}

			if (receiveMessage.equals("false")) {
				SignupFrame.this.dispose();
				JOptionPane.showMessageDialog(null, "UNSUCCESFULL signup");
			}

		}

		private void sendToServer() {

			accountName = name.getText();
			accountUsername = username.getText();
			accountPassword = new String(password.getPassword());
			MessageServer.getInstance().sendMessage(
					"signup n " + accountName + " u " + accountUsername + " p "
							+ accountPassword);
		}

		private String getFromServer() {
			NetworkReader reader = new NetworkReader();
			return reader.receiveMessage();
		}
	}

}
