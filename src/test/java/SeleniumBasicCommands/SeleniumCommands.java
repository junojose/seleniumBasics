package SeleniumBasicCommands;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SeleniumCommands {
	private static final String WebElement = null;
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
	public void TC_002_verifySingleInputFieldMessage() {
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		WebElement messageField = driver.findElement(By.id("single-input-field"));
		WebElement showButton = driver.findElement(By.id("button-one"));
		WebElement message = driver.findElement(By.id("message-one"));
		messageField.sendKeys("data");
		showButton.click();
		String actualMessage = message.getText();
		System.out.println(actualMessage);
		String expectedMessage = "Your Message : data";
		Assert.assertEquals(actualMessage, expectedMessage, "Invalid Message Found");

	}

	@Test
	public void TC_003_verifyTwoInputFieldMessage() {
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		WebElement valueA = driver.findElement(By.id("value-a"));
		WebElement valueB = driver.findElement(By.id("value-b"));
		WebElement getTotalButton = driver.findElement(By.id("button-two"));
		WebElement totalMessage = driver.findElement(By.id("message-two"));
		valueA.sendKeys("2");
		valueB.sendKeys("4");
		getTotalButton.click();
		String actualTotal = totalMessage.getText();
		System.out.println(actualTotal);
		String expectedTotal = "Total A + B : 6";
		Assert.assertEquals(actualTotal, expectedTotal, "Invalid Total Message Found");

	}

	@Test
	public void TC_004_verifyLocators() throws InterruptedException {
		driver.get("https://demowebshop.tricentis.com/register");
		WebElement firstName = driver.findElement(By.id("FirstName"));
		WebElement lastName = driver.findElement(By.id("LastName"));
		WebElement email = driver.findElement(By.id("Email"));
		WebElement password = driver.findElement(By.id("Password"));
		WebElement confirmPassword = driver.findElement(By.id("ConfirmPassword"));
		WebElement registerButton = driver.findElement(By.name("register-button"));
		firstName.sendKeys("Juno");
		lastName.sendKeys("Jose");
		email.sendKeys("juno0009@gmail.com");
		password.sendKeys("test@123");
		confirmPassword.sendKeys("test@123");
		registerButton.click();
		Thread.sleep(3000);
		WebElement resultText = driver.findElement(By.className("result"));
		String actualText = resultText.getText();
		System.out.println(actualText);
		String expectedText = "Your registration completed";
		Assert.assertEquals(actualText, expectedText, "Invalid Text Found");

	}

	@Test
	public void TC_004_verifyDemoShopLoginPage() {
		driver.get("https://demowebshop.tricentis.com/");
		WebElement loginLink = driver.findElement(By.className("ico-login"));
		loginLink.click();
		WebElement emailField = driver.findElement(By.name("Email"));
		WebElement passwordField = driver.findElement(By.name("Password"));
		emailField.sendKeys("jun123@gmail.com");
		passwordField.sendKeys("test@123");
		// WebElement
		// loginbutton=driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));
		WebElement loginbutton = driver.findElement(By.cssSelector(
				"body > div.master-wrapper-page > div.master-wrapper-content > div.master-wrapper-main > div.center-2 > div > div.page-body > div.customer-blocks > div.returning-wrapper > div.form-fields > form > div.buttons > input"));
		loginbutton.click();
	}

	@Test
	public void TC_005_verifyNewToursRegistration() throws InterruptedException {
		driver.get("https://demo.guru99.com/test/newtours/");
		// WebElement registerLink=d"river.findElement(By.linkText("REGISTER"));

		WebElement registerLink = driver.findElement(By.partialLinkText("REGIS"));
		registerLink.click();
		WebElement firstNameField = driver.findElement(By.name("firstName"));
		WebElement lastNameField = driver.findElement(By.name("lastName"));
		WebElement phoneNumberField = driver.findElement(By.name("phone"));
		WebElement emailIdField = driver.findElement(By.name("userName"));

		firstNameField.sendKeys("Jun");
		lastNameField.sendKeys("job");
		phoneNumberField.sendKeys("050612");
		emailIdField.sendKeys("jos143@gmail.com");

		WebElement addressField = driver.findElement(By.xpath(
				"/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[7]/td[2]/input"));
		WebElement cityField = driver.findElement(By.xpath(
				"/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[8]/td[2]/input"));
		WebElement stateField = driver.findElement(By.xpath(
				"/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/input"));
		WebElement postalField = driver.findElement(By.xpath(
				"/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[10]/td[2]/input"));

		addressField.sendKeys("HillView");
		cityField.sendKeys("Kochi");
		stateField.sendKeys("Kerala");
		postalField.sendKeys("555500");

		WebElement userNameField = driver.findElement(By.cssSelector("#email"));
		WebElement passwordField = driver.findElement(By.cssSelector(
				"body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(14) > td:nth-child(2) > input[type=password]"));
		WebElement confirmPasswordField = driver.findElement(By.cssSelector(
				"body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(15) > td:nth-child(2) > input[type=password]"));

		userNameField.sendKeys("jos143@gmail.com");
		passwordField.sendKeys("test@321");
		confirmPasswordField.sendKeys("test@321");
		WebElement submitButton = driver.findElement(By.name("submit"));
		submitButton.click();

		Thread.sleep(3000);
		WebElement resultText = driver.findElement(By.xpath(
				"/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[2]/font"));
		String actualText = resultText.getText();
		System.out.println(actualText);
		String expectedText = "Thank you for registering. You may now sign-in using the user name and password you've just entered.";
		Assert.assertEquals(expectedText, actualText, "Invalid Text Found");
	}

	@Test
	public void TC_006_verifyEmptyFieldValidation() {
		driver.get("https://selenium.obsqurazone.com/form-submit.php");
		WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit form']"));
		submitButton.click();

		WebElement firstNameValidation = driver
				.findElement(By.xpath("//input[@id='validationCustom01']//following-sibling::div[1]"));
		String actualFirstNameValidation = firstNameValidation.getText();
		System.out.println(actualFirstNameValidation);
		String expectedFirstNameValidation = "Please enter First name.";
		Assert.assertEquals(expectedFirstNameValidation, actualFirstNameValidation, "Invalid Field Found");

		WebElement lastNameValidation = driver
				.findElement(By.xpath("//input[@id='validationCustom02']//following-sibling::div[1]"));
		String actualLastNameValidation = lastNameValidation.getText();
		System.out.println(actualLastNameValidation);
		String expectedLastNameValidation = "Please enter Last name.";
		Assert.assertEquals(expectedLastNameValidation, actualLastNameValidation, "Invalid Field Found");

		WebElement userNameValidation = driver
				.findElement(By.xpath("//input[@id='validationCustomUsername']//following-sibling::div[1]"));
		String actualUserNameValidation = userNameValidation.getText();
		System.out.println(actualUserNameValidation);
		String expectedUserNameValidation = "Please choose a username.";
		Assert.assertEquals(expectedUserNameValidation, actualUserNameValidation, "Invalid Field Found");

		WebElement cityNameValidation = driver
				.findElement(By.xpath("//input[@id='validationCustom03']//following-sibling::div[1]"));
		String actualCityNameValidation = cityNameValidation.getText();
		System.out.println(actualCityNameValidation);
		String expectedCityNameValidation = "Please provide a valid city.";
		Assert.assertEquals(expectedCityNameValidation, actualCityNameValidation, "Invalid Field Found");

		WebElement stateNameValidation = driver
				.findElement(By.xpath("//input[@id='validationCustom04']//following-sibling::div[1]"));
		String actualStateNameValidation = stateNameValidation.getText();
		System.out.println(actualStateNameValidation);
		String expectedStateNameValidation = "Please provide a valid state.";
		Assert.assertEquals(expectedStateNameValidation, actualStateNameValidation, "Invalid Field Found");

		WebElement zipCodeValidation = driver
				.findElement(By.xpath("//input[@id='validationCustom05']//following-sibling::div[1]"));
		String actualZipCodeValidation = zipCodeValidation.getText();
		System.out.println(actualZipCodeValidation);
		String expectedZipCodeValidation = "Please provide a valid zip.";
		Assert.assertEquals(expectedZipCodeValidation, actualZipCodeValidation, "Invalid Field Found");

		WebElement checkBoxValidation = driver
				.findElement(By.xpath("//label[@class='form-check-label']//following-sibling::div"));
		String actualCheckBoxValidation = checkBoxValidation.getText();
		System.out.println(actualCheckBoxValidation);
		String expectedCheckBoxValidation = "You must agree before submitting.";
		Assert.assertEquals(expectedCheckBoxValidation, actualCheckBoxValidation, "Invalid Field Found");

	}

	@Test
	public void TC_007_verifyEmptyStateZipCode() {
		driver.get("https://selenium.obsqurazone.com/form-submit.php");
		WebElement firstNamefield = driver.findElement(By.xpath("//input[@id='validationCustom01']"));
		WebElement lastNamefield = driver.findElement(By.xpath("//input[@id='validationCustom02']"));
		WebElement userNamefield = driver.findElement(By.xpath("//input[@id='validationCustomUsername']"));
		WebElement cityNamefield = driver.findElement(By.xpath("//input[@id='validationCustom03']"));
		WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit form']"));
		firstNamefield.sendKeys("kit");
		lastNamefield.sendKeys("kil");
		userNamefield.sendKeys("kit2123");
		cityNamefield.sendKeys("kochi");
		submitButton.click();

		WebElement stateNameValidation = driver
				.findElement(By.xpath("//input[@id='validationCustom04']//following-sibling::div[1]"));
		WebElement zipCodeValidation = driver
				.findElement(By.xpath("//input[@id='validationCustom05']//following-sibling::div[1]"));

		String actualStateNameMessage = stateNameValidation.getText();
		System.out.println(actualStateNameMessage);
		String expectedStateNameMessage = "Please provide a valid state.";
		String actualZipCodeMessage = zipCodeValidation.getText();
		System.out.println(actualZipCodeMessage);
		String expectedZipCodeValidation = "Please provide a valid zip.";

		Assert.assertEquals(expectedStateNameMessage, actualStateNameMessage, "Invalid state Found");
		Assert.assertEquals(expectedZipCodeValidation, actualZipCodeMessage, "Invalid zip Found");
	}

	@Test
	public void Tc_008_verifySuccessfulFormSubmission() throws InterruptedException {
		driver.get("https://selenium.obsqurazone.com/form-submit.php");
		WebElement firstNameField = driver.findElement(By.xpath("//input[@id='validationCustom01']"));
		WebElement lastNameField = driver.findElement(By.xpath("//input[@id='validationCustom02']"));
		WebElement userNameField = driver.findElement(By.xpath("//input[@id='validationCustomUsername']"));
		WebElement cityNameField = driver.findElement(By.xpath("//input[@id='validationCustom03']"));
		WebElement stateNameField = driver.findElement(By.xpath("//input[@id='validationCustom04']"));
		WebElement zipCodeField = driver.findElement(By.xpath("//input[@id='validationCustom05']"));
		WebElement checkBoxField = driver.findElement(By.xpath("//label[@class='form-check-label']"));
		WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit form']"));

		firstNameField.sendKeys("jjj");
		lastNameField.sendKeys("joo");
		userNameField.sendKeys("juo@123");
		cityNameField.sendKeys("kochi");
		stateNameField.sendKeys("kkkk");
		zipCodeField.sendKeys("6789");
		checkBoxField.click();
		submitButton.click();
		Thread.sleep(3000);

		WebElement resultText = driver.findElement(By.xpath("//div[@id='message-one']"));
		String actualResultText = resultText.getText();
		System.out.println("actualResultText");
		String expectedResultText = "Form has been submitted successfully!";
		Assert.assertEquals(expectedResultText, actualResultText, "Invalid Result Found");

	}

	@Test
	public void TC_009_verifyNewLetterSubscriptionUsingCSSSelector() {
		driver.get("https://demowebshop.tricentis.com");
		WebElement emailField = driver.findElement(By.cssSelector("input#newsletter-email"));
		WebElement subscribeButton = driver.findElement(By.cssSelector("input#newsletter-subscribe-button"));
		emailField.sendKeys("juh@123");
		subscribeButton.click();

	}

	@Test
	public void TC_010_verifyQuitAndClose() {
		driver.get("https://demo.guru99.com/popup.php");
		WebElement clickLink = driver.findElement(By.xpath("//a[text()='Click Here']"));
		clickLink.click();

	}

	@Test
	public void TC_011_verifyInstantDemoRequestForm() throws InterruptedException {
		driver.get("https://phptravels.com/demo/");
		WebElement firstNameField = driver.findElement(By.cssSelector("input.first_name"));
		WebElement lastNameField = driver.findElement(By.cssSelector("input.last_name"));
		WebElement businessNameField = driver.findElement(By.cssSelector("input.business_name"));
		WebElement emailField = driver.findElement(By.cssSelector("input.email"));
		WebElement firstNumber = driver.findElement(By.cssSelector("span#numb1"));
		WebElement secondNumber = driver.findElement(By.cssSelector("span#numb2"));
		WebElement resultField = driver.findElement(By.cssSelector("input#number"));
		WebElement submitButton = driver.findElement(By.cssSelector("button#demo"));

		firstNameField.sendKeys("Mim");
		lastNameField.sendKeys("Mik");
		businessNameField.sendKeys("Mirk");
		emailField.sendKeys("mm@yop.com");

		String actualNumber1 = firstNumber.getText();
		String actualNumber2 = secondNumber.getText();

		int number1 = Integer.parseInt(actualNumber1);
		int number2 = Integer.parseInt(actualNumber2);
		int total = number1 + number2;

		String actualtotal = String.valueOf(total);
		resultField.sendKeys(actualtotal);
		Thread.sleep(3000);
		submitButton.click();
		WebElement completeBox = driver.findElement(By.cssSelector("polyline.st0"));
		completeBox.isDisplayed();
	}

	@Test
	public void TC_012_verifyNavigateTo() {
		driver.navigate().to("https://demowebshop.tricentis.com");
	}

	@Test
	public void TC_013_verifyRefresh() {
		driver.get("https://demowebshop.tricentis.com");
		WebElement emailField = driver.findElement(By.xpath("//input[contains(@id,'newsletter-email')]"));
		emailField.sendKeys("mir@yop.com");
		driver.navigate().refresh();
	}

	@Test
	public void TC_014_verifyForwardAndBackwordnavigation() throws InterruptedException {
		driver.get("https://demowebshop.tricentis.com");
		WebElement loginClick = driver.findElement(By.xpath("//a[text()='Log in']"));
		loginClick.click();
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().forward();
	}

	@Test
	public void TC_015_verifyWebElementsCommands() throws InterruptedException {
		driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
		WebElement subjectField = driver.findElement(By.xpath("//input[@id='subject']"));
		WebElement descriptionField = driver.findElement(By.xpath("//textarea[@id='description']"));
		WebElement submitButton = driver.findElement(By.xpath("//input[@value='Submit']"));
		subjectField.sendKeys("selenium");
		descriptionField.sendKeys("automation testing");
		subjectField.clear();
		String classAttributeValue = subjectField.getAttribute("class");
		System.out.println(" text----------" + classAttributeValue);
		String tagNamevalue = subjectField.getTagName();
		System.out.println("text------" + tagNamevalue);
		subjectField.sendKeys("Selenium Testing");
		submitButton.click();
		Thread.sleep(3000);
		WebElement validationMessage = driver.findElement(By.xpath("//div[@id='message-one']"));
		String actualValidationMessage = validationMessage.getText();
		String expectedValidationMessage = "Form has been submitted successfully!";
		Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Invalid Message");

	}

	@Test
	public void TC_016_verifyIsDisplayed() {
		driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
		WebElement subjectField = driver.findElement(By.xpath("//input[@id='subject']"));
		subjectField.sendKeys("Selenium");
		boolean status = subjectField.isDisplayed();
		System.out.println(status);
		Assert.assertTrue(status, "subject field is not displayed");
	}

	@Test
	public void TC_017_verifyIsSelected() {
		driver.get("https://selenium.obsqurazone.com/check-box-demo.php");
		WebElement singleCheckBoxDemo = driver.findElement(By.xpath("//input[@id='gridCheck']"));
		boolean statusBeforeClick = singleCheckBoxDemo.isSelected();
		System.out.println("  status---------" + statusBeforeClick);
		Assert.assertFalse(statusBeforeClick, "checckbox is checked");
		singleCheckBoxDemo.click();
		boolean statusAfterClick = singleCheckBoxDemo.isSelected();
		System.out.println(" status--------" + singleCheckBoxDemo);
		Assert.assertTrue(statusAfterClick, "Checkbox is not selected");
	}

	@Test
	public void TC_018_verifyIsEnabled() {
		driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
		WebElement submitButton = driver.findElement(By.xpath("//input[@value='Submit']"));
		// submitButton.click();
		boolean submitButtonClick = submitButton.isEnabled();
		System.out.println(" status-----" + submitButtonClick);
		Assert.assertTrue(submitButtonClick, "submit button not  displayed");
		Point point = submitButton.getLocation(); // to get location of an element
		System.out.println(point.x + "," + point.y);
		Dimension dim = submitButton.getSize();
		System.out.println(dim.height + "," + dim.width);
		String backGroundColor = submitButton.getCssValue("background-color");
		System.out.println(backGroundColor);
		WebElement inputElement = driver.findElement(By.tagName("input")); // only one value
		System.out.println(inputElement);
		List<WebElement> elements = driver.findElements(By.tagName("input"));// all input elements
		System.out.println(elements);
		submitButton.submit();
	}

	@Test
	public void TC_019_verifyTheMessageDisplayedInNewTab() {
		driver.get("https://demoqa.com/browser-windows");
		WebElement newTabButton = driver.findElement(By.xpath("//button[@id='tabButton']"));
		boolean newTabbuttonStatus = newTabButton.isEnabled();
		Assert.assertTrue(newTabbuttonStatus, "button is not enabled");
		newTabButton.click();
		driver.navigate().to("https://demoqa.com/sample");
		WebElement samplePage = driver.findElement(By.xpath("//h1[@id='sampleHeading']")); // child window
		String actualMessage = samplePage.getText();
		String expectedMessage = "This is a sample page";
		Assert.assertEquals(actualMessage, expectedMessage, "Invalid Message Found");

	}

	@Test
	public void TC_020_verifyTheMessageDisplayedInNewWindow() {
		driver.get("https://demoqa.com/browser-windows");
		String parentWindow = driver.getWindowHandle();
		System.out.println("Parent Window ID=" + parentWindow);
		WebElement newWindowButton = driver.findElement(By.id("windowButton"));
		newWindowButton.click();
		Set<String> handles = driver.getWindowHandles(); // all windows
		System.out.println("Windows ID=" + handles);
		Iterator<String> handleIds = handles.iterator();
		while (handleIds.hasNext()) {
			String childWindow = handleIds.next();
			if (!childWindow.equals(parentWindow)) {
				driver.switchTo().window(childWindow);
				WebElement sampleHeading = driver.findElement(By.id("sampleHeading"));
				String actualText = sampleHeading.getText();
				String expectedText = "This is a sample page";
				Assert.assertEquals(actualText, expectedText, "Invalid heading Found");
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}

	@Test
	public void TC_022_verifyMultipleWindowHandling() {
		driver.get("http://www.webdriveruniversity.com/");
		WebElement contactUs = driver.findElement(By.xpath("//h1[text()='CONTACT US']"));
		contactUs.click();
		WebElement loginPortal = driver.findElement(By.xpath("//h1[text()='LOGIN PORTAL']"));
		loginPortal.click();
		String parent = driver.getWindowHandle(); // parent tab or window
		System.out.println("parent:" + parent);
		String title = "";
		Set<String> allWindows = driver.getWindowHandles(); // all windows
		for (String temp : allWindows) {
			if (!temp.equals(parent)) {
				System.out.println("Child Windows:" + temp);
				driver.switchTo().window(temp);
				title = driver.getTitle();
				System.out.println(driver.getCurrentUrl());
				System.out.println("---------------------");
			}
			if (title.equals("WebDriver | Login Portal")) {
				WebElement uname = driver.findElement(By.id("text"));
				uname.sendKeys("UserName");
			}

			if (title.equals("WebDriver | Contact Us")) {
				WebElement firstName = driver.findElement(By.name("first_name"));
				firstName.sendKeys("Juno");
			}
		}

	}

	@Test
	public void TC_023_verifySimpleAlert() {
		driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
		WebElement clickButton = driver.findElement(By.xpath("//button[@ class='btn btn-success']"));
		clickButton.click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);
		alert.accept();
	}

	@Test
	public void TC_024_verifyConfirmAlert() {
		driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
		WebElement clickMeButton = driver.findElement(By.xpath("//button[@ class='btn btn-warning']"));
		clickMeButton.click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);
		alert.dismiss();
	}

	@Test
	public void TC_025_verifyPromptAlert() {
		driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
		WebElement promptButton = driver.findElement(By.xpath("//button[@class='btn btn-danger']"));
		promptButton.click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);
		alert.sendKeys("Testing");
		alert.accept();
	}

	@Test
	public void TC_026_verifyTextInAFrame() {
		driver.get("https://demoqa.com/frames");
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		int noOfFrames = frames.size();
		System.out.println(noOfFrames);
		// driver.switchTo().frame(3); //using index
		driver.switchTo().frame("frame1"); // using name-copy id from inspect tag
		// WebElement frame = driver.findElement(By.id("frame1")); // using webelememt
		// driver.switchTo().frame(frame);
		WebElement heading = driver.findElement(By.id("sampleHeading"));
		String headingtext = heading.getText();
		System.out.println(headingtext);
		driver.switchTo().parentFrame();
		// driver.switchTo().defaultContent();
	}

	@Test
	public void TC_027_verifyRightClick() {
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		WebElement rightClickButton = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
		Actions action = new Actions(driver);
		action.contextClick(rightClickButton).build().perform();
		// action.build().perform();
	}

	@Test
	public void TC_028_verifyDoubleClick() {
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
		Actions action = new Actions(driver);
		action.doubleClick(doubleClickButton).build().perform();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@Test
	public void TC_029_verifyMouseOver() {
		driver.get("https://demoqa.com/menu/");
		WebElement mainItemOne = driver.findElement(By.xpath("//a[text()='Main Item 1']"));
		Actions action = new Actions(driver);
		action.moveToElement(mainItemOne).build().perform();
		// action.moveToElement(mainItemOne ,50,50).build().perform();
		// action.moveByOffset(40, 50).build().perform();

	}

	@Test
	public void TC_030_verifyDragAndDrop() {
		driver.get("https://demoqa.com/droppable");
		WebElement dragMeButton = driver.findElement(By.id("draggable"));
		WebElement dropMeButton = driver.findElement(By.id("droppable"));
		Actions action = new Actions(driver);
		action.dragAndDrop(dragMeButton, dropMeButton).build().perform();
	}

	@Test
	public void TC_031_verifyDragAndDropOffset() {
		driver.get("https://demoqa.com/dragabble");
		WebElement dragMeBox = driver.findElement(By.xpath("//div[@id='dragBox']"));
		Actions action = new Actions(driver);
		action.dragAndDropBy(dragMeBox, 100, 100).build().perform();

	}

	@Test
	public void TC_032_verifyDragAndDropAssignment() {
		driver.get("https://selenium.obsqurazone.com/drag-drop.php");
		WebElement draggableOne = driver.findElement(By.xpath("//span[text()='Draggable n°1']"));
		WebElement draggableTwo = driver.findElement(By.xpath("//span[text()='Draggable n°2']"));
		WebElement draggableThree = driver.findElement(By.xpath("//span[text()='Draggable n°3']"));
		WebElement draggableFour = driver.findElement(By.xpath("//span[text()='Draggable n°4']"));
		WebElement droppBox = driver.findElement(By.xpath("//div[@id='mydropzone']"));
		Actions action = new Actions(driver);
		action.dragAndDrop(draggableOne, droppBox).build().perform();
		action.dragAndDrop(draggableTwo, droppBox).build().perform();
		action.dragAndDrop(draggableThree, droppBox).build().perform();
		action.dragAndDrop(draggableFour, droppBox).build().perform();
	}

	@Test
	public void TC_033_verifyclickHoldAndResize() {
		// driver.get("https://demoqa.com/resizable");
		// WebElement
		// dragResizeBox=driver.findElement(By.xpath("//div[@id='resizableBoxWithRestriction']/child::span"));
		// Actions action=new Actions(driver);
		// action.clickAndHold(dragResizeBox).build().perform();
		// action.dragAndDropBy(dragResizeBox,75,75).build().perform();
		driver.get("https://jqueryui.com/resizable/");
		WebElement frame = driver.findElement(By.xpath("//*[@id='content']/iframe"));
		driver.switchTo().frame(frame);
		WebElement reSizebox = driver.findElement(By.xpath("//*[@id='resizable']/div[3]"));
		Actions action = new Actions(driver);
		action.clickAndHold(reSizebox).build().perform();
		action.dragAndDropBy(reSizebox, 300, 100).build().perform();
	}

	@Test
	public void TC_034_verifyValuesInDropDown() {
		driver.get("https://demo.guru99.com/test/newtours/register.php");
		WebElement countryDropMenu = driver.findElement(By.xpath("//select[@name='country']"));
		List<String> expDropDownList = new ArrayList<String>();
		expDropDownList.add("ALBANIA");
		expDropDownList.add("ALGERIA");
		expDropDownList.add("AMERICAN SAMOA");
		expDropDownList.add("ANDORRA");
		List<String> actDropDownList = new ArrayList<String>();
		Select select = new Select(countryDropMenu);
		List<WebElement> dropDownOptions = select.getOptions();
		for (int i = 0; i < 4; i++) {
			actDropDownList.add(dropDownOptions.get(i).getText());

		}
		System.out.println(actDropDownList);
		Assert.assertEquals(actDropDownList, expDropDownList, "Invalid dropDown option");
		// select.selectByVisibleText("INDIA");
		select.selectByIndex(23);
		// select.selectByValue("ICELAND");

	}

	@Test
	public void TC_035_verifyMultiSelectDropdown() {
		driver.get("https://www.softwaretestingmaterial.com/sample-webpage-to-automate/");
		WebElement multiSelectDropDown = driver.findElement(By.xpath("//select[@name='multipleselect[]']"));
		Select select = new Select(multiSelectDropDown);
		boolean status = select.isMultiple();
		System.out.println(status);
		select.selectByVisibleText("Performance Testing");
		select.selectByVisibleText("Manual Testing");
		List<WebElement> selectedOptions = select.getAllSelectedOptions();
		for (int i = 0; i < selectedOptions.size(); i++) {
			System.out.println(selectedOptions.get(i).getText());
		}
		select.deselectAll();
	}

	@Test
	public void TC_036_verifyFindElementsCommand() {
		driver.get("https://selenium.obsqurazone.com/radio-button-demo.php");
		List<WebElement> genders = driver.findElements(By.xpath("//input[@name='student-gender']"));
		System.out.println(genders);
		for (int i = 0; i < genders.size(); i++)// Multiple checkBox Handling
		{
			String gender = genders.get(i).getAttribute("value");
			System.out.println(gender);
			if (gender.equals("Female")) {
				genders.get(i).click();
			}

		}
	}

	@Test
	public void TC_037_verifyFileUploadInSelenium() {
		driver.get("https://demo.guru99.com/test/upload/");
		WebElement chooseFileUpload = driver.findElement(By.xpath("//input[@id='uploadfile_0']"));
		chooseFileUpload.sendKeys("C:\\Users\\junoj\\OneDrive\\Desktop\\Selenium\\Data\\test.txt ");
		WebElement termsAccept = driver.findElement(By.xpath("//input[@id='terms']"));
		termsAccept.click();
		WebElement submitButton = driver.findElement(By.xpath("//button[@id='submitbutton']"));
		submitButton.click();

	}

	@Test
	public void TC_038_verifyClickAndSenKeysUsingJavaScriptExecutor() {
		driver.get("https://demowebshop.tricentis.com/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('newsletter-email').value='abc@gmail.com'");
		js.executeScript("document.getElementById('newsletter-subscribe-button').click()");
	}

	@Test
	public void TC_039_verifyScrollDownOfAWebPage() {
		driver.get("https://demo.guru99.com/test/guru99home/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}

	@Test
	public void TC_040_verifyScrollIntoAViewOfWebElement() {
		driver.get("https://demo.guru99.com/test/guru99home/");
		WebElement linuxText = driver.findElement(By.linkText("Linux"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", linuxText);

	}

	@Test
	public void TC_041_verifyTheBottomOfThePage() {
		driver.get("https://demo.guru99.com/test/guru99home/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

	}

	@Test
	public void TC_042_verifyHorizontalScroll() {
		driver.get("http://demo.guru99.com/test/guru99home/scrolling.html");
		WebElement vbScript = driver.findElement(By.linkText("VBScript"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", vbScript);
	}

	@Test
	public void TC_043_verifyTable() throws IOException {
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		List<WebElement> rowElements = driver.findElements(By.xpath("//table[@id='customers']//tbody//tr"));
		List<WebElement> columnElement = driver.findElements(By.xpath("//table[@id='customers']//tbody//tr//td"));
		List<ArrayList<String>> actGridData = TableUtility.get_Dynamic_TwoDimension_TablElemnts(rowElements,
				columnElement);
		List<ArrayList<String>> expGridData = ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx",
				"Table");
		Assert.assertEquals(actGridData, expGridData, "Invalid Data found in table");
	}

	@Test
	public void TC_044_verifyTableElement() throws IOException {
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		List<WebElement> rowElements = driver.findElements(By.xpath("//table[@id='customers']//tbody//tr"));
		List<WebElement> columnElement = driver.findElements(By.xpath("//table[@id='customers']//tbody//tr//td"));
		List<ArrayList<String>> actGridData = TableUtility.get_Dynamic_TwoDimension_TablElemnts(rowElements,
				columnElement);
		List<ArrayList<String>> expGridData = ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx",
				"Table");
		Assert.assertEquals(actGridData, expGridData, "Invalid Data found in table");
		for (int i = 0; i < actGridData.size(); i++) {
			if (actGridData.get(i).get(0).equals("Island Trading")) {
				for (int j = 1; j < actGridData.get(i).size(); j++) {
					System.out.println(actGridData.get(i).get(j));
				}
			}
		}
	}

	@Test
	public void TC_045_verifyFileUploadUsingRobotClass() throws InterruptedException, AWTException {
		driver.get("https://www.foundit.in/seeker/registration");
		StringSelection s = new StringSelection("C:\\Users\\junoj\\OneDrive\\Desktop\\Selenium\\Data\\test.txt");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		WebElement chooseFileButton = driver.findElement(By.xpath("//span[text()='Choose CV']"));
		chooseFileButton.click();
		Thread.sleep(2000);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

	}

	@Test
	public void TC_046_verifyDynamicTable() throws IOException {
		driver.get("https://selenium.obsqurazone.com/table-sort-search.php");
		WebElement searchField = driver.findElement(By.xpath("//input[@type='search']"));
		searchField.sendKeys("Caesar Vance");
		List<WebElement> rowElements = driver.findElements(By.xpath("//table[@id='dtBasicExample']//tr"));
		List<WebElement> columnElements = driver.findElements(By.xpath("//table[@id='dtBasicExample']//tr//td"));
		List<ArrayList<String>> actGridData = TableUtility.get_Dynamic_TwoDimension_ObscuraTablElemnts(rowElements,
				columnElements);
		List<ArrayList<String>> expGridData = ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx",
				"ObsTable");
		Assert.assertEquals(actGridData, expGridData, "Invalid data found in table");
		System.out.println(actGridData);
		for (int i = 0; i < actGridData.size(); i++) {
			if (actGridData.get(i).get(0).equals("Caesar Vance")) {
				for (int j = 1; j < actGridData.get(i).size(); j++) {
					System.out.println(actGridData.get(i).get(j));
				}
			}
		}
		System.out.println(actGridData);
	}
	@Test
	public void TC_047_verifyWaitsInSelenium()
	{
	driver.get("https://demowebshop.tricentis.com/");
	/*pageLoadWait*/
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	/*implicitWait*/
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	/*explicitWait*/
	WebElement emailField=driver.findElement(By.xpath("//input[@id='newsletter-email']"));
	emailField.sendKeys("kitten@yopmail.com");
	WebElement subscribeButton=driver.findElement(By.xpath("//input[@id='newsletter-subscribe-button']"));
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(subscribeButton));
	/*fluentWait*/
	FluentWait fwait=new FluentWait<WebDriver>(driver);
	fwait.withTimeout(Duration.ofSeconds(10));
	fwait.pollingEvery(Duration.ofSeconds(1));
	fwait.until(ExpectedConditions.visibilityOf(subscribeButton));
	subscribeButton.click();
	}
	@Test
	public void TC_048_verifySoftAssert() {
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		SoftAssert softAssert=new SoftAssert();
		WebElement valueA = driver.findElement(By.id("value-a"));
		WebElement valueB = driver.findElement(By.id("value-b"));
		WebElement getTotalButton = driver.findElement(By.id("button-two"));
		WebElement totalMessage = driver.findElement(By.id("message-two"));
		valueA.sendKeys("100");
		valueB.sendKeys("200");
		getTotalButton.click();
		String actualTotal = totalMessage.getText();
		String expectedTotal = "Total A + B : 301";
		softAssert.assertEquals(actualTotal, expectedTotal, "Invalid Total Message Found");
		System.out.println(actualTotal);

	}
}
