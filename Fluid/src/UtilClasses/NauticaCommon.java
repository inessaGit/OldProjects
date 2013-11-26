package UtilClasses;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;



public class NauticaCommon {
	
	 static String monogramItem= "100104116";
     String visaCard ="4111111111111111";
     String cardPin ="111";
     String giftCard= "0000800756000000009";
     String giftPin="1234";
     static String coupon= "SEPT40";
     static String email = "inessa2209@hotmail.com";
     static String password = "kiplling1";
    
    
	static String [] category= new String[]{"nav-mens","nav-womens", "nav-kids","nav-home",
        "nav-watches", "nav-sale", "nav-inside-nautica"};
	
	public static List <WebElement> menSubcategory= new ArrayList<WebElement>();
    public static List<WebElement>menSubcategoryCollection =new ArrayList<WebElement>();
   
    public static List <WebElement> womenSubcategory= new ArrayList<WebElement>();
    public static List<WebElement>womenSubcategoryCollection =new ArrayList<WebElement>();

    public static List <WebElement> kidsSubcategory= new ArrayList<WebElement>();
    public static List<WebElement>kidsSubcategoryCollection =new ArrayList<WebElement>();
    
    public static List <WebElement> homeSubcategory= new ArrayList<WebElement>();
    public static List<WebElement>homeSubcategoryCollection =new ArrayList<WebElement>();
	
    public static List <WebElement> watchesSubcategory= new ArrayList<WebElement>();
    public static List<WebElement>watchesSubcategoryCollection =new ArrayList<WebElement>();
	
    public static List <WebElement> saleSubcategory= new ArrayList<WebElement>();
    public static List<WebElement>saleSubcategoryCollection =new ArrayList<WebElement>();
    
	public static List<WebElement>subcat = new ArrayList<WebElement>();
	
	public static ArrayList<String>stringLinkText= new ArrayList<String>();//this is to get strings of subcat used in listAllSubcat method

	
	public static  String getUrl (String requestedUrl) {
		 String url="";
	     	
	      	  HashMap<String, String> envUrl = new HashMap<String, String>();
	      	  
	      	  envUrl.put("baseUrl", "http://www.nautica.com/");
	      	  
	      	  envUrl.put("stagingUrl", "http://storefront:vfc123@staging.spwrealm.vfc.demandware.net"+ 
	      	  "/on/demandware.store/Sites-nau-Site/default/Home-Show?locale=en_US");
	      	  
	      	   envUrl.put("devUrl", "http://storefront:vfc123@development.spwrealm.vfc.demandware.net"+ 
	      	  "/on/demandware.store/Sites-nau-Site/default/Home-Show?locale=en_US");
	      	   
	      	   if ("stagingUrl".equals (requestedUrl))
	      	   {
	      		    url=envUrl.get("stagingUrl");
	      	   }
	      	  
	      	   
	      	   else  if ("devUrl".equals (requestedUrl))
	      	   {
	      			      url= (envUrl.get("devUrl"));
	      		  
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
			System.out.print("Firefox DRIVER"+" ");
			Reporter.log("Current browser: Firefox version 24"+ " ");
		} 
		
		else if (driver instanceof ChromeDriver) {
			System.out.print("Chrome DRIVER"+ " ");
			Reporter.log("Current browser: Chrome version 30"+ " ");
		}
		
		else if (driver instanceof SafariDriver) {
			System.out.print("Safari DRIVER" + " ");
			Reporter.log("Current browser:  Safari 6" +" ");
		}
	}
	
	
	//usage           waitToLoadElement(driver, By.xpath("//input[@value='Login']"), 5);

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
    
	
	public static boolean isElementPresent(WebDriver driver, By by) {
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
              FileUtils.copyFile(source, target);
         } catch (IOException e) {
              e.printStackTrace();
         }
         System.out.println("Screenshot taken at "+ screenshotName.substring(0, screenshotName.length()-4));
         Reporter.log("Screenshot taken at "+ screenshotName.substring(0, screenshotName.length()-4));
         String str = System.getProperty("user.dir") + "/"; // Current dir

         Reporter.log("<img src=\"file:///" + str +"/target/screenshots/"+ screenshotName + "\" alt=\"\"/><br/>");

    }
    
    public static void useChrome(WebDriver driver)
    {
    	
    	File file=new File("/Users/igonzalez/Desktop/SELENIUM_jar/selenium/chromedriver");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
    	
    }
    
    //HOW TO USE @BeforeMethod and @BeforeClass so I can set up driver ???
    
    public static void listAllSubcat(WebDriver driver)
    {
    	subcat= driver.findElements(By.cssSelector("a.level2.trackEvent"));
    	
    	Reporter.log(subcat.size()+" is the number of subcategories.");
    	
    	
    	
    	for (int i =0; i<subcat.size();i++)
    	{   
    		WebElement e = subcat.get(i);
    		stringLinkText.add(e.getAttribute("innerHTML").trim());
    		Reporter.log("Subcategory #" + i+" " + e.getAttribute("innerHTML").trim());
    	}
    }
    
    public static void menSubcat(WebDriver driver) {
    	Actions action = new Actions(driver);
    	
    	try{
        action.moveToElement(driver.findElement(By.id(category[0]))).build().perform();//CAN BE ANY NUM 0-5;   1 = MEN
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
   
    		//menSubcategory =driver.findElements(By.xpath("//ul[@id='navigation-menu']/li/div/div/ul/li[5]/a"));//instead of 5 can be 1-14
		//menSubcategory =driver.findElements(By.xpath("///div/div/ul/li/a"));
		
		//.//*[@id='navigation-menu']/li[1]/div/div[1]/ul this is xpath for menSubcat all except for 'Collections' 
    	
    	WebElement menColumn = driver.findElement(By.xpath(".//*[@id='navigation-menu']/li[1]/div/div[1]/ul"));//inside of this ul need to get all li
    	menSubcategory =menColumn.findElements(By.tagName("li"));
    	
        Reporter.log("Men Subcategories has: " + menSubcategory.size()+ " 'Subcategory' links   ");
        System.out.println("Men Subcategories has: " + menSubcategory.size()+ " 'Subcategory' links");

        for (WebElement element: menSubcategory) 
    		{
    		      System.out.println("Men Subcategories: "+ element.getText().trim());
    		      Reporter.log("Men Subcategories: "+ element.getText().trim());
    		}
    	
        	//.//*[@id='navigation-menu']/li[1]/div/div[2]/ul THIS is xpath for menColumnCollection
        	
        	WebElement menColumnCollection=driver.findElement(By.xpath(".//*[@id='navigation-menu']/li[1]/div/div[2]/ul "));//Collection ul
        menSubcategoryCollection =menColumnCollection.findElements(By.tagName("li"));
        
        Reporter.log("Men Subcategories has: " + menSubcategoryCollection.size()+ " 'Collections'");
        System.out.println("Men Subcategories has: " + menSubcategoryCollection.size()+ " 'Collections'");
        
        for (WebElement element: menSubcategoryCollection) 
		{
		      System.out.println("Men Subcategories include these 'Collections' :  "+ element.getText().trim());
		      Reporter.log("Men Subcategories include these 'Collections' : "+ element.getText().trim());
		}
        
        try{
    	menSubcategory.get(1).click();//CAN SELECT ANY OF THE WEBELEMENTS FROM THIS METHOD - GRABBED LOCATORS ALREADY!!
    		//pause(1500);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	System.out.println("failing in method menSubcategory");
        	
        }
    	    Reporter.log("Current page title is : "+ driver.getTitle());
        	NauticaCommon.takeScreenshot(driver);
    }
    public static void womenSubcat(WebDriver driver)
    {
    	Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id(category[1]))).build().perform();
        
        WebElement womenColumn = driver.findElement(By.xpath(".//*[@id='navigation-menu']/li[2]/div/div[1]/ul"));//inside of this ul need to get all li
    	womenSubcategory =womenColumn.findElements(By.tagName("li"));
    	
        Reporter.log("Women Subcategories has: " + womenSubcategory.size()+ " 'Subcategory' links   ");
        System.out.println("Women Subcategories has: " + womenSubcategory.size()+ " 'Subcategory' links");

        for (WebElement element: womenSubcategory) 
    		{
    		      System.out.println("Women Subcategories: "+ element.getText().trim());
    		      Reporter.log("Women Subcategories: "+ element.getText().trim());
    		}
    	
        	WebElement womenColumnCollection=driver.findElement(By.xpath(".//*[@id='navigation-menu']/li[2]/div/div[2]/ul"));//Collection ul
        womenSubcategoryCollection =womenColumnCollection.findElements(By.tagName("li"));
        
        Reporter.log("Women Subcategories has: " + womenSubcategoryCollection.size()+ " 'Collections'");
        System.out.println("Women Subcategories has: " + womenSubcategoryCollection.size()+ " 'Collections'");
        
        for (WebElement element: womenSubcategoryCollection) 
		{
		      System.out.println("Women Subcategories include these 'Collections' :  "+ element.getText().trim());
		      Reporter.log("Women Subcategories include these 'Collections' : "+ element.getText().trim());
		}

       
        //driver.findElement(By.linkText("Pants")).click();
    womenSubcategory.get(1).click();//CAN SELECT ANY OF THE WEBELEMENTS FROM THIS METHOD - GRABBED LOCATORS ALREADY!!
        //driver.manage().womenColumn.click();
       // womenSubcategory.get(1).click();//CAN SELECT ANY OF THE WEBELEMENTS FROM THIS METHOD - GRABBED LOCATORS ALREADY!!
        
    		pause(2000);
    	
    	    Reporter.log("Current page title is : "+ driver.getTitle());
        	NauticaCommon.takeScreenshot(driver);

    }
    
    public static void kidsSubcat(WebDriver driver)
    {
    	Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id(category[2]))).build().perform();//2 because index start at 0 in array
        
        //li 3 because for category nav li starts at 1
    	WebElement kidsColumn = driver.findElement(By.xpath(".//*[@id='navigation-menu']/li[3]/div/div[1]/ul"));//inside of this ul need to get all li
    	kidsSubcategory =kidsColumn.findElements(By.tagName("li"));
    	
        Reporter.log("Kids Subcategories has: " + kidsSubcategory.size()+ " 'Subcategory' links   ");
        System.out.println("Kids Subcategories has: " + kidsSubcategory.size()+ " 'Subcategory' links");

        for (WebElement element: kidsSubcategory) 
    		{
    		      System.out.println("Kids Subcategories: "+ element.getText().trim());
    		      Reporter.log("Kids Subcategories: "+ element.getText().trim());
    		}
    	
        	WebElement kidsColumnCollection=driver.findElement(By.xpath(".//*[@id='navigation-menu']/li[3]/div/div[2]/ul"));//Collection ul
        kidsSubcategoryCollection =kidsColumnCollection.findElements(By.tagName("li"));
        
        Reporter.log("Kids Subcategories has: " + kidsSubcategoryCollection.size()+ " 'Collections'");
        System.out.println("Kids Subcategories has: " + kidsSubcategoryCollection.size()+ " 'Collections'");
        
        for (WebElement element: kidsSubcategoryCollection) 
		{
		      System.out.println("Kids Subcategories include these 'Collections' :  "+ element.getText().trim());
		      Reporter.log("Kids Subcategories include these 'Collections' : "+ element.getText().trim());
		}
        
        
    	kidsSubcategory.get(1).click();//CAN SELECT ANY OF THE WEBELEMENTS FROM THIS METHOD - GRABBED LOCATORS ALREADY!!
    	
    		pause(2000);
    	
    	    Reporter.log("Current page title is : "+ driver.getTitle());
        	NauticaCommon.takeScreenshot(driver);
    }
    
    public static void homeSubcat(WebDriver driver)
    {
    	Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id(category[3]))).build().perform();
        
        WebElement homeColumn = driver.findElement(By.xpath(".//*[@id='navigation-menu']/li[4]/div/div[1]/ul"));//home subcat column
        
        homeSubcategory =homeColumn.findElements(By.tagName("li"));
    	
        Reporter.log("Home Subcategories has: " + homeSubcategory.size()+ " 'Subcategory' links   ");
        System.out.println("Home Subcategories has: " + homeSubcategory.size()+ " 'Subcategory' links");

        for (WebElement element: homeSubcategory) 
    		{
    		      System.out.println("Home Subcategories: "+ element.getText().trim());
    		      Reporter.log("Home Subcategories: "+ element.getText().trim());
    		}
    	
        	//.//*[@id='navigation-menu']/li[4]/div/div[2]/ulTHIS is xpath for homeColumnCollection
        	
        WebElement homeColumnCollection=driver.findElement(By.xpath(".//*[@id='navigation-menu']/li[4]/div/div[2]/ul"));//Collection ul    
        homeSubcategoryCollection =homeColumnCollection.findElements(By.tagName("li"));
       pause(2000);
        
        Reporter.log("Home Subcategories has: " + homeSubcategoryCollection.size()+ " 'Collections'");
        System.out.println("Home Subcategories has: " + menSubcategoryCollection.size()+ " 'Collections'");
        
        for (WebElement element: homeSubcategoryCollection) 
		{
		      System.out.println("Home Subcategories include these 'Collections' :  "+ element.getText().trim());
		      Reporter.log("Home Subcategories include these 'Collections' : "+ element.getText().trim());
		}
        
        
    	//driver.findElement(By.linkText("Bedding")).click();
        action.moveToElement(driver.findElement(By.id(category[3]))).build().perform();

    	homeSubcategory.get(1).click();//CAN SELECT ANY OF THE WEBELEMENTS FROM THIS METHOD - GRABBED LOCATORS ALREADY!!
    	
    		pause(2000);
    	
    	    Reporter.log("Current page title is : "+ driver.getTitle());
        	NauticaCommon.takeScreenshot(driver);
    	
    }
    
    public static void watchesSubcat(WebDriver driver)
    {
    	
    	Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id(category[4]))).build().perform();
        //WATCHES HAS ONLY SUBCATEGORIES ONLY -NO COLLECTIONS NOW
     	
     	WebElement watchesColumn = driver.findElement(By.xpath(".//*[@id='navigation-menu']/li[5]/div/div[1]/ul"));//inside of this ul need to get all li
    	watchesSubcategory =watchesColumn.findElements(By.tagName("li"));
    	
        Reporter.log("Watches Subcategories has: " + watchesSubcategory.size()+ " 'Subcategory' links   ");
        System.out.println("Watches Subcategories has: " + watchesSubcategory.size()+ " 'Subcategory' links");

        for (WebElement element: watchesSubcategory) 
    		{
    		      System.out.println("Watches Subcategories: "+ element.getText().trim());
    		      Reporter.log("Watches Subcategories: "+ element.getText().trim());
    		}
    	
        	WebElement watchesColumnCollection=driver.findElement(By.xpath(".//*[@id='navigation-menu']/li[5]/div/div[2]/ul"));//Collection ul
        watchesSubcategoryCollection =watchesColumnCollection.findElements(By.tagName("li"));
        
        Reporter.log("Watches Subcategories has: " + watchesSubcategoryCollection.size()+ " 'Collections'");
        System.out.println("Watches Subcategories has: " + watchesSubcategoryCollection.size()+ " 'Collections'");
        
        for (WebElement element: watchesSubcategoryCollection) 
		{
		      System.out.println("Watches Subcategories include these 'Collections' :  "+ element.getText().trim());
		      Reporter.log("Watches Subcategories include these 'Collections' : "+ element.getText().trim());
		}
        
    	watchesSubcategoryCollection.get(1).click();//CAN SELECT ANY OF THE WEBELEMENTS FROM THIS METHOD - GRABBED LOCATORS ALREADY!!
    	
    		pause(2000);
    	
    	    Reporter.log("Current page title is : "+ driver.getTitle());
        	NauticaCommon.takeScreenshot(driver);
 
        //
    	driver.findElement(By.linkText("Womens")).click();
    	String pageTitle= driver.getTitle();
    	Reporter.log("Current page title is : "+pageTitle);
    	NauticaCommon.takeScreenshot(driver);
    }
    
    public static void saleSubcat(WebDriver driver)
    {
    	Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id(category[5]))).build().perform();
      //SALE  HAS ONLY SUBCATEGORIES NOW
     
     	WebElement saleColumn = driver.findElement(By.xpath(".//*[@id='navigation-menu']/li[6]/div/div[1]/ul"));//inside of this ul need to get all li
    	saleSubcategory =saleColumn.findElements(By.tagName("li"));
    	
        Reporter.log("Sale Subcategories has: " + saleSubcategory.size()+ " 'Subcategory' links   ");
        System.out.println("Sale Subcategories has: " + saleSubcategory.size()+ " 'Subcategory' links");

        for (WebElement element: saleSubcategory) 
    		{
    		      System.out.println("Sale Subcategories: "+ element.getText().trim());
    		      Reporter.log("Sale Subcategories: "+ element.getText().trim());
    		}
    	
        
        try
        {
        	WebElement saleColumnCollection=driver.findElement(By.xpath(".//*[@id='navigation-menu']/li[5]/div/div[2]/ul"));//Collection ul
        	
        	if(saleColumnCollection.isDisplayed())
        	{
        saleSubcategoryCollection =saleColumnCollection.findElements(By.tagName("li"));
        
        Reporter.log("Sale Subcategories has: " + saleSubcategoryCollection.size()+ " 'Collections'");
        System.out.println("Sale Subcategories has: " + saleSubcategoryCollection.size()+ " 'Collections'");
        
        for (WebElement element: saleSubcategoryCollection) 
		{
		      System.out.println("Sale Subcategories include these 'Collections' :  "+ element.getText().trim());
		      Reporter.log("Sale Subcategories include these 'Collections' : "+ element.getText().trim());
		}
        }
        }
        
        catch (NoSuchElementException e)
        {
        	e.printStackTrace();
        	System.out.println("There are no 'Collections' in 'Sale' category");
        	Reporter.log("There are no 'Collections' in 'Sale' category");

        }
        action.moveToElement(driver.findElement(By.id(category[5]))).build().perform();

        
    	saleSubcategory.get(1).click();//CAN SELECT ANY OF THE WEBELEMENTS FROM THIS METHOD - GRABBED LOCATORS ALREADY!!
    	
    		pause(2000);
    	    Reporter.log("Current page title is : "+ driver.getTitle());
        	NauticaCommon.takeScreenshot(driver);
 
    }
    
   
    public static void subCategorySelected(WebDriver driver)
    {
    	int imageNum=4;//this can be 0-11
    	//this is locator for 12 images on subcat page -  By.className  thumb-link
     List<WebElement> imagesOnSubcat = driver.findElements(By.className("thumb-link"));
     Reporter.log("There are " +imagesOnSubcat.size() + " images  on the subcategory page");
     NauticaCommon.takeScreenshot(driver);
     imagesOnSubcat.get(imageNum).click();
     Reporter.log("After clicking on the image on 'Subcategory' page  - page title is  " + driver.getTitle());
     WebElement productNum= driver.findElement(By.className("product-number"));
     //productNum.getAttribute("innerHTML");
     Reporter.log("After clicking on the image on 'Subcategory' page  - selected PID " + productNum.getAttribute("innerHTML"));
 
     
    }
    
    public static void PDPswatchColor(WebDriver driver)
    {
    	
    	try
    	{
    		List <WebElement> selectedSwatch = driver.findElements(By.className("selected"));
    		
    		if (selectedSwatch.isEmpty())
    		{
    			//selected swatch color not present;
    			Reporter.log("WARNING!" + selectedSwatch.size()+ " color swatch(es)  selected on default PDP load");
    		}
    		
    		else
    			
    		{
    			Reporter.log("The number of selected swatch colors on default PDP load: " +selectedSwatch.size());
    			
    			
    			for (WebElement el: selectedSwatch)
    			{
    				String selectedColor = el.getText().trim();
    				Reporter.log("Currently selected swatch color on PDP on defaul load: " +selectedColor);
    			}
    		}
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		
    	}
          
         
         //class swatchanchor trackEvent
           List<WebElement> trackEvent = driver.findElements(By.className("emptyswatch"));
           List<String> htmlOfTrackEvent = new ArrayList();
        
           
           System.out.println(trackEvent.size() + " empty color swatch(es) displayed. ");
           		
           if (trackEvent.size()>0)
           {
               Reporter.log(trackEvent.size() + " empty color swatch(es) displayed. ");

              for ( int i = 0; i<trackEvent.size(); i++){
                   WebElement ele = trackEvent.get(i);
                
                 //htmlOfTrackEvent.add(ele.getAttribute("title").trim());  
                   htmlOfTrackEvent.add(ele.getText().trim());         

              }
              Reporter.log("List of empty swatch colors on PDP: " );
             
              Iterator it =htmlOfTrackEvent.iterator();
              while(it.hasNext())
              {
            	  
                   Object elem=it.next();
                   System.out.println("These are empty swatch colors on PDP: " + elem+" ");
                   Reporter.log(elem.toString());
                   
              }
           }
           
           else
           {
        	   Reporter.log("There are no empty color swatches on PDP");
           }
           
    }
    
    public static void sizeChartOnPDP(WebDriver driver)
    {
    	
    	try
    	{
    		driver.findElement(By.linkText("Size Chart"));
    		if( driver.findElement(By.linkText("Size Chart")).isDisplayed()){
        		System.out.println("TEST FOR SIZE CHART PRESENCE: 'Size Chart link' -Element is Visible");
        		Reporter.log("Size chart link is visible on PDP- true.");
        		NauticaCommon.takeScreenshot(driver); 
    		}
    		
    	}
    	
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println("WARNING!: Size chart is not present on PDP.");
    		Reporter.log("WARNING!: Size chart is not present on PDP." + " Current page title is "+ driver.getTitle());
    		NauticaCommon.takeScreenshot(driver); 

    	}
    	
    }
    
    //PDP SELECT SIZE 
    public static void selectSizeOnPDP(WebDriver driver)
    {
    	Reporter.log("Available sizes on PDP: ");
    	driver.findElement(By.cssSelector("div b")).click();
    	
    	Select select = new Select(driver.findElement(By.id("va-size")));

    	List<WebElement> allSizes= select.getOptions();
    	List<String> sizes= new ArrayList<String>();
    	
    	for(WebElement size:allSizes)
    	{
 
      		String sizeOption =size.getAttribute("innerHTML").trim();
    	    System.out.println("This is getText of each select option: "+ sizeOption);    //It will return the text of each option

    	      System.out.println("This is innerHTML for options in select: " +size.getAttribute("innerHTML").trim());
    	      System.out.println("This is value of select options: " +size.getAttribute("value").trim());    //it will return the value attribute of each option
    	      String sizeSelect = size.getAttribute("innerHTML").trim();
    	      
    	      sizes.add(sizeSelect);//THIS IS ARRAY OF STRINGS WITH VISIBLE TEXT FOR SIZE VALUES
    	      
    	      Reporter.log("" + size.getAttribute("innerHTML").trim());
    	}
    	NauticaCommon.takeScreenshot(driver);
    	
    	int howManySizes = sizes.size();
    	
    		try{
    			
    			//driver.findElement(By.cssSelector("div b")).click();
    			Actions action = new Actions(driver);
    		      WebElement we = driver.findElement(By.xpath("html/body/div[11]/ul/li[2]/div"));

    		      action.moveToElement(we).click().build().perform();
    		      waitToLoadElement(driver, By.xpath("//ul/li[2]/div/div/a/span"), 3);//BECAUSE AFTER SELECTING SIZE THERE IS AJAX RELOAD
    		      String size= driver.findElement(By.xpath("//ul/li[2]/div/div/a/span")).getAttribute("innerHTML").trim();
//JavascriptExecutor js = (JavascriptExecutor) driver; js.executeScript("document.getElementById('va-size').sty‌​le.display='';"); 
//waitToLoadElement(driver, By.xpath("//li[2]/div/div/a/span"), 5);
    		
    			Reporter.log("Selected size on PDP : "+ size+ " -true");
                takeScreenshot(driver);
                pause(3000);
    		}
    		
    		catch(Exception e)
    		{  
			Reporter.log("Could not select size on PDP");
    			System.out.println("Could not select size on PDP");
    			e.printStackTrace();
    			NauticaCommon.takeScreenshot(driver);
    		}
    	
    }
    
    public static void selectQuantityOnPDP(WebDriver driver)
    {
             
            //div b:nth-child(1) - did not work - was stil clickin on size toggle
             driver.findElement(By.xpath("//div[5]/form/div/div/div/a/div/b")).click();
           
            Select select = new Select(driver.findElement(By.name("Quantity")));
            List<WebElement> allQuantityOptions= select.getOptions();
            
            Reporter.log("Available quantities on PDP: ");
            for(WebElement quantityOption:allQuantityOptions)
        	{ 
        	      System.out.println("This is innerHTML for options in select: " +quantityOption.getAttribute("innerHTML").trim());
        	      System.out.println("This is value of select options: " +quantityOption.getAttribute("value").trim());//it will return the value attribute of each option
        	      String quantitySelectOption = quantityOption.getAttribute("innerHTML").trim();
        	      if (quantitySelectOption.isEmpty())
        	      {
        	    	  continue;
        	      }
        	      Reporter.log("" + quantityOption.getAttribute("innerHTML").trim());
        	}
            
             NauticaCommon.takeScreenshot(driver);
             try{
            	 Actions action = new Actions(driver);

            	 WebElement we = driver.findElement(By.xpath("/html/body/div[11]/ul/li[3]/div"));//li can be any not neccessarily 3

            	 String quantity=driver.findElement(By.xpath("/html/body/div[11]/ul/li[3]/div")).getAttribute("innerHTML").trim();
            	 action.moveToElement(we).click().build().perform();

            	//  waitToLoadElement(driver, By.xpath("//div[5]/form/div/div/div/a/span"), 2);//BECAUSE AFTER SELECTING quantity  there is NO AJAX reload

            	 System.out.println("On PDP selected quantity : " +quantity);
            	 Reporter.log(("On PDP selected quantity - " +quantity));

            	 takeScreenshot(driver);
             }
     		
     		catch(Exception e)
     		{  
 			Reporter.log("Could not select quantity on PDP");
     			System.out.println("Could not select size on PDP");
     			e.printStackTrace();
     			NauticaCommon.takeScreenshot(driver);
     		}
             
    }
    
    public static void writeReviewPresent(WebDriver driver)
    {
    	try
    	{
    	WebElement writeReview = driver.findElement(By.className("pr-snippet-link"));//can be diff locator By.linkText("Write a Review")
    	if( driver.findElement(By.linkText("Write a Review")).isDisplayed()){
    		System.out.println("TEST FOR 'Write a Review'  PRESENCE: 'Write a Review link' -Element is Visible");
    		Reporter.log(" 'Write a Review' link link is visible on PDP- true.");
    		NauticaCommon.takeScreenshot(driver); 
		}
    	
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println("WARNING!: 'Write a Review' is not present on PDP.");
    		Reporter.log("WARNING! Missing element on PDP: 'Write a Review'  is not present on." + " Current page title is "+ driver.getTitle());
    		NauticaCommon.takeScreenshot(driver); 

    	}
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
    
}
