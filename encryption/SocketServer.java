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
			int whileState = 0;
			while ((fromClient = in.readLine()) != null) {
				System.out.println("From client: " + fromClient);
				
				if (i==0) {
					userName = fromClient;
					i++;
				}
				else if (i==1) {
					password1 = fromClient;
					i++;
					System.out.println("i ==" + i);
				}
				else {
					password2 = fromClient;
					i++;
					System.out.println("i ==" + i);
				}
				
				System.out.println(userName);
				System.out.println(password1);
				System.out.println(password2);
				
				if (toClient == "Enter Password" & i==3 & (password1.equals(password2)) || i % 3 == 0 & (password1.equals(password2)) ) {
					toClient = "Password Confirmed";
					System.out.println("first if");
				}
				
				if (password1.equals(password2)){
					System.out.println("true");
				}
				else {
					System.out.println("false");
				}
				if (i == 3) {
					i = 1;
				}
				//if (whileState ==0) {
				out.println(toClient);
				//}
				
				if (toClient == "Password Confirmed") {
					toClient = "changed up";
					out.println(toClient);
				}
				System.out.println("toClient is: " + toClient);
				
				
				
				j++;
				System.out.println("Sending to client: " + toClient);
				
				
				
				if ("kill server".equalsIgnoreCase(fromClient)) {
					out.println("Server received kill command, disconnecting");
					break;
				}
				else if (whileState==0){
					
					
					
					
					
					if (j % 2==0 & j != 0 || password1.contentEquals(password2) & i==1) {
						
						if (password1.contentEquals(password2)){
							//toClient = "Password Confirmed";
							System.out.println("this should run wtf");
							//out.println(toClient);
							map.put(userName, password1);
							System.out.println(toClient);
							System.out.println(j + " j%2 and pass matches");
							whileState = 1;	
						}	
						
						else {					
							toClient = "Confirm Password";
							System.out.println(j + " j%2 failed");
						}
					} //end of password confirmed if statement
					
					else if (j % 2 == 1){
						toClient = "Enter Password";
						System.out.println(j + " else if j%2=0+1");
					}
					
					else {
						toClient = "Confirm Password";
						System.out.println(j + " j == 0");
					}
				} //end of big if else statement
				else {
					toClient = "ayy";
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