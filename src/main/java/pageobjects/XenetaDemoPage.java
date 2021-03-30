package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class XenetaDemoPage {
	WebDriver driver;

	public XenetaDemoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@title='XENETA']")
	public WebElement logo_Xeneta;

	@FindBy(xpath = "//a[contains(text(),'Our Customers')]")
	public WebElement lnkCustomers;

	@FindBy(xpath = "//a[contains(text(),'Platform')]")
	public WebElement lnkPlatform;

	@FindBy(xpath = "//a[contains(text(),'Learn')]")
	public WebElement lnkLearn;

	@FindBy(xpath = "//a[contains(text(),'Company')]")
	public WebElement lnkCompany;

	@FindBy(xpath = "//a[contains(text(),'Sign in')]")
	public WebElement lnkSignIn;

	@FindBy(xpath = "//a[contains(text(),'Book a Meeting')]")
	public WebElement lnkBookAMeeting;

	@FindBy(xpath = "//a[contains(text(),'Close')]")
	public WebElement iconClose;

	@FindBy(xpath = "//a[text()='WATCH VIDEOS']//..//..//..//..//..//div[contains(@class,'bullets-circle')]")
	public WebElement iconWatchVideos;

	@FindBy(xpath = "//a[text()='WATCH VIDEOS']")
	public WebElement lnkWatchVideo;

	@FindBy(xpath = "//a[text()='Schedule Now']//..//..//..//..//div[contains(@class,'bullets-circle')]")
	public WebElement iconBookAmeeting;

	@FindBy(xpath = "//a[text()='Schedule Now']")
	public WebElement lnkScheduleNow;

	@FindBy(xpath = "//a[text()='Sign up here']//..//..//..//..//div[contains(@class,'bullets-circle')]")
	public WebElement iconGroupLiveDemo;

	@FindBy(xpath = "//a[text()='Sign up here']")
	public WebElement lnkSignUpHere;

	@FindBy(xpath = "//div[@id='form-popup-1']")
	public WebElement formScheduleNow;

	@FindBy(xpath = "(//a[@title='XENETA']//following::ul)[1]")
	public WebElement lst_topBar;

}
