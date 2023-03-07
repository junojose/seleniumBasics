package BasicCommands;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumDrivers {

	public static void main(String[] args) {
		
		WebDriver driver;
		driver=new EdgeDriver();
		driver.get("https://demowebshop.tricentis.com/");
		driver.close();
		
		WebDriver driver1;
		driver1=new ChromeDriver();
		driver1.get("https://demowebshop.tricentis.com/");
		driver1.close();
		
		WebDriver driver2;
		driver2=new FirefoxDriver();
		driver2.get("https://demowebshop.tricentis.com/");
		driver2.close();
		
		
		

	}

}
