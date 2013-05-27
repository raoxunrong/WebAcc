package org.raoxunrong.check.spellcheck.firefox;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.raoxunrong.check.spellcheck.dic.CustomisedDictionary;
import org.raoxunrong.check.spellcheck.dic.DefaultCustomisedDictionary;
import org.raoxunrong.check.spellcheck.PageSpellChecker;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.ArrayList;
import java.util.List;

import static org.raoxunrong.check.JavascriptGenerator.generateDispatchEventScript;

public class FirefoxSpellCheckerImprovedChecker extends PageSpellChecker {

    private CustomisedDictionary customisedDictionary;

    public FirefoxSpellCheckerImprovedChecker() {
        this.customisedDictionary = new DefaultCustomisedDictionary();
    }

    public FirefoxSpellCheckerImprovedChecker(CustomisedDictionary customisedDictionary) {
        this.customisedDictionary = customisedDictionary;
    }

    @Override
    protected List<String> filterCustomisedWords(List<String> sourceWrongWords) {
        return sourceWrongWords;
    }

    @Override
    protected CustomisedDictionary getCustomisedDictionary() {
        return customisedDictionary;
    }

    @Override
    protected List<String> extractWrongWordsFromPage(CheckablePage page) throws Exception {
        List<String> wrongWords = new ArrayList<String>();
        doSpellCheckerActionEvent(page);
        List<WebElement> elements = page.getWebDriver().findElements(By.className("misspelling"));
        for (WebElement webElement : elements) {
            wrongWords.add(extractWordFromElement(webElement));
        }
        return wrongWords;
    }

    private String extractWordFromElement(WebElement webElement) {
        String errorWord = webElement.getText().trim();
        if (errorWord.isEmpty() && !webElement.getAttribute("innerHTML").isEmpty()) {
            errorWord = webElement.getAttribute("innerHTML");
        }
        return errorWord;
    }

    private void doSpellCheckerActionEvent(CheckablePage page) {
        ((JavascriptExecutor) page.getWebDriver()).executeScript(withCheckScript(page.getPageId()));
    }

    private String withCheckScript(String pageId) {
        return generateDispatchEventScript(pageId, "CheckSpellingEvent");
    }

}


