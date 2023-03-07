package SeleniumBasicCommands;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumCommands {
	private static final String WebElement = null;
	public WebDriver driver;

	public void testInitialise(String browser) {
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
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
	public void tearDown() {
		// driver.close();
		 driver.quit();
	}

	@Test
	public void TC_001_verifyObsquraTitle() {
		driver.get("https://selenium.obsqurazone.com/index.php");

		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		String expectedTitle = "Obsqura Testing";
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
	public void TC_018_verifyIsEnabled() 
	{
		driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
		WebElement submitButton=driver.findElement(By.xpath("//input[@value='Submit']"));
		//submitButton.click();
		boolean submitButtonClick = submitButton.isEnabled();
		System.out.println(" status-----" +submitButtonClick);
		Assert.assertTrue(submitButtonClick, "submit button not  displayed");
		Point point=submitButton.getLocation();  //to get location of an element
		System.out.println(point.x + ","+ point.y);
		Dimension dim=submitButton.getSize();
		System.out.println(dim.height + "," + dim.width);
		String backGroundColor=submitButton.getCssValue("background-color");
		System.out.println(backGroundColor);
		WebElement inputElement=driver.findElement(By.tagName("input")); //only one value
		System.out.println(inputElement);
		List<WebElement> elements=driver.findElements(By.tagName("input"));// all input elements
		System.out.println(elements);
		submitButton.submit();
	}
	@Test
	public void TC_019_verifyTheMessageDisplayedInNewTab()
	{
		driver.get("https://demoqa.com/browser-windows");
	}
	
	
}

