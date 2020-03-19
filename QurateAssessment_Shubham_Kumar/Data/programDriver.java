package drivers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

public class programDriver extends OpenCloseBrowser {

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

	@Test
	public void testFunctionalities() throws Exception {
		homepage.openHomepage(rc.getApplicationUrl());
		wait.until(ExpectedConditions.titleContains("My Store"));
		Reporter.log("Site Opened", true);
		Thread.sleep(1000);

		homepage.moveToElement();
		Reporter.log("Mouse Moved", true);
		Thread.sleep(1000);

		homepage.clickSummerDresses();
		wait.until(ExpectedConditions.titleContains("Summer Dresses"));
		Reporter.log("Summer Dresses Clicked", true);
		Thread.sleep(1000);

		Reporter.log("Compare Value Before :" + plp.getCompareCount(), true);
		
		plp.getListView();
		Reporter.log("List View Clicked", true);
		Thread.sleep(1000);

		plp.addToCompare();
		
		Reporter.log("Compare Value After :" + plp.getCompareCount(), true);
		
		plp.clickOnProduct();
		Reporter.log("Product with highest amount clicked", true);
		Thread.sleep(1000);
		
		pdp.selectMSize();
		Reporter.log("M Size Selected", true);
		Thread.sleep(1000);
		
		pdp.clickAddToCart();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"layer_cart\"]")));
		Reporter.log("Added to Cart", true);
		Thread.sleep(1000);
		
		pdp.proceedToCheckout();
		Reporter.log("Proceeded from product details page", true);
		Thread.sleep(1000);
		
		csp.proceedToCheckout();
		Reporter.log("Proceeded from cart summary page", true);
		Thread.sleep(1000);
		
		ap.enterUserName(rc.getUsername());
		Reporter.log("Username Entered", true);
		Thread.sleep(500);
		
		ap.enterPassWord(rc.getPassword());
		Reporter.log("Password Entered", true);
		Thread.sleep(500);
		
		ap.clickLoginButton();
		Reporter.log("Log In Button Clicked", true);
		Thread.sleep(1000);
		
		addrp.proceedToCheckout();
		Reporter.log("Proceeded from address page", true);
		Thread.sleep(1000);
		
		sp.checkTermsOfService();
		Reporter.log("Checked Terms of Service", true);
		Thread.sleep(1000);
		
		sp.proceedToCheckout();
		Reporter.log("Proceeded from shipping page", true);
		Thread.sleep(1000);
		
		pmp.clickPaymentMethod();
		Reporter.log("Payment Method Selected", true);
		Thread.sleep(1000);
		
		cp.clickOnConfirmation();
		Reporter.log("Confirmed for the order", true);
		Thread.sleep(1000);
		
		osp.printOrderSuccessfulMessage();
		Reporter.log("Order Successfully Placed", true);
		Thread.sleep(1000);
			
	}WebElement paymentModes=driver.findElement(By.id("HOOK_PAYMENT"));
	List<WebElement> paymentGateways=paymentModes.findElements(By.tagName("a"));
	String paymentOption[]= {"Pay by bank wire","Pay by check"};
	Thread.sleep(3000);
	int c=0;
	for(WebElement paymentGateway: paymentGateways) {
		sAssert.assertTrue(paymentGateway.getText().contains(paymentOption[c]));
		Reporter.log(paymentOption[c]+" :-> paymode mode available");
		c++;
	}
	sAssert.assertAll();
}
