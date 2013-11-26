package UtilClasses;

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

public class KipCommonMethods {
    
     static String monogramItem= "100104116";
     String visaCard ="4111111111111111";
     String cardPin ="111";
     String giftCard= "0000800752000000001";
     String giftPin="1234";
     static String coupon= "CONSUMERR";
     static String email = "software_test22@hotmail.com";
     static String password = "kipling1";
	
        
     //If you have object.equals(???literal???) you should replace with ???literal???.equals(object) to avoid null pointer exception
     
 public static  String getUrl (String requestedUrl) {
	 String url="";
     	
      	  HashMap<String, String> envUrl = new HashMap<String, String>();
      	  
      	  envUrl.put("baseUrl", "http://www.kipling-usa.com/");
      	  
      	  envUrl.put("stagingUrl", "http://storefront:vfc123@staging.spwrealm.vfc.demandware.net"+ 
      	  "/on/demandware.store/Sites-kip-Site/default/Home-Show?locale=en_US");
      	  
      	   envUrl.put("devUrl", "http://storefront:vfc123@development.spwrealm.vfc.demandware.net"+ 
      	  "/on/demandware.store/Sites-kip-Site/default/Home-Show?locale=en_US");
      	   
      	   if ("stagingUrl".equals (requestedUrl))
      	   {
      		    url=envUrl.get("stagingUrl");
      	   }
      	  
      	   
      	   else  if ("devUrl".equals (requestedUrl))
      	   {
      			      url= (envUrl.get("devUrl"));
      		   //url= "http://storefront:vfc123@development.spwrealm.vfc.demandware.net"+ 
      		      	 // "/on/demandware.store/Sites-kip-Site/default/Home-Show?locale=en_US";
      	   }
      	   else if ("baseUrl".equals(requestedUrl))
      	   {
      		   url=envUrl.get("baseUrl");
      	   }
      	   return url;
      	}
    
     public static void getBrowser(WebDriver driver)
 	{
 		if (driver instanceof FirefoxDriver) {
 			System.out.println("Firefox DRIVER");
 			Reporter.log("Using Firefox browser version 24");
 		} 
 		
 		else if (driver instanceof ChromeDriver) {
 			System.out.println("Chrome DRIVER");
 			Reporter.log("Using Chrome version 30 browser");
 		}
 		
 		else if (driver instanceof SafariDriver) {
 			System.out.println("Safari DRIVER");
 			Reporter.log("Using Safari 6 browser");
 		}
 	}
     
     public static boolean waitToLoadElement(WebDriver driver, By by, int seconds) {
         boolean found = true;

         long bailOutPeriod = 1000 * seconds;
         long lStartTime = new Date().getTime();

         while (!isElementPresent(driver, by)) {
             long lEndTime = new Date().getTime();
             long difference = lEndTime - lStartTime;

             if (difference < bailOutPeriod) {
                 pause(1);
             }
             else {
                 found = false;
                 break;
             }
         }
         return found;
     }
     
 	public static boolean isElementPresent(final WebDriver driver, By by) {
 		try {
 			WebElement element = driver.findElement(by);
 			return element.isDisplayed();
 		} catch (NoSuchElementException e) {
 			return false;
 		}
 	}
     public static void pause(int milliseconds) {
 		try {
 			Thread.sleep(milliseconds);
 		} catch (InterruptedException e) {
 			e.printStackTrace();
 		}
 	}
    
     
     public static void defaultWindowSize(WebDriver driver)
 	
 	{
 		
 		//diver.manage().window().maximize(); //this would work only for Firefox and IE
 		driver.manage().window().setPosition(new Point(0,0));
 		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
 		Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
 		driver.manage().window().setSize(dim);
 		Reporter.log("Currently testing URL: " +driver.getCurrentUrl());
 		
 	}
     
     public static void takeScreenshot(WebDriver driver)
     
     {
    	 getBrowser(driver);
          Calendar c = Calendar.getInstance();
          
          java.text.SimpleDateFormat df2 = new java.text.SimpleDateFormat("MMM");

          String screenshotName= "" + c.get(Calendar.YEAR) +
                           "-" + df2.format(c.getTime())+
                           "-" + c.get(Calendar.DAY_OF_MONTH) +
                           "-" + c.get(Calendar.HOUR_OF_DAY) +
                           "hr-" + c.get(Calendar.MINUTE) +
                           "min-" + c.get(Calendar.SECOND) +
                           " sec-"+ c.get(Calendar.MILLISECOND)+"mls.png";
         
          File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String path= "./target/screenshots/" + screenshotName;
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
     
     
     
     public static void searchForMonogram(WebDriver driver,String monogram)
     {
    	 WebElement searchBox = driver.findElement(By.id("search-query"));
    	   WebElement searchBtn= driver.findElement(By.className("magBtn"));
    	  searchBox.clear();
    	  searchBox.sendKeys(monogram);
    	  searchBtn.click();
pause(3000);
String monogramPageTitle = driver.getTitle();

    	  System.out.println("After search for monogram item -current page title is "+ monogramPageTitle+ ". Successful search");

  	    try {
  	      
  	    	AssertJUnit.assertEquals("Challenger II Backpack", driver.findElement(By.cssSelector("h1.product-name")).getText());
  	    } catch (Error e) {//todo
  	    }
     }
     public static void writeRewiewOnPDP(WebDriver driver)
     {
     	
     	try
     	{
     		driver.findElement(By.className("pr-snippet-link"));
     		if( driver.findElement(By.className("pr-snippet-link")).isDisplayed()){
         		System.out.println("TEST FOR 'Write a Review' link PRESENCE: 'Write a Review link' -Element is Visible");
         		Reporter.log(" 'Write a Review' link is visible on PDP");
         		KipCommonMethods.takeScreenshot(driver); 
     		}
     		
     	}
     	
     	catch(Exception e)
     	{
     		e.printStackTrace();
     		System.out.println("WARNING!: 'Write a Review' link is not present on PDP.");
     		Reporter.log("WARNING!: 'Write a Review' link is not present on PDP." + " Current page title is "+ driver.getTitle());
     		KipCommonMethods.takeScreenshot(driver); 

     	}
     	
     }
    
     public static void backpacksSubcategory(WebDriver driver)
     { 
          //CATEGORY = 8
          String [] category= new String[]{"nav-new","nav-handbags", "nav-backpacks","nav-messengers-totes",
               "nav-luggage", "nav-wallets-accessories", "nav-sale", "nav-favorite-things"};
         
          //BACKPACKS SUBCAT = 7
          String subcategory [] = new String [] {"backpacks-new", "backpacks-mostloved", "backpacks-laptop",
                    "backpacks-everyday", "backpacks-unisex", "backpacks-work", "backpacks-sale"};
         
            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(By.id(category[2]))).build().perform();
            driver.findElement(By.id(subcategory[3])).click();
            System.out.println("After click on subcategory - the page title is " + driver.getTitle());
            Reporter.log("Running backpacksSubcategory method: "+" After click on subcategory - the page title is " + driver.getTitle());
            //WANT TO GET ITEM # AND SAVE IT!!
         
     }
    
     public static void backpacksPDP(WebDriver driver)
     {
          driver.findElement(By.xpath("//div/a/img")).click();
          WebElement productNum= driver.findElement(By.className("product-number"));
         
          System.out.println("After clicking on an item on - got page title "+ driver.getTitle());
          //get all of the swatch colors and take a screenshot
          Reporter.log("Running backpacksPDP method: " +" After clicking on an item on - got page title "+ driver.getTitle());
         
     }
    
     public  static void trackEvent(WebDriver driver)
     {
          List<WebElement> trackEvent = driver.findElements(By.className("trackEvent"));
         
          //MIGHT BE USEFUL TO GRAB ALL LOCATORS??
             List<String> htmlOfTrackEvent = new ArrayList();

          for (int i = 0; i<trackEvent.size(); i++){
               WebElement ele = trackEvent.get(i);
            
             htmlOfTrackEvent.add(ele.getAttribute("innerHTML").trim());
             System.out.println(htmlOfTrackEvent.get(i));         
          }
          //display number of elements
        System.out.print("Number of trackEvent  elements: "+ trackEvent.size());
     }
    
     public static void PDPswatchColor(WebDriver driver)
     {
           WebElement selectedSwatch = driver.findElement(By.className("selected"));
           String selectedColor = selectedSwatch.getText().trim();
          
          //class swatchanchor trackEvent
            List<WebElement> trackEvent = driver.findElements(By.className("emptyswatch"));
            List<String> htmlOfTrackEvent = new ArrayList();
         
            
            System.out.println(trackEvent.size() + " empty color swatches are displayed. Plus one selected color swatch "+selectedColor);
            Reporter.log(trackEvent.size() + " empty color swatches are displayed. Plus one selected color swatch "+selectedColor);
            		
               for ( int i = 0; i<trackEvent.size(); i++){
                    WebElement ele = trackEvent.get(i);
                 
                 
                  //htmlOfTrackEvent.add(ele.getAttribute("title").trim());  
                    htmlOfTrackEvent.add(ele.getText().trim());         

               }
               Reporter.log("These are empty swatch colors on PDP: " );
              
               Iterator it =htmlOfTrackEvent.iterator();
               while(it.hasNext())
               {
                    Object elem=it.next();
                    System.out.println("These are empty swatch colors on PDP: " + elem);
                    Reporter.log(elem.toString());
               }
     }
    
     public static void PDPquantity(WebDriver driver)
     {
         
          String quantity="1";
         
          driver.findElement(By.xpath("//b")).click();
          new Select(driver.findElement(By.name("Quantity"))).selectByVisibleText(quantity);
          //driver.findElement(By.cssSelector("a.select2-choice")).click();
          //String selectedQ= driver.findElement(By.cssSelector("a.select2-choice")).getAttribute("innerHTML").trim();
          System.out.println("On PDP selected quantity : " +quantity);
          Reporter.log(("On PDP selected quantity - " +quantity));
         
     }
    
     public static void checkoutCart(WebDriver driver)
     {
          System.out.println("On the PDP page - the title is "+driver.getTitle());
          Reporter.log("In checkoutCart method: "+" On the PDP page - the title is "+driver.getTitle());
        //  String itemNum=driver.findElement(By.cssSelector("div.sku > span.value")).getAttribute("innerHTML").trim();
          
          // by.className("product-number") for kipling live
          
           String itemNum=driver.findElement(By.className("product-number")).getAttribute("innerHTML").trim();
          String itemColor=driver.findElement(By.cssSelector("span.value")).getAttribute("innerHTML").trim();
          System.out.println("On the PDP page - item number is " + itemNum+ " and color "+itemColor);
          Reporter.log("On the PDP page - item number is " + itemNum+ " and color "+itemColor);
          //driver.findElement(By.name("dwfrm_cart_checkoutCart")).click();
          
          
          WebElement miniCart = driver.findElement(By.id("mini-cart"));
          miniCart.click();
          pause(2000);

        
          System.out.println("From 'Cart' page went to '"+driver.getTitle()+" ' page");
          Reporter.log("From 'Cart' page went to '"+driver.getTitle()+" ' page");
     }
    
     public static void cartCoupon(WebDriver driver)
     {
    	 
    	 System.out.println("On the PDP page - the title is "+driver.getTitle());
         Reporter.log("In cartCoupon method: "+" On the PDP page - the title is "+driver.getTitle());
         String itemNum=driver.findElement(By.className("product-number")).getAttribute("innerHTML").trim();
         String itemColor=driver.findElement(By.cssSelector("span.value")).getAttribute("innerHTML").trim();
         System.out.println("On the PDP page - item number is " + itemNum+ " and color "+itemColor);
         Reporter.log("On the PDP page - item number is " + itemNum+ " and color "+itemColor);
         
         //need to click on mini-cart link
         
        // WebElement miniCart = driver.findElement(By.id("mini-cart"));
         WebElement miniCart= driver.findElement(By.xpath("//li[@id='mini-cart']/a"));
         miniCart.click();
         pause(1500);
         
          WebElement couponField = driver.findElement(By.id("dwfrm_cart_couponCode"));
          couponField.clear();
          couponField.sendKeys(coupon);
          WebElement couponBtn = driver.findElement(By.id("add-coupon"));
          couponBtn.click();
          Reporter.log("Coupon applied. "+" Went to '"+driver.getTitle()+" ' page");
          takeScreenshot(driver);

          driver.findElement(By.name("dwfrm_cart_checkoutCart")).click();
          System.out.println("From 'Cart' page went to '"+driver.getTitle()+" ' page");
          try {
               Thread.sleep(1000);
          } catch (InterruptedException e) {
               e.printStackTrace();
          }
    
     }

     public static void checkoutSignInGuest(WebDriver driver)
     {
    	 Reporter.log("Current page title is : "+ driver.getTitle());
    	 takeScreenshot(driver);
          WebElement guestBtn= driver.findElement(By.name("dwfrm_login_unregistered"));
          guestBtn.click();
          Reporter.log("After click on 'CHECKOUT AS GUEST' button page title is " + driver.getTitle());
     }
    
     public static void checkoutSignInEmail(WebDriver driver)
     {
         // Actions action = new Actions(driver);
        //  action.moveToElement(driver.findElement(By.xpath("//label[.='Email Address']//following::input[1]"))).build().perform();
          //WebElement emailField=   driver.findElement(By.xpath("//label[.='Email Address']//following::input[1]"));

    	 assertEquals("My Kipling Account Login", driver.getTitle());
    	 Reporter.log("Page title is "+ driver.getTitle());
    	 
          WebElement emailField =driver.findElement(By.xpath("//div[@id='primary']/div/div[2]/div/div/form/fieldset/div/input"));
            
            
          emailField.clear();
          emailField.sendKeys(email);
          //WebElement pswField = driver.findElement(By.id("dwfrm_login_password"));
          WebElement pswField = driver.findElement(By.xpath("(//input[@id='dwfrm_login_password'])[2]"));

          pswField.clear();
          pswField.sendKeys(password);
         
          
          //WebElement signInBtn = driver.findElement(By.id("dwfrm_login_login"));
         // WebElement signInBtn = driver.findElement(By.xpath("(//button[@name='dwfrm_login_login'])[2]"));
          WebElement signInBtn = driver.findElement(By.xpath("//div[@id='primary']/div/div[2]/div/div/form/fieldset/div[4]/button"));
          signInBtn.click();
     }
     
     public static void logout(WebDriver driver)
     {
    	 driver.findElement(By.cssSelector("a.user-account")).click();
    	    String myAccount = driver.findElement(By.cssSelector("h1")).getText();//BECAUSE page title is very non descriptive
    	    Reporter.log("On the page with "+ myAccount);
    	    
    	    takeScreenshot(driver);
    	    driver.findElement(By.linkText("LOGOUT")).click();
    	    
    	   // driver.findElement(By.className("account-logout")).click();
    	    waitToLoadElement(driver, By.className("login_button"), 4);
    	    
    	    assertEquals("My Kipling Account Login", driver.getTitle());
    	    Reporter.log("After click on 'LOGOUT' link got the page title "+ driver.getTitle());
    	    takeScreenshot(driver);
    	  
     }
     
     //not complete yet 11/14
     public static void orderLookUp(WebDriver driver, String s)
     {
    	 String orderNum;
    	 driver.findElement(By.cssSelector("a.user-signin.ignore")).click();
    	 driver.findElement(By.id("dwfrm_ordertrack_orderNumber")).clear();
    	 driver.findElement(By.id("dwfrm_ordertrack_orderNumber")).sendKeys();

    	 
     }
     
public static void sendOrderNumToFile(WebDriver driver,String orderNum) throws IOException
     {
    	 /*
    	  * Make sure that when you create an instance of a FileWriter, that you are appending to the end of it.
    	  *  This can be done by using this specific FileWriter constructor which takes an additional boolean as a second parameter.
    	  *  This boolean tells the FileWriter to append to the end of the file, rather than overwriting the file.
    	  
    	  Calendar c = Calendar.getInstance();
    	     java.text.SimpleDateFormat df2 = new java.text.SimpleDateFormat("MMM");

    		 String currDate= "" + c.get(Calendar.YEAR) +
    	             "-" + df2.format(c.getTime())+
    	             "-" + c.get(Calendar.DAY_OF_MONTH) +
    	             "-" + c.get(Calendar.HOUR_OF_DAY) +
    	             "hr-" + c.get(Calendar.MINUTE) ;

    		 
    	     File f = new File("OrderNumbers_"+currDate+".txt");
    	    
    	   FileWriter fw = new FileWriter(f, true); 
    	     
    	BufferedWriter bf = new BufferedWriter(fw);
    		 
    	 fw.append(orderNum);
			fw.close();
			*/
	System.out.println("hello");
    	 final Logger log= Logger.getLogger(KipCommonMethods.class.getName());
    	 log.debug("this is debug message");
    	 
    	 
     }
     public static void checkoutStep1(WebDriver driver)
     {
    	 WebElement fname = driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName"));
    	 fname.clear();
    	 fname.sendKeys("ines");
    	 driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).clear();
    	 driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).sendKeys("test");
    	 WebElement address1=driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1"));
    	 address1.clear();
    	 address1.sendKeys();//NEED ADDRESS DATA
    	 
    	 WebElement address2 =driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address2"));
    	 address2.clear();
    	 address2.sendKeys();//OPTIONAL apt suite etc.
    	 
    //	 WebElement country = driver.findElement(By.) //C
    			 
    			 
    	 WebElement city= driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_city"));
    	 city.clear();
    	 city.sendKeys();// NEED DATA
    	 
    	 WebElement zip = driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_zip"));
    	 		 zip.clear();
    	 		 zip.sendKeys();;//NEED DATA
    	 		 
    	 		 WebElement phone = driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_phone"));
    	 		 phone.clear();
    	 		 phone.sendKeys();//NEED DATA
    	 		 
    	 		 
    	 		 WebElement useAsBillingAddress=driver.findElement(By.name("dwfrm_singleshipping_shippingAddress_useAsBillingAddress"));
    	 		 	 
     }
     
     public static void checkoutStep2 (WebDriver driver)
     {
    	 
    	 WebElement billingFname= driver.findElement(By.id("id=dwfrm_billing_billingAddress_addressFields_firstName"));
    	 billingFname.clear();
    	 billingFname.sendKeys();//NEED DATA
    	 
    	 WebElement billingLname = driver.findElement(By.id("id=dwfrm_billing_billingAddress_addressFields_lastName"));
    	 billingLname.clear();
    	 billingLname.sendKeys();//NEED DATA
    	 
    	 WebElement billingAddress1= driver.findElement(By.id("id=dwfrm_billing_billingAddress_addressFields_address1"));
    	 billingAddress1.clear();
    	 billingAddress1.sendKeys();//NEED DATA
    	 
    	 
     }
     
     public static void checkoutCountries(WebDriver driver)
     {
    	 
    	 //click on country drop down toggle
        System.out.println("From 'My Kipling Account Login ' page went to 'Checkout Step 1 Shipping' page");
		driver.findElement(By.cssSelector("div b")).click();
		takeScreenshot(driver);

		List<WebElement> countries = driver.findElements(By
				.className("select2-result-label"));
		List<String> htmlCountries = new ArrayList();
		for (int i = 0; i < countries.size(); i++) {
			WebElement oneCountry = countries.get(i);
			
			String aCountry = oneCountry.getText().trim();
			
			htmlCountries.add(aCountry);
		}
		
		System.out.println("Number of 'Country'  elements: " + countries.size());
		System.out.println("These are countries in the drop down 'Country' list on 'Step 1 Shipping' in 'Checkout' : ");

		Iterator it = htmlCountries.iterator();
		int countryCounter =0;
		while (it.hasNext()) {
			countryCounter++;
			Object elem = it.next();
			System.out.println(countryCounter+"."+elem);

		}
     }
     
	public static void selectCountry(WebDriver driver) {
		
		
		//driver.findElement(By.cssSelector("div b")).click();// CLICK ON COUNTRY
															// TOGGLE

		List<WebElement> countries = driver.findElements(By
				.className("select2-result-label"));// GET ALL COUNTRIES
													// WEBELEMENTS
		for (int i=0;i<countries.size();i++)
		{
			WebElement oneCountry = countries.get(i);
			takeScreenshot(driver);
			if(i==0)
			{
				//it should be USA
				//STATE MANDATORY
				//if state Alaska or Hawaii - 2 shipping options
			}
			
			if(i==1)
			{
				//then country should be Canada - A. PROVINCE MANDATORY
			}
			else 
			{
				//should be all other countries - A. STATE/PROVINCE OTPIONAL B. SHIPPING OPTION -INTERNAIONAL FEDEX
			}
		}
	}
	
	public static void selectState(WebDriver driver)
	{
		
	}

	
     }
    
    

