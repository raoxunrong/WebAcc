package org.raoxunrong.check.accessibility.firefox;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.raoxunrong.check.PageChecker;
import org.raoxunrong.domain.item.ListItem;
import org.raoxunrong.domain.item.PlainTextItem;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.ArrayList;
import java.util.List;

import static org.raoxunrong.check.CheckType.AccessibilityCheck;
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

        List<String> checkedItemList = new ArrayList<String>();
        for (WebElement webElement : elements) {
            StringBuffer accessibilityError = new StringBuffer();
            accessibilityError.append(webElement.getAttribute("targetelement").trim()).append(": ");
            accessibilityError.append(webElement.getAttribute("alt").trim());

            checkedItemList.add(accessibilityError.toString());
        }

        addCheckedItem(new ListItem(page.getPageName(), (checkedItemList.size() == 0), checkedItemList, AccessibilityCheck));
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
