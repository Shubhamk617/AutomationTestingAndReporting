package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class ProductListingPage {
	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"center_column\"]/div[2]/div[2]/form/button/span/strong")
	WebElement compareCount;
	@FindBy(xpath = "//*[@id=\"list\"]/a")
	WebElement listViewButton;
	@FindBy(xpath = "//*[@id=\"center_column\"]/ul/li[2]/div/div/div[3]/div/div[2]/a[2]")
	WebElement prod;
	@FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
	WebElement signInButt;

	public ProductListingPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public String getCompareCount() {
		return compareCount.getText();
	}

	public ProductListingPage getListView() {
		listViewButton.click();
		return this;
	}

	public ProductListingPage addToCompare() throws Exception {
		List<WebElement> products = driver.findElements(By.xpath("//*[@id=\"center_column\"]/ul/li"));
		System.out.println("Number of Products :" + products.size());
		for (int i = 1; i <= products.size(); i++) {
			String xpath = "//*[@id=\"center_column\"]/ul/li[" + i + "]/div/div/div[3]/div/div[3]/div[2]/a";
			driver.findElement(By.xpath(xpath)).click();
			Reporter.log(i + "th item added to cart", true);
			Thread.sleep(1000);
		}
		return this;
	}

	public ProductDetailsPage clickOnProduct() {
		prod.click();
		return new ProductDetailsPage(driver);
	}

	public String getSignInText() {
		return signInButt.getText();
	}
}
