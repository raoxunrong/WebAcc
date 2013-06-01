package org.raoxunrong.domain.item;

public class ElementDescription {

    private String elementInfo;

    private String description;

    public ElementDescription(String elementInfo, String description) {
        this.description = description;
        this.elementInfo = elementInfo;
    }

    @Override
    public String toString() {
        return elementInfo + ':' + description;
    }
}
