package org.raoxunrong.demo;

import org.languagetool.language.AustralianEnglish;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.raoxunrong.check.spellcheck.common.LanguageToolChecker;
import org.raoxunrong.domain.item.CheckedItem;
import org.raoxunrong.domain.page.CheckablePage;
import org.raoxunrong.utils.CheckedItemStatistic;

import java.util.Collection;
import java.util.Iterator;

import static org.raoxunrong.check.CheckType.SpellingCheck;

public class LanguageToolCheckerDemo {

    public static void main(String args[]) throws Exception {
        WebDriver webDriver = new FirefoxDriver();
        LanguageToolChecker languageToolChecker = new LanguageToolChecker(new AustralianEnglish());
        webDriver.get("http://www.duckduckgo.com");
        DuckPage page = new DuckPage(webDriver);
        languageToolChecker.doCheck(page);

        Collection<CheckedItem> statisticInfo = CheckedItemStatistic.getStatisticInfo(SpellingCheck);
        Iterator<CheckedItem> iterator = statisticInfo.iterator();
        while(iterator.hasNext()){
            CheckedItem checkedItem = iterator.next();
            System.out.println(checkedItem.getItemName());
            System.out.println(checkedItem.getAdditionalInfo());
            System.out.println("-------------");
        }

        CheckedItemStatistic.generateReport("/Users/twer", SpellingCheck);
    }
}

class DuckPage implements CheckablePage {
    private WebDriver webDriver;

    DuckPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public String getPageId() {
        return "content_wrapper_homepage";
    }

    @Override
    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    public String getPageName() {
        return "Duck Home Page";
    }
}
