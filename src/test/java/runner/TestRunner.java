package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

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

        //Within the feature file all scenarios will run parallel
        @DataProvider(parallel = false)
        @Override
        public Object[][] scenarios() {

                return super.scenarios();
        }
}
