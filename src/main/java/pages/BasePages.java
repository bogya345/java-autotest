package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class BasePages {

    public static final Logger _logger = LogManager.getLogger(BasePages.class);

    public BasePages() {
        PropertyConfigurator.configure("D:\\#Work\\autotests\\Test-task\\project\\src\\log4j.properties");
        _logger.info("Initialize BasePages");
    }

    // in footer click on 223-fl - suppliers (by xpath)
    public void clickLink() {
//        $(By.xpath("//*[@id=\"footer-common\"]/section[1]/div/div[2]/div/nav[2]/ul[1]/li[1]/a")).click();
        $(By.xpath("//div[@id='footer']//child::*[text()='Поставщикам']")).click();
    }

    // click on panel (to-do - make that i can click via text in div)
    public void clickPanel() {
//        $(By.xpath("//*[@id=\"dnn_ctr1253_HtmlModule_lblContent\"]/div/div/div[1]/a")).click();
        $(By.xpath("//*[text()='Расширенный поиск']")).click();
    }

    //
    public void clickOnSearchSettings() {
//        $(By.xpath("//*[@id=\"root\"]/div/div\n")).click();
        $(By.xpath("//*[text()='Настройка поиска']")).click();
    }

    //
    public void chooseAllRules() {
//        $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[2]/div[2]/a[1]")).click();
        $(By.xpath("//*[text()='Правило проведения']//ancestor::*[contains(@class, 'section')]//child::*[contains(text(), 'Выбрать')]")).click();
    }

    //
    public void chooseBehaiverRules(String[] args) {
        // disable all combobox
//        $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[2]/div[2]/a[2]")).click();
        $(By.xpath("//*[text()='Правило проведения']//ancestor::*[contains(@class, 'section')]//child::*[contains(translate(text(), 'СНЯТЬ','снять'), 'снять')]")).click();

        for (String i : args) {
            $(By.xpath("//label[.='" + i + "']")).click();
        }

    }

    //
    public void chooseOnQuickSettings(String[] args) {
        for (String i : args) {
            $(By.xpath("//label[.='" + i + "']")).click();
        }
    }

    //
    public void setUntilDate(String arg) {
        //collapse div
        WebElement parent_div = $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[12]/div/div"));
        parent_div.click();

        WebElement dateUntil = $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[12]/div[2]/div/div/div[1]/div[2]/div[3]/div/div/input"));
        dateUntil.sendKeys(arg + "\n");
    }

    //
    public void chooseRegions(String[] args) {

        //collapse div
        WebElement parent_div = $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[7]/div[1]/div"));
        parent_div.click();

        // disable all options
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
    public void clickSearchBtn() {
        $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[6]/div/div/button")).click();
    }

    //
    public List<Double> accumPurchases() throws Exception {

        WebElement parent_div = $(By.xpath("//*[@id=\"content\"]"));

        List<WebElement> childs = parent_div.findElements(By.xpath("./child::div[contains(@class, 'card')]"));

        List<Double> accum;
        if (childs.size() > 0) {
            accum = new LinkedList<Double>();
        } else {
            throw new Exception("Where are my children???");
        }

        for (WebElement i : childs) {
            WebElement item_props = i.findElement(By.className("card-item__properties-desc"));
            String startPrice_str = item_props.getAttribute("content");

//            List<Integer> accum_counts = new LinkedList<Integer>();
//            WebElement table = i.findElement(By.className("card-table"));
//            List<WebElement> item_rows = table.findElements(By.tagName("tr"));
//            item_rows.remove(0);
//            for (WebElement j : item_rows) {
//                WebElement td = j.findElement(By.xpath("./td[2]"));
//                String count = td.getText();
//                System.out.println(count);
//                try{
//                    accum_counts.add(Integer.parseInt(count));
//                }
//                catch (Exception e){
//                    continue;
//                }
//            }

            accum.add(Double.parseDouble(startPrice_str));
        }

        System.out.println(accum);
        return accum;
    }

    public void saveAsTxtFile(String filename, List<Double> items) {
        try {

            File myObj = new File(filename + ".txt");
            if (myObj.createNewFile()) {
                _logger.info("File created: " + myObj.getName());
            }
            else {
                _logger.info("File already exists");
            }

            FileWriter myWriter = new FileWriter(filename + ".txt");

            _logger.info("==========\tPurchases info start\t==========");
            int index = 0;
            for (Double i : items) {
                String item_message = "Purchase #" + index + "\t" + "Start Price = " + String.format("%,.2f", i) .replace(" ", " ") + "\n";
                myWriter.write(item_message);
                _logger.info(item_message);
                index++;
            }
            String sum_message = "Count of purchases: " + items.size();
            myWriter.write(sum_message);
            _logger.info(sum_message);
            myWriter.close();
            _logger.info("==========\tPurchases info end\t==========");

            _logger.info("Successfully wrote to the file");

            _logger.error("Test error", new Exception("Test exception"));

        } catch (IOException e) {
            _logger.info("An error occurred.", e);
        }
    }
}
