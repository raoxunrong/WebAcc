package org.raoxunrong.check.spellcheck.common;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.language.English;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import org.raoxunrong.check.spellcheck.dic.CustomisedDictionary;
import org.raoxunrong.check.spellcheck.dic.DefaultCustomisedDictionary;
import org.raoxunrong.check.spellcheck.PageSpellChecker;
import org.raoxunrong.domain.page.CheckablePage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.raoxunrong.utils.HTMLTextLoader.getPlainText;

public class LanguageToolChecker extends PageSpellChecker {

    private JLanguageTool langTool;
    private CustomisedDictionary customisedDictionary;

    public LanguageToolChecker(Language language) throws IOException {
        this.customisedDictionary = new DefaultCustomisedDictionary();
        this.langTool = new JLanguageTool(language);
//        disableNotSpellingRules();
    }

    @Override
    protected CustomisedDictionary getCustomisedDictionary() {
        return customisedDictionary;
    }

    @Override
    protected List<String> extractWrongWordsFromPage(CheckablePage page) throws IOException {
        String texts = getPlainText(page);
        List<String> wrongWords = new ArrayList<String>();
        String error;
        for (RuleMatch match : langTool.check(texts)) {
            error = texts.substring(match.getFromPos(), match.getToPos()).trim();
            if (!error.isEmpty()) {
                wrongWords.add(error);
            }
        }

        return wrongWords;
    }

    private void disableNotSpellingRules() {
        for (Rule rule : this.langTool.getAllRules()) {
            if (!rule.isSpellingRule()) {
                this.langTool.disableRule(rule.getId());
            }
        }
    }
}
