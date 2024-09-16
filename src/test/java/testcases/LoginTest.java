package testcases;

import org.apache.log4j.chainsaw.Main;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.Base;

public class LoginTest extends Base {

	
	@Test
	public void demoWebShopLogin() throws Exception
	{
		createTest("Login Test");
		
		navigateAppUrl();
		main().homepage().verifyDemoWebShopTitle();
		main().headerPage().clickLogInLink();
		String userName = excelReader.getCellData(1, "UserName", "Login");
		String password = excelReader.getCellData(1, "Password", "Login");
		System.out.println("User Name "+userName+"---password "+password);
		main().loginPage().enterUserName(userName);
		main().loginPage().enterPassword(password);
		main().loginPage().clickSignIn();
		main().headerPage().waitTillUserNameLoggedIN();
		
		main().headerPage().verifyUserNameLoggedIN(excelReader.getCellData(1, "UserName", "Login"));
		
		main().headerPage().verifyShoppingCartQty("0");
		main().headerPage().clickLogout();
		
	}
	
	
}
