package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthenticationPage {
	private WebDriver driver;

	@FindBy(id = "email")
	WebElement email;
	@FindBy(id = "passwd")
	WebElement passwd;
	@FindBy(xpath = "//*[@id=\"SubmitLogin\"]")
	WebElement LoginButton;

	public AuthenticationPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public AuthenticationPage enterUserName(String username) {
		email.sendKeys(username);
		return this;
	}

	public AuthenticationPage enterPassWord(String password) {
		passwd.sendKeys(password);
		return this;
	}

	public AddressPage clickLoginButton() {
		LoginButton.click();
		return new AddressPage(driver);
	}
}
