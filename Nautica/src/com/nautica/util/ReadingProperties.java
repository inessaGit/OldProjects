package com.nautica.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;


//this is a class for reading from properties files; 
//it has 2 methods: one for reading from locators.properties and another for environment.properties
//Test class is for unit testing (which was successful);

public class ReadingProperties {

	public   String value;
	public  Logger loger =Logger.getLogger("myLogger");
	public String key;
	Properties property=new Properties();

	//**************************************************************
	public  String readORProperties(String key)
	{

		File file = new  File("OR.properties");
		try {
			System.out.println("path is "+file.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		//read from the properties file		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("/Users/igonzalez/Desktop/WORKING_WITH_ECLIPSE/Nautica/src/com/nautica/config/OR.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {

			property.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		if (property.getProperty(key)!=null)//check if key exists in config.properties file
{
		System.out.println("Reading from OR.properties file");
loger.info("Successfully read from OR.properties file <"+key+">");
		value =	property.getProperty(key);
}

		else 
		{
			loger.debug("Could not read specified key from the OR.properties file <"+key+">");
			
		}
		return value;
	}

	//*****************************************************************************
	
	public  String readConfigProperties(String key)
	{
		File file = new  File("config.properties");
		try {
			System.out.println("path is "+file.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		//read from the properties file		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("/Users/igonzalez/Desktop/WORKING_WITH_ECLIPSE/Nautica/src/com/nautica/config/config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {

			property.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//String val=property.getProperty(key);

		if (property.getProperty(key)!=null)//check if key exists in config.properties file
{
		System.out.println("Reading from config.properties file");
loger.info("Successfully read from config.properties file <"+ key+">");
		value =	property.getProperty(key);
}

else 
{
	loger.debug("Could not read specified key from the config.properties file <"+key+">");
}
		return value;
	}

}

class TestReadingProperies
{
	public static void main (String args[])
	{
		Logger log = Logger.getLogger("myLog");
		ReadingProperties rp= new ReadingProperties();
		String s =rp.readConfigProperties("Usernam");
		System.out.println(s);
		String e= rp.readORProperties("name");
		
		log.debug(e);
		log.debug(s);
		
	}
}
