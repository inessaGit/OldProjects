package com.nautica.util;

//responsible for zipping a directory

//set CLASSPATH=%CLASSPATH%;activation.jar;mail.jar

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;

import org.apache.james.mime4j.message.Multipart;

import java.util.*;



public class SendMail

{
	public static void execute(String reportFileName) throws Exception

	{

		//result_FolderName="Reports"+"_"+date;//this is folder name for Reports generated with each run of test it will be inside of Reports folder
		//public static void zip(String filepath,String reportFileName) from Zip class

		String path=System.getProperty("user.dir")+"/Reports"+reportFileName;//reportFileName should be an html testng report
		Zip.zip(System.getProperty("user.dir")+"/"+ReportUtil.result_FolderName,reportFileName);

		path=System.getProperty("user.dir")+"/TestReports.zip";
		String[] to={"inez2209@gmail.com"};// specify receipient here

		String[] cc={};
		String[] bcc={};

		//This is for google

		SendMail.sendMail("inez2209@gmail.com",
				"Jenny1983",
				"smtp.gmail.com",
				"465",
				"true",
				"true",
				true,
				"javax.net.ssl.SSLSocketFactory",
				"false",
				to,
				cc,
				bcc,
				"Automation test Reports",
				"Please find the reports attached.\n\n Regards\nInessa",
				path,
				"TestReports.zip");
	}



	public  static boolean sendMail(String userName,
			String passWord,
			String host,
			String port,
			String starttls,
			String auth,
			boolean debug,
			String socketFactoryClass,
			String fallback,
			String[] to,
			String[] cc,
			String[] bcc,
			String subject,
			String text,
			String attachmentPath,
			String attachmentName){


		Properties props = new Properties();

		//Properties props=System.getProperties();

		props.put("mail.smtp.user", userName);

		props.put("mail.smtp.host", host);

		if(!"".equals(port))

			props.put("mail.smtp.port", port);

		if(!"".equals(starttls))

			props.put("mail.smtp.starttls.enable",starttls);

		props.put("mail.smtp.auth", auth);
		// props.put("mail.smtps.auth", "true");


		if(debug){

			props.put("mail.smtp.debug", "true");

		}else{

			props.put("mail.smtp.debug", "false");         

		}

		if(!"".equals(port))

			props.put("mail.smtp.socketFactory.port", port);

		if(!"".equals(socketFactoryClass))

			props.put("mail.smtp.socketFactory.class",socketFactoryClass);

		if(!"".equals(fallback))

			props.put("mail.smtp.socketFactory.fallback", fallback);



		try

		{
			//Session session = Session.getDefaultInstance(props, null);
			Session session = Session.getInstance(props, new GMailAuthenticator("inez2209@gmail.com", "Jenny1983"));
			session.setDebug(debug);

			MimeMessage msg = new MimeMessage(session);

			msg.setText(text);

			msg.setSubject(subject);
			//attachment start
			// create the message part 

			MimeMultipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			DataSource source = 
					new FileDataSource(attachmentPath);
			messageBodyPart.setDataHandler(
					new DataHandler(source));
			messageBodyPart.setFileName(attachmentName);
			multipart.addBodyPart(messageBodyPart);

			// attachment ends

			// Put parts in message
			msg.setContent(multipart);
			msg.setFrom(new InternetAddress("inez2209@gmail.com"));

			for(int i=0;i<to.length;i++){

				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));

			}

			for(int i=0;i<cc.length;i++){

				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));

			}

			for(int i=0;i<bcc.length;i++){

				msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));

			}

			msg.saveChanges();

			Transport transport = session.getTransport("smtp");

			transport.connect(host, userName, passWord);

			transport.sendMessage(msg, msg.getAllRecipients());

			transport.close();

			return true;

		}

		catch (Exception mex)

		{

			mex.printStackTrace();

			return false;

		}

	}

}