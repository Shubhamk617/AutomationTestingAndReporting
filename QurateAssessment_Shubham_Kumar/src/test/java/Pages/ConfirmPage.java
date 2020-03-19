package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmPage {
	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"cart_navigation\"]/button")
	WebElement confirmationButton;

	public ConfirmPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public OrderSuccessfullPage clickOnConfirmation() {
		confirmationButton.click();
		return new OrderSuccessfullPage(driver);
	}
}
