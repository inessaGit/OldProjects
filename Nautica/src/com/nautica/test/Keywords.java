package com.nautica.test;
import static com.nautica.test.DriverScript.APP_LOGS;
import static com.nautica.test.DriverScript.CONFIG;
import static com.nautica.test.DriverScript.OR;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Keywords {
	public WebDriver driver;
	   
    //To open a particular Web browser defined in Config file
    public String openBrowser(String object,String data){       
        APP_LOGS.debug("Opening browser");
        //if(data.equals("Mozilla"))
        if(CONFIG.getProperty(data).equalsIgnoreCase("Mozilla"))
            driver=new FirefoxDriver();
        else if(CONFIG.getProperty(data).equalsIgnoreCase("IE"))
            {
                System.setProperty("webdriver.ie.driver", "C:\\Users\\meenu\\workspace\\OptTest\\IEDriverServer.exe");
            driver=new InternetExplorerDriver();
            }
        else if(CONFIG.getProperty(data).equalsIgnoreCase("Chrome"))
            {
        	
        	File file=new File("/Users/igonzalez/Desktop/SELENIUM_jar/selenium/chromedriver");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            
            driver=new ChromeDriver();
            }
       
       
        return Constants.KEYWORD_PASS;

    }
	
    public String navigate(String object,String data){        
        APP_LOGS.info("Navigating to URL");
        try{
        driver.navigate().to(CONFIG.getProperty(data));
        
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" -- Not able to navigate";
        }
        return Constants.KEYWORD_PASS;
    }
    
    //click link by ID
    public String clickLink_BYID(String object,String data){
        APP_LOGS.debug("Clicking a link by id ");
        try{
        	 
        driver.findElement(By.id(OR.getProperty(object))).click();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" -- Not able to click on link"+e.getMessage();
        }
     
        return Constants.KEYWORD_PASS;
    }
    
	
  //To click a link- by xpath
    public String clickLink_BYXpath(String object,String data){
        APP_LOGS.debug("Clicking a link by xpath ");
        try{
        	 
        driver.findElement(By.xpath(OR.getProperty(object))).click();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" -- Not able to click on link"+e.getMessage();
        }
     
        return Constants.KEYWORD_PASS;
    }
  
    
	public String verifyPageHeader(String object, String data)
	{
		APP_LOGS.debug("Verifying page header on Mens page");
		
		
		try{ 
			String actual=driver.findElement(By.xpath(OR.getProperty(object))).getText();
			String expected=OR.getProperty(data);
			System.out.println("actual="+actual);
			System.out.println("expected="+expected);
			if(actual.equals(expected))
			{
				return Constants.KEYWORD_PASS;
			}
			else 
			{
				return Constants.KEYWORD_FAIL;
			}
		
		} catch(Exception e)
		{
			return Constants.KEYWORD_FAIL+"<----no such element on the page";
		}
	}
	
	

}
