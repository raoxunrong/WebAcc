package org.raoxunrong.check.accessibility.filter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.Collection;

public class DeprecatedElementsFilter implements WebElementFilter {

    @Override
    public Collection<WebElement> filter(CheckablePage page) {
        return page.getWebDriver().findElements(By.xpath("//marquee | //blink"));
    }
}
