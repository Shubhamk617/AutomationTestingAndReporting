package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShippingPage {
	private WebDriver driver;

	@FindBy(id ="cgv" )
	WebElement checkBox;
	@FindBy(xpath = "//*[@id=\"form\"]/p/button")
	WebElement proceedToCheckoutButton;
	public ShippingPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public ShippingPage checkTermsOfService() {
		checkBox.click();
		return this;
	}

	public PaymentMethodPage proceedToCheckout() {
		proceedToCheckoutButton.click();
		return new PaymentMethodPage(driver);
	}

}
