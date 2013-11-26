package com.nautica.util;

import static org.testng.AssertJUnit.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.apache.bcel.verifier.exc.VerificationException;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.apache.log4j.Logger;

public class ScreenShotOnFailure {
	
	Calendar c = Calendar.getInstance();
    
    java.text.SimpleDateFormat df2 = new java.text.SimpleDateFormat("MMM");
	
	
	public  void takeScreenshot(WebDriver driver)
    
    {
         String screenshotName= "" + c.get(Calendar.YEAR) +
                          "-" + df2.format(c.getTime())+
                          "-" + c.get(Calendar.DAY_OF_MONTH) +
                          "-" + c.get(Calendar.HOUR_OF_DAY) +
                          "hr-" + c.get(Calendar.MINUTE) +
                          "min-" + c.get(Calendar.SECOND) +
                          " sec-"+ c.get(Calendar.MILLISECOND)+"mls.png";
        
         File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
           String path= "/Users/igonzalez/Desktop/WORKING_WITH_ECLIPSE/Nautica/Reports" + screenshotName;
           File target =new File (path);
          
         try {
       	  //copying file from original directory into wanted directory
              FileUtils.copyFile(source, target);
         } catch (IOException e) {
              e.printStackTrace();
         }
         
         System.out.println("Screenshot taken at "+ screenshotName.substring(0, screenshotName.length()-4));
         Reporter.log("Screenshot taken at "+ screenshotName.substring(0, screenshotName.length()-4));
         String str = System.getProperty("user.dir") + "/"; // Current dir

         Reporter.log("<img src=\"file:///" + str +"/target/screenshots/"+ screenshotName + "\" alt=\"\"/><br/>");

    }
    
    
}