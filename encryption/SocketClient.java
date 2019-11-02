package com.encryption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClient {
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
						if (fromServer.contentEquals(ComparePass)) {
							
						}
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