package org.raoxunrong.check.spellcheck;

import org.raoxunrong.check.PageChecker;
import org.raoxunrong.check.spellcheck.dic.CustomisedDictionary;
import org.raoxunrong.domain.item.PlainTextItem;
import org.raoxunrong.domain.page.CheckablePage;

import java.util.ArrayList;
import java.util.List;

import static org.raoxunrong.utils.CheckedItemStatistic.addCheckedItem;

public abstract class PageSpellChecker implements PageChecker {

    @Override
    public void doCheck(CheckablePage page) throws Exception {
        addCheckedItems(page, filterCustomisedWords(extractWrongWordsFromPage(page)));
    }

    protected void addCheckedItems(CheckablePage page, List<String> wrongWords) {
        StringBuffer errorSpellWords = new StringBuffer();
        for (String wrongWord : wrongWords) {
            errorSpellWords.append(wrongWord).append("\n");
        }
        addCheckedItem(new PlainTextItem(page.getPageName(), (errorSpellWords.length() == 0), errorSpellWords.toString()));
    }

    protected List<String> filterCustomisedWords(List<String> sourceWrongWords) {
        CustomisedDictionary customisedDictionary = getCustomisedDictionary();
        List<String> filteredWords = new ArrayList<String>();
        for(String wrongWord : sourceWrongWords) {
            if (!customisedDictionary.isWord(wrongWord)) {
                filteredWords.add(wrongWord);
            }
        }
        return filteredWords;
    }

    protected abstract CustomisedDictionary getCustomisedDictionary();

    protected abstract List<String> extractWrongWordsFromPage(CheckablePage page) throws Exception;
}
