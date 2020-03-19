package configurations;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class OpenCloseBrowser {

	public static WebDriver driver;
	public WebDriverWait wait;
	public ReadConfig rc = new ReadConfig();
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite(groups = "Sanity")
	public void beforeSuite() {
		extent = new ExtentReports("ExtentReports//Test_Report.html", true);
		extent.loadConfig(new File("extent-config.xml"));
		extent.addSystemInfo("Environment", "Selenium-Training");
	}

	@BeforeTest
	public void openChrome() {
		System.setProperty("webdriver.chrome.driver", rc.getDriverPath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(rc.getImplicitlyWait(), TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, rc.getExplicitWait());

	}


	@BeforeMethod(groups = "Sanity")
	public void beforeMethod(Method method) {
		test = extent.startTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName()); 
		test.assignAuthor("Shubham Kumar");
	}
	@AfterMethod
	public static void PublishSnapShots(ITestResult result) throws Exception
	{
		String fn1=result.getMethod().getMethodName();
		String fn=result.getTestClass().getName()+"_"+fn1;
		if(!result.isSuccess())
		{
			System.setProperty("org.uncommons.reportng.escape-output", "false");			
			String FileName=fn+new SimpleDateFormat("dd-MM-yyyy_hh_mm_ss").format(new Date());
			File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String projectPath=System.getProperty("user.dir");
			System.out.println(projectPath);
			String dest=projectPath+"\\SnapShots\\"+FileName+".jpg";
			File destFile=new File(dest) ;
			FileUtils.copyFile(srcFile, destFile);
			String destPath=destFile.getAbsolutePath();
			destPath=destPath.replace('\\', '/');
			destPath="file:///"+destPath;
			String rprt="<Html><Body><p><font color=\"red\">Method " +fn + "   FAILED <a href=\"" +destPath+ "\" >SnapShot</a></p></Body></Html>";
			Reporter.log(rprt);			
			test.log(LogStatus.FAIL, "Test : " +  fn + " - Failed","Snapshot at : " + rprt);			
		}
		else
		{
			Reporter.log("<p><font color=\"green\">Method " +fn + "   PASSED</p>");
			test.log(LogStatus.PASS, "Test Passed : - " + fn);		
		}
	}
	
	@AfterTest
	public void closeChrome() {
		driver.quit();
		extent.endTest(test);
		extent.flush();
	}

}
