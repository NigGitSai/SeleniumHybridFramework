package Pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.GenericUIFunctions;

public class LoginPage extends GenericUIFunctions {
	RemoteWebDriver driver;
	WebDriverWait wait;
	public LoginPage(RemoteWebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(id ="Email") private WebElement userName;
	@FindBy(id ="Password") private WebElement password;
	@FindBy(xpath ="//input[@value='Log in']") private WebElement logIn;

	public void enterUserName(String userNameinp) throws IOException
	{
		enter(userName,userNameinp, "UserName");
	}
	
	public void enterPassword(String passwordInp) throws IOException
	{
		enter(password,passwordInp, "Password");
	}
	
	public void clickSignIn() throws IOException
	{
		click(logIn, "Sign IN");
		
	}
}
