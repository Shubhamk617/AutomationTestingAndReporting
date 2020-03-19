package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentMethodPage {
	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")
	WebElement paymentMethodButton;
	public PaymentMethodPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public ConfirmPage clickPaymentMethod() {
		paymentMethodButton.click();
		return new ConfirmPage(driver);
	}
}
