package org.raoxunrong.utils;

import org.jsoup.Jsoup;
import java.io.IOException;

public class HTMLTextLoader{

    public static String getTextFromUrl(String url) throws IOException {
        return getText(getHtml(url));
    }

    public static String getText(String htmlSource) throws IOException {
        return Jsoup.parse(htmlSource).text();
    }

    private static String getHtml(String url) throws IOException {
        return Jsoup.connect(url).get().html();
    }

}
