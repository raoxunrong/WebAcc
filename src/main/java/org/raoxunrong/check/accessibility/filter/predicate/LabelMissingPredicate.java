package org.raoxunrong.check.accessibility.filter.predicate;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LabelMissingPredicate extends WebElementPredicate {

    public LabelMissingPredicate(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean apply(WebElement candidate) {
        boolean labelMissing = false;
        String id = candidate.getAttribute("id");
        if (StringUtils.isEmpty(id)) {
            labelMissing = true;
        } else {
            List<WebElement> elements = getWebDriver().findElements(By.xpath("//label[@for='" + id + "']"));
            if (elements.size() != 1) {
                labelMissing = true;
            }
        }

        return labelMissing;
    }

}
