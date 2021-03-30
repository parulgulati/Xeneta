package Utils;

import org.junit.Assert;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;

public class BaseClass {
	public BaseClass() {

	}

	public static WebDriver driver;
	public static FileInputStream fis;
	public static Properties prop;
	public static WebDriverWait wait;
	String path = "PropertiesFile\\Environment.properties";

	/*
	 * @description: initiate the webDriver
	 * 
	 * @param : Browser Name
	 */
	public void webDriverInit(String Browser) throws IOException {
		if (Browser.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--start-maximized");
			System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			System.out.println("Launching Chrome and clearing all the cache and cookies");
		}

		else if (Browser.equalsIgnoreCase("IE")) {
			// similarly we can call Internet explorer
		}

		else if (Browser.equalsIgnoreCase("FireFox")) {
			// similarly we can call Firefox
		}

	}

	/*
	 * @description: closed the webDriver
	 * 
	 * @param : null
	 */
	public void closeBrowser() throws FileNotFoundException {
		driver.quit();
		System.out.println("Browser is closed");
	}

	public void LoadProperties() throws FileNotFoundException {
		fis = new FileInputStream(path);
		prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String GetData(String Key) throws FileNotFoundException {
		LoadProperties();
		String Value = prop.getProperty(Key);
		return Value;
	}

	public void SetData(String Key, String Value) throws FileNotFoundException {
		LoadProperties();
		prop.setProperty(Key, Value);
		StoreData(path);

	}

	public void StoreData(String path) throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream(path);
		try {
			prop.store(fos, "Stored in Properties");
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * @description: initiate the url, navigates to the given url
	 * 
	 * @param : String of url
	 */
	public void initURL(String URL) throws IOException {
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("User is Navigated to " + URL);

	}

	/*
	 * @description: element wait
	 * 
	 * @param : webelement, time for element to wait
	 */
	public void waitForTheElementToVisible(WebElement Element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(Element));

	}

	/*
	 * @description: takes screenshot of the webDriver
	 * 
	 * @param : filename of screenshot
	 */
	public void takeScreenshot(String fileName) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		String location = "Print\\Screenshots";
		File DestFile = new File(location + fileName + "_" + ".png");
		FileUtils.copyFile(srcFile, DestFile);
	}

	/*
	 * @description: verify text of element with expected text
	 * 
	 * @param : webelement and expected text
	 */
	public boolean verifyText(WebElement element, String expText) {
		try {
			if (element.isDisplayed()) {
				String text = element.getText();
				if (text.equalsIgnoreCase(expText)) {
					Reporter.addStepLog("Match found " + text);
					System.out.println("Match found " + text);
					return true;
				}

				else {
					Reporter.addStepLog("Match not found " + text);
					System.out.println("Match not found " + text);
					return false;
				}
			} else {
				System.err.println("Element is not visible");
				return false;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	/*
	 * @description: verify page title of driver
	 * 
	 * @param : expected page title
	 */
	public boolean verifyPageTitle(String expText) {
		try {
			String text = driver.getTitle();
			if (text.equalsIgnoreCase(expText)) {
				System.out.println("Expected Title is loaded: " + expText);
				Reporter.addStepLog("Expected Title is loaded: " + expText);
				return true;
			}

			else {
				System.out.println("Expected Title is not loaded: " + expText);
				Reporter.addStepLog("Expected Title is not loaded: " + expText);
				return false;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/*
	 * @description: verify current url of driver
	 * 
	 * @param : expected url
	 */
	public boolean verifyPageURL(String expText) {
		try {
			String URL = driver.getCurrentUrl();
			if (URL.equalsIgnoreCase(expText)) {
				System.out.println("Expected URL is loaded: " + expText);
				return true;
			}

			else {
				System.out.println("Expected URL is not loaded: " + expText);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * @description: verify text of element with expected text using assert
	 * 
	 * @param : message, actual text and expected text
	 */
	public void verifyElementsText(String message, String actualText, String expText) {
		try {
			Assert.assertEquals(message, actualText, expText);

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * @description: verify element is not present on screen
	 * 
	 * @param : webelement and text to be printed in message
	 */
	public boolean verifyElementNotPresent(WebElement element, String text) {
		try {
			if (element.isDisplayed()) {
				System.err.println("Element is present");
				return false;
			} else {
				System.out.println("Element is not present- Expected Result");
				return true;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * @description: verify element is present on screen
	 * 
	 * @param : webelement and text to be printed in message
	 */
	public boolean verifyElementPresent(WebElement element, String name) throws NoSuchElementException {
		// int text=driver.findElements((By) element).size();
	//	try {
			if (element.isDisplayed()) {
				scrollIntoView(element);
				System.out.println(name + " is present on screen");
				return true;
			} else {
				System.out.println(name + " is not present on screen");
				return false;
			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
	}

	/*
	 * @description: enter text in element, sendkeys
	 * 
	 * @param : webelement and text to be entered in input
	 */
	public boolean setText(WebElement element, String text) {
		try {
			waitForTheElementToVisible(element, 20);
			if (element.isDisplayed() && element.isEnabled()) {
				element.clear();
				element.sendKeys(text);
				System.out.println("Value is set to: " + text);
				return true;
			} else {
				System.out.println("textbox is not editable");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

	/*
	 * @description: click on element
	 * 
	 * @param : webelement and text to be printed in message
	 */
	public boolean clickElement(WebElement element, String text) {
		try {
			waitForTheElementToVisible(element, 30);
			if (element.isDisplayed() && element.isEnabled()) {
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				System.out.println(text + " is clicked");
				return true;
			} else {
				System.out.println(text + " is not clickable");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * @description: click element using javascript
	 * 
	 * @param : webelement and text to be printed in message
	 */
	public void clickByJavascript(WebElement element, String text) {
		try {
			waitForTheElementToVisible(element, 30);
			Thread.sleep(3000);
			scrollIntoView(element);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			System.out.println(text + " is clicked using Javascript");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * @description: refreshes the browsers
	 * 
	 * @param : null
	 */
	public void refresh() {
		driver.navigate().refresh();
		System.out.println("Page refreshed");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	/*
	 * @description: initiate the url, navigates to the given url
	 * 
	 * @param : String of url
	 */
	public void fetchListItems(WebElement element, String list) throws IOException {

		String[] searchText = list.split(",");
		List<WebElement> li = element.findElements(By.tagName("li"));
		
		for (String a : searchText)
			for (WebElement option : li) {
				if (option.getText().contains(a)) {
					System.out.println(a + " :is present in the list");
					Reporter.addStepLog(a+ " :is present in the list");
					break;
				}
			}
	}

	/*
	 * @description: scroll into the given
	 * 
	 * @param : webelement
	 */
	public void scrollIntoView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	/*
	 * @description:window scroll up
	 * 
	 * @param : element
	 */
	public void windowscrollUp(List<WebElement> element) {
		while (element.size() == 0) {
			((JavascriptExecutor) driver).executeScript("scroll(0, -250);");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
	}

	/*
	 * @description:window scroll down until element is visible
	 * 
	 * @param : webelement
	 */
	public void windowscrollDown(List<WebElement> element) {
		while (element.size() == 0) {
			((JavascriptExecutor) driver).executeScript("scroll(0, 500);");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
	}

	/*
	 * @description: hard wait
	 * 
	 * @param : time in seconds
	 */
	public void javawait(int time) throws Exception {
		Thread.sleep(time);
	}

	/*
	 * @description:select the value from dropdown
	 * 
	 * @param : element,value to be selected, name of the dropdown
	 */
	public void selectByValue(WebElement element, String text, String list) {

		Select dropdown = new Select(element);
		dropdown.selectByValue(text);
		System.out.println(text + "is selected from dropdown from list " + list);
	}

	/*
	 * @description:switch to new window/tab
	 * 
	 * @param : null
	 */
	public boolean switchWindow() throws Throwable   {
		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();

		// Now iterating using Iterator
		Iterator<String> I1 = s.iterator();
		Thread.sleep(3000);
		while (I1.hasNext()) {

			String child_window = I1.next();

			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				System.out.println("Switched to new window:"+driver.switchTo().window(child_window).getTitle());
				Reporter.addStepLog("Switched to new window:" +driver.switchTo().window(child_window).getTitle());
				return true;
			}
			else {
				System.out.println("No new window opened:" +driver.switchTo().window(child_window).getTitle());
				Reporter.addStepLog("No new window opened:" +driver.switchTo().window(child_window).getTitle());
				return false;
			}
		}
		return false;

	}
}