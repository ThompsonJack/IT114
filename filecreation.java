package Classwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class filecreation {
	public static void main(String[] args) throws IOException {
		
		File file = new File("C:\\Users\\footb\\Documents\\Homework\\IT114\\hw5\\kappa.txt");
		  
		//Create the file
		if (file.createNewFile())
		{
		    System.out.println("File is created");
		} else {
		    System.out.println("File already exists");
		}
		 
		//Write Content
		FileWriter writer = new FileWriter(file);
		writer.write("Step 2 and 3 done");
		writer.close();
		//Read Content
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		String st; 
		st = br.readLine();
		System.out.println(st); 
		//Store different data types (This took forever)
		String stringsFromFile = st.replaceAll("[1-9]", "");
		String noExtraSpaces = stringsFromFile.trim().replaceAll(" +", ", ");
		System.out.println("Strings in file: " + noExtraSpaces);
	
		  String newKappa = st.replaceAll("[a-zA-Z]", " ");
		  //System.out.println(newKappa);
		  String after = newKappa.trim().replaceAll(" +", ",");
		  //System.out.println(after);
          String[] stringArray = after.split(",");
          //System.out.println(stringArray);
          int[] intArray = new int[stringArray.length];
             for (int i = 0; i < stringArray.length; i++) {
                 String numberAsString = stringArray[i];
                 intArray[i] = Integer.parseInt(numberAsString);
          }
          System.out.println("Number of integers: " + intArray.length);
          System.out.print("Integers in file: ");
          for (int number : intArray) {
              System.out.print(number + ", ");
      }
	   
	
		br.close();
	}
}