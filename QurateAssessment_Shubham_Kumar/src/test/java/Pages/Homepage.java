package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Homepage {
	private WebDriver driver;

	@FindBy(xpath = "//div[@id='block_top_menu']/ul/li[2]/a[@title='Dresses']")
	WebElement dresses;
	@FindBy(xpath = "//div[@id='block_top_menu']/ul/li[2]/ul//a[@title='Summer Dresses']")
	WebElement summerDresses;
	@FindBy(xpath = "//*[@id=\"header_logo\"]/a/img")
	WebElement pageLogo;
	@FindBy(id = "search_query_top")
	WebElement searchInput;
	@FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/span/strong")
	WebElement phonenumber;
	@FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
	WebElement signInHoverText;

	public Homepage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public Homepage openHomepage(String url) {
		driver.navigate().to(url);
		return this;
	}

	public Homepage moveToElement() {
		Actions builder = new Actions(driver);
		builder.moveToElement(dresses).build().perform();
		return this;
	}

	public ProductListingPage clickSummerDresses() {
		summerDresses.click();
		return new ProductListingPage(driver);
	}

	public WebElement getPageLogo() {
		return pageLogo;
	}

	public String getPlaceholderText() {
		return searchInput.getAttribute("placeholder");
	}

	public String getPhoneNumber() {
		return phonenumber.getText();
	}

	public String getsignInHoverText() {
		return signInHoverText.getAttribute("title");
	}

}
