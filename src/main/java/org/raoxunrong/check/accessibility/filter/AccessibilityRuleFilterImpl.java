package org.raoxunrong.check.accessibility.filter;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.raoxunrong.check.accessibility.AccessibilityErrorType;
import org.raoxunrong.check.accessibility.filter.predicate.AnchorMissingPredicate;
import org.raoxunrong.check.accessibility.filter.predicate.LabelMissingPredicate;
import org.raoxunrong.check.accessibility.filter.predicate.LabelOrphanedPredicate;
import org.raoxunrong.domain.item.ElementDescription;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.Collection;
import java.util.List;

public class AccessibilityRuleFilterImpl implements AccessibilityRuleFilter {

    @Override
    public Collection<ElementDescription> filterErrorElements(CheckablePage page, AccessibilityErrorType errorType) {
        WebDriver webDriver = page.getWebDriver();
        Collection<WebElement> altMissingAltElements = filterElementsByErrorType(webDriver, errorType);
        Collection<ElementDescription> elementDescriptions = constructErrorDescription(altMissingAltElements, errorType, (JavascriptExecutor) webDriver);
        return elementDescriptions;
    }

    private Collection<WebElement> filterElementsByErrorType(WebDriver webDriver, AccessibilityErrorType errorType) {
        Collection<WebElement> errorElements = Lists.newArrayList();
        switch (errorType) {
            case AltMissing:
                errorElements = filterAltMissingElements(webDriver);
                break;
            case LabelMissing:
                errorElements = filterLabelMissingElements(webDriver);
                break;
            case LabelOrphaned:
                errorElements = filterLabelOrphanedElements(webDriver);
                break;
            case TitleMissing:
                errorElements = filterTitleMissingElements(webDriver);
                break;
            case AnchorMissing:
                errorElements = filterAnchorMissingElements(webDriver);
                break;
        }
        return errorElements;
    }

    private Collection<WebElement> filterAnchorMissingElements(WebDriver webDriver) {
        List<WebElement> anchorMissingCandidates = webDriver.findElements(By.xpath("//a[starts-with(@href, '#')]"));
        return Collections2.filter(anchorMissingCandidates, new AnchorMissingPredicate(webDriver));
    }

    private Collection<WebElement> filterTitleMissingElements(WebDriver webDriver) {
        return webDriver.findElements(By.xpath("//frame[not(@title)] | //frame[@title=''] | //head[not(title)]"));
    }

    private Collection<WebElement> filterLabelOrphanedElements(WebDriver webDriver) {
        List<WebElement> labelOrphanedCandidates = webDriver.findElements(By.xpath("//label"));
        return Collections2.filter(labelOrphanedCandidates, new LabelOrphanedPredicate(webDriver));
    }

    private Collection<WebElement> filterLabelMissingElements(WebDriver webDriver) {
        final String inputElementXPath = "//input[not(@type)] | //input[@type='checkbox' or @type='password' or @type='radio' or @type='text' or @type='']";
        final String selectElementXPath = "//select";
        final String textAreaElementXPath = "//textarea";
        List<WebElement> labelMissingCandidates = webDriver.findElements(By.xpath(inputElementXPath + " | " + selectElementXPath + " | " + textAreaElementXPath));

        return Collections2.filter(labelMissingCandidates, new LabelMissingPredicate(webDriver));
    }

    private Collection<WebElement> filterAltMissingElements(WebDriver webDriver) {
        return webDriver.findElements(By.xpath("//img[not(@alt)] | //img[@alt=''] | //map/area[not(@alt)] | //map/area[@alt='']"));
    }

    private Collection<ElementDescription> constructErrorDescription(Collection<WebElement> webElements, AccessibilityErrorType accessibilityErrorType, JavascriptExecutor jsExecutor) {
        Collection<ElementDescription> description = Lists.newArrayList();
            for (WebElement element : webElements) {
            String elementStr = getElementOuterHTML(jsExecutor, element);
            description.add(new ElementDescription(elementStr, accessibilityErrorType.getDescription()));
        }
        return description;
    }

    private String getElementOuterHTML(JavascriptExecutor jsExecutor, WebElement element) {
        return StringEscapeUtils.escapeHtml((String) (jsExecutor.executeScript("return arguments[0].outerHTML", element)));
    }

}
