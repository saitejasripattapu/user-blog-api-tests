package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


    @CucumberOptions(features = {"src/test/java/features/APIAutomation.feature"},
            glue = "stepDefs", monochrome = true, tags = "@API",
            plugin = {"html:target/cucumber_api.html", "json:target/cucumber_api.json",
                    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:${version}"})
    public class TestRunner extends AbstractTestNGCucumberTests {
    }

