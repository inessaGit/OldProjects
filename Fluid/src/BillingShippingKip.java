

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BillingShippingKip {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeTest
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.kipling-usa.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testBillingShippingKip() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.cssSelector("#handbags-new > span")).click();
    driver.findElement(By.cssSelector("img[alt=\"Bagsational Handbag - Beige Snake\"]")).click();
    driver.findElement(By.id("add-to-cart")).click();
    driver.findElement(By.cssSelector("a.cartLink.ignore")).click();
    driver.findElement(By.name("dwfrm_cart_checkoutCart")).click();
    driver.findElement(By.name("dwfrm_login_unregistered")).click();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName")).sendKeys("i");
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).sendKeys("g");
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1")).sendKeys("373 elizabeth ct");
    driver.findElement(By.name("dwfrm_singleshipping_shippingAddress_save")).click();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address2")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address2")).sendKeys("apt 345b");
    driver.findElement(By.cssSelector("a.select2-choice.select2-default > span")).click();
    try {
      assertTrue(isElementPresent(By.xpath("//form[@id='dwfrm_singleshipping_shippingAddress']/fieldset/div[3]/div[2]/label/span")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("a.select2-choice.select2-default > span")).click();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_city")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_city")).sendKeys("dublin");
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_zip")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_zip")).sendKeys("94568");
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_phone")).clear();
    driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_phone")).sendKeys("9258889977");
    driver.findElement(By.name("dwfrm_singleshipping_shippingAddress_save")).click();
    try {
      assertEquals("", driver.findElement(By.id("dwfrm_billing_billingAddress_addressFields_firstName")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("", driver.findElement(By.id("dwfrm_billing_billingAddress_addressFields_lastName")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("a.select2-choice.select2-default > span")).click();
    driver.findElement(By.cssSelector("a.select2-choice.select2-default > span")).click();
    driver.findElement(By.cssSelector("#s2id_dwfrm_billing_billingAddress_addressFields_states_state > a.select2-choice.select2-default > span")).click();
    driver.findElement(By.cssSelector("a.select2-choice.select2-default > span")).click();
    driver.findElement(By.id("toggleShippingAddress")).click();
    driver.findElement(By.id("useShipping")).click();
    driver.findElement(By.id("dwfrm_billing_giftCertCode")).clear();
    driver.findElement(By.id("dwfrm_billing_giftCertCode")).sendKeys("0000800752000000001");
    driver.findElement(By.id("dwfrm_billing_giftCertPin")).clear();
    driver.findElement(By.id("dwfrm_billing_giftCertPin")).sendKeys("1234");
    driver.findElement(By.id("add-gift-cert")).click();
    driver.findElement(By.id("dwfrm_billing_billingAddress_addToEmailList")).click();
    driver.findElement(By.xpath("//form[@id='dwfrm_billing']/fieldset/div[7]/div[2]/label")).click();
    driver.findElement(By.cssSelector("#s2id_dwfrm_billing_paymentMethods_creditCard_year > a.select2-choice > span")).click();
    driver.findElement(By.id("dwfrm_billing_billingAddress_email_emailAddress")).clear();
    driver.findElement(By.id("dwfrm_billing_billingAddress_email_emailAddress")).sendKeys("software_test22@hotmail.com");
    driver.findElement(By.id("dwfrm_billing_billingAddress_addToEmailList")).click();
    driver.findElement(By.xpath("//form[@id='dwfrm_billing']/fieldset/div[7]/div[2]/label")).click();
    driver.findElement(By.cssSelector("#s2id_dwfrm_billing_paymentMethods_creditCard_year > a.select2-choice > div > b")).click();
  }

  @AfterTest
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
