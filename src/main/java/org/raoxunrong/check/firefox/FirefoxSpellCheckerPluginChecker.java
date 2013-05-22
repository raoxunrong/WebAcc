package org.raoxunrong.check.firefox;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.raoxunrong.check.PageChecker;
import org.raoxunrong.domain.item.PlainTextItem;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.List;

import static org.raoxunrong.utils.CheckedItemStatistic.addCheckedItem;

public class FirefoxSpellCheckerPluginChecker implements PageChecker {

    @Override
    public void doCheck(CheckablePage page) {
        doSpellCheckerActionEvent(page);
        handleSpellCheckerInfo(page);
    }

    private void handleSpellCheckerInfo(CheckablePage page) {
        List<WebElement> elements = page.getWebDriver().findElements(By.className("misspelling"));
        StringBuffer stringBuffer = new StringBuffer();
        for (WebElement webElement : elements) {
            System.out.println(webElement.getText());
            stringBuffer.append(webElement.getText()).append("\n");
        }
        addCheckedItem(new PlainTextItem(page.getPageName(), (elements.size() == 0), stringBuffer.toString()));
    }

    private void doSpellCheckerActionEvent(CheckablePage page){
        ((JavascriptExecutor) page.getWebDriver()).executeScript(withCheckScript(page.getPageId()));
    }

    private String withCheckScript(String pageId) {
        return withDefaultScript(pageId, "CheckSpellingEvent");
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


