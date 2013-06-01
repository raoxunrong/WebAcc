package org.raoxunrong.check.accessibility.filter.predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AnchorMissingPredicate extends WebElementPredicate {

    public AnchorMissingPredicate(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean apply(WebElement webElement) {
        boolean anchorMissing = false;
        String hrefValue = webElement.getAttribute("href");
        String replace = hrefValue.substring(hrefValue.indexOf("#") + 1);
        try {
            getWebDriver().findElement(By.id(replace));
        } catch (Exception e) {
            anchorMissing = true;
        }
        return anchorMissing;
    }
}
