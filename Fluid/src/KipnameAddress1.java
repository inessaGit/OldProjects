

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class KipnameAddress1 {
  private WebDriver driver;
  private String baseUrl;
  String devUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeTest
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.kipling-usa.com/";
    devUrl="http://development.spwrealm.vfc.demandware.net/on/demandware.store/Sites-kip-Site/default/Home-Show";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testKipnameAddress1() throws Exception {
	  
	    driver.get(devUrl);

	    Actions action =new Actions(driver);
	    
	    WebElement newHandbags = driver.findElement(By.id("nav-handbags"));
action.moveToElement(newHandbags).build().perform();

	    
	    driver.findElement(By.cssSelector("#handbags-new > span")).click();
    driver.findElement(By.cssSelector("img[alt=\"Bagsational Handbag - Beige Snake\"]")).click();
    driver.findElement(By.id("add-to-cart")).click();
    driver.findElement(By.cssSelector("a.cartLink.ignore")).click();//THIS IS MINI CART ON PDP LINK
    driver.findElement(By.name("dwfrm_cart_checkoutCart")).click();
    driver.findElement(By.name("dwfrm_login_unregistered")).click();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName")).sendKeys("i");
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).sendKeys("g");
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1")).sendKeys("373 elizabeth ct");
    driver.findElement(By.name("dwfrm_singleshipping_shippingAddress_save")).click();
    
    
  }

  @AfterTest
  public void tearDown() throws Exception {
   // driver.quit();
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
