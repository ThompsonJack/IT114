package com.encryption;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//cd eclipse-workspace/EncryptAcct/src


public class SocketClient {
	static String uname;
	Socket server;
	public SocketClient() {
		
	}
	public void connect(String address, int port) {
		try {
			server = new Socket(address, port);
			System.out.println("Client connected");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void start() throws IOException {
		if(server == null) {
			return;
		}
		System.out.println("Listening for input");
		try(Scanner si = new Scanner(System.in);
				PrintWriter out = new PrintWriter(server.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));){
			String line = "";
			System.out.println("Enter Username: ");
			int i = 0;
			String ComparePass = "Enter Password";
			String PassConfirm = "Password Confirmed";
			while(true) {
				try {
					
					//System.out.println("Waiting for input");
					line = si.nextLine();
					if(!"quit".equalsIgnoreCase(line)) {
						out.println(line);
					}
					else {
						break;
					}
					line = "";
					String fromServer = in.readLine();
					if (fromServer.contentEquals(ComparePass) & i != 0) {
						System.out.println("Passwords do not match, please try again");
						i++;
					}
					
					else {
						i++;
						System.out.println(i);
					}
				
					if(fromServer != null) {
						System.out.println("Reply from server: " + fromServer);
						
					}
					
					else {
						System.out.println("Server disconnected");
						break;
					}
					if (fromServer.contentEquals(PassConfirm)){
						System.out.println("Account created");
					}
				}
				catch(IOException e) {
					System.out.println("Connection dropped");
					break;
				}
			}
			System.out.println("Exited loop");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	private void close() {
		if(server != null) {
			try {
				server.close();
				System.out.println("Closed socket");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		/*
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
				uname = username.getText();
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
	*/
		//############################## END OF UI ###################################
		
		SocketClient client = new SocketClient();
		int port = 3002;
		try{
			//not safe but try-catch will get it
			port = Integer.parseInt(args[0]);
		}
		catch(Exception e){
			System.out.println("Invalid port");
		}
		if(port == -1){
			return;
		}
		client.connect("127.0.0.1", port);
		try {
			//if start is private, it's valid here since this main is part of the class
			client.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}