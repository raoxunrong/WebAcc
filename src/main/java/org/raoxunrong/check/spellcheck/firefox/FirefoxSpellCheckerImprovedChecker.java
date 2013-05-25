package org.raoxunrong.check.spellcheck.firefox;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.raoxunrong.check.PageChecker;
import org.raoxunrong.check.spellcheck.CustomisedDictionary;
import org.raoxunrong.domain.item.PlainTextItem;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.List;

import static org.raoxunrong.check.JavascriptGenerator.generateDispatchEventScript;
import static org.raoxunrong.utils.CheckedItemStatistic.addCheckedItem;

public class FirefoxSpellCheckerImprovedChecker implements PageChecker {

    private CustomisedDictionary customisedDictionary;

    public FirefoxSpellCheckerImprovedChecker() {
    }

    public FirefoxSpellCheckerImprovedChecker(CustomisedDictionary customisedDictionary) {
        this.customisedDictionary = customisedDictionary;
    }

    @Override
    public void doCheck(CheckablePage page) {
        doSpellCheckerActionEvent(page);
        handleSpellCheckerInfo(page);
    }

    private void handleSpellCheckerInfo(CheckablePage page) {
        List<WebElement> elements = page.getWebDriver().findElements(By.className("misspelling"));
        StringBuffer stringBuffer = new StringBuffer();
        String errorWord;
        for (WebElement webElement : elements) {
            errorWord = extractWordFromElement(webElement);
            if (!isCustomisedWord(errorWord)) {
                stringBuffer.append(errorWord).append("\n");
            }
        }
        String errorSpellWords = stringBuffer.toString();
        addCheckedItem(new PlainTextItem(page.getPageName(), (errorSpellWords.length() == 0), errorSpellWords));
    }

    private String extractWordFromElement(WebElement webElement) {
        String errorWord = webElement.getText().trim();
        if (errorWord.isEmpty() && !webElement.getAttribute("innerHTML").isEmpty()) {
            errorWord = webElement.getAttribute("innerHTML");
        }
        return errorWord;
    }

    private boolean isCustomisedWord(String errorWord) {
        return (customisedDictionary != null) ? customisedDictionary.isWord(errorWord) : false;
    }

    private void doSpellCheckerActionEvent(CheckablePage page) {
        ((JavascriptExecutor) page.getWebDriver()).executeScript(withCheckScript(page.getPageId()));
    }

    private String withCheckScript(String pageId) {
        return generateDispatchEventScript(pageId, "CheckSpellingEvent");
    }

}


