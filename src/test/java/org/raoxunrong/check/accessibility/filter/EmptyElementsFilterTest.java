package org.raoxunrong.check.accessibility.filter;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EmptyElementsFilterTest extends AbstractHtmlFilterTest {

    private EmptyElementsFilter emptyElementsFilter;

    @Override
    public void setup() throws IOException, URISyntaxException {
        super.setup();
        emptyElementsFilter = new EmptyElementsFilter();
    }

    @Test
    public void shouldFilterEmptyElements() throws IOException, URISyntaxException {
        StringBuffer elementBuffer = new StringBuffer();
        elementBuffer.append("<h1></h1>").append("<h2></h2>").append("<h3></h3>")
                .append("<h4></h4>").append("<h5></h5>").append("<h6></h6>")
                .append("<table><th></th></table>").append("<a href=\"123\"></a>");

        setBodyResource(elementBuffer.toString());
        writeHtmlResource();
        openTestPage();

        Collection<WebElement> filterElements = emptyElementsFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(8));

        Iterator<WebElement> iterator = filterElements.iterator();
        WebElement webElement = iterator.next();
        assertThat(webElement.getTagName(), is("h1"));

        webElement = iterator.next();
        assertThat(webElement.getTagName(), is("h2"));

        webElement = iterator.next();
        assertThat(webElement.getTagName(), is("h3"));

        webElement = iterator.next();
        assertThat(webElement.getTagName(), is("h4"));

        webElement = iterator.next();
        assertThat(webElement.getTagName(), is("h5"));

        webElement = iterator.next();
        assertThat(webElement.getTagName(), is("h6"));

        webElement = iterator.next();
        assertThat(webElement.getTagName(), is("th"));

        webElement = iterator.next();
        assertThat(webElement.getTagName(), is("a"));
    }

    @Test
    public void shouldAcceptNotEmptyElements() throws IOException, URISyntaxException {
        StringBuffer elementBuffer = new StringBuffer();
        elementBuffer.append("<h1>12</h1>").append("<h2>12</h2>").append("<h3><12/h3>")
                .append("<h4>12</h4>").append("<h5>12</h5>").append("<h6>12</h6>")
                .append("<table><th>12</th></table>").append("<a href=\"123\">12</a>");

        setBodyResource(elementBuffer.toString());
        writeHtmlResource();
        openTestPage();

        Collection<WebElement> filterElements = emptyElementsFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(0));
    }
}
