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
import java.util.concurrent.TimeUnit;

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

    enum DatepeackerType {
        SubApps_from,
        SubApps_until,
        Publish_from,
        Pulish_until
    }

    //
    public void setDate(String arg, DatepeackerType typeDatepeaker) {

        //collapse div
        WebElement parent_div = $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[12]/div/div"));
//        WebElement parent_div = $(By.xpath("//*[text()='Фильтры по датам']//ancestor::*[contains(@class, 'section')]"));
        parent_div.click();

//        List<WebElement> list = parent_div.findElements(By.xpath("//child::input"));
//        int selectedDatepeaker = -1;
//
//        switch (typeDatepeaker) {
//            case SubApps_until:
//                selectedDatepeaker = 2;
//                break;
//        }
//
//        list.get(selectedDatepeaker).sendKeys(arg + "\n");

        WebElement dateUntil = $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[12]/div[2]/div/div/div[1]/div[2]/div[3]/div/div/input"));
//        WebElement dateUntil = parent_div.findElement(By.xpath("//div[text()='ПОДАЧА ЗАЯВОК']/parent::*//child::input")); //[2]
        dateUntil.sendKeys(arg + "\n");
    }

    //
    public void chooseRegions(String[] args) {

        //collapse div
        WebElement parent_div = $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[7]/div[1]/div"));
//        WebElement parent_div = $(By.xpath("//*[text()='Регион поставки']//ancestor::*[contains(@class, 'section')]"));
        parent_div.click();

        // disable all options
//        $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[7]/div[2]/a[2]")).click();
        parent_div.findElement(By.xpath("//child::*[contains(translate(text(), 'СНЯТЬ','снять'), 'снять')]")).click();

        WebElement searcher = $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[7]/div[3]/div[1]/input"));
//        WebElement searcher = parent_div.findElement(By.xpath("//child::input[text()='Регион поставки']"));

        for (String i : args) {
            searcher.sendKeys(i);

            WebElement results = $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[5]/div[7]/div[3]/div[1]/div/div"));
//            WebElement results = parent_div.findElement(By.xpath("//child::div[contains(text(), 'cstm-search__autocomplite')]"));
//            WebElement results = parent_div.findElement(By.xpath("//child::div[text()='cstm-search__autocomplite']"));

            results.findElement(By.xpath("./a[text()='" + i + "']")).click();

            searcher.clear();
        }
    }

    //
    public void clickSearchBtn() {
//        $(By.xpath("//*[@id=\"ftfbn-portal\"]/div[2]/div/div/div/div[1]/div[6]/div/div/button")).click();
        $(By.xpath("//div[@class='bottomCenterSearch']//child::button[text()='Найти']")).click();
    }

    private List<WebElement> awaitGetElementsFromParent(WebElement parent, String request, int repeats) throws Exception {

        List<WebElement> childs = parent.findElements(By.xpath(request));

        int times = 1;
        while (childs.size() == 0 && times <= repeats) {
            _logger.info("Empty list is empty. Waiting while they appear... Repeats " + times + "of 3");
            TimeUnit.SECONDS.sleep(1);
            childs = parent.findElements(By.xpath("./child::div[contains(@class, 'card')]"));
            times++;
        }

        if (times == repeats) {
            throw new Exception("Cannot get children from parent");
        }

        return childs;
    }

    //
    public List<Double> accumPurchases() throws Exception {

        WebElement parent_div = $(By.xpath("//*[@id=\"content\"]"));

        List<WebElement> childs = awaitGetElementsFromParent(parent_div, "./child::div[contains(@class, 'card')]", 3);

        List<Double> accum = new LinkedList<Double>();

        for (WebElement i : childs) {
            WebElement item_props = i.findElement(By.className("card-item__properties-desc"));
            String startPrice_str = item_props.getAttribute("content");
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

            FileWriter myWriter = new FileWriter(filename + ".txt");

            _logger.info("==========\tPurchases info start\t==========");
            int index = 0;
            for (Double i : items) {
                String item_message = "Purchase #" + index + "\t" + "Start Price = " + String.format("%,.2f", i).replace(" ", " ") + "\n";
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
            _logger.info("Unhandled exception during save to the file", e);
//            throw new Exception("Unhandled exception during save to the file");
        }
    }
}
