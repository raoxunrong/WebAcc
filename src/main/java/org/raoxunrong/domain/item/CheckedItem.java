package org.raoxunrong.domain.item;

public class CheckedItem<T> {

    private boolean pass;

    private T additionalInfo;

    public CheckedItem(boolean pass, T additionalInfo) {
        this.pass = pass;
        this.additionalInfo = additionalInfo;
    }

    public T getAdditionalInfo() {
        return additionalInfo;
    }

    public boolean isPass() {
        return pass;
    }
}
