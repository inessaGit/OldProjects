

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import UtilClasses.KipCommonMethods;
import UtilClasses.ReadingProperties;
import UtilClasses.ReadingProperties.*;		

public class CheckoutUSA {
	
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  ReadingProperties rp;
  
  WebDriverWait wait;
  Actions action;
private boolean stagingTrue;
private boolean devTrue;

  @BeforeSuite
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    String url=KipCommonMethods.getUrl("baseUrl");
	driver.get(url);
	KipCommonMethods.defaultWindowSize(driver);
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test (priority=1)
  public void testCheckoutUSA() throws Exception {
	

			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.id("nav-handbags"))).build().perform();


			driver.findElement(By.cssSelector("#handbags-new > span")).click();
			driver.findElement(By.xpath("//li[2]/div/div/a/img")).click();// li is image number in grid
			driver.findElement(By.id("add-to-cart")).click();
			Thread.sleep(4000);

		}

		@Test (priority=2)
		public void testCartPage()
		{
			driver.findElement(By.xpath("//li[@id='mini-cart']/a")).click();
			KipCommonMethods.takeScreenshot(driver);
		}
		
		@Test (priority=3)
		public void testcheckoutInterceptPage()
		{
			driver.findElement(By.xpath("//button[@name='dwfrm_cart_checkoutCart']")).click();
			KipCommonMethods.takeScreenshot(driver);

			driver.findElement(By.name("dwfrm_login_unregistered")).click();

		}
		@Test (priority=4)
		public void changeCountry()
		{
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName")));
//country toggle 
			
			rp=new ReadingProperties();
	String s=	rp.readLocatorsProperties("kip_countryToggleXpath");
	System.out.println(s);
driver.findElement(By.xpath(s)).click();

		stagingTrue = driver.getCurrentUrl().contains("staging");
	     devTrue = driver.getCurrentUrl().contains("development");

		System.out.println("Current environment staging? "+ stagingTrue);
		System.out.println("Current environment dev? "+ devTrue);

		   
		   if(stagingTrue==true|| devTrue==true)
		   {
			   WebElement countryDropDown = driver.findElement(By.xpath(rp.readLocatorsProperties("kip_stagingCountryDropDownXpath")));//STAGING XPATH for country/state
			    action.moveToElement(countryDropDown).click().build().perform();

		   }
		   
		   else if (stagingTrue!=true)
		   {
			    WebElement countryDropDown = driver.findElement(By.xpath(rp.readLocatorsProperties("kip_productionCountryDropDownXpath")));//production this is state drop down /or country because div[8] is for both
			    action.moveToElement(countryDropDown).click().build().perform();

		   }

		//@FindBy(xpath="//*[@id='dwfrm_singleshipping_shippingAddress_addressFields_country' and @tabindex='5']");
		
	//WebElement el = driver.findElement(By.xpath("//span[contains (text(), 'China')]"));
//WebElement el = driver.findElement(By.cssSelector("ul.select2-result :nth-child(6)")); //this is locator for li 6th country in the drop down

		    		//inside of li div inside of div span contains country name
		    		//WebElement country = el.findElement(By.tagName("div"));
		    		//action.moveToElement(country).click().build().perform(); 
		}
		
		@Test (priority=5)
		public void testSelectReportState()
		{
			Select selectState= new Select(driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_states_state")));
			List<WebElement>allStates=selectState.getOptions();
			List<String> states= new ArrayList<String>();
			for(WebElement state: allStates)
			{
				String stateOption = state.getAttribute("innerHTML").trim();

				//  Reporter.log("This are the states in the state drop down: "+ stateOption);
				System.out.println ("This are the states in the state drop down: "+ stateOption);
				driver.findElement(By.xpath(".//*[@id='s2id_dwfrm_singleshipping_shippingAddress_addressFields_states_state']/a/div/b")).click();//THIS IS STATE toggle

				//html/body/div[8]/ul/li[6]/div
				stagingTrue = driver.getCurrentUrl().contains("staging");
				   
				System.out.println(stagingTrue);
				   devTrue = driver.getCurrentUrl().contains("development");

				   
				   if(stagingTrue==true|| devTrue==true)
				   {
					   WebElement stateDropDown = driver.findElement(By.xpath("//div[96]/ul/li[6]/div "));//STAGING XPATH
					    action.moveToElement(stateDropDown).click().build().perform();

				   }
				   
				   else if (stagingTrue!=true)
				   {
					    WebElement stateDropDown = driver.findElement(By.xpath("html/body/div[8]/ul/li[6]/div"));//production this is state drop down /or country because div[8] is for both
					    action.moveToElement(stateDropDown).click().build().perform();

				   }

				//waitToLoadElement (driver, By.xpath(".//*[@id='s2id_dwfrm_singleshipping_shippingAddress_addressFields_country']/a/span"), 5);//BECAUSE country defines 'State'/'Province'/State_Province
				try {
					AssertJUnit.assertEquals("State", driver.findElement(By.xpath("//form[@id='dwfrm_singleshipping_shippingAddress']/fieldset/div[4]/div[2]/label/span")).getText());
				} catch (Error e) {
					verificationErrors.append(e.toString());
				}
			}
		}
@Test (priority=6)
public void checkout()
{
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName")).sendKeys("i");
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).sendKeys("g");
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1")).sendKeys("5010 haven pl");
    
    		driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_city")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_city")).sendKeys("dublin");
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_zip")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_zip")).sendKeys("94568");
    driver.findElement(By.id("shipping-method-ground_48_contiguous_states")).click();
    driver.findElement(By.id("shipping-method-2day_48_contiguous_states")).click();
    driver.findElement(By.xpath("//div[@id='shipping-method-list']/fieldset/div/label")).click();
    driver.findElement(By.id("shipping-method-2day_48_contiguous_states")).click();
    driver.findElement(By.id("shipping-method-ground_48_contiguous_states")).click();
    driver.findElement(By.xpath("//div[@id='shipping-method-list']/fieldset/div[3]/label")).click();
    driver.findElement(By.name("dwfrm_singleshipping_shippingAddress_save")).click();
    try {
      assertEquals("Please enter a Telephone Number", driver.findElement(By.xpath("//form[@id='dwfrm_singleshipping_shippingAddress']/fieldset/div[5]/div/span[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_phone")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_phone")).sendKeys("9256667788");
    driver.findElement(By.name("dwfrm_singleshipping_shippingAddress_save")).click();
    driver.findElement(By.id("dwfrm_billing_billingAddress_addToEmailList")).click();
    driver.findElement(By.xpath("//form[@id='dwfrm_billing']/fieldset/div[7]/div[2]/label")).click();
    driver.findElement(By.cssSelector("#s2id_dwfrm_billing_paymentMethods_creditCard_year > a.select2-choice > span")).click();
    driver.findElement(By.cssSelector("#s2id_dwfrm_billing_paymentMethods_creditCard_type > a.select2-choice > span")).click();
    driver.findElement(By.id("dwfrm_billing_paymentMethods_creditCard_owner")).clear();
    driver.findElement(By.id("dwfrm_billing_paymentMethods_creditCard_owner")).sendKeys("i");
    driver.findElement(By.id("dwfrm_billing_paymentMethods_creditCard_number")).clear();
    driver.findElement(By.id("dwfrm_billing_paymentMethods_creditCard_number")).sendKeys("4111111111111111");
    driver.findElement(By.id("dwfrm_billing_paymentMethods_creditCard_cvn")).clear();
    driver.findElement(By.id("dwfrm_billing_paymentMethods_creditCard_cvn")).sendKeys("111");
    driver.findElement(By.cssSelector("button[name=\"dwfrm_billing_save\"]")).click();
    driver.findElement(By.id("dwfrm_billing_billingAddress_email_emailAddress")).clear();
    driver.findElement(By.id("dwfrm_billing_billingAddress_email_emailAddress")).sendKeys("software-test22@hotmail.com");
    driver.findElement(By.cssSelector("button[name=\"dwfrm_billing_save\"]")).click();
    driver.findElement(By.name("submit")).click();
    try {
      assertTrue(isElementPresent(By.cssSelector("div.order-number. > span.value")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertTrue(isElementPresent(By.xpath("//div[@id='primary']/div[2]/table/tbody/tr[2]/td[4]/div/table/tbody/tr/td[2]")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @AfterSuite
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
