package unitTests;

import com.nautica.util.*;


public class TestSendMail {
	
	public static void main(String args[])
	{
		SendMail testSM= new SendMail();
		try {
			testSM.execute("testReport");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to send mail");
		}
		
		System.out.println(System.getProperty("user.dir"));
	}

}
