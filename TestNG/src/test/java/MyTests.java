import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class MyTests {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void test1() {
        driver.get("https://www.trendyol.com");

        WebElement cerezKabul = driver.findElement(By.id("onetrust-accept-btn-handler"));
        cerezKabul.click();

        WebElement aramaKutusu = driver.findElement(By.className("V8wbcUhU"));
        aramaKutusu.sendKeys("iPhone"+Keys.ENTER);

        WebElement bilgiKapat = driver.findElement(By.className("overlay"));
        bilgiKapat.click();

        String listelemeYazisi = driver.findElement(By.className("dscrptn-V2")).getText();
        Assert.assertNotNull(listelemeYazisi);

        WebElement urunTikla = driver.findElement(By.className("best-seller-title"));
        urunTikla.click();

        List<String> tabs = new java.util.ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        WebElement bilgiKapat2 = driver.findElement(By.className("onboarding-button"));
        bilgiKapat2.click();

        WebElement addToBasket = driver.findElement(By.className("add-to-basket-button-text"));
        addToBasket.click();

        String basketCounter = driver.findElement(By.className("basket-item-count-container")).getText();
        Assert.assertFalse(basketCounter.equals("0"));
    }



    @AfterClass
    public void afterClass() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
    }


}
