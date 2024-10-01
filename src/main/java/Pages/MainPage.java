package Pages;

import org.openqa.selenium.remote.RemoteWebDriver;

public class MainPage {
	static RemoteWebDriver driver;
	HeaderPage header = null;
	LoginPage loginpage = null;
	HomePage homepage = null;
	ProductsPage productPage = null;
	
	public MainPage (RemoteWebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public HeaderPage headerPage()
	{
		if(header == null)
		{
			header = new HeaderPage(driver);
		}
		return header;
	}
	public LoginPage loginPage()
	{
		if(loginpage == null)
		{
			loginpage = new LoginPage(driver);
		}
		return loginpage;
	}
	
	public HomePage homepage()
	{
		if(homepage == null)
		{
			homepage = new HomePage(driver);
		}
		return homepage;
	}
	
	public ProductsPage productPage()
	{
		if(productPage == null)
		{
			productPage = new ProductsPage(driver);
		}
		return productPage;
	}
	
	
	
}
