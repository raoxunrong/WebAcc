package org.raoxunrong.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.raoxunrong.check.accessibility.common.HtmlSourceAccessibilityChecker;
import org.raoxunrong.domain.page.CheckablePage;
import org.raoxunrong.utils.CheckedItemStatistic;

import static org.raoxunrong.check.CheckType.AccessibilityCheck;

public class HtmlSourceAccessibilityCheckerDemo {

    public static void main(String[] args) throws Exception {

        FirefoxDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.get("file:///Users/twer/Desktop/NavigationLink");

        HtmlSourceAccessibilityChecker accessibilityChecker = new HtmlSourceAccessibilityChecker();

        LocalTestPage localTestPage = new LocalTestPage(firefoxDriver);
        accessibilityChecker.doCheck(localTestPage);

        CheckedItemStatistic.generateReport("/Users/twer", AccessibilityCheck);

    }

}

class LocalTestPage implements CheckablePage {

    private WebDriver  webDriver;

    LocalTestPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public String getPageId() {
        return "sizer";
    }

    @Override
    public WebDriver getWebDriver() {
        return webDriver
                ;
    }

    @Override
    public String getPageName() {
        return "Local Test Page Name";
    }
}
