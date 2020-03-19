package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderSuccessfullPage {
	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"center_column\"]/div/p")
	WebElement confirmationMessage;
	public OrderSuccessfullPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public OrderSuccessfullPage printOrderSuccessfulMessage() {
		System.out.println(confirmationMessage.getText());
		return this;
	}
}
