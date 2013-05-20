package org.raoxunrong.utils;

import org.raoxunrong.domain.item.CheckedItem;

import java.util.HashMap;
import java.util.Map;

public final class AccessibilityStatistic {

    private AccessibilityStatistic() {
    }

    private static Map<String, CheckedItem> checkMap = new HashMap<String, CheckedItem>();

    public static void addFailedItem(String pageName, String description) {


    }

    public static void addPassedItem(String pageName) {

    }
}
