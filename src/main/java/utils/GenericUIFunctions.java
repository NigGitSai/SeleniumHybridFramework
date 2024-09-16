package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;

import Factory.Report;

public class GenericUIFunctions extends Report {
	RemoteWebDriver driver;
	Logger log ;
	File srcFile,desFile;
	String srcScreenshot;
	byte[] byteArrScreenshot;
	WebDriverWait wait;
	public GenericUIFunctions(RemoteWebDriver driver)
	{
		this.driver = driver;
		log = Logger.getLogger(GenericUIFunctions.class);
		PropertyConfigurator.configure("./src/test/resources/config/log4j.properties");
		wait = new WebDriverWait(driver, 20);
	}
	
	public void click(WebElement ele,String objectName) throws IOException
	{
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			
			log.info( objectName+" is clicked");
			testStatus("info", objectName+" is clicked", "Not req");
			//testStatus("log", objectName+" is clicked", "Not req");
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			testStatus("fail",  e.getMessage(),  takeScreenshot());
			testStatus("log", e.getMessage(), "Not req");
		}
	}
	public void enter(WebElement ele,String input,String objectName) throws IOException
	{
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			ele.sendKeys(input);
			log.info( input+" is entered in "+objectName);
			testStatus("info",input+" is entered in "+objectName, "Not req");
			//testStatus("log",input+"is entered in "+objectName,"Not req");
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			testStatus("fail",  e.getMessage(),  takeScreenshot());
			testStatus("log", e.getMessage(), "Not req");
		}
	}
	
	public void verifyActualVsExpected(String expValue,String actValue,String verification) throws IOException
	{
		try
		{
		if(expValue.equals(actValue))
		{
			//Assert.assertEquals(expValue, actValue);
			log.info(verification+": "+actValue+"==Equals=="+expValue );
			testStatus("pass",verification+": "+actValue+"==Equals=="+expValue, takeScreenshot());
			//testStatus("log",verification+": "+actValue+"==Equals=="+expValue,"Not req");
		}
		else
		{
			
			log.info(actValue+"==Not Equals=="+expValue );
			testStatus("fail",verification+": "+actValue+"==Equals=="+expValue, takeScreenshot());
			
		}
		}
		catch(Exception e)
		{
			//Assert.assertFalse(true);
			log.error(e.getMessage());
			testStatus("fail",  e.getMessage(),  takeScreenshot());
			testStatus("log", e.getMessage(), "Not req");
		}
	}
	
	public String getText(WebElement ele, String objectName) throws IOException
	{
		
		
		String actText="";
		try
		{
			actText= ele.getText();
			log.info(objectName+" element Text fetched");
			testStatus("info",objectName+" element Text fetched", "Not req");
			//testStatus("log",objectName+" element Text fetched","Not req");
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			testStatus("fail",  e.getMessage(),  takeScreenshot());
			testStatus("log", e.getMessage(), "Not req");
		}
		return actText;
	}
	
	/**
	 * take screenshot
	 * @throws IOException 
	 * 
	 */

	protected  String takeScreenshot() throws IOException {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		//getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		Shutterbug.shootPage(driver, Capture.FULL, true).withName("Fullpage").save(System.getProperty("user.dir") + "/screenshot/");
		//srcScreenshot =  driver.getScreenshotAs(OutputType.BASE64);
		byte[] fileContent = FileUtils.readFileToByteArray(new File(System.getProperty("user.dir")+"/screenshot/Fullpage.png"));
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		
		//return srcScreenshot;
		return encodedString;
	}

	public void verifyElementDisp(WebElement ele, String objectName) throws IOException
	{
		try
		{
			
		if(ele.isDisplayed()==true)
		{
			log.info(objectName+" is displayed ");
			testStatus("pass",objectName+" is displayed ", "Not req");
			
		}
		else
		{
			
			log.info(objectName+"is not displayed ");
			testStatus("fail",objectName+"is not displayed ", takeScreenshot());
			
		}
		}
		catch(Exception e)
		{
			
			log.error(e.getMessage());
			testStatus("fail",  e.getMessage(),  takeScreenshot());
			testStatus("log", e.getMessage(), "Not req");
		}
	}
	
	public void switchToNewWindow() throws IOException
	{
		try
		{
			Set<String> windows = driver.getWindowHandles();
			List<String> hlist = new ArrayList<String>(windows);
			for(int i=0; i<hlist.size(); i++)
			{
				driver.switchTo().window(hlist.get(1));
			}
			log.info("New window is switched");
			testStatus("pass","New window is switched ", "Not req");
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			testStatus("fail",  e.getMessage(),  takeScreenshot());
			testStatus("log", e.getMessage(), "Not req");
		}
		
	}
	
	public void switchToParentWindow() throws IOException
	{
		try
		{
		Set<String> windows = driver.getWindowHandles();
		List<String> hlist = new ArrayList<String>(windows);
		driver.switchTo().window(hlist.get(0));
		log.info("Parent window is switched");
		testStatus("pass","Parent window is switched ", "Not req");
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			testStatus("fail",  e.getMessage(),  takeScreenshot());
			testStatus("log", e.getMessage(), "Not req");
		}
	}
	
	public void closeCurrentWindow() throws IOException
	{
		try
		{
			driver.close();
			log.info("Current browser window is closed");
			testStatus("pass","Current browser window is closed ", "Not req");
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			testStatus("fail",  e.getMessage(),  takeScreenshot());
			testStatus("log", e.getMessage(), "Not req");
		}
	}
	
	
}
