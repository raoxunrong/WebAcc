package org.raoxunrong.check.accessibility.filter.factory;

import org.raoxunrong.check.accessibility.AccessibilityType;
import org.raoxunrong.check.accessibility.filter.*;
import org.raoxunrong.check.accessibility.filter.factory.WebElementsFilterFactory;

public class WebElementsFilterFactoryImpl implements WebElementsFilterFactory {

    @Override
    public WebElementFilter buildFilter(AccessibilityType type){

        WebElementFilter filter = null;
        switch (type) {
            case AltMissing:
                filter = new AltMissingFilter();
                break;
            case LabelMissing:
                filter = new LabelMissingFilter();
                break;
            case LabelOrphaned:
                filter = new LabelOrphanedFilter();
                break;
            case TitleMissing:
                filter = new TitleMissingFilter();
                break;
            case AnchorMissing:
                filter = new AnchorMissingFilter();
                break;
            case ElementEmpty:
                filter = new EmptyElementsFilter();
                break;
            case ElementDeprecated:
                filter = new DeprecatedElementsFilter();
                break;
        }
        return filter;
    }
}
