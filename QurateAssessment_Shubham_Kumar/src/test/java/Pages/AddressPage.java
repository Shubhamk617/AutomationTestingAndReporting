package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressPage {
	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"center_column\"]/form/p/button")
	WebElement proceedToCheckoutButton;

	public AddressPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public ShippingPage proceedToCheckout() {
		proceedToCheckoutButton.click();
		return new ShippingPage(driver);
	}

}
