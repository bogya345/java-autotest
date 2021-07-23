package pages;

import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Character.isDigit;

public class FirstTest {

    private static final Logger _logger = LogManager.getLogger(SecondTest.class);

    BasePages basicPage = new BasePages();

    public FirstTest() {
        PropertyConfigurator.configure("D:\\#Work\\autotests\\Test-task\\project\\src\\log4j.properties");
        _logger.info("Initialize FirstTest");
    }


    @Then("Click selected link")
    public void clickSelectedLink() {
        basicPage.clickLink();
    }

    @Then("Click advanced search")
    public void clickAdvancedSearch() throws InterruptedException {
        basicPage.clickPanel();
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("Click on search settings")
    public void clickOnSearchSettings() throws InterruptedException {
        basicPage.clickOnSearchSettings();
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("Choose all options in behavior rules")
    public void chooseAllOptionsInBehaviorRules() throws InterruptedException {
        basicPage.chooseAllRules();
        TimeUnit.SECONDS.sleep(1);
    }
    @Then("Choose only {string} in behavior rules")
    public void chooseOnlyInBehaviorRules(String arg0) throws InterruptedException {
        basicPage.chooseBehaiverRules(new String[] {arg0});
        TimeUnit.SECONDS.sleep(1);
    }

    @Then("Choose in quick settings {string}")
    public void chooseInQuickSettings(String arg0) throws InterruptedException {
        basicPage.chooseOnQuickSettings(new String[] {arg0});
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("Set until date on {string}")
    public void setUntilDateOn(String arg0) throws InterruptedException {
        if(isDigit(arg0.charAt(0))){
            basicPage.setUntilDate(arg0);
        }
        else{
            if(arg0 == "сегодня"){basicPage.setUntilDate("20-07-2021");}
        }
        TimeUnit.SECONDS.sleep(1);
    }

    @Then("Choose needed region {string}")
    public void chooseNeededRegion(String arg0) throws InterruptedException {
        basicPage.chooseRegions(new String[] {arg0});
        TimeUnit.SECONDS.sleep(1);
    }

    @Then("Click on search button")
    public void clickOnSearchButton() throws InterruptedException {
        basicPage.clickSearchBtn();
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("Save the results to a file called {string}")
    public void saveTheResultsToAFileCalled(String arg0) throws Exception {
        List<Double> res = basicPage.accumPurchases();
        basicPage.saveAsTxtFile(arg0, res);
    }
}
