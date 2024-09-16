package Pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constants.APPConstants;
import utils.GenericUIFunctions;

public class HomePage  extends GenericUIFunctions{
	RemoteWebDriver driver;
	WebDriverWait wait;
	public HomePage(RemoteWebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(xpath ="//img[@alt='Tricentis Demo Web Shop']") private WebElement demoWebShopTitle;
	
	
	public void verifyDemoWebShopImageDisplayed() throws IOException
	{
		wait.until(ExpectedConditions.visibilityOf(demoWebShopTitle));
		verifyElementDisp(demoWebShopTitle, "Demo Web Shop Home page image");
	}
	
	public void verifyDemoWebShopTitle() throws IOException
	{
		String actTitle = driver.getTitle();
		verifyActualVsExpected(APPConstants.APP_TITLE, actTitle, "Application Title");
	}
}
