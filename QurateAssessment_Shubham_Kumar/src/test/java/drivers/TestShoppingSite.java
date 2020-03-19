package drivers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.AddressPage;
import Pages.AuthenticationPage;
import Pages.ConfirmPage;
import Pages.Homepage;
import Pages.OrderSuccessfullPage;
import Pages.PaymentMethodPage;
import Pages.ProductDetailsPage;
import Pages.ProductListingPage;
import Pages.ShippingPage;
import Pages.ShoppingCartSummaryPage;
import configurations.OpenCloseBrowser;

public class TestShoppingSite extends OpenCloseBrowser {

	Homepage homepage;
	ProductListingPage plp;
	ProductDetailsPage pdp;
	ShoppingCartSummaryPage csp;
	AuthenticationPage ap;
	AddressPage addrp;
	ShippingPage sp;
	PaymentMethodPage pmp;
	ConfirmPage cp;
	OrderSuccessfullPage osp;
	SoftAssert sAssert = new SoftAssert();
	public String menuItemArray[] = new String[3];

	@BeforeMethod
	public void initElem() {
		homepage = PageFactory.initElements(driver, Homepage.class);
		plp = PageFactory.initElements(driver, ProductListingPage.class);
		pdp = PageFactory.initElements(driver, ProductDetailsPage.class);
		csp = PageFactory.initElements(driver, ShoppingCartSummaryPage.class);
		ap = PageFactory.initElements(driver, AuthenticationPage.class);
		addrp = PageFactory.initElements(driver, AddressPage.class);
		sp = PageFactory.initElements(driver, ShippingPage.class);
		pmp = PageFactory.initElements(driver, PaymentMethodPage.class);
		cp = PageFactory.initElements(driver, ConfirmPage.class);
		osp = PageFactory.initElements(driver, OrderSuccessfullPage.class);
	}

	@Test(priority = 0, description = "Opens The Homepage and verifies the title")
	public void openHomepage() throws Exception {
		homepage.openHomepage(rc.getApplicationUrl());
		wait.until(ExpectedConditions.titleContains("My Store"));
		Reporter.log("Site Opened", true);
		Thread.sleep(1000);
	}

	@Test(dependsOnMethods = { "openHomepage" }, description = "Verifies the page logo is displayed or not")
	public void verifyPageLogo() {
		sAssert.assertTrue(homepage.getPageLogo().isDisplayed());
		Reporter.log("Homepage Logo Is Displayed", true);
		sAssert.assertAll();
	}

	@Test(dependsOnMethods = { "openHomepage" }, description = "verifies if placehoder contans the text \"Search\"")
	public void verifyPlaceHolderText() {
		sAssert.assertEquals(homepage.getPlaceholderText(), "Search");
		Reporter.log("PlaceHolder text Contains Search", true);
		sAssert.assertAll();
	}

	@Test(dependsOnMethods = { "openHomepage" }, description = "Veifies if the cart shows Empty Status")
	public void verifyEmptyCart() {
		String cartSpan = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/span[5]"))
				.getText();
		sAssert.assertTrue(cartSpan.contains("empty"));
		Reporter.log("Cart Shows Empty", true);
		sAssert.assertAll();
	}

	@Test(dependsOnMethods = { "openHomepage" }, description = "Verifies if the phone Number is displayed Correctly")
	public void verifyPhoneNumber() {
		sAssert.assertEquals(homepage.getPhoneNumber(), "0123-456-789");
		Reporter.log("Phonenumbers are matching", true);
		sAssert.assertAll();
	}

	@Test(dependsOnMethods = { "openHomepage" }, description = "Verifies SignInHoverText")
	public void verifySignInHover() {
		sAssert.assertEquals(homepage.getsignInHoverText(), "Log in to your customer account");
		Reporter.log("SignIn hoverText are same as required", true);
		sAssert.assertAll();
	}

	@Test(dependsOnMethods = { "openHomepage" }, description = "Verifies Menu Limks")
	public void verifyMenuLinks() {
		List<WebElement> menuElements = driver.findElements(By.xpath(("//div[@id='block_top_menu']/ul/li")));
		sAssert.assertEquals(menuElements.size(), 3);
		for (int i = 1; i <= menuElements.size(); i++) {
			String menuElementXpath = "//*[@id=\"block_top_menu\"]/ul/li[" + i + "]/a";
			menuItemArray[i - 1] = driver.findElement(By.xpath(menuElementXpath)).getText();
			System.out.println(driver.findElement(By.xpath(menuElementXpath)).getText());
			// System.out.println(menuItemArray[i-1]);
		}
		sAssert.assertAll();
	}

	@Test(priority = 1, description = "Hovers On Dresses Menu Item")
	public void hoverOverDresses() throws Exception {
		homepage.moveToElement();
		Reporter.log("Mouse Moved", true);
		Thread.sleep(1000);
	}

	@Test(priority = 2, description = "Clicks On Summer Dresses")
	public void ClickSummerDresses() throws Exception {
		homepage.clickSummerDresses();
		wait.until(ExpectedConditions.titleContains("Summer Dresses"));
		Reporter.log("Summer Dresses Clicked", true);
		Thread.sleep(1000);
	}

	String menuItemArray2[] = new String[3];

	@Test(dependsOnMethods = { "ClickSummerDresses" }, description = "Compares the menu items")
	public void verifyMenuBars() {
		List<WebElement> menuElements = driver.findElements(By.xpath(("//div[@id='block_top_menu']/ul/li")));
		sAssert.assertEquals(menuElements.size(), 3);
		for (int i = 1; i <= menuElements.size(); i++) {
			String menuElementXpath = "//*[@id=\"block_top_menu\"]/ul/li[" + i + "]/a";
			menuItemArray2[i - 1] = driver.findElement(By.xpath(menuElementXpath)).getText();
		}
		sAssert.assertEquals(menuItemArray, menuItemArray2);
		Reporter.log("Menu Items Are Same", true);
		sAssert.assertAll();
	}

	@Test(dependsOnMethods = { "ClickSummerDresses" }, description = "verifies if signout link is available")
	public void verifySignOutLink() {
		sAssert.assertNotEquals(plp.getSignInText(), "Sign Out");
		Reporter.log("SignOut Is not yet displayed", true);
		sAssert.assertAll();
	}

	@Test(priority = 3, dependsOnMethods = { "ClickSummerDresses" }, description = "gets The ListView")
	public void getListView() throws Exception {
		plp.getListView();
		Reporter.log("List View Clicked", true);
		Thread.sleep(1000);
	}

	@Test(priority = 4, dependsOnMethods = { "ClickSummerDresses" }, description = "verifies compare count")
	public void verifyCompareCount() throws Exception {
		Reporter.log("Compare Value Before :" + plp.getCompareCount(), true);
		sAssert.assertTrue(plp.getCompareCount().contains(String.valueOf(0)));
		Reporter.log("Checked for before value as 0", true);
		plp.addToCompare();
		Thread.sleep(1000);
		Reporter.log("Compare Value After :" + plp.getCompareCount(), true);
		sAssert.assertTrue(plp.getCompareCount().contains(String.valueOf(3)));
		Reporter.log("Checked for after value as 3", true);
		sAssert.assertAll();
	}

	@Test(priority = 5, description = "Clicks On product with highest price")
	public void clickProdWithHighestPrice() throws Exception {
		plp.clickOnProduct();
		wait.until(ExpectedConditions.titleContains("Printed Summer Dress"));
		Reporter.log("Product with highest amount clicked", true);
		Thread.sleep(1000);
	}

	String prodDetails, prodPrice;
	String atcprodDetails, atcprodPrice;

	@Test(priority = 6, dependsOnMethods = {
			"clickProdWithHighestPrice" }, description = "Add to cart and store prod details and prices")
	public void addToCart() throws Exception {
		prodDetails = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/div[3]/h1")).getText();
		prodPrice = driver.findElement(By.id("our_price_display")).getText();
		System.out.println("Earlier prod details :" + prodDetails + "\n" + prodPrice);
		pdp.selectMSize();
		Thread.sleep(1000);
		pdp.clickAddToCart();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("layer_cart"))));
		sAssert.assertTrue(driver.findElement(By.id("layer_cart")).isDisplayed());
		Reporter.log("Add to cart popup is displayed", true);
		atcprodDetails = driver.findElement(By.id("layer_cart_product_title")).getText();
		atcprodPrice = driver.findElement(By.id("layer_cart_product_price")).getText();
		System.out.println("After cart prod details :" + atcprodDetails + "  " + atcprodPrice);
		sAssert.assertEquals(atcprodDetails, prodDetails);
		Reporter.log("Product Details are same", true);
		Reporter.log("Product price are same", true);
		sAssert.assertAll();
	}

	@Test(priority = 7, dependsOnMethods = { "addToCart" }, description = "Proceeds to checkout from cart page")
	public void clickProceedToCart() {
		pdp.proceedToCheckout();
		wait.until(ExpectedConditions.titleIs("Order - My Store"));
		Reporter.log("Proceeded from add to cart page", true);
	}

	@Test(dependsOnMethods = { "clickProceedToCart" }, description = "Verifies presence of 1 product in Cart Logo")
	public void verifyCartNumber() {
		sAssert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/span[1]")).getText(),
				String.valueOf(1));
		sAssert.assertAll();
	}

	@Test(priority = 8, dependsOnMethods = {
			"verifyCartNumber" }, description = "Proceeds to checkout from cart summary page")
	public void proceedToCheckout() {
		csp.proceedToCheckout();
		wait.until(ExpectedConditions.titleIs("Login - My Store"));
		Reporter.log("Proceeded from cart summary page", true);
	}

	@Test(priority = 9, dependsOnMethods = {
			"proceedToCheckout" }, description = "Verifies that user logs in succesfully")
	public void loginUser() throws Exception {
		ap.enterUserName(rc.getUsername());
		Reporter.log("Username Entered", true);
		Thread.sleep(500);

		ap.enterPassWord(rc.getPassword());
		Reporter.log("Password Entered", true);
		Thread.sleep(500);

		ap.clickLoginButton();
		Reporter.log("Log In Button Clicked", true);
		wait.until(ExpectedConditions.titleIs("Order - My Store"));
		Reporter.log("User Logged In Successfully", true);
	}

	@Test(priority = 10, dependsOnMethods = {
			"loginUser" }, description = "Checks that log out is available now and proceeds")
	public void checkLogoutButtonAndProceed() {
		sAssert.assertEquals(driver.findElement(By.cssSelector("a[title='Log me out']")).getText(), "Sign out");
		addrp.proceedToCheckout();
		wait.until(ExpectedConditions.titleIs("Order - My Store"));
		Reporter.log("Proceeded from address page", true);
		sAssert.assertAll();
	}

	@Test(dependsOnMethods = { "checkLogoutButtonAndProceed" }, description = "checks that checkbox is not selected")
	public void verifyCheckbox() {
		WebElement chBox = driver.findElement(By.id("cgv"));
		sAssert.assertFalse(chBox.isSelected());
		Reporter.log("Checkbox Is Not Selected", true);
		sAssert.assertAll();
	}

	@Test(dependsOnMethods = { "checkLogoutButtonAndProceed" }, description = "tries to proceed and checks for popup")
	public void proceedAndPopupCheck() {
		sp.proceedToCheckout();
		sAssert.assertTrue(driver.findElement(By.xpath("//*[@id=\"order\"]/div[2]/div")).isDisplayed());
		Reporter.log("Popup is shown ", true);
		driver.findElement(By.cssSelector("a[title='Close']")).click();
		Reporter.log("Popup is dismissed ", true);
		sAssert.assertAll();
	}

	@Test(priority = 11, dependsOnMethods = { "verifyCheckbox" }, description = "Checks the TOS and proceeds")
	public void checkTOsAndProceed() {
		sp.checkTermsOfService();
		Reporter.log("TermsOfService is now checked ", true);
		sp.proceedToCheckout();
		wait.until(ExpectedConditions.titleIs("Order - My Store"));
		Reporter.log("Proceeded to Payments Page ", true);
	}

	@Test(dependsOnMethods = { "checkTOsAndProceed" }, description = "Verifies Product name, price and size selected")
	public void verifyDetails() throws Exception {
		sAssert.assertEquals(driver.findElement(By.cssSelector(".address_233586.cart_item.odd  p > a")).getText(),
				prodDetails);
		Reporter.log("Same product details confirmed", true);
		sAssert.assertEquals(driver.findElement(By.cssSelector(".cart_total > .price")).getText(), prodPrice);
		Reporter.log("Same product price confirmed", true);
		sAssert.assertTrue(driver.findElement(By.cssSelector("small:nth-of-type(2) > a")).getText().contains("M"));
		Reporter.log("Same product size confirmed", true);
		sAssert.assertAll();
	}

	@Test(dependsOnMethods = { "checkTOsAndProceed" }, description = "Verifies Product name, price and size selected")
	public void verifyPaymentDetails() throws Exception {
		WebElement paymentModes = driver.findElement(By.id("HOOK_PAYMENT"));
		List<WebElement> paymentGateways = paymentModes.findElements(By.tagName("a"));
		String paymentOption[] = { "Pay by bank wire", "Pay by check" };
		int c = 0;
		for (WebElement paymentGateway : paymentGateways) {
			System.out.println(paymentGateway.getText());
			sAssert.assertTrue(paymentGateway.getText().contains(paymentOption[c]));
			System.out.println(paymentOption[c] + ":-> payment Method Available");
			c++;
		}
		sAssert.assertAll();
	}

	@Test(priority = 11, dependsOnMethods = { "verifyPaymentDetails" }, description = "Proceeds to Payment and Confirm")
	public void proceedToPayment() throws Exception {
		pmp.clickPaymentMethod();
		wait.until(ExpectedConditions.titleIs("My Store"));
		Reporter.log("Payment Method Selected", true);
		Thread.sleep(1000);

		cp.clickOnConfirmation();
		wait.until(ExpectedConditions.titleIs("Order confirmation - My Store"));
		Reporter.log("Confirmed for the order", true);
		Thread.sleep(1000);

		osp.printOrderSuccessfulMessage();
		Reporter.log("Order Successfully Placed", true);
		Thread.sleep(1000);
	}

}
