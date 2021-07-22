package package1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class main {

    public static void main(String[] args){

        System.setProperty("webdriver.chrome.driver", "D:\\#Work\\autotests\\plugins\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("");

        System.out.println(driver.getTitle());
        driver.quit();

    }

}
