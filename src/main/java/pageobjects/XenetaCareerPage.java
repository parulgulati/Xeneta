package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class XenetaCareerPage {
	WebDriver driver;
	
	public XenetaCareerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[text()='Our Values']//following::ul[@class='tabs']")
	public WebElement lstOurValues;
	
	@FindBy(xpath="(//*[text()='Oslo, Norway']//following::a)[1]")
	public WebElement lnkVisitOslo;
	
	@FindBy(xpath="//*[text()='Oslo, Norway']")
	public WebElement lnkOslo;

	@FindBy(xpath="(//*[text()='New York, USA']//following::a)[1]")
	public WebElement lnkVisitNewYork;
	
	@FindBy(xpath="//*[text()='New York, USA']")
	public WebElement lnkNewYork;
	
	@FindBy(xpath="(//*[text()='Hamburg, Germany']//following::a)[1]")
	public WebElement lnkVisitHamburg;
	
	@FindBy(xpath="//*[text()='Hamburg, Germany']")
	public WebElement lnkHamburg;
	
	@FindBy(xpath="//*[contains(text(),'Open Roles')]")
	public WebElement OpenRoles;
	
	@FindBy(xpath="(//*[contains(text(),'Open Roles')]//following::div//h4)[1]")
	public WebElement lnk_OpenRoles1;
	
	@FindBy(xpath="(//*[contains(text(),'Open Roles')]//following::div//h4//following::a)[1]")
	public WebElement lnk_ApplyOpenRoles1;	
	
	
	@FindBy(xpath="//ul[@class='active-branch']")
	public WebElement lst_BottomNavigationBar;	
	
	@FindBy(xpath="//h2[contains(text(),'Open Roles')]//following::div[@class='accordion']")
	public List<WebElement> lstOpenRoles;	
	
}
