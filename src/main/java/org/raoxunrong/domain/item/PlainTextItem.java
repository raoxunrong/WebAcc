package org.raoxunrong.domain.item;

import org.raoxunrong.check.CheckType;

public class PlainTextItem extends CheckedItem<String>{

    public PlainTextItem(String itemName, boolean pass, String additionalInfo, CheckType checkType) {
        super(itemName, pass, additionalInfo, checkType);
    }
}
