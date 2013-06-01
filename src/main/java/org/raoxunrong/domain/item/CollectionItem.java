package org.raoxunrong.domain.item;

import org.raoxunrong.check.CheckType;

import java.util.Collection;
import java.util.List;

public class CollectionItem extends CheckedItem<Collection>{

    public CollectionItem(String itemName, boolean pass, Collection additionalInfo, CheckType checkType) {
        super(itemName, pass, additionalInfo, checkType);
    }
}
