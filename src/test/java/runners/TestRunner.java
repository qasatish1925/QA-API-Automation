package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

@CucumberOptions(features = {"src/test/resources/Features"},
        plugin = {"com.apiautomation.FailedStepReport:target/failedstep.txt","json:target/cucumber.json","html:target/cucumber-html/index.html"}, dryRun = false , snippets = CAMELCASE ,
        monochrome = true,
        tags = "")
public class TestRunner  extends AbstractTestNGCucumberTests {
}
