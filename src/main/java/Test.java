import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.raoxunrong.check.WaveAccessibilityChecker;
import org.raoxunrong.domain.page.CheckablePage;
import org.raoxunrong.profile.FirefoxProfileHandle;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 5/18/13
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile = new FirefoxProfileHandle().installWAVE(firefoxProfile);


        FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxProfile);
        firefoxDriver.get("http://baidu.com");

        WaveAccessibilityChecker waveAccessibilityChecker = new WaveAccessibilityChecker();

        BaiduPage baiduPage = new BaiduPage(firefoxDriver);
        waveAccessibilityChecker.doCheck(baiduPage);

    }

}

class BaiduPage implements CheckablePage {
    private  WebDriver webDriver;

    public BaiduPage(WebDriver webDriver) {
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
