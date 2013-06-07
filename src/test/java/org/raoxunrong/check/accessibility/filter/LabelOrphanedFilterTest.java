package org.raoxunrong.check.accessibility.filter;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LabelOrphanedFilterTest extends AbstractHtmlFilterTest {
    private LabelOrphanedFilter labelOrphanedFilter;

    @Override
    public void setup() throws IOException, URISyntaxException {
        super.setup();
        labelOrphanedFilter = new LabelOrphanedFilter();
    }

    @Test
    public void shouldFilterOrphanedLabel() throws IOException, URISyntaxException {
        StringBuffer elementBuffer = new StringBuffer();
        elementBuffer.append("<label for=\"id1\"/>");

        setBodyResource(elementBuffer.toString());
        writeHtmlResource();
        openTestPage();

        Collection<WebElement> filterElements = labelOrphanedFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(1));
    }

    @Test
    public void shouldAcceptNotOrphanedLabel() throws IOException, URISyntaxException {
        StringBuffer elementBuffer = new StringBuffer();
        elementBuffer.append("<label for=\"id1\"/>").append("<input id=\"id1\" />");

        setBodyResource(elementBuffer.toString());
        writeHtmlResource();
        openTestPage();

        Collection<WebElement> filterElements = labelOrphanedFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(0));
    }
}
