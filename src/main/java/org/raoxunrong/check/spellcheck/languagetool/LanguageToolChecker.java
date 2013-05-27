package org.raoxunrong.check.spellcheck.languagetool;

import org.languagetool.JLanguageTool;
import org.languagetool.language.English;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import org.raoxunrong.check.PageChecker;
import org.raoxunrong.domain.item.PlainTextItem;
import org.raoxunrong.domain.page.CheckablePage;
import org.raoxunrong.utils.HTMLTextLoader;
import java.io.IOException;
import java.util.List;
import static org.raoxunrong.utils.CheckedItemStatistic.addCheckedItem;
import static org.raoxunrong.utils.HTMLTextLoader.getPlainText;

public class LanguageToolChecker implements PageChecker{

    private JLanguageTool langTool;

    public LanguageToolChecker(English english) throws IOException {
        this.langTool = new JLanguageTool(english);
    }

    @Override
    public void doCheck(CheckablePage page) {
        try {
            handleSpellCheckerInfo(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleSpellCheckerInfo(CheckablePage page) throws IOException {
        String texts = getPlainText(page);
        disableNotSpellingRules();
        String error;
        StringBuffer stringBuffer = new StringBuffer();
        List<RuleMatch> matches = langTool.check(texts);
        for (RuleMatch match : matches) {
            error = texts.substring(match.getFromPos(), match.getToPos()).trim();
            if(!error.isEmpty()){
                stringBuffer.append(error).append("\n");
            }
        }
        String errorSpellWords = stringBuffer.toString();
        addCheckedItem(new PlainTextItem(page.getPageName(), (errorSpellWords.length() == 0), errorSpellWords));
    }

    private void disableNotSpellingRules() {
        for (Rule rule : this.langTool.getAllRules()) {
            if (!rule.isSpellingRule()) {
                this.langTool.disableRule(rule.getId());
            }
        }
    }
}
