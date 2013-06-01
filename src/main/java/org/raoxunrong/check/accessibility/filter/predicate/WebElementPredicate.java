package org.raoxunrong.check.accessibility.filter.predicate;

import com.google.common.base.Predicate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class WebElementPredicate implements Predicate<WebElement> {

    private WebDriver webDriver;

    protected WebElementPredicate(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
