package org.raoxunrong.check.accessibility.filter;

import org.openqa.selenium.WebElement;
import org.raoxunrong.check.accessibility.AccessibilityErrorType;
import org.raoxunrong.domain.item.ElementDescription;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.Collection;
import java.util.List;

public interface AccessibilityRuleFilter {

    Collection<ElementDescription> filterErrorElements(CheckablePage page, AccessibilityErrorType errorType);

}
