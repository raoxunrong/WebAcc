package org.raoxunrong.check.accessibility.filter;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LabelMissingFilterTest extends AbstractHtmlFilterTest {

    private LabelMissingFilter labelMissingFilter;

    @Override
    public void setup() throws IOException, URISyntaxException {
        super.setup();
        labelMissingFilter = new LabelMissingFilter();
    }

    @Test
    public void shouldFilterLabelMissingElements() throws IOException, URISyntaxException {
        StringBuffer elementBuffer = new StringBuffer();
        elementBuffer.append("<input/>").append("<input type='checkbox'/>").append("<input type='password'/>")
                .append("<input type='radio'/>").append("<input type='text'/>").append("<input type=''/>");

        setBodyResource(elementBuffer.toString());
        writeHtmlResource();
        openTestPage();

        Collection<WebElement> filterElements = labelMissingFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(6));
    }

    @Test
    public void shouldAcceptInputWithLabelElements() throws IOException, URISyntaxException {
        StringBuffer elementBuffer = new StringBuffer();
        elementBuffer.append("<input id='1'/>").append("<label for='1'/>")
                .append("<input type='checkbox' id='2'/>").append("<label for='2'/>")
                .append("<input type='password' id='3'/>").append("<label for='3'/>")
                .append("<input type='radio' id='4'/>").append("<label for='4'/>")
                .append("<input type='text' id='5'/>").append("<label for='5'/>")
                .append("<input type='' id='6'/>").append("<label for='6'/>");

        setBodyResource(elementBuffer.toString());
        writeHtmlResource();
        openTestPage();

        Collection<WebElement> filterElements = labelMissingFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(0));
    }
}
