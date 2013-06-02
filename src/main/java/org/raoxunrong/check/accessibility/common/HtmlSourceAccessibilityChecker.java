package org.raoxunrong.check.accessibility.common;

import com.google.common.collect.Lists;
import org.raoxunrong.check.PageChecker;
import org.raoxunrong.check.accessibility.filter.AccessibilityResultFilter;
import org.raoxunrong.check.accessibility.filter.AccessibilityResultFilterImpl;
import org.raoxunrong.domain.item.CollectionItem;
import org.raoxunrong.domain.item.ElementDescription;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.Collection;

import static org.raoxunrong.check.CheckType.AccessibilityCheck;
import static org.raoxunrong.check.accessibility.AccessibilityType.*;
import static org.raoxunrong.utils.CheckedItemStatistic.addCheckedItem;

public class HtmlSourceAccessibilityChecker implements PageChecker {

    private AccessibilityResultFilter ruleFilter;

    public HtmlSourceAccessibilityChecker() {
        ruleFilter = new AccessibilityResultFilterImpl();
    }

    @Override
    public void doCheck(CheckablePage page) throws Exception {
        Collection<ElementDescription> missingAltList = ruleFilter.filterErrorElements(page, AltMissing);
        Collection<ElementDescription> missingLabelList = ruleFilter.filterErrorElements(page, LabelMissing);
        Collection<ElementDescription> orphanedLabelList = ruleFilter.filterErrorElements(page, LabelOrphaned);
        Collection<ElementDescription> missingTitleList = ruleFilter.filterErrorElements(page, TitleMissing);
        Collection<ElementDescription> missingAnchorList = ruleFilter.filterErrorElements(page, AnchorMissing);
        Collection<ElementDescription> emptyElementList = ruleFilter.filterErrorElements(page, ElementEmpty);
        Collection<ElementDescription> deprecatedElementList = ruleFilter.filterErrorElements(page, ElementDeprecated);

        Collection<ElementDescription> allCollection = Lists.newArrayList();
        allCollection.addAll(missingAltList);
        allCollection.addAll(missingLabelList);
        allCollection.addAll(orphanedLabelList);
        allCollection.addAll(missingTitleList);
        allCollection.addAll(missingAnchorList);
        allCollection.addAll(emptyElementList);
        allCollection.addAll(deprecatedElementList);

        addCheckedItem(new CollectionItem(page.getPageName(), allCollection.size() == 0, allCollection, AccessibilityCheck));
    }

}
