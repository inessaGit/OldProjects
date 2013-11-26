package KiplingTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class KipOrderSummary {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
   driver = new FirefoxDriver();
    

	//File file=new File("/Users/igonzalez/Desktop/SELENIUM_jar/selenium/chromedriver");
    //System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	//driver = new ChromeDriver();
    baseUrl = "http://www.kipling-usa.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
	
	public void waitForPageLoaded(WebDriver driver) {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};

		Wait<WebDriver> wait = new WebDriverWait(driver,30);
		try {
			wait.until(expectation);
		} catch(Throwable error) {
			assertFalse("Timeout waiting for Page Load Request to complete.",true);
		}
	} 
  @Test
  public void testKipOrderSummary() throws Exception {
    driver.get(baseUrl + "/");
    
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(By.id("nav-handbags"))).build().perform();
    
    
    driver.findElement(By.cssSelector("#handbags-new > span")).click();
    driver.findElement(By.xpath("//div/a/img")).click();
    driver.findElement(By.id("add-to-cart")).click();
    driver.findElement(By.xpath("//li[@id='mini-cart']/a")).click();
    driver.findElement(By.xpath("//button[@name='dwfrm_cart_checkoutCart']")).click();
    driver.findElement(By.name("dwfrm_login_unregistered")).click();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName")).sendKeys("ines");
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).sendKeys("g");
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1")).sendKeys("6810 Main Street");
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_city")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_city")).sendKeys("Vancouver");
    
    
    Select selectState= new Select(driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_states_state")));

    List<WebElement>allStates=selectState.getOptions();
    List<String> states= new ArrayList<String>();
    for(WebElement state: allStates)
    {
       String stateOption = state.getAttribute("innerHTML").trim();
       
       //Reporter.log("This are the states in the state drop down: "+ stateOption);
          System.out.println ("This are the states in the state drop down: "+ stateOption);
         
    }
    
    driver.findElement(By.cssSelector("div b")).click();//this is country toggle

    WebElement countrytDropDown = driver.findElement(By.xpath("html/body/div[8]/ul/li[2]/div"));//this is state drop down /or country because div[8] is for both
    action.moveToElement(countrytDropDown).click().build().perform();
    pause(3000);
    
   driver.findElement(By.cssSelector("a.select2-choice.select2-default > div > b")).click();//THIS IS STATE toggle

    WebElement stateDropDown = driver.findElement(By.xpath("html/body/div[8]/ul/li[2]/div"));//this is state drop down /or country because div[8] is for both
    action.moveToElement(stateDropDown).click().build().perform();
    //waitToLoadElement (driver, By.xpath(".//*[@id='s2id_dwfrm_singleshipping_shippingAddress_addressFields_country']/a/span"), 5);//BECAUSE country defines 'State'/'Province'/State_Province
   

//List<WebElement> statesInDropDown = driver.findElements(By.className("select2-match"));//this are items in  the state drop down
//statesInDropDown.get(4).click();


   // driver.findElement(By.xpath("//div[2]/div/div/a/span")).click();//THIS IS XPATH FOR 'select' value in state drop down
    //selectState.getFirstSelectedOption().click();

   // JavascriptExecutor jse = (JavascriptExecutor)driver;
    //jse.executeScript("document.getElementById('dwfrm_singleshipping_shippingAddress_addressFields_states_state').style.display='';");
    
    //selectDropDown.findElement(By.xpath("//fieldset/div[4]/div[2]/div/div/a/span")).click();
    //selectDropDown.findElement(By.xpath("//ul/li[9]/div")).click();

    //new Select(driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_states_state"))).selectByVisibleText(stateCA);
    //new Select(driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_states_state"))).selectByValue("CA");

    
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_zip")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_zip")).sendKeys("V5X 0A1");
    
   // String stateOrProvince= driver.findElement(By.xpath(".//*[@id='dwfrm_singleshipping_shippingAddress']/fieldset/div[4]/div[3]/label/span")).getAttribute("innerHTML").trim();
    //System.out.println("After selecting country - " + stateOrProvince);
   
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_phone")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_phone")).sendKeys("9255443344");
    //driver.findElement(By.id("shipping-method-2day_48_contiguous_states")).click();
   // driver.findElement(By.xpath("//div[@id='shipping-method-list']/fieldset/div/label")).click();
  
    
    try {
        assertEquals("Province", driver.findElement(By.xpath("//form[@id='dwfrm_singleshipping_shippingAddress']/fieldset/div[4]/div[3]/label/span")).getText());
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
    WebDriverWait wait= new WebDriverWait( driver, 60);

    WebElement continueStep1 =driver.findElement(By.name("dwfrm_singleshipping_shippingAddress_save"));
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dwfrm_singleshipping_shippingAddress']/div[3]/button")));
    //continueStep1.
    continueStep1.click();//CONTINUE BUTTON
    
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dwfrm_billing']/div/button")));
   // waitForPageLoaded(driver);
    
   pause(5000);
   
   waitToLoadElement(driver, By.xpath("//form[@id='dwfrm_billing']/fieldset/legend"), 6);
    driver.findElement(By.id("toggleShippingAddress")).click();
    driver.findElement(By.id("useShipping")).click();
    driver.findElement(By.id("toggleShippingAddress")).click();
    driver.findElement(By.id("useShipping")).click();
    driver.findElement(By.id("dwfrm_billing_billingAddress_email_emailAddress")).clear();
    driver.findElement(By.id("dwfrm_billing_billingAddress_email_emailAddress")).sendKeys("software_test22@hotmail.com");
    driver.findElement(By.id("dwfrm_billing_billingAddress_addToEmailList")).click();
    driver.findElement(By.xpath("//form[@id='dwfrm_billing']/fieldset/div[8]/div[2]/label")).click();
    driver.findElement(By.cssSelector("#s2id_dwfrm_billing_paymentMethods_creditCard_type > a.select2-choice > div > b")).click();
    driver.findElement(By.cssSelector("#s2id_dwfrm_billing_paymentMethods_creditCard_year > a.select2-choice.select2-default > div > b")).click();
    driver.findElement(By.id("dwfrm_billing_paymentMethods_creditCard_owner")).clear();
    driver.findElement(By.id("dwfrm_billing_paymentMethods_creditCard_owner")).sendKeys("ines");
    driver.findElement(By.id("dwfrm_billing_paymentMethods_creditCard_number")).clear();
    driver.findElement(By.id("dwfrm_billing_paymentMethods_creditCard_number")).sendKeys("4111111111111111");
    driver.findElement(By.id("dwfrm_billing_paymentMethods_creditCard_cvn")).clear();
    driver.findElement(By.id("dwfrm_billing_paymentMethods_creditCard_cvn")).sendKeys("111");
    driver.findElement(By.cssSelector("a.select2-choice.select2-default > div > b")).click();
    driver.findElement(By.cssSelector("button[name=\"dwfrm_billing_save\"]")).click();
    try {
      assertEquals("Order Total:", driver.findElement(By.cssSelector("tr.order-total > td.totLabel")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertTrue(isElementPresent(By.xpath("//div[@id='primary']/div[2]/div/table/tbody/tr[5]/td[2]")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.name("submit")).click();
    assertEquals("Checkout Confirmation", driver.getTitle());
    try {
      assertTrue(isElementPresent(By.cssSelector("div.order-number. > span.value")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("5010 haven pl \ndublin, CA 94568\n United States \nPhone: 9255443344", driver.findElement(By.cssSelector("address")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Shipping 2 Day Express", driver.findElement(By.cssSelector("tr.order-shipping > td.totLabel")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @After
  public void tearDown() throws Exception {
  //  driver.quit();
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
