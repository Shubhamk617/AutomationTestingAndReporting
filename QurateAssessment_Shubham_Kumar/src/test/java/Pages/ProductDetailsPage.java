package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductDetailsPage {
	private WebDriver driver;

	@FindBy(xpath = "//p[@id='add_to_cart']/button[@name='Submit']")
	WebElement addToCartButton;
	
	@FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")
	WebElement proceedToCheckoutButton;
	public ProductDetailsPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public ProductDetailsPage selectMSize() {
		Select size = new Select(driver.findElement(By.id("group_1")));
		size.selectByVisibleText("M");
		return this;
	}

	public ProductDetailsPage clickAddToCart() {
		addToCartButton.click();
		return this;
	}

	public ShoppingCartSummaryPage proceedToCheckout() {
		proceedToCheckoutButton.click();
		return new ShoppingCartSummaryPage(driver);
	}
}
