package org.raoxunrong.demo;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.raoxunrong.check.spellcheck.firefox.FirefoxSpellCheckerImprovedChecker;
import org.raoxunrong.domain.item.CheckedItem;
import org.raoxunrong.profile.FirefoxProfileHandle;
import org.raoxunrong.utils.CheckedItemStatistic;

import java.util.Collection;
import java.util.Iterator;

import static org.raoxunrong.check.CheckType.SpellingCheck;

public class SpellCheckerImprovedDemo {

    public static void main(String[] args) throws Exception {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        FirefoxProfileHandle firefoxProfileHandle = new FirefoxProfileHandle();
        FirefoxSpellCheckerImprovedChecker spellingCheckerFirefoxPluginChecker = new FirefoxSpellCheckerImprovedChecker();

        firefoxProfile = firefoxProfileHandle.installSpellChecker(firefoxProfile);
        FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxProfile);
        firefoxDriver.get("http://baidu.com/");

        BaiduPage baiduPage = new BaiduPage(firefoxDriver);
        spellingCheckerFirefoxPluginChecker.doCheck(baiduPage);

        Collection<CheckedItem> statisticInfo = CheckedItemStatistic.getStatisticInfo(SpellingCheck);
        Iterator<CheckedItem> iterator = statisticInfo.iterator();
        while(iterator.hasNext()){
            CheckedItem checkedItem = iterator.next();
            System.out.println(checkedItem.getItemName());
            System.out.println(checkedItem.getAdditionalInfo());
            System.out.println("-------------");
        }

    }

}