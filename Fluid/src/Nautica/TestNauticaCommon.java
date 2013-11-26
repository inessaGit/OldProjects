package Nautica;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import UtilClasses.NauticaCommon;
import UtilClasses.NauticaCommon.*;


public class TestNauticaCommon {
	
	private static WebDriver driver; //declared but no instance yet
	 static String monogramItem= "100104116";
     String visaCard ="4111111111111111";
     String cardPin ="111";
     String giftCard= "0000800752000000001";
     String giftPin="1234";
     static String coupon= "SEPT40";
     static String email = "inessa2209@hotmail.com";
     static String password = "kiplling1";
    
  
    //@BeforeMethod is invoked before each method with a @Test annotation.
	@BeforeTest 
	public static void setUp()
	{ 
		NauticaCommon.useChrome(driver);
		driver =new ChromeDriver(); //CAN USE CHROME with Chrome on PDP getting interm 'element not clickable' exception
		
	//driver = new SafariDriver(); //CAN USE SAFARI 6 but it does not support all of WebDriver API will break with action.moveTo
	//driver = new FirefoxDriver();//CAN USE FIREFOX 24

		NauticaCommon.defaultWindowSize(driver);
		String url=NauticaCommon.getUrl("stagingUrl");
		driver.get(url);
	}

	 @Test
	    public static void testListAllSubcat()
	    {
	    	NauticaCommon.listAllSubcat(driver);
	    }
	    
	    @Test
	    public static void testMenSubcat()
	    {
	    	
	    	NauticaCommon.menSubcat(driver);
	    	NauticaCommon.subCategorySelected(driver);
	    	NauticaCommon.PDPswatchColor(driver);
	    	NauticaCommon.sizeChartOnPDP(driver);
	    	NauticaCommon.writeReviewPresent(driver);
	    	NauticaCommon.selectSizeOnPDP(driver);
	    	NauticaCommon.selectQuantityOnPDP(driver);

	    }
	    @Test
	    public static void testWomenSubcat()
	    {
	    	
	    	NauticaCommon.womenSubcat(driver);
	    	NauticaCommon.subCategorySelected(driver);
	    	NauticaCommon.PDPswatchColor(driver);
	    	NauticaCommon.sizeChartOnPDP(driver);
	    	NauticaCommon.writeReviewPresent(driver);
	    	NauticaCommon.selectSizeOnPDP(driver);
	    	NauticaCommon.selectQuantityOnPDP(driver);
	    }
	    
	    @Test
	    public static void testHomeSubcat()
	    {
	    	
	    	NauticaCommon.homeSubcat(driver);
	    	NauticaCommon.subCategorySelected(driver);
	    	NauticaCommon.PDPswatchColor(driver);
	    	NauticaCommon.sizeChartOnPDP(driver);
	    	NauticaCommon.writeReviewPresent(driver);
	    	//NauticaCommon.selectSizeOnPDP(driver); //home items do not have size
	    	NauticaCommon.selectQuantityOnPDP(driver);
            driver.findElement(By.id("add-to-cart")).click();
            

	    }
	    
	    @Test
	    public static void testKidsSubcat()
	    {
	    	
	    	NauticaCommon.kidsSubcat(driver);
	    	NauticaCommon.subCategorySelected(driver);
	    	NauticaCommon.PDPswatchColor(driver);
	    	NauticaCommon.sizeChartOnPDP(driver);
	    	NauticaCommon.writeReviewPresent(driver);

	    	NauticaCommon.selectSizeOnPDP(driver);
	    	NauticaCommon.selectQuantityOnPDP(driver);
	    }
	    

	    @Test
	    public static void testWatchesSubcat()
	    {
	    	
	    	NauticaCommon.watchesSubcat(driver);
	    	NauticaCommon.subCategorySelected(driver);
	    	NauticaCommon.PDPswatchColor(driver);
	    	NauticaCommon.sizeChartOnPDP(driver);
	    	NauticaCommon.writeReviewPresent(driver);

	    	NauticaCommon.selectSizeOnPDP(driver);
	    	NauticaCommon.selectQuantityOnPDP(driver);
	    }
	    
	    @Test
	    public static void testSaleSubcat()
	    {
	    	
	    	NauticaCommon.saleSubcat(driver);
	    	NauticaCommon.subCategorySelected(driver);
	    	NauticaCommon.PDPswatchColor(driver);
	    	NauticaCommon.sizeChartOnPDP(driver);
	    	NauticaCommon.writeReviewPresent(driver);

	    	NauticaCommon.selectSizeOnPDP(driver);
	    	NauticaCommon.selectQuantityOnPDP(driver);
	    }
	    @Test
	    public static void testPDPswatch()
	    {
	    	driver=new FirefoxDriver();
	    	
	    	String myUrl ="http://www.nautica.com/"
	    			+ "nautica-logo-hoodie-%282t-7%29/52B02A.html?dwvar_52B02A_color=469#prefn1=colorFamily&prefv1=BLUE&start=1";
	    	driver.get(myUrl);
	    	
	    	Reporter.log("Page title: " + driver.getTitle());
	    	NauticaCommon.PDPswatchColor(driver);
	    	
	    }
	    
	    @AfterTest
	    public static void endTest()
	    {
	    	driver.quit();

	    }

}


	