package org.raoxunrong.domain.page;

import org.openqa.selenium.WebDriver;

public interface CheckablePage {
    String getPageId();

    WebDriver getWebDriver();

    String getPageName();
}
