package org.raoxunrong.check.accessibility.filter;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.raoxunrong.domain.page.CheckablePage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractHtmlFilterTest {

    private File testResource;

    private StringBuffer htmlTemplate;
    private StringBuffer headInfo;
    private StringBuffer bodyInfo;

    private CheckablePage checkablePage;

    @Before
    public void setup() throws IOException, URISyntaxException {
        htmlTemplate = new StringBuffer();
        headInfo = new StringBuffer();
        bodyInfo = new StringBuffer();
        testResource = new File(getResourceURL().getPath());

        checkablePage = mock(CheckablePage.class);
        when(checkablePage.getWebDriver()).thenReturn(new HtmlUnitDriver());
    }

    protected URL getResourceURL() throws URISyntaxException, MalformedURLException {
        return AbstractHtmlFilterTest.class.getClassLoader().getResource("testResource.html");
    }

    protected void setBodyResource(String resource) {
        bodyInfo.append(resource);
    }

    protected void setHeadResource(String resource) {
        headInfo.append(resource);
    }

    protected void writeHtmlResource() throws IOException, URISyntaxException {
        htmlTemplate.append("<html><head>").append(headInfo).append("</head><body>").append(bodyInfo).append("</body></html>");
        FileUtils.writeStringToFile(testResource, htmlTemplate.toString());
    }

    protected void openTestPage() throws URISyntaxException, MalformedURLException {
        checkablePage.getWebDriver().get(getResourceURL().toString());
    }

    public CheckablePage getCheckablePage() {
        return checkablePage;
    }
}
