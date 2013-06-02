package org.raoxunrong.check.accessibility.filter.factory;

import org.raoxunrong.check.accessibility.AccessibilityType;
import org.raoxunrong.check.accessibility.filter.WebElementFilter;

public interface WebElementsFilterFactory {

    WebElementFilter buildFilter(AccessibilityType type);
}
