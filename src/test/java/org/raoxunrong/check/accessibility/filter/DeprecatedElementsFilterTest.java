package org.raoxunrong.check.accessibility.filter;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DeprecatedElementsFilterTest extends AbstractHtmlFilterTest {

    private DeprecatedElementsFilter deprecatedElementsFilter;

    @Override
    public void setup() throws IOException, URISyntaxException {
        super.setup();
        deprecatedElementsFilter = new DeprecatedElementsFilter();
    }

    @Test
    public void shouldFilterAnchorMissingElements() throws IOException, URISyntaxException {
        String element = "<marquee src=\"123\"></marquee> <blink src=\"223\"/>";

        setBodyResource(element);
        writeHtmlResource();
        openTestPage();

        Collection<WebElement> filterElements = deprecatedElementsFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(2));

        Iterator<WebElement> iterator = filterElements.iterator();
        WebElement webElement = iterator.next();
        assertThat(webElement.getTagName(), is("marquee"));
        assertThat(webElement.toString(), is("<marquee src=\"123\" />"));

        webElement = iterator.next();
        assertThat(webElement.getTagName(), is("blink"));
        assertThat(webElement.toString(), is("<blink src=\"223\" />"));
    }
}
