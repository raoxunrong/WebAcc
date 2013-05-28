package org.raoxunrong.domain.item;

import org.raoxunrong.check.CheckType;

public class CheckedItem<T> {

    private String itemName;

    private boolean pass;

    private T additionalInfo;

    public CheckType getCheckType() {
        return checkType;
    }

    private CheckType checkType;

    public CheckedItem(String itemName, boolean pass, T additionalInfo, CheckType checkType) {
        this.itemName = itemName;
        this.pass = pass;
        this.additionalInfo = additionalInfo;
        this.checkType = checkType;
    }

    public T getAdditionalInfo() {
        return additionalInfo;
    }

    public boolean isPass() {
        return pass;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckedItem)) return false;

        CheckedItem that = (CheckedItem) o;

        if (!itemName.equals(that.itemName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return itemName.hashCode();
    }
}
