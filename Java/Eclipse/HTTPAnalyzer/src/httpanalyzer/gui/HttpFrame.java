package httpanalyzer.gui;

import httpanalyzer.network.HttpRequest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class HttpFrame extends JFrame {

	private static final long serialVersionUID = -7983964315329244457L;

	private JPanel contentPane;
	private JTextField URLTextField;
	private JLabel serverAppLabel;

	public HttpFrame() {
		super("HTTP Analyzer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		URLTextField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, URLTextField, 0,
				SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, URLTextField, 0,
				SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, URLTextField, 0,
				SpringLayout.EAST, contentPane);
		contentPane.add(URLTextField);
		URLTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					setContent(URLTextField.getText());
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		});

		serverAppLabel = new JLabel("Server HTTP Deamon : ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, serverAppLabel, 0,
				SpringLayout.SOUTH, URLTextField);
		contentPane.add(serverAppLabel);
	}

	private void setContent(String addr) throws IOException {
		serverAppLabel.setText("Server HTTP Deamon : ");
		HttpRequest httpRequest = new HttpRequest(addr);
		serverAppLabel.setText(serverAppLabel.getText()
				+ httpRequest.getServerApp());
		System.err.println(httpRequest.getCookiesSize());
		System.err.println(httpRequest.getCookie(0).getMaxAge());
	}

}
