package testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import Base.Base;

public class ProductsTest extends Base {

	
	@Test
	public void productsPageUITest() throws IOException, InterruptedException
	{
		createTest("Products Page UI Test");
		navigateAppUrl();
		main().homepage().verifyDemoWebShopTitle();
		main().headerPage().clickLogInLink();
		String userName = excelReader.getCellData(1, "UserName", "Login");
		String password = excelReader.getCellData(1, "Password", "Login");
		main().loginPage().enterUserName(userName);
		main().loginPage().enterPassword(password);
		main().loginPage().clickSignIn();

		main().headerPage().mouseHoverMainMenu("Computers");
		main().headerPage().clickubMenu("Desktops");
		main().productPage().verifyBreadCrumb("HOME / COMPUTERS / DESKTOPS");
		main().productPage().verifyPageTitle("Desktops");
		
		main().productPage().verifyGridIsSelectedByDefault();
		
		main().productPage().selectSortByOption("Price: Low to High");
		main().productPage().verifyProductsSortedByPriceLowToHigh();
		
		main().headerPage().clickLogout();
		main().headerPage().verifyUserLoggedOut();
		
	}
	
}
