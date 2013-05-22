import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.raoxunrong.check.firefox.FirefoxWaveAccessibilityChecker;
import org.raoxunrong.domain.item.CheckedItem;
import org.raoxunrong.domain.page.CheckablePage;
import org.raoxunrong.profile.FirefoxProfileHandle;
import org.raoxunrong.utils.CheckedItemStatistic;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class DemoTest {

    public static void main(String[] args) throws IOException {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile = new FirefoxProfileHandle().installWAVE(firefoxProfile);

        FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxProfile);
        firefoxDriver.get("http://baidu.com");

        FirefoxWaveAccessibilityChecker waveAccessibilityChecker = new FirefoxWaveAccessibilityChecker();

        BaiduPage baiduPage = new BaiduPage(firefoxDriver);
        waveAccessibilityChecker.doCheck(baiduPage);

        WebElement inputBox = firefoxDriver.findElement(By.id("kw"));
        inputBox.sendKeys("suncorp");
        firefoxDriver.findElement(By.id("su")).submit();

        BaiduResultPage baiduResultPage = new BaiduResultPage(firefoxDriver);
        waveAccessibilityChecker.doCheck(baiduResultPage);


        Collection<CheckedItem> statisticInfo = CheckedItemStatistic.getStatisticInfo();
        Iterator<CheckedItem> iterator = statisticInfo.iterator();
        while(iterator.hasNext()){
            CheckedItem checkedItem = iterator.next();
            System.out.println(checkedItem.getItemName());
            System.out.println(checkedItem.getAdditionalInfo());
            System.out.println("-------------");
        }

    }

}

class BaiduPage implements CheckablePage {
    private WebDriver webDriver;

    BaiduPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public String getPageId() {
        return "wrapper";
    }

    @Override
    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    public String getPageName() {
        return "Baidu Home Page";
    }
}

class BaiduResultPage implements  CheckablePage {
    private WebDriver webDriver;

    BaiduResultPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public String getPageId() {
        return "out";
    }

    @Override
    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    public String getPageName() {
        return "Baidu Result Page";
    }
}
