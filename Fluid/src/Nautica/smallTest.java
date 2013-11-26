package Nautica;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;






public class smallTest {
static WebDriver dr1 ;
	static String [] category= new String[]{"nav-mens","nav-womens", "nav-kids","nav-home",
        "nav-watches", "nav-sale", "nav-inside-nautica"};
	
	static String baseUrl = "http://www.nautica.com";
    static String devUrl="https://storefront:vfc123@development.spwrealm.vfc.demandware.net/on/demandware.store/Sites-nau-Site";
   static String prodUrl ="http://production.spwrealm.vfc.demandware.net/on/demandware.store/Sites-nau-Site/default/Home-Show?locale=en_US";
   static String stagingUrl ="http://storefront:vfc123@staging.spwrealm.vfc.demandware.net/on/demandware.store/Sites-nau-Site/default/Home-Show?locale=en_US";
	
   @BeforeTest
   public void setUp()
   {
	   dr1 = new FirefoxDriver();
       //dr1.get(stagingUrl);
	   dr1.get(baseUrl);
   }
   
   @Test
	public static void main (String [] args)
	{
		//File file=new File("/Users/igonzalez/Desktop/SELENIUM_jar/selenium/chromedriver");
        //System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		//WebDriver driver =new ChromeDriver();
		//driver.get("http://www.nautica.com/non-iron-stripe-button-down-shirt/3309WR.html?dwvar_3309WR_color=401#start=1");
		   

   
Actions action = new Actions(dr1);
    	
    	try{
        action.moveToElement(dr1.findElement(By.id("nav-kids"))).build().perform();//CAN BE ANY NUM 0-5;   1 = MEN
        
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
   
    	//click on product image

    	  dr1.findElement(By.linkText("boys")).click();
dr1.findElement(By.xpath("//li[2]/div/div/a/img")).click();

    	  System.out.println(dr1.getTitle());

    	  //click on size drop down

    	  dr1.findElement(By.xpath("//*[@id='s2id_va-size']/a/div/b")).click();


    	  WebElement we = dr1.findElement(By.xpath("html/body/div[11]/ul/li[2]/div"));

    	  action.moveToElement(we).click().build().perform();

    	 

    	  WebDriverWait wait = new WebDriverWait(dr1,60); // The int here is the maximum time in seconds the element can wait.

    	  wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart")));

    	 

    	  WebElement textlink = dr1.findElement(By.id("add-to-cart"));

    	  if (textlink.isEnabled())

    	    System.out.println("View link: Enabled");

    	  else

    	    System.out.println("View link: Disabled");

    	 

    	dr1.findElement(By.xpath("//*[@id='add-to-cart']")).click();

    	 

    	dr1.findElement(By.xpath("//*[@id='mini-cart']/a")).click();

    	 

    	dr1.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    	 

    	dr1.findElement(By.xpath("//*[@id='checkout-form']/fieldset/button")).click();

    	 

    	  }

    	 /*@Test

    	 public void minicart()

    	 {

    	Actions actions2 = new Actions(dr1);

    	WebElement menuHoverLink = dr1.findElement(By.xpath("//*[@id='mini-cart']/a"));

    	actions2.moveToElement(menuHoverLink);

    	dr1.findElement(By.xpath("//*[@id='mini-cart']/a")).click();

    	/*WebDriverWait wait2 = new WebDriverWait(dr1,60); // The int here is the maximum time in seconds the element can wait.

    	  wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mini-cart']/div/div[3]/div[3]/a")));

    	 

    	//click checkout to redirect to shopping cart page

    	dr1.findElement(By.xpath("//*[@id='mini-cart']/div/div[3]/div[3]/a")).click();

    	//now on shopping cart page

    	dr1.findElement(By.xpath("//*[@id='checkout-form']/fieldset/button")).click();

    	//guest checkout

    	 } */

    	 @Test

    	 public void guestCheckout()

    	 {// now on checkout intercept- clicking guest checkout


    	WebDriverWait wait4 = new WebDriverWait(dr1,60); // The int here is the maximum time in seconds the element can wait.

    	  wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='primary']/div/div[1]/div/div/form/fieldset/div/button")));

    	 

    	  WebElement textlink4 = dr1.findElement(By.xpath("//*[@id='primary']/div/div[1]/div/div/form/fieldset/div/button"));

    	  if (textlink4.isEnabled())

    	    System.out.println("Guest checkout Enabled");

    	  else

    	    System.out.println("Guest checkout Disabled");

    	  dr1.findElement(By.name("dwfrm_login_unregistered")).click();

    	    dr1.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName")).clear();
    	    dr1.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName")).sendKeys("ines");
    	    dr1.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).clear();
    	    dr1.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).sendKeys("g");
    	    dr1.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1")).clear();
    	    dr1.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1")).sendKeys("5010 haven pl");
    	    dr1.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_city")).clear();
    	    dr1.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_city")).sendKeys("dubli");
    	    
    	    WebElement addressOption2 = dr1.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address2"));
    	    addressOption2.click();
    	    addressOption2.sendKeys("apt 665");
    	    
    	    
    	    Select selectState= new Select(dr1.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_states_state")));
    	    List<WebElement>allStates=selectState.getOptions();
    	    List<String> states= new ArrayList<String>();
    	    for(WebElement state: allStates)
    	    {
    	       String stateOption = state.getAttribute("innerHTML").trim();
    	       
    	     //  Reporter.log("This are the states in the state drop down: "+ stateOption);
    	          System.out.println ("This are the states in the state drop down: "+ stateOption);
    	         
    	    }
    	    
    	    Select selectCountry= new Select(dr1.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_country")));

    	    List<WebElement>allCountries=selectCountry.getOptions();
    	    List<String> countries= new ArrayList<String>();
    	    for(WebElement country: allCountries)
    	    {
    	       String countryOption = country.getAttribute("innerHTML").trim();
    	       
    	     //  Reporter.log("This are the states in the state drop down: "+ stateOption);
    	          System.out.println ("This are the countries in the country drop down: "+ countryOption);
    	         
    	    }
    	     


    //	dr1.findElement(By.xpath("//*[@id='primary']/div/div[1]/div/div/form/fieldset/div/button")).click();


    	dr1.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


    	//fill form- shipping
    		//menSubcategory =driver.findElements(By.xpath("//ul[@id='navigation-menu']/li/div/div/ul/li[5]/a"));//instead of 5 can be 1-14
		//menSubcategory =driver.findElements(By.xpath("///div/div/ul/li/a"));
		
		//.//*[@id='navigation-menu']/li[1]/div/div[1]/ul this is xpath for menSubcat all except for 'Collections' 
    	
    	WebElement menColumn = dr1.findElement(By.xpath(".//*[@id='navigation-menu']/li[1]/div/div[1]/ul"));//inside of this ul need to get all li
    	List<WebElement>menSubcategory =menColumn.findElements(By.tagName("li"));
	 /*  WebElement select= driver.findElement(By.xpath("//div[@id='s2id_va-size']/span"));//XPATH SIZE 
	   
	   List<WebElement> el = select.findElements(By.tagName("span"));
	   
	  
		driver.quit();
		*/

//action.sendKeys(Keys.ENTER).build().perform();

//JavascriptExecutor js = (JavascriptExecutor) driver;
//js.executeScript(driver, "triggerEvent", ((Object) size).blur() );



	}
}
