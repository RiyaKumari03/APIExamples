package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(features = "src/test/java/features",
                 glue={"stepsDefinition"},
                 tags="@AddPlace or @DeletePlace",
                 plugin = {"html:target/cucumber/report.html"})
public class Runner extends AbstractTestNGCucumberTests{

}
