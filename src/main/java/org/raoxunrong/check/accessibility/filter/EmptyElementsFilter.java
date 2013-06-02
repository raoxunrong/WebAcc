package org.raoxunrong.check.accessibility.filter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.Collection;

public class EmptyElementsFilter implements WebElementFilter {
    @Override
    public Collection<WebElement> filter(CheckablePage page) {
        return page.getWebDriver().findElements(By.xpath("//th[not(text())] | //h1[not(text())] | //h2[not(text())] | //h3[not(text())] | //h4[not(text())] | //h5[not(text())] | //h6[not(text())] | //a[not(text()) and not(*)]"));
    }
}
