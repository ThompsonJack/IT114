package com.encryption;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


public class SimpleChatUI {
	public static void main(String[] args) {
		//create frame
		JFrame frame = new JFrame("Simple Chat Mockup");
		frame.setLayout(new BorderLayout());
		//create panel
		JPanel simpleChat = new JPanel();
		simpleChat.setPreferredSize(new Dimension(400,400));
		simpleChat.setLayout(new BorderLayout());
		//create text area for messages
		JTextArea textArea = new JTextArea();
		//don't let the user edit this directly
		textArea.setEditable(false);
		textArea.setText("");
		//create panel to hold multiple controls
		JPanel chatArea = new JPanel();
		chatArea.setLayout(new BorderLayout());
		//add text area to chat area
		chatArea.add(textArea, BorderLayout.CENTER);
		chatArea.setBorder(BorderFactory.createLineBorder(Color.black));
		//add chat area to panel
		simpleChat.add(chatArea, BorderLayout.CENTER);
		//create panel to hold multiple controls
		JPanel userInput = new JPanel();
		
		
		
		
		
		JLabel lblUsername = new JLabel ("Userame");
		lblUsername.setBounds(58, 97, 112, 23);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(58, 146, 112, 23);;
		frame.getContentPane().add(lblPassword);
		
		JLabel lblConfPassword = new JLabel("Confirm Password");
		lblConfPassword.setBounds(58, 195, 112,23);;
		frame.getContentPane().add(lblConfPassword);
		
		JTextField username = new JTextField();
		username.setBounds(214, 98, 154, 23);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(214, 147, 154, 23);
		frame.getContentPane().add(passwordField);
		
		
		JPasswordField ConfPassField = new JPasswordField();
		ConfPassField.setBounds(214, 195, 154, 23);
		frame.getContentPane().add(ConfPassField);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uname = username.getText();
				char[] pass = passwordField.getPassword();
				System.out.println(uname + pass);
			}
		});
		btnLogin.setBounds(171, 230, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		//add panel to simpleChat panel
		simpleChat.add(userInput, BorderLayout.SOUTH);
		//add simpleChat panel to frame
		frame.add(simpleChat, BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
	}
}