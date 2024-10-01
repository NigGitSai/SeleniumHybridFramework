package Pages;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.GenericUIFunctions;

public class HeaderPage extends GenericUIFunctions{
	RemoteWebDriver driver;
	WebDriverWait wait;
	
	
	public HeaderPage(RemoteWebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 15);
		PageFactory.initElements(driver, this);
		
		
	}

	@FindBy(xpath ="//a[text()='Log in']") private WebElement logIn;
	@FindBy(xpath ="(//a[@href='/customer/info'])[1]") private List<WebElement> userNameLoggedIn;
	@FindBy(xpath ="//span[@class='cart-qty']") private WebElement shoppingCartQty;
	@FindBy(xpath ="//a[text()='Log out']") private WebElement logOut;
	
	private WebElement productMenu(WebDriver driver,String menu)
	{
		return driver.findElement(By.xpath("//ul[@class='top-menu']/li/a[contains(text(),'"+menu+"')]"));
	}
	
	private WebElement productSubListMenu(WebDriver driver,String submenu)
	{
		return driver.findElement(By.xpath("//ul[@class='sublist firstLevel active']//a[contains(text(),'"+submenu+"')]"));
	}
	
	public void clickLogInLink() throws IOException, InterruptedException
	{
		WebElement logInEle;
		try
		{
			 logInEle = wait.until(ExpectedConditions.elementToBeClickable(logIn));
			click(logInEle, "Log In Link");
			Thread.sleep(2000);
		}
		catch(StaleElementReferenceException e)
		{
			logInEle = wait.until(ExpectedConditions.elementToBeClickable(logIn));
			click(logInEle, "Log In Link");
			Thread.sleep(2000);
		}
	}

	public void verifyUserNameLoggedIN(String expUserName) throws IOException
	{
		String actUserNameLoggedIn = userNameLoggedIn.get(0).getText();
		verifyActualVsExpected(expUserName, actUserNameLoggedIn, "User Name Logged IN");
	}
	
	public void waitTillUserNameLoggedIN()
	{
		wait.until(ExpectedConditions.visibilityOf(userNameLoggedIn.get(0)));
	}
	
	public void verifyShoppingCartQty(String expQty) throws IOException
	{
		String actCartQty = shoppingCartQty.getText().replaceAll("[^0-9]", "");
		verifyActualVsExpected(actCartQty, expQty, "Cart Quantity");
	}
	
	public void clickLogout() throws IOException
	{
		click(logOut,"Log Out");
	}
	
	public void mouseHoverMainMenu(String menu) throws IOException
	{
		moveToElement(productMenu(driver, menu), menu+" Main menu");
	}
	
	public void clickubMenu(String submenu) throws IOException
	{
		click(productSubListMenu(driver, submenu),submenu+" Sub Menu");
	}
	
	
	public void verifyUserLoggedOut() throws IOException
	{
		verifyElementDisp(logIn, "Log in");
		
		
	}
	
	

}
