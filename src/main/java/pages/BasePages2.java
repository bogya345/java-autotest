package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class BasePages2 {

    public static final Logger _logger = LogManager.getLogger(BasePages2.class);

    public BasePages2(Logger logger){
//        BasicConfigurator.configure();
        PropertyConfigurator.configure("D:\\#Work\\autotests\\Test-task\\project\\src\\log4j.properties");
        _logger.info("Init Basepages2");
    }

    //
    public void clickAdvancedSearch() {
        $(By.xpath("/html/body/form/div[3]/div[2]/div/div/div[1]/div[1]/div[2]/div/div[1]/div/div/section/div/div[2]")).click();
    }

    //
    public void chooseRules(String[] args) throws InterruptedException {
        WebElement f = $(By.xpath("/html/body/div[7]/div[2]/div/div/div/div[1]/div[5]/div[2]/div[3]/div/div"));
        List<WebElement> fs = f.findElements(By.xpath("./child::div"));

        // disable all combobox
        $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[2]/div[2]/a[2]")).click();

        for (WebElement i : fs) {
            String text = i.getText();
            String text2 = args[0];

            WebElement el_rule = i.findElement(By.xpath("./label"));

            if (text.charAt(0) == text2.charAt(0)) {
                System.out.println("#" + text + "==" + args[0]);
                el_rule.click();
                if (args.length == 1) {
                    break;
                }
            }
        }

    }

    //
    public void setUntilDate(String arg) {

        //collapse div
        WebElement parent_div = $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[12]/div/div"));
        parent_div.click();

        WebElement dateUntil = $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[12]/div[2]/div/div/div[1]/div[2]/div[3]/div/div/input"));
        dateUntil.sendKeys(arg + "\n");

        return;
    }

    //
    public void chooseRegions(String[] args) {

        //collapse div
        WebElement parent_div = $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[7]/div[1]/div"));
        parent_div.click();

        // disable all
        $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[7]/div[2]/a[2]")).click();

        WebElement searcher = $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[7]/div[3]/div[1]/input"));

        for (String i : args) {
            searcher.sendKeys(i);

            WebElement results = $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[7]/div[3]/div[1]/div/div"));

            results.findElement(By.xpath("./a[text()='" + i + "']")).click();

            searcher.clear();
        }
    }

    //
    public void findNow() {
        $(By.xpath("//*[@id=\"searchBtn\"]")).click();
    }

    class Purchase {
        public String StartPrice;
        public List<String> Counts;
    }

    //
    public void accumPurchases() throws Exception {

        WebElement parent_div = $(By.xpath("//*[@id=\"content\"]"));

        List<WebElement> childs = parent_div.findElements(By.xpath("./child::div[contains(@class, 'card')]"));

        List<Purchase> accum;
        if (childs.size() > 0) {
            accum = new LinkedList<Purchase>();
        }
        else {
            throw new Exception("Where are my children???");
        }

        for (WebElement i : childs) {
            WebElement item_props = i.findElement(By.xpath("//div[contains(@class, 'card-item__properties-desc')]"));
//            WebElement item_props = i.findElement(By.xpath("./div[@class, 'card-item__properties-desc']"));
            String startPrice_str = item_props.getAttribute("content");



            List<String> accum_counts = new LinkedList<String>();
//            List<WebElement> item_rows = i.findElements(By.xpath("//table[contains(@class, 'card-table')]/tbody/child::tr"));
//            List<WebElement> item_rows = i.findElements(By.xpath("./child::table[contains(@class, 'card-table')]/tbody/child::tr"));
//            List<WebElement> item_rows = i.findElements(By.xpath("./child::table/tbody/child::tr"));

            WebElement table = i.findElement(By.className("card-table"));
            List<WebElement> item_rows = table.findElements(By.tagName("tr"));
            item_rows.remove(0);

            for(WebElement j : item_rows) {
                WebElement td = j.findElement(By.xpath("./td[2]"));
                String count = td.getText();
                accum_counts.add(count);
            }

            Purchase purchase = new Purchase();
            purchase.StartPrice = startPrice_str;
            purchase.Counts = accum_counts;
            accum.add(purchase);
        }

    }

    //
    public String poof() {
        _logger.info("Poof - Its a Magic");
        return "Poof";
    }
}
