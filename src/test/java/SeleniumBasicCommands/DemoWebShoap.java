package SeleniumBasicCommands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.Table.Cell;

public class DemoWebShoap {
	public WebDriver driver;

	public void testInitialise(String browser) {
		if (browser.equals("chrome")) {
			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(ops);
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			try {
				throw new Exception("Invalid browser");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@BeforeMethod
	public void setUp() {
		testInitialise("chrome");
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("./Screenshots/" + result.getName() + ".png"));
		}
		driver.close();
		// driver.quit();
	}

	@Test
	public void TC_001_verifyObsquraTitle() {
		driver.get("https://selenium.obsqurazone.com/index.php");

		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		String expectedTitle = "Obsqura Testing1";
		Assert.assertEquals(actualTitle, expectedTitle, "Invalid Title Found");
	}

	@Test
	public void TC_002_verifyDemoShopLoginPage() {
		driver.get("https://demowebshop.tricentis.com/");
		WebElement loginLink = driver.findElement(By.xpath("//a[text()='Log in']"));
		loginLink.click();
		WebElement emailField = driver.findElement(By.name("Email"));
		WebElement passwordField = driver.findElement(By.name("Password"));
		emailField.sendKeys("jun123@gmail.com");
		passwordField.sendKeys("test@123");
		WebElement loginbutton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
		loginbutton.click();
	}

	@Test
	public void TC_003_verifyDemoPageRegistration() throws InterruptedException {
		driver.get("https://demowebshop.tricentis.com/");
		WebElement registerLink = driver.findElement(By.xpath("//a[@class='ico-register']"));
		registerLink.click();
		List<WebElement> gender = driver.findElements(By.xpath("//input[@name='Gender']"));
		selectGender("F", gender);
		WebElement firstName = driver.findElement(By.xpath("//input[@id='FirstName']"));
		WebElement lastName = driver.findElement(By.xpath("//input[@id='LastName']"));
		WebElement email = driver.findElement(By.xpath("//input[@id='Email']"));
		WebElement password = driver.findElement(By.xpath("//input[@id='Password']"));
		WebElement confirmpaassword = driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
		WebElement registerButton = driver.findElement(By.xpath("//input[@id='register-button']"));
		firstName.sendKeys("juno");
		lastName.sendKeys("jose");
		email.sendKeys("junojose123@yopmail.com");
		password.sendKeys("Test123");
		confirmpaassword.sendKeys("Test123");
		registerButton.click();
		Thread.sleep(2000);
		WebElement valudationMail = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
		String actualMail = valudationMail.getText();
		String expectedMail = "junojose123@yopmail.com";
		Assert.assertEquals(actualMail, expectedMail, "Invalid Mail found");

	}

	public void selectGender(String gen, List<WebElement> gender) {
		for (int i = 0; i < gender.size(); i++) {
			String genderValue = gender.get(i).getAttribute("value");
			if (genderValue.equals(gen)) {
				gender.get(i).click();
			}
		}

	}

	@Test
	public void TC_004_verifyTitleFromExcel() throws IOException {

		driver.get("https://demowebshop.tricentis.com/");
		String actualTitle = driver.getTitle();
		String excelPath = "\\src\\test\\resources\\TestData.xlsx";
		String sheetName = "HomePage";
		String expectedTile = ExcelUtility.readStringData(excelPath, sheetName, 1, 0);
		Assert.assertEquals(actualTitle, expectedTile, "Invalid Title found ");

	}

	@Test
	public void TC_005_verifyRegistrationFromExcelSheet() throws IOException
	{
		driver.get("https://demowebshop.tricentis.com/");
		WebElement registerLink = driver.findElement(By.xpath("//a[@class='ico-register']"));
		registerLink.click();
		String actualTitle = driver.getTitle();
		String excelPath = "\\src\\test\\resources\\TestData.xlsx";
		String sheetName = "RegisterPage";
		String expectedTitle=ExcelUtility.readStringData(excelPath, sheetName, 1, 0);
		Assert.assertEquals(actualTitle, expectedTitle,"Invalid Registration Title Found");
		//Assert.assertEquals(actualTitle, ExcelUtility.readStringData(excelPath, sheetName, 1, 0),"Invalid Registration Title Found");
		List<WebElement> gender = driver.findElements(By.xpath("//input[@name='Gender']"));
		selectGender(ExcelUtility.readStringData(excelPath, sheetName, 1, 1), gender);
		WebElement firstName = driver.findElement(By.xpath("//input[@id='FirstName']"));
		WebElement lastName = driver.findElement(By.xpath("//input[@id='LastName']"));
		WebElement email = driver.findElement(By.xpath("//input[@id='Email']"));
		WebElement password = driver.findElement(By.xpath("//input[@id='Password']"));
		WebElement confirmpassword = driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
		WebElement registerButton = driver.findElement(By.xpath("//input[@id='register-button']"));
		firstName.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 1, 2));
		lastName.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 1, 3));
		email.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 1, 4));
		password.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 1, 5));
		confirmpassword.sendKeys(ExcelUtility.readStringData(excelPath, sheetName, 1, 6));
		registerButton.click();
		WebElement validationMail = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
		String actualMail = validationMail.getText();
		Assert.assertEquals(actualMail, ExcelUtility.readStringData(excelPath, sheetName, 1, 4), "Invalid Mail found");

	}

}
