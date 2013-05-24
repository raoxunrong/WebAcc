package org.raoxunrong.check.accessibility.firefox;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.raoxunrong.check.PageChecker;
import org.raoxunrong.domain.item.PlainTextItem;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.List;

import static org.raoxunrong.check.JavascriptGenerator.generateDispatchEventScript;
import static org.raoxunrong.utils.CheckedItemStatistic.addCheckedItem;

public class FirefoxWaveAccessibilityChecker implements PageChecker {

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
            stringBuffer.append(webElement.getAttribute("targetelement")).append(": ");
            stringBuffer.append(webElement.getAttribute("alt"));
            stringBuffer.append("\n");
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
        return generateDispatchEventScript(pageId, "ResetEvent");
    }

    private String withCheckScript(String pageId) {
        return generateDispatchEventScript(pageId, "CheckAccessibilityEvent");
    }

}
