package unitTests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZipFile {


	
	public static void createZip()
	{
		final StringBuilder sb = new StringBuilder();

		sb.append("Test String");

		final File f = new File("d:\\test.zip");
		ZipOutputStream out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(f));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ZipEntry e = new ZipEntry("mytext.txt");
		try {
			out.putNextEntry(e);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		byte[] data = sb.toString().getBytes();
		try {
			out.write(data, 0, data.length);
			out.closeEntry();
			out.close();


		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

	}
	/*
This will create a Zip-File located in the root of D: named 'test.zip' which will contain one single file called 'mytext.txt'. Of course you can add more zip entries and also specify a sub directory like:

ZipEntry e = new ZipEntry("folderName/mytext.txt");*/
}
