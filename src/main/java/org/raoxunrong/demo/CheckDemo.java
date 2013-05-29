package org.raoxunrong.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.raoxunrong.check.accessibility.firefox.FirefoxWaveAccessibilityChecker;
import org.raoxunrong.check.spellcheck.dic.PlainTextFileDictionary;
import org.raoxunrong.check.spellcheck.firefox.FirefoxSpellCheckerImprovedChecker;
import org.raoxunrong.profile.FirefoxProfileHandle;
import org.raoxunrong.utils.CheckedItemStatistic;

import static org.raoxunrong.check.CheckType.AccessibilityCheck;
import static org.raoxunrong.check.CheckType.SpellingCheck;

public class CheckDemo {

    public static void main(String[] args) throws Exception {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        FirefoxProfileHandle firefoxProfileHandle = new FirefoxProfileHandle();

        PlainTextFileDictionary plainTextFileDictionary = new PlainTextFileDictionary("plaintext_dictionary.txt");
        FirefoxSpellCheckerImprovedChecker spellingCheckerFirefoxPluginChecker = new FirefoxSpellCheckerImprovedChecker(plainTextFileDictionary);
        FirefoxWaveAccessibilityChecker firefoxWaveAccessibilityChecker = new FirefoxWaveAccessibilityChecker();

        firefoxProfile = firefoxProfileHandle.installSpellChecker(firefoxProfile);
        firefoxProfile = firefoxProfileHandle.installWAVE(firefoxProfile);

        FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxProfile);
        firefoxDriver.get("http://baidu.com/");

        BaiduPage baiduPage = new BaiduPage(firefoxDriver);
        spellingCheckerFirefoxPluginChecker.doCheck(baiduPage);
        firefoxWaveAccessibilityChecker.doCheck(baiduPage);

        WebElement inputBox = firefoxDriver.findElement(By.id("kw"));
        inputBox.sendKeys("suncorp");
        firefoxDriver.findElement(By.id("su")).submit();

        BaiduResultPage baiduResultPage = new BaiduResultPage(firefoxDriver);
        firefoxWaveAccessibilityChecker.doCheck(baiduResultPage);
        spellingCheckerFirefoxPluginChecker.doCheck(baiduResultPage);

        CheckedItemStatistic.generateReport("/Users/twer", SpellingCheck);
        CheckedItemStatistic.generateReport("/Users/twer", AccessibilityCheck);
    }
}
