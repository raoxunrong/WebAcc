package org.raoxunrong.check.accessibility.filter;

import com.google.common.collect.Collections2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.raoxunrong.check.accessibility.filter.predicate.LabelMissingPredicate;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.Collection;
import java.util.List;

public class LabelMissingFilter implements WebElementFilter{

    @Override
    public Collection<WebElement> filter(CheckablePage page) {
        WebDriver webDriver = page.getWebDriver();
        final String inputElementXPath = "//input[not(@type)] | //input[@type='checkbox' or @type='password' or @type='radio' or @type='text' or @type='']";
        final String selectElementXPath = "//select";
        final String textAreaElementXPath = "//textarea";
        List<WebElement> labelMissingCandidates = webDriver.findElements(By.xpath(inputElementXPath + " | " + selectElementXPath + " | " + textAreaElementXPath));

        return Collections2.filter(labelMissingCandidates, new LabelMissingPredicate(webDriver));
    }
}
