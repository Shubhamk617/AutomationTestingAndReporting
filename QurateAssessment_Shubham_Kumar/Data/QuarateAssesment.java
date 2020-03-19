
package drivers;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;

import configurations.OpenCloseBrowser;

public class QuarateAssesment extends OpenCloseBrowser {

	@Test
	public void functionalityQuarate() throws Exception {
		driver.navigate().to(rc.getApplicationUrl());
		wait.until(ExpectedConditions.titleContains("My Store"));
		Reporter.log("Site Opened", true);
		Thread.sleep(1000);
		System.out.println("Placeholder Text: "
				+ driver.findElement(By.xpath("/html//input[@id='search_query_top']")).getAttribute("placeholder"));
		System.out.println("Cart Status :" + driver.findElement(By.xpath(
				"/html//header[@id='header']/div[3]/div[@class='container']//a[@title='View my shopping cart']/span[@class='ajax_cart_no_product']"))
				.getText());
		System.out.println("Phone Number : "
				+ driver.findElement(By.xpath("//*[@id=\"block_contact_infos\"]/div/ul/li[2]/span")).getText());
		System.out.println("Login Title : " + driver
				.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).getAttribute("title"));
		List<WebElement> menuElements = driver.findElements(By.xpath(("//div[@id='block_top_menu']/ul/li")));
		System.out.println("Number of links in Top Menu :" + menuElements.size());

		int i = 0;
		for (WebElement menuElement : menuElements) {
			System.out.println(
					menuElement.findElement(By.tagName("//*[@id=\"block_top_menu\"]/ul/li[" + i + "]/a")).getText());
			i++;
		}

		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li[2]/a[@title='Dresses']")))
				.build().perform();
		Reporter.log("Mouse Moved", true);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li[2]/ul//a[@title='Summer Dresses']")).click();
		Reporter.log("Summer Dresses Clicked", true);
		wait.until(ExpectedConditions.titleContains("Summer Dresses"));
		Thread.sleep(1000);
		System.out.println("Compare Value Before : " + driver
				.findElement(By.xpath("//*[@id=\"center_column\"]/div[2]/div[2]/form/button/span/strong")).getText());
		driver.findElement(By.xpath("//*[@id=\"list\"]/a")).click();
		Reporter.log("List View Clicked", true);

		List<WebElement> products = driver.findElements(By.xpath("//*[@id=\"center_column\"]/ul/li"));
		System.out.println("Number of Products :" + products.size());
		Iterator<WebElement> itr = products.iterator();
		while (itr.hasNext()) {
			String xpath = "//*[@id=\"center_column\"]/ul/li[" + i + "]/div/div/div[3]/div/div[3]/div[2]/a";
			driver.findElement(By.xpath(xpath)).click();
			Reporter.log(i + "th item added to cart", true);

			wait.until(ExpectedConditions.textToBePresentInElement(
					driver.findElement(By.xpath("//*[@id=\\\"center_column\\\"]/div[2]/div[2]/form/button/span")),
					String.valueOf(i)));

			i++;
			Thread.sleep(1000);
			if (i == products.size() + 1)
				break;
		}

		System.out.println("Compare Value After : " + driver
				.findElement(By.xpath("//*[@id=\"center_column\"]/div[2]/div[2]/form/button/span/strong")).getText());
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div/div/div[3]/div/div[2]/a[1]/span")).click();
		Reporter.log("Add to Cart Clicked", true);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"layer_cart\"]")));
		Reporter.log("Layer Showed", true);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();
		Reporter.log("Proceed to checkout clicked", true);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"product_6_31_0_0\"]/td[2]/small[2]/a")).click();
		Reporter.log("Size check and ad clicked", true);
		Thread.sleep(1000);
		Select size = new Select(driver.findElement(By.id("group_1")));
		size.selectByVisibleText("M");
		Reporter.log("M Size Selected", true);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button")).click();
		Reporter.log("Add to cart Clicked", true);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"layer_cart\"]")));
		Reporter.log("Layer Showed", true);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();
		Reporter.log("Proceed to checkout clicked", true);
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("//table[@id='cart_summary']/tbody/tr[1]//a[@title='Delete']/i[@class='icon-trash']")).click();
		Reporter.log("Old Sized Item Deleted", true);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='center_column']//a[@title='Proceed to checkout']/span")).click();
		Reporter.log("Proceed to checkout clicked", true);
		Thread.sleep(1000);
		driver.findElement(By.id("email")).sendKeys(rc.getUsername());
		Reporter.log("Username Entered", true);
		Thread.sleep(1000);
		driver.findElement(By.id("passwd")).sendKeys(rc.getPassword());
		Reporter.log("Password Entered", true);
		Thread.sleep(1000);
		driver.findElement(By.id("SubmitLogin")).click();
		Reporter.log("Login Button Clicked", true);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button")).click();
		Reporter.log("Adress updated and procedded", true);
		Thread.sleep(1000);
		driver.findElement(By.id("cgv")).click();
		Reporter.log("Terms of Service Aggreed", true);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"form\"]/p/button")).click();
		Reporter.log("Terms of service Agreed and procedded", true);
		Thread.sleep(1000);
		driver.findElement(By.xpath(
				"/html//div[@id='HOOK_PAYMENT']//a[@title='Pay by bank wire']/span[.='(order processing will be longer)']"))
				.click();
		Reporter.log("Payment mode selected and proceeded", true);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();
		Reporter.log("Order Confirmation Clicked", true);
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p")).getText());
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).click();
		Reporter.log("User Logged Out", true);
		Thread.sleep(1000);
		Thread.sleep(10000);
	}

}
