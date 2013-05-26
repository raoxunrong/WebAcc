package org.raoxunrong.check.spellcheck.languagetool;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AustralianEnglish;
import org.languagetool.rules.RuleMatch;
import org.raoxunrong.check.PageChecker;
import org.raoxunrong.domain.item.PlainTextItem;
import org.raoxunrong.domain.page.CheckablePage;
import org.raoxunrong.utils.HTMLTextLoader;
import java.io.IOException;
import java.util.List;
import static org.raoxunrong.utils.CheckedItemStatistic.addCheckedItem;

public class LanguageToolChecker implements PageChecker{

    @Override
    public void doCheck(CheckablePage page) {
        try {
            handleSpellCheckerInfo(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleSpellCheckerInfo(CheckablePage page) throws IOException {
        String htmlSource = page.getWebDriver().getPageSource();
        String texts = HTMLTextLoader.getText(htmlSource);
        StringBuffer stringBuffer = new StringBuffer();
        JLanguageTool langTool = new JLanguageTool(new AustralianEnglish());
        String error;
        List<RuleMatch> matches = langTool.check(texts);
        for (RuleMatch match : matches) {
            error = texts.substring(match.getFromPos(), match.getToPos()).trim();
            if(!error.isEmpty()){
                stringBuffer.append(error).append("\n");
            }
//            System.out.println("Potential error at " +
//                    match.getLine() + ", " + match.getColumn() + " to " + match.getEndLine() + ", " + match.getEndColumn() +
//                    ": " + error);
//            System.out.println(match.getMessage());
//            System.out.println("Suggested correction: " +
//                    match.getSuggestedReplacements());
        }
        String errorSpellWords = stringBuffer.toString();
        addCheckedItem(new PlainTextItem(page.getPageName(), (errorSpellWords.length() == 0), errorSpellWords));
    }
}
