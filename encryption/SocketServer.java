package com.encryption;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
//cd eclipse-workspace/EncryptAcct/src/com/encryption
//javac IT114encryptionproj/SocketServer.java


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
			String toClient = "";
			while ((fromClient = in.readLine()) != null) {
				System.out.println("From client: " + fromClient);
				
				
				
				String[] account = fromClient.split(",");
				
				
				
				if (account[1].contentEquals(account[2])) {
					HashMap<String, String> hashAcc = new HashMap<String, String>();
					hashAcc.put(account[0],account[1]);
					System.out.println(hashAcc);
					toClient = "Account has been created";
					
					final String secretKey = "sshh!!!!";
				    
					String pass = account[0];
				    String originalString = pass;
				    String encryptedString = SocketServer.encrypt(originalString, secretKey) ;
				    String decryptedString = SocketServer.decrypt(encryptedString, secretKey) ; 
				    
				    System.out.println(originalString);
				    System.out.println(encryptedString);
				    System.out.println(decryptedString);
					
				}
				else {
					toClient = "Passwords do not match, please try again";
					
				}
				
				System.out.println("Sending to client: " + toClient);
				
				if ("kill server".equalsIgnoreCase(fromClient)) {
					out.println("Server received kill command, disconnecting");
					break;
				}
				else {
					out.println(toClient);
				}
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
	
	
	private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encrypt(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
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