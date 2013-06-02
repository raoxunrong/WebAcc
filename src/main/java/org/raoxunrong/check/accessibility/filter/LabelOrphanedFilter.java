package org.raoxunrong.check.accessibility.filter;

import com.google.common.collect.Collections2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.raoxunrong.check.accessibility.filter.predicate.LabelOrphanedPredicate;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.Collection;
import java.util.List;

public class LabelOrphanedFilter implements WebElementFilter{
    @Override
    public Collection<WebElement> filter(CheckablePage page) {
        WebDriver webDriver = page.getWebDriver();
        List<WebElement> labelOrphanedCandidates = webDriver.findElements(By.xpath("//label"));
        return Collections2.filter(labelOrphanedCandidates, new LabelOrphanedPredicate(webDriver));
    }
}
