import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.raoxunrong.check.firefox.FirefoxSpellCheckerImprovedChecker;
import org.raoxunrong.domain.item.CheckedItem;
import org.raoxunrong.domain.page.CheckablePage;
import org.raoxunrong.profile.FirefoxProfileHandle;
import org.raoxunrong.utils.CheckedItemStatistic;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class SpellCheckerImprovedDemo {

    public static void main(String[] args) throws IOException {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        FirefoxProfileHandle firefoxProfileHandle = new FirefoxProfileHandle();
        FirefoxSpellCheckerImprovedChecker spellingCheckerFirefoxPluginChecker = new FirefoxSpellCheckerImprovedChecker();

        firefoxProfile = firefoxProfileHandle.installSpellChecker(firefoxProfile);
        FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxProfile);
        firefoxDriver.get("http://baidu.com/");

        BaiduPage googlePage = new BaiduPage(firefoxDriver);
        spellingCheckerFirefoxPluginChecker.doCheck(googlePage);

        Collection<CheckedItem> statisticInfo = CheckedItemStatistic.getStatisticInfo();
        Iterator<CheckedItem> iterator = statisticInfo.iterator();
        while(iterator.hasNext()){
            CheckedItem checkedItem = iterator.next();
            System.out.println(checkedItem.getItemName());
            System.out.println(checkedItem.getAdditionalInfo());
            System.out.println("-------------");
        }

    }

}