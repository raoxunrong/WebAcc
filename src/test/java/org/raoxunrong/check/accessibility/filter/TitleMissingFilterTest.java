package org.raoxunrong.check.accessibility.filter;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TitleMissingFilterTest extends AbstractHtmlFilterTest {

    private TitleMissingFilter titleMissingFilter;

    @Override
    public void setup() throws IOException, URISyntaxException {
        super.setup();
        titleMissingFilter = new TitleMissingFilter();
    }

    @Test
    public void shouldFilterTitleMissingFrame() throws IOException, URISyntaxException {
        StringBuffer elementBuffer = new StringBuffer();
        elementBuffer.append("<frame/>").append("<frame title='' />");

        setBodyResource(elementBuffer.toString());
        setHeadResource("<title>123</title>");
        writeHtmlResource();
        openTestPage();

        Collection<WebElement> filterElements = titleMissingFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(2));
    }

    @Test
    public void shouldFIlterTitleMissingHead() throws IOException, URISyntaxException {
        writeHtmlResource();
        openTestPage();

        Collection<WebElement> filterElements = titleMissingFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(1));
    }
}
