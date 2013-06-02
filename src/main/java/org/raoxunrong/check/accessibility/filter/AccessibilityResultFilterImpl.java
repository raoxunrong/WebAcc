package org.raoxunrong.check.accessibility.filter;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringEscapeUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.raoxunrong.check.accessibility.AccessibilityType;
import org.raoxunrong.check.accessibility.filter.factory.WebElementsFilterFactory;
import org.raoxunrong.check.accessibility.filter.factory.WebElementsFilterFactoryImpl;
import org.raoxunrong.domain.item.ElementDescription;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.Collection;

public class AccessibilityResultFilterImpl implements AccessibilityResultFilter<ElementDescription> {

    private WebElementsFilterFactory filterFactory;

    public AccessibilityResultFilterImpl() {
        this.filterFactory = new WebElementsFilterFactoryImpl();
    }

    public AccessibilityResultFilterImpl(WebElementsFilterFactory filterFactory) {
        this.filterFactory = filterFactory;
    }

    @Override
    public Collection<ElementDescription> filterErrorElements(CheckablePage page, AccessibilityType errorType) {
        WebDriver webDriver = page.getWebDriver();
        Collection<WebElement> filteredElements = filterFactory.buildFilter(errorType).filter(page);
        Collection<ElementDescription> elementDescriptions = constructErrorDescription(filteredElements, errorType, (JavascriptExecutor) webDriver);
        return elementDescriptions;
    }

    private Collection<ElementDescription> constructErrorDescription(Collection<WebElement> webElements, AccessibilityType accessibilityType, JavascriptExecutor jsExecutor) {
        Collection<ElementDescription> description = Lists.newArrayList();
            for (WebElement element : webElements) {
            String elementStr = getElementOuterHTML(jsExecutor, element);
            description.add(new ElementDescription(elementStr, accessibilityType.getDescription()));
        }
        return description;
    }

    private String getElementOuterHTML(JavascriptExecutor jsExecutor, WebElement element) {
        return StringEscapeUtils.escapeHtml((String) (jsExecutor.executeScript("return arguments[0].outerHTML", element)));
    }

}
