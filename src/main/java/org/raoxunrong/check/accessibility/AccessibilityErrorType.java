package org.raoxunrong.check.accessibility;

public enum AccessibilityErrorType {
    AltMissing("Alternative text is not present for this element."),
    LabelMissing("A form element does not have a corresponding label or has more than 1 corresponding label."),
    TitleMissing("The title is missing or not descriptive for the page or frame."),
    AnchorMissing("A anchor link exists, but the anchor for the link does not exist."),
    LabelOrphaned("A form label is present, but it is not associated with any form element");

    private final String description;

    private AccessibilityErrorType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
