package Factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public static RemoteWebDriver driver;
	Properties properties;
	private String env ="";
	private String region = "";
	private String URL = "";
	Logger log;
	private static ThreadLocal<RemoteWebDriver> driverTl = new ThreadLocal<>();
	
	public static RemoteWebDriver  getDriver()
	{
		System.out.println(driverTl.get());
		return driverTl.get();
		//return driver;
	}
	
	public RemoteWebDriver intBrowser(Properties prop)
	{
		
		String execution = prop.getProperty("exec").trim();
		String browserName="";
		log = Logger.getLogger(DriverFactory.class);
		PropertyConfigurator.configure("./src/test/resources/config/log4j.properties");
		log.info("Execution is set from "+execution);
		if(execution.equalsIgnoreCase("Jenkins"))
		{
			browserName = System.getProperty("browserName");
		}
		else if(execution.equalsIgnoreCase("Local"))
		{
			browserName = prop.getProperty ("browser").trim();
		}
		else
		{
			System.out.println("Invalid execution value");
		}
		System.out.println("Browser Name "+browserName);
		
		
		switch(browserName.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driverTl.set(driver);
			log.info("Chrome Browser initialized");
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driverTl.set(driver);
			log.info("Firefox browser initialized");
			break;
		
		
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driverTl.set(driver);
			log.info("Edge browser initialized");
			break;
		default:
			System.out.println("Please pass correct browser name");
			log.error("Error in browser value which is passed");
			break;
		
		}
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("setDefaultTimeout")), TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(Long.parseLong(prop.getProperty("pageLoadTimeOut")), TimeUnit.SECONDS);
		return getDriver();
	}
	
	public Properties init_Properties()
	{
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/configuration.properties");
			properties = new Properties();
			properties.load(ip);
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	
	
	

}
