package org.raoxunrong.check;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.raoxunrong.domain.item.PlainTextItem;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.List;

import static org.raoxunrong.utils.CheckedItemStatistic.addCheckedItem;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 5/21/13
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpellingCheckerFirefoxPluginChecker implements SpellingChecker {

    @Override
    public void doCheck(CheckablePage page) {
        doSpellCheckAction(page);
        handleSpellCheckerInfo(page);
    }

    private void handleSpellCheckerInfo(CheckablePage page) {
        List<WebElement> elements = page.getWebDriver().findElements(By.id("//*[@class='misspelling']"));
        StringBuffer stringBuffer = new StringBuffer();
        for (WebElement webElement : elements) {
            stringBuffer.append(webElement.getText());
        }
        addCheckedItem(new PlainTextItem(page.getPageName(), (elements.size() == 0), stringBuffer.toString()));
    }

    private void doSpellCheckAction(CheckablePage page) {
        int spellCheckerIndex = 8;
        WebElement pageOperator = page.getWebDriver().findElement(By.id(page.getPageId()));
        Actions clickAction = new Actions(page.getWebDriver());
        clickAction.contextClick(pageOperator).perform();
        for(int i = 0; i < spellCheckerIndex; i++){
            clickAction.contextClick(pageOperator).sendKeys(Keys.DOWN).perform();
        }
        clickAction.sendKeys(Keys.RETURN).perform();
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


