package StepdefFiles;

import java.sql.SQLTransactionRollbackException;

import Utils.BaseClass;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.XenetaCareerPage;
import pageobjects.XenetaDemoPage;

public class StepDef extends BaseClass {
	XenetaDemoPage demo = new XenetaDemoPage(driver);
	XenetaCareerPage career = new XenetaCareerPage(driver);

	@Given("^User Navigate to Application \"([^\"]*)\"$")
	public void user_Navigate_to_Application(String url) throws Throwable {
		String URL = GetData(url);
		initURL(URL);
	}

	@Given("^Page is Loaded successfully \"([^\"]*)\"$")
	public void page_is_Loaded_successfully(String expTitle) throws Throwable {
		verifyPageTitle(expTitle);
		verifyElementPresent(demo.logo_Xeneta, "Xeneta Logo");
	}

	@Then("^Validate home page has main Components \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void validate_home_page_has_main_Components(String comp1, String comp2, String comp3) throws Throwable {
		scrollIntoView(demo.lnkWatchVideo);
		verifyText(demo.lnkWatchVideo, comp1);
		verifyText(demo.lnkScheduleNow, comp2);
		verifyText(demo.lnkSignUpHere, comp3);
	}

	@Then("^Verify icons of all components are working as expected \"([^\"]*)\"$")
	public void verify_icons_of_all_components_are_working_as_expected(String pgWatchVideo) throws Throwable {
		// verify WatchVideo
		scrollIntoView(demo.lnkWatchVideo);
		clickByJavascript(demo.iconWatchVideos, "Watch Videos Icon");
		verifyPageTitle(pgWatchVideo);

		// verify book a meeting
		scrollIntoView(demo.iconBookAmeeting);
		clickByJavascript(demo.iconBookAmeeting, "Book a meeting");
		verifyElementPresent(demo.iconClose, "Pop Up form");

		// verify group demo
		scrollIntoView(demo.iconGroupLiveDemo);
		clickByJavascript(demo.iconGroupLiveDemo, "Group Live Demo");
		verifyElementPresent(demo.iconClose, "Pop Up form");

	}

	@Then("^Verify Top Bar links on the Page \"([^\"]*)\"$")
	public void verify_Top_Bar_links_on_the_Page(String items) throws Throwable {
		driver.navigate().back();
		fetchListItems(demo.lst_topBar, items);
	}

	@Then("^Verify links of all components are working as expected \"([^\"]*)\"$")
	public void verify_links_of_all_components_are_working_as_expected(String pgWatchVideo) throws Throwable {
		//driver.navigate().back();

		// verify WatchVideo
		scrollIntoView(demo.lnkWatchVideo);
		clickByJavascript(demo.lnkWatchVideo, "Watch Videos Icon");
		verifyPageTitle(pgWatchVideo);

		driver.navigate().back();

		// verify book a meeting
		scrollIntoView(demo.lnkScheduleNow);
		clickByJavascript(demo.lnkScheduleNow, "ScheduleNow");
		verifyElementPresent(demo.iconClose, "Pop Up form");
		clickByJavascript(demo.iconClose, "Close Button");

		// verify group demo
		scrollIntoView(demo.lnkSignUpHere);
		clickByJavascript(demo.lnkSignUpHere, "Sign Up Here");
		verifyElementPresent(demo.iconClose, "Close Button");
	}

	@Then("^User Click on SignIn Login Page should Open \"([^\"]*)\"$")
	public void user_click_on_signin_Login_page_should_open(String loginPageTitle) throws Throwable {
		clickElement(demo.lnkSignIn, "Sign In link");
		verifyPageTitle(loginPageTitle);
	}

	@When("^User Clicks Xeneta logo it navigates to home Page \"([^\"]*)\" \"([^\"]*)\"$")
	public void user_clicks_xeneta_logo_it_navigates_to_home_Page(String XenetapageTitle, String XenetaURL)
			throws Throwable {
		clickElement(demo.logo_Xeneta, "Xeneta logo");
		verifyPageTitle(XenetapageTitle);
		verifyPageURL(XenetaURL);

	}

}
