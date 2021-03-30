package StepdefFiles;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import Utils.BaseClass;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends BaseClass {
	public static File destinationPath;
	@Before
	public void beforeScenario() throws IOException {
		//This method is running before every Scenario"
		webDriverInit("Chrome");
	}

	@After
	public void afterScenario(Scenario scenario) throws IOException {
		//"This method is running after every Scenario"
		String screenshotName = scenario.getName().replaceAll(" ", "_");
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File sourcePath = ts.getScreenshotAs(OutputType.FILE);
			destinationPath = new File(System.getProperty("user.dir") + "\\target\\cucumber-reports\\screenshots\\"
					+ screenshotName + ".png");
			FileUtils.copyFile(sourcePath, destinationPath);

		} catch (IOException e) {

			e.printStackTrace();
		}
		closeBrowser();
	}

}
