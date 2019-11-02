package com.encryption;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

//cd eclipse-workspace/EncryptAcct/src/com/encryption
//javac IT114encryptionproj/SocketServer.java

public class SocketServer {
	int port = 3102;
	public SocketServer() {
	}
	private void start(int port) {
		this.port = port;
		System.out.println("Waiting for client");
		try (ServerSocket serverSocket = new ServerSocket(port);
				Socket client = serverSocket.accept();
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));) {
			
			System.out.println("Client connected, waiting for message");
			
			String fromClient = "";
			String toClient = "Enter Password";
			int i = 0, j = -1;
			String userName = "userName", password1 = "pass1", password2 = "pass2";
			System.out.println("Sending to client: " + "Enter Username");
			HashMap<String, String> map = new HashMap<>();
			
			while ((fromClient = in.readLine()) != null) {
				System.out.println("From client: " + fromClient);
				
				
				
				if (i == 3) {
					i = 1;
				}
				
				out.println(toClient);
				
				
				
				j++;
				System.out.println("Sending to client: " + toClient);
				if (i==0) {
					userName = fromClient;
					i++;
				}
				else if (i==1) {
					password1 = fromClient;
					i++;
				}
				else {
					password2 = fromClient;
					i++;
				}
				System.out.println(userName);
				System.out.println(password1);
				System.out.println(password2);
				
				if ("kill server".equalsIgnoreCase(fromClient)) {
					out.println("Server received kill command, disconnecting");
					break;
				}
				else {
					if (j % 2==0 & j != 0 || password1.contentEquals(password2) & i==1) {
						if (password1.contentEquals(password2)){
							toClient = "Password Confirmed";
							System.out.println("this should run wtf");
							out.println(toClient);
							map.put(userName, password1);
							System.out.println(toClient);
							System.out.println(j + " j%2 and pass matches");
							
							
						}	
						else {
							
							toClient = "Confirm Password";
							System.out.println(j + " j%2 failed");
							
						
						}
					}
					else if (j % 2 == 1){
						
						toClient = "Enter Password";
						System.out.println(j + " else if j%2=0+1");
					}
					else {
						toClient = "Confirm Password";
						System.out.println(j + " j == 0");
						
					}
				}
			}
			if (map.containsKey(userName)) { 
		        String a = map.get(userName); 
		        System.out.println("value for key "
		                           + userName + " is: "
		                           + a); 
		    } 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("closing server socket");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	

	public static void main(String[] arg) {
		System.out.println("Starting Server");
		SocketServer server = new SocketServer();
		int port = 3002;
		if(arg.length > 0){
			try{
				port = Integer.parseInt(arg[0]);
			}
			catch(Exception e){
				System.out.println("Invalid port: " + arg[0]);
			}		
		}
		if(port > -1){
			System.out.println("Server listening on port " + port);
			server.start(port);
		}
		System.out.println("Server Stopped");
	}
}