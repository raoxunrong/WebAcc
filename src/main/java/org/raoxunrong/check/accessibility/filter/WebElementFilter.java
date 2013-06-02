package org.raoxunrong.check.accessibility.filter;

import org.openqa.selenium.WebElement;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.Collection;

public interface WebElementFilter {
    Collection<WebElement> filter(CheckablePage page);
}
