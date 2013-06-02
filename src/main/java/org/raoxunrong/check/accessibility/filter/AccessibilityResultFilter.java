package org.raoxunrong.check.accessibility.filter;

import org.raoxunrong.check.accessibility.AccessibilityType;
import org.raoxunrong.domain.item.ElementDescription;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.Collection;

public interface AccessibilityResultFilter<T> {

    Collection<T> filterErrorElements(CheckablePage page, AccessibilityType errorType);

}
