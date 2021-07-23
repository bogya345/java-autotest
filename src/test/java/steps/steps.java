package steps;

import io.cucumber.java.Before;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

public class steps {

    @Before
    public void openUrl() {
        System.setProperty("webdriver.chrome.driver", "D:\\#Work\\autotests\\plugins\\chromedriver.exe");
        browser = "chrome";
//        browser = "D:\\#Work\\autotests\\plugins\\chromedriver.exe";
//        browser = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";

        open("https://www.rts-tender.ru/");
        // to-do await while connected
    }

}
