package Runner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;

import StepdefFiles.Hooks;
import Utils.BaseClass;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
features = "src/test/java/FeatureFiles"
,glue= {"StepdefFiles"},
plugin = {"com.cucumber.listener.ExtentCucumberFormatter:"},
monochrome = true
,tags="@HomePage,@Career"
)

public class TestRunner extends BaseClass {
	
	@BeforeClass
    public static void setup() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath("target/cucumber-reports/"+timeStamp.replace(":","_").replace(".","_")+".html");
        
    }
	@AfterClass
    public static void writeExtentReport() throws IOException {
        Reporter.loadXMLConfig(new File("config/report.xml"));
        Reporter.setSystemInfo("User Name", "Xeneta Automation Team");
        Reporter.setSystemInfo("Application Name", "Xeneta Product");
        Reporter.setTestRunnerOutput("Automation Test Execution Report - Xeneta");
        Reporter.addScreenCaptureFromPath(Hooks.destinationPath.toString());
    }
}
