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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SocketClient {
	Socket server;
	String uname;
	String pass;
	String confPass;
	
	
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
		
		
		
		
		
		JLabel lblUsername = new JLabel ("Username");
		lblUsername.setBounds(44, 97, 112, 23);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(44, 146, 112, 23);;
		frame.getContentPane().add(lblPassword);
		
		JLabel lblConfPassword = new JLabel("Confirm Password");
		lblConfPassword.setBounds(44, 195, 112,23);;
		frame.getContentPane().add(lblConfPassword);
		
		JTextField username = new JTextField();
		username.setBounds(200, 98, 154, 23);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JTextField passField = new JTextField();
		passField.setBounds(200, 147, 154, 23);
		frame.getContentPane().add(passField);
		
		
		JTextField ConfPassField = new JTextField();
		ConfPassField.setBounds(200, 195, 154, 23);
		frame.getContentPane().add(ConfPassField);
		
		
		
		
		JButton btnLogin = new JButton("Login");
		PrintWriter out = new PrintWriter(server.getOutputStream(), true);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uname = username.getText();
				String pass = passField.getText();
				String confPass = ConfPassField.getText();
				/*
				List<String> account = new ArrayList<String>();
				account.add(uname);
				account.add(pass);
				account.add(confPass);
				*/
				
				String acc = uname + "," + pass + "," + confPass;
				
				out.println(acc);
				if (pass.contentEquals(confPass)) {
					JTextField myOutput = new JTextField(50);
					myOutput.setBounds(120, 70, 149, 20);
					lblUsername.setBounds(0,0,0,0);
					lblPassword.setBounds(0,0,0,0);
					lblConfPassword.setBounds(0,0,0,0);
					
					username.setBounds(0,0,0,0);
					passField.setBounds(0,0,0,0);
					ConfPassField.setBounds(0,0,0,0);
					myOutput.setText("Account has been created");
					
					frame.getContentPane().add(myOutput);
					
					/*
					lblUsername.setBounds(0,0,0,0);
					lblPassword.setBounds(0,0,0,0);
					lblConfPassword.setBounds(0,0,0,0);
					
					username.setBounds(0,0,0,0);
					passField.setBounds(0,0,0,0);
					ConfPassField.setBounds(0,0,0,0);
					*/
					
				}
				else {
					JTextField myOutput = new JTextField(50);
					myOutput.setBounds(120, 70, 145, 20);
					myOutput.setText("Passwords do not match");
					frame.getContentPane().add(myOutput);
					
					username.removeAll();
					passField.removeAll();
					ConfPassField.removeAll();
					chatArea.removeAll();
				}
			}
			
		});
		
		
		
		btnLogin.setBounds(150, 230, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		//add panel to simpleChat panel
		simpleChat.add(userInput, BorderLayout.SOUTH);
		//add simpleChat panel to frame
		frame.add(simpleChat, BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
		
			

		System.out.println("Listening for input");
		try(Scanner si = new Scanner(System.in);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));){
			String line = "";
			while(true) {
				try {
					System.out.println("Waiting for input");
					line = si.nextLine();
					if(!"quit".equalsIgnoreCase(line)) {
						out.println(line);
					}
					else {
						break;
					}
					line = "";
					String fromServer = in.readLine();
					
					
					
					if(fromServer != null) {
						System.out.println("Reply from server: " + fromServer);
					}
					else {
						System.out.println("Server disconnected");
						break;
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