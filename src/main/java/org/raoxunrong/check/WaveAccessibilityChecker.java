package org.raoxunrong.check;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.raoxunrong.domain.item.PlainTextItem;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.List;

import static org.raoxunrong.utils.CheckedItemStatistic.addCheckedItem;

public class WaveAccessibilityChecker implements AccessibilityChecker {

    @Override
    public void doCheck(CheckablePage page) {
        doWaveCheckAction(page);
        handleAccessibilityInfo(page);
        resetPage(page);
    }

    private void handleAccessibilityInfo(CheckablePage page) {
        List<WebElement> elements = page.getWebDriver().findElements(By.xpath("//*[@class='wave4tip' and starts-with(@alt, 'ERROR:')]"));

        StringBuffer stringBuffer = new StringBuffer();
        for (WebElement webElement : elements) {
            stringBuffer.append(webElement.getAttribute("alt") + "\n");
        }

        addCheckedItem(new PlainTextItem(page.getPageName(), (elements.size() == 0), stringBuffer.toString()));
    }

    private void resetPage(CheckablePage page) {
        ((JavascriptExecutor) page.getWebDriver()).executeScript(withResetScript(page.getPageId()));
    }

    private void doWaveCheckAction(CheckablePage page) {
        ((JavascriptExecutor) page.getWebDriver()).executeScript(withCheckScript(page.getPageId()));
    }

    private String withResetScript(String pageId) {
        return withDefaultScript(pageId, "ResetEvent");
    }

    private String withCheckScript(String pageId) {
        return withDefaultScript(pageId, "CheckAccessibilityEvent");
    }

    private String withDefaultScript(String pageId, final String event) {
        final String createEvent = "var evt = document.createEvent(\"Events\");";
        final String initEvent = "evt.initEvent(\"" + event + "\", true, false);";
        final String findElement = "var element = document.getElementById(\"" + pageId + "\");";
        final String dispatchEvent = "element.dispatchEvent(evt);";
        StringBuffer scriptString = new StringBuffer().append(createEvent).append(initEvent).append(findElement).append(dispatchEvent);
        return scriptString.toString();
    }

}
