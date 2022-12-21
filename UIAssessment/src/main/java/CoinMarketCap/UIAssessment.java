package CoinMarketCap;

import java.time.Duration;

//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UIAssessment {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty(
	            "webdriver.chrome.driver",
	            "src\\main\\resources\\chromedriver.exe");
		
			
		HashMap<String, List<String>> allPagesMap = new HashMap<String, List<String>>();
		HashMap<String, List<String>> filteredPagesMap = new HashMap<String, List<String>>();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
	        // Instantiate a ChromeDriver class.
	        WebDriver driver = new ChromeDriver(options);
	  
	        // Launch Website
	        driver.get("https://coinmarketcap.com");
	        // Maximize the browser
	        driver.manage().window().maximize();
			        
	        click(driver,"//div[@class='gv-close']");
			click(driver,"//*[local-name()='svg' and contains(@class,'close-button')]");
			click(driver,"//div[@id='cmc-cookie-policy-banner']//div[2]");
			pause(6000);		
			if(presenceOfElement(driver,"//button[text()='Maybe later']")) {
				click(driver,"//button[text()='Maybe later']");
			}
			scroll(driver,"100");
			pause(10000);
			click(driver,"(//p[text()='Show rows']//following::div)[1]");
			if(presenceOfElement(driver,"//button[text()='20']")) {
				click(driver,"//button[text()='20']");	
			}else { 
				click(driver,"(//p[text()='Show rows']//following::div)[1]");
				click(driver,"//button[text()='20']");	
			}
			String getTotalCount = driver.findElement(By.xpath("//p[contains(text(),'Showing 1')]")).getText();
			getTotalCount = getTotalCount.split(" ")[getTotalCount.split(" ").length-1].trim();
			System.out.println("Total Number of Count :" + getTotalCount );
			//int iter = Integer.valueOf(getTotalCount)/20;
			int iter = 1;
			for(int i=0; i <= iter; i++){
				scroll(driver,"1200");			
				List<WebElement> innerRow = driver.findElements(By.xpath("//table//tr/td[1]"));
				for(int j=1; j <= innerRow.size(); j++) {
					ArrayList<String> list = new ArrayList<String>(); 
					String position = driver.findElement(By.xpath("//table//tr["+j+"]/td[2]")).getText();
					String name = driver.findElement(By.xpath("//table//tr["+j+"]/td[3]")).getText();
					String price = driver.findElement(By.xpath("//table//tr["+j+"]/td[4]")).getText();	
					System.out.println(position);
					System.out.println(name);
					System.out.println(price);
					list.add(position);
					list.add(price);				
					allPagesMap.put(name, list);
				}
				//click(driver,"(//a[@aria-label='Next page'])[2]");		
				System.out.println("===============================");
				//pause(5000);
			}
			System.out.println("Print all page Content: "+allPagesMap);
			scroll(driver,"150");
			click(driver,"(//button[text()='Customize']//preceding::button[text()='Filters'][1])[2]");		
			click(driver,"//button[text()='Algorithm']");	
			//enterTxt(driver, "//input[@placeholder='Search']", "PoW");
			//click(driver,"//li[text()='PoW']");	
			click(driver,"//h6[text()='Popular Algorithms']//following::li[5]");
			if(presenceOfElement(driver,"//*[local-name()='svg' and contains(@class,'close-button')]")) {
				click(driver,"//*[local-name()='svg' and contains(@class,'close-button')]");
			}
			pause(3000);
			click(driver,"//button[text()='Add Filter']");	
			click(driver,"//*[@id='mineable']//span");	
			click(driver,"//button[text()='All Cryptocurrencies']");	
			click(driver,"//button[text()='Coins']");	
			click(driver,"//button[text()='Price']");
			enterTxt(driver, "//h5[text()='Price Range']//following::div[1]/input[1]", "100");
			enterTxt(driver, "//h5[text()='Price Range']//following::div[1]/input[2]", "10000");
			pause(3000);
			click(driver,"//button[text()='Apply Filter']");
			pause(3000);
			click(driver,"//button[text()='Show results']");	
			String getFilteredCount = driver.findElement(By.xpath("//p[contains(text(),'Showing 1')]")).getText();
			getFilteredCount = getFilteredCount.split(" ")[getFilteredCount.split(" ").length-1].trim();
			int filteredIter =1;
			/*if(Integer.valueOf(getFilteredCount) >20) {
				filteredIter = Integer.valueOf(getFilteredCount)/20;
			}*/
			for(int i=0; i <= filteredIter;i++) {
				scroll(driver,"400");			
				List<WebElement> innerRow = driver.findElements(By.xpath("//table//tr/td[1]"));
				for(int j=1; j <= innerRow.size(); j++) {
					ArrayList<String> list = new ArrayList<String>(); 
					String position = driver.findElement(By.xpath("//table//tr["+j+"]/td[2]")).getText();
					String name = driver.findElement(By.xpath("//table//tr["+j+"]/td[3]")).getText();
					String price = driver.findElement(By.xpath("//table//tr["+j+"]/td[4]")).getText();	
					System.out.println(position);
					System.out.println(name);
					System.out.println(price);
					list.add(position);
					list.add(price);				
					filteredPagesMap.put(name, list);
				}
				//click(driver,"(//a[@aria-label='Next page'])[2]");		
				System.out.println("===============================");
				//pause(5000);
			}
			System.out.println("Print Filtered value Contents:"+filteredPagesMap);
			driver.close();
		}
		
		public static void click(WebDriver driver, String locator) {		
			try {
				WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(30));
				WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
				Actions action = new Actions(driver);
				action.moveToElement(el).click().perform();
				pause(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void pause(int wait) throws InterruptedException {
			Thread.sleep(wait);
		}
		
		public static boolean presenceOfElement(WebDriver driver, String locator) {
			boolean flag = false;
			try {
				WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(15));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
				flag = true;
			}catch (Exception e) {
				System.err.println("Element Not Presents Exception "+locator);
			}		
			return flag;		
		}
		
		
		public static void enterTxt(WebDriver driver, String locator,String inputValue) {		
		    try {
		    	WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(30));
				WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
			    el.sendKeys(inputValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void scroll(WebDriver driver,String pageEndRange) {		
		    try {
				 ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+pageEndRange+")");
				pause(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		

	}
