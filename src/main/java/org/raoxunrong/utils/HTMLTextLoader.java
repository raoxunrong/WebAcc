package org.raoxunrong.utils;

import org.jsoup.Jsoup;
import org.raoxunrong.domain.page.CheckablePage;

import java.io.IOException;

public class HTMLTextLoader{

    public static String getText(String htmlSource) throws IOException {
        return Jsoup.parse(htmlSource).text();
    }

    public static String getPlainText(CheckablePage page) throws IOException {
        return getText(page.getWebDriver().getPageSource());
    }
}
