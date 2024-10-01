package Pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.GenericUIFunctions;

public class ProductsPage extends GenericUIFunctions {
	RemoteWebDriver driver;
	WebDriverWait wait;
	
	public ProductsPage(RemoteWebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(id ="products-viewmode") private WebElement viewAsDropDown;
	@FindBy(id ="products-orderby") private WebElement sortByDropDown;
	
	@FindBy(xpath ="//div[@class='breadcrumb']") private WebElement breadCrumb;
	
	@FindBy(xpath ="//div[@class='page-title']/h1") private WebElement pageTitle;
	private List<WebElement> priceListOfProducts()
	{
		return driver.findElements(By.xpath("//span[@class='price actual-price']"));
	}
	
	
	public void verifyGridIsSelectedByDefault() throws IOException
	{
		WebElement selOption = retrieveSelectedOption(viewAsDropDown, "View as drop down");
		
		verifyActualVsExpected("Grid",selOption.getText().strip(), "View as default selected option");
	}
	
	public void verifyBreadCrumb(String expBreadCrumb) throws IOException
	{
		
		String actBreadCrumb =getText(breadCrumb, "Breadcrumb");
		
		verifyActualVsExpected(expBreadCrumb, actBreadCrumb.toUpperCase(), "Breadcrumb");
	}
	
	public void verifyPageTitle(String expPageTitle) throws IOException
	{
		String actPageTitle = getText(pageTitle," Page title");
		verifyActualVsExpected(expPageTitle,actPageTitle , "Page Title");
	}
	
	public void selectSortByOption(String expOption) throws IOException, InterruptedException
	{
		selectValueFromList(sortByDropDown, expOption, "Sort By");
		Thread.sleep(2000);
	}
	
	public void verifyProductsSortedByPriceLowToHigh() throws IOException
	{
		List<WebElement> productsPrice  = priceListOfProducts();
		
		ArrayList<Double> priceOfProd = new ArrayList<Double>();
		for(WebElement price:productsPrice)
		{
			priceOfProd.add(Double.parseDouble(price.getText().trim()));
		}
		
		System.out.println("Price of Products in current order "+priceOfProd);
		
		ArrayList<Double> sortedPrice = new ArrayList<Double>(priceOfProd);
		Collections.sort(sortedPrice);
		
		System.out.println("Sorting the Products price in current order "+sortedPrice);
		
		verifyActualVsExpected(sortedPrice, priceOfProd, "Products Price after sorting by Price Low to High");
	}
}
