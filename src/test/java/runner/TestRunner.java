package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {".\\src\\test\\resources\\features\\incident.feature"},
        glue = {"steps"},
        dryRun = false,
        //tags
        plugin = {
                "html:cucumber-reports/result.html"
        },
        publish = true
)


public class TestRunner extends AbstractTestNGCucumberTests {
}
