package org.raoxunrong.domain.item;

import org.raoxunrong.check.CheckType;

import java.util.List;

public class ListItem extends CheckedItem<List>{

    public ListItem(String itemName, boolean pass, List additionalInfo, CheckType checkType) {
        super(itemName, pass, additionalInfo, checkType);
    }
}
