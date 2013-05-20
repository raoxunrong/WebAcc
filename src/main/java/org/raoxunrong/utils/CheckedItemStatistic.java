package org.raoxunrong.utils;

import org.raoxunrong.domain.item.CheckedItem;

import java.util.*;

public final class CheckedItemStatistic {

    private static Set<CheckedItem> checkedItems = new LinkedHashSet<CheckedItem>();

    private CheckedItemStatistic() {
    }

    public static void addCheckedItem(CheckedItem checkedItem) {
        checkedItems.add(checkedItem);
    }

    public static Collection<CheckedItem> getStatisticInfo() {
        return Collections.unmodifiableCollection(checkedItems);
    }

}
