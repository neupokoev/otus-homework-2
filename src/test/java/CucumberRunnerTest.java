import io.cucumber.junit.platform.engine.Cucumber;

/*@RunWith(Cucumber.class)
@CucumberOptions(
    monochrome = true,
    features = "src/test/java/features",
    glue = {"logic","framework"},
    plugin = {"pretty","json:target/cucumber.json", "html:target/cucumber-html-report"},
    tags = {"~@Ignore"}
)*/
@Cucumber
public class CucumberRunnerTest {

}
