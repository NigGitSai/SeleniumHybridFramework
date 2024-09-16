package Base;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.RemoteWebDriver;import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import Constants.APPConstants;
import Factory.DriverFactory;
import Factory.Report;
import Pages.MainPage;
import utils.ExcelReader;

public class Base extends Report {
	protected static DriverFactory driverfactory;
	public static RemoteWebDriver driver;
	protected String region = "";
	public String URL = "";
	public ExcelReader excelReader;
	static Logger log;
	String filePath ="";
	
	@BeforeSuite
	public void setUp()
	{
		driverfactory = new DriverFactory();
		log=Logger.getLogger(Base.class);
		PropertyConfigurator.configure("./src/test/resources/config/log4j.properties");
		log.info("Driver Factor Initialized");
		System.out.println("In Before Suite");
		startReport();
	}
	
	@Parameters({"DataSheet"})
	@BeforeTest
	public void launchBrowser(String dataSheet)
	{
		driver = driverfactory.intBrowser(driverfactory.init_Properties());
		filePath = System.getProperty("user.dir")+File.separator+"//src//test//resources//TestData//"+dataSheet; 
		excelReader = new ExcelReader(System.getProperty("user.dir")+File.separator+"//src//test//resources//TestData//"+dataSheet);
		log.info("Excel File Initialiized");
		
	}
	
	public void navigateAppUrl() throws InterruptedException
	{
		
		this.URL =APPConstants.HOMEPAGE_URL;
		DriverFactory.getDriver().get(this.URL);
		
		System.out.println(this.URL);
		log.info("Application URL Navigated");
		Thread.sleep(3000);
	}
	public MainPage main()
	{
		
		MainPage main = new MainPage(DriverFactory.getDriver());
		return main;
	}
	
	@AfterTest
	public void closeDriver() throws IOException
	{
		DriverFactory.getDriver().quit();
		System.out.println("In After Test");
		
		
	}
	
	@AfterSuite
	public void tearDown() throws IOException
	{
		excelReader.closeExcelWorkbook();
		stopReport();
	}
}
