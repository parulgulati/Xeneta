package StepdefFiles;

import static com.cucumber.listener.Reporter.addStepLog;
import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utils.BaseClass;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.XenetaCareerPage;
import pageobjects.XenetaDemoPage;

public class CareerStepDef extends BaseClass {
	XenetaDemoPage demo = new XenetaDemoPage(driver);
	XenetaCareerPage career = new XenetaCareerPage(driver);

	@Then("^Verify Xenetas Values \"([^\"]*)\"$")
	public void verify_Xenetas_Values(String values) throws Throwable {
		scrollIntoView(career.lstOurValues);
		fetchListItems(career.lstOurValues, values);
	}

	@When("^Office locations are displayed on the page$")
	public void office_locations_are_displayed_on_the_page() throws Throwable {
		scrollIntoView(career.lnkOslo);
		verifyElementPresent(career.lnkOslo, "Oslo is present");
		verifyElementPresent(career.lnkNewYork, "Newyork is present");
		verifyElementPresent(career.lnkHamburg, "Hamburg is present");

	}

	@Then("^Location links given are working as expected \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void location_links_given_are_working_as_expected(String osloPage, String newyork, String hamburg)
			throws Throwable {
		// verify oslo
		scrollIntoView(career.lnkOslo);
		clickByJavascript(career.lnkVisitOslo, "Link to Oslo");
		verifyPageTitle(osloPage);

		javawait(3000);
		driver.navigate().back();

		// verify newyork
		scrollIntoView(career.lnkNewYork);
		clickByJavascript(career.lnkVisitNewYork, "Link to Newyork,USA");
		verifyPageTitle(newyork);

		javawait(3000);
		driver.navigate().back();

		// verify germany
		scrollIntoView(career.lnkHamburg);
		clickByJavascript(career.lnkVisitHamburg, "Link to Hamburg, Germany");
		verifyPageTitle(hamburg);

		javawait(3000);
		driver.navigate().back();

	}

	@When("^Open Roles section is visible$")
	public void open_Roles_section_is_visible() throws Throwable {
		verifyElementPresent(career.OpenRoles, "Open Role Section");
	}

	@Then("^Verify list of Open roles and description$")
	public void verify_Open_roles_and_description() throws Throwable {

		String role;
		int numberOfRoles = career.lstOpenRoles.size();
		String[] roles = new String[numberOfRoles];
		System.out.println("Total Number of roles available are: " + numberOfRoles);
		for (int i = 1; i <= numberOfRoles; i++) {
			WebElement element = driver.findElement(
					By.xpath("//h2[contains(text(),'Open Roles')]//following::div[@class='accordion'][" + i + "]"));
			role = element.getText();
			roles[i - 1] = role;
		}
		System.out.println(Arrays.toString(roles));
		addStepLog(Arrays.toString(roles));
	}

	@Then("^Verify Apply link of open role$")
	public boolean verify_Apply_link_of_open_role() throws Throwable {
		clickByJavascript(career.lnk_OpenRoles1, "Expand Open Role");
		clickByJavascript(career.lnk_ApplyOpenRoles1, "Apply to Role");
		ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
		if (driver.getWindowHandles().size() > 1) {
			// switch to new tab
			driver.switchTo().window(newTb.get(1));
			addStepLog("New Tab is opened,Page title is: " + driver.getTitle());
			System.out.println("Page title of new tab: " + driver.getTitle());
			return true;
		} else {
			addStepLog("No New Tab is opened,Page title is: " + driver.getTitle());
			System.out.println("No new tab: " + driver.getTitle());
			return false;
		}
		
	}

}
