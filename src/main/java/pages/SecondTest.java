package pages;

import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Character.isDigit;

public class SecondTest {

    private static final Logger _logger = LogManager.getLogger(SecondTest.class);

    public SecondTest() {
//        BasicConfigurator.configure();
        PropertyConfigurator.configure("D:\\#Work\\autotests\\Test-task\\project\\src\\log4j.properties");
        _logger.info("Init SecondTest");
    }

    BasePages2 acts = new BasePages2(_logger);

    @Then("Click advanced search from home")
    public void clickAdvancedSearchFromHome() throws InterruptedException {
        acts.clickAdvancedSearch();
        TimeUnit.SECONDS.sleep(1);
    }

    @Then("Choose only {string} in behavior rules from home")
    public void chooseOnlyInBehaviorRulesFromHome(String arg0) throws InterruptedException {
        acts.chooseRules(new String[]{arg0});
    }

    @Then("Set until date on {string} from home")
    public void setUntilDateOnFromHome(String arg0) throws Exception {
        if (isDigit(arg0.charAt(0))) {
            acts.setUntilDate(arg0);
        } else {
            String arg = "";
            if (arg0.equalsIgnoreCase("сегодня")) {
                Date now = new Date();
                SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-YYYY");
                arg = sdfDate.format(now);
            }

            if (arg == "") {
                throw new Exception("Unhandled argument");
            }
            acts.setUntilDate(arg);
        }
    }

    @Then("Choose region {string}")
    public void chooseRegion(String arg0) {
        acts.chooseRegions(new String[]{arg0});
    }

    @Then("Search right now")
    public void searchRightNow() throws InterruptedException {
        acts.findNow();
        TimeUnit.SECONDS.sleep(1);
    }

    @Then("Accumulate purchases")
    public void accumulatePurchases() throws Exception {
        acts.accumPurchases();
    }

    @Then("Create file with test values")
    public void createFileWithTestValues() throws IOException {
        _logger.info("Creating a file");
        String filename = "test";
        File myObj = new File(filename + ".txt");
        if (myObj.createNewFile()) {
            _logger.info("File created: " + myObj.getName());
        } else {
            _logger.info("File already exists.");
        }

//        FileWriter myWriter = new FileWriter(filename + ".txt");
        FileWriter myWriter = new FileWriter(myObj);

        _logger.info(myWriter.getEncoding());

        List<Double> items = Arrays.asList(10987760.20, 2000000.90, 202314.99);
        int index = 0;
        for (Double i : items) {
            String value_str = "Purchase #" + index + "\t" + "Start Price = " + String.format("%,.2f", i).replace(" ", " ") + "\n";
            myWriter.write(value_str);
            index++;
        }
        myWriter.write("Count of purchases: " + items.size());
        myWriter.close();

        // reading
//        File file = new File("C:\\Users\\pankaj\\Desktop\\test.txt");
//        File file = new File(myObj);

        BufferedReader br = new BufferedReader(new FileReader(myObj));

        String st;
        while ((st = br.readLine()) != null) {
            _logger.info(st);
        }

        _logger.info(acts.poof());
    }
}
