package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

@CucumberOptions(features = {"src/test/resources/Features"},
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-html/index.html",
                "rerun:target/failedstep.txt"
        },
        glue = {"steps" , "com.apiautomation.steps"}, dryRun = false , snippets = CAMELCASE ,
        monochrome = true,
        tags = "@Create or @Update or @AllBooks or @BookByID or @Delete")
public class TestRunner  extends AbstractTestNGCucumberTests {
}
