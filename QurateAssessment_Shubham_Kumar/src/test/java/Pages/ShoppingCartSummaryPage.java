package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartSummaryPage {
	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"center_column\"]/p[2]/a[1]")
	WebElement proceedToCheckoutButton;

	public ShoppingCartSummaryPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public AuthenticationPage proceedToCheckout() {
		proceedToCheckoutButton.click();
		return new AuthenticationPage(driver);
	}
}
