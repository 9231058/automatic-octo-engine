package jscriptlanguage.client.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

import jscriptlanguage.client.net.ScriptClient;

public class ScriptFrame extends JFrame {

	private static final long serialVersionUID = -5433701295715556389L;
	private JPanel contentPane;
	private SpringLayout sl_contentpane;
	private JTextPane scriptPane;
	private JTextField ipField;
	private JButton exitButton;
	private JButton submitButton;

	public ScriptFrame() {
		super("JScrip Language");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(450, 500));
		contentPane = new JPanel();
		sl_contentpane = new SpringLayout();
		contentPane.setLayout(sl_contentpane);

		scriptPane = new JTextPane();
		scriptPane.setPreferredSize(new Dimension(300, 450));
		sl_contentpane.putConstraint(SpringLayout.SOUTH, scriptPane, -10,
				SpringLayout.SOUTH, contentPane);
		sl_contentpane.putConstraint(SpringLayout.WEST, scriptPane, 10,
				SpringLayout.WEST, contentPane);
		contentPane.add(scriptPane);

		ipField = new JTextField();
		sl_contentpane.putConstraint(SpringLayout.SOUTH, ipField, -10,
				SpringLayout.NORTH, scriptPane);
		sl_contentpane.putConstraint(SpringLayout.WEST, ipField, 0,
				SpringLayout.WEST, scriptPane);
		sl_contentpane.putConstraint(SpringLayout.EAST, ipField, 0,
				SpringLayout.EAST, scriptPane);
		contentPane.add(ipField);

		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(100, 50));
		sl_contentpane.putConstraint(SpringLayout.NORTH, submitButton, 10,
				SpringLayout.NORTH, contentPane);
		sl_contentpane.putConstraint(SpringLayout.WEST, submitButton, 25,
				SpringLayout.EAST, scriptPane);
		submitButton.addActionListener(new SubmitHandler());
		contentPane.add(submitButton);

		exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(100, 50));
		sl_contentpane.putConstraint(SpringLayout.NORTH, exitButton, 10,
				SpringLayout.SOUTH, submitButton);
		sl_contentpane.putConstraint(SpringLayout.WEST, exitButton, 25,
				SpringLayout.EAST, scriptPane);
		exitButton.addActionListener(new exitHandler());
		contentPane.add(exitButton);

		setContentPane(contentPane);
	}

	private class SubmitHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			new ScriptClient(scriptPane.getText(), ipField.getText());
		}

	}

	private class exitHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}

	}

}
