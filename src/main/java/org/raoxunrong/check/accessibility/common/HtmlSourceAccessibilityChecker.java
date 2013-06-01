package org.raoxunrong.check.accessibility.common;

import com.google.common.collect.Lists;
import org.raoxunrong.check.PageChecker;
import org.raoxunrong.check.accessibility.filter.AccessibilityRuleFilter;
import org.raoxunrong.check.accessibility.filter.AccessibilityRuleFilterImpl;
import org.raoxunrong.domain.item.CollectionItem;
import org.raoxunrong.domain.item.ElementDescription;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.Collection;

import static org.raoxunrong.check.CheckType.AccessibilityCheck;
import static org.raoxunrong.check.accessibility.AccessibilityErrorType.*;
import static org.raoxunrong.utils.CheckedItemStatistic.addCheckedItem;

public class HtmlSourceAccessibilityChecker implements PageChecker {

    private AccessibilityRuleFilter ruleFilter;

    public HtmlSourceAccessibilityChecker() {
        ruleFilter = new AccessibilityRuleFilterImpl();
    }

    @Override
    public void doCheck(CheckablePage page) throws Exception {
        Collection<ElementDescription> missingAltList = ruleFilter.filterErrorElements(page, AltMissing);
        Collection<ElementDescription> missingLabelList = ruleFilter.filterErrorElements(page, LabelMissing);
        Collection<ElementDescription> orphanedLabelList = ruleFilter.filterErrorElements(page, LabelOrphaned);
        Collection<ElementDescription> missingTitleList = ruleFilter.filterErrorElements(page, TitleMissing);
        Collection<ElementDescription> missingAnchorList = ruleFilter.filterErrorElements(page, AnchorMissing);

        Collection<ElementDescription> allCollection = Lists.newArrayList();
        allCollection.addAll(missingLabelList);
        allCollection.addAll(orphanedLabelList);
        allCollection.addAll(missingTitleList);
        allCollection.addAll(missingAnchorList);

        addCheckedItem(new CollectionItem(page.getPageName(), allCollection.size() == 0, allCollection, AccessibilityCheck));
    }

}
