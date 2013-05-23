package org.raoxunrong.check;

public final class JavascriptGenerator {

    private JavascriptGenerator() {
    }

    public static String generateDispatchEventScript(final String elementId, final String eventId) {
        final String createEvent = "var evt = document.createEvent(\"Events\");";
        final String initEvent = "evt.initEvent(\"" + eventId + "\", true, false);";
        final String findElement = "var element = document.getElementById(\"" + elementId + "\");";
        final String dispatchEvent = "element.dispatchEvent(evt);";
        StringBuffer scriptString = new StringBuffer().append(createEvent).append(initEvent).append(findElement).append(dispatchEvent);
        return scriptString.toString();
    }
}
