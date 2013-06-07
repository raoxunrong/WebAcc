package org.raoxunrong.check.accessibility.filter;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnchorMissingFilterTest extends AbstractHtmlFilterTest {

    private AnchorMissingFilter anchorMissingFilter;

    @Override
    public void setup() throws IOException, URISyntaxException {
        super.setup();
        anchorMissingFilter = new AnchorMissingFilter();
    }

    @Test
    public void shouldFilterAnchorMissingElements() throws IOException, URISyntaxException {
        String element = "<a href=\"#\">123</a>";

        setBodyResource(element);
        writeHtmlResource();
        openTestPage();

        Collection<WebElement> filterElements = anchorMissingFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(1));

        Iterator<WebElement> iterator = filterElements.iterator();
        WebElement webElement = iterator.next();
        assertThat(webElement.getTagName(), is("a"));
        assertThat(webElement.toString(), is("<a href=\"#\">"));
    }

    @Test
    public void shouldFilterAnchorErrorElements() throws IOException, URISyntaxException {
        String element = "<a href=\"#123\">123</a><input id=\"234\" />";

        setBodyResource(element);
        writeHtmlResource();
        openTestPage();

        Collection<WebElement> filterElements = anchorMissingFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(1));

        Iterator<WebElement> iterator = filterElements.iterator();
        WebElement webElement = iterator.next();
        assertThat(webElement.getTagName(), is("a"));
        assertThat(webElement.toString(), is("<a href=\"#123\">"));
    }

    @Test
    public void shouldAcceptAnchorElements() throws IOException, URISyntaxException {
        String element = "<a href=\"#123\">123</a><input id=\"123\" />";

        setBodyResource(element);
        writeHtmlResource();
        openTestPage();

        Collection<WebElement> filterElements = anchorMissingFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(0));
    }

    @Test
    public void shouldAcceptNormalAnchorElements() throws IOException, URISyntaxException {
        String element = "<a href=\"testLink\">123</a>";

        setBodyResource(element);
        writeHtmlResource();
        openTestPage();

        Collection<WebElement> filterElements = anchorMissingFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(0));
    }
}
