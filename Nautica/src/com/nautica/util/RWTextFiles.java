package com.nautica.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class RWTextFiles {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// create the file
		File ff=new File ("\\temp.txt");
		ff.createNewFile();
		
		//write into the file
		FileWriter w=new FileWriter("\\temp.txt");
		BufferedWriter out=new BufferedWriter(w);
		out.write("writing to the file now");
		out.newLine();
		out.write("this is a new line");
		out.newLine();
		out.write("another  new line");
		out.flush();
		//reading from the file
		FileReader r=new FileReader("\\temp.txt");
		BufferedReader bfr=new BufferedReader(r);
		String x="";
		while ((x=bfr.readLine())!=null)
		{
		System.out.println(x);
		}
	}

}

