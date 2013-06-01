package org.raoxunrong.check.accessibility.filter.predicate;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LabelOrphanedPredicate extends WebElementPredicate{
    public LabelOrphanedPredicate(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean apply(WebElement webElement) {
        boolean labelOrphaned = false;
        String targetElement = webElement.getAttribute("for");
        if (StringUtils.isEmpty(targetElement)) {
            labelOrphaned = true;
        } else {
            try {
                getWebDriver().findElement(By.id(targetElement));
            } catch (Exception e) {
                labelOrphaned = true;
            }
        }
        return labelOrphaned;
    }
}
