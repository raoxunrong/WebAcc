package org.raoxunrong.check.accessibility.filter;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AltMissingFilterTest extends AbstractHtmlFilterTest {

    private AltMissingFilter altMissingFilter;

    @Override
    public void setup() throws IOException, URISyntaxException {
        super.setup();
        altMissingFilter = new AltMissingFilter();
    }

    @Test
    public void shouldFilterAltMissingElements() throws IOException, URISyntaxException {
        String imgElement = "<img src=\"123\" />";

        setBodyResource(imgElement);
        writeHtmlResource();
        openTestPage();

        List<WebElement> filterElements = (List) altMissingFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(1));
        assertThat(filterElements.get(0).getTagName(), is("img"));
        assertThat(filterElements.get(0).toString(), is(imgElement));
    }

    @Test
    public void shouldFilterAltEmptyElements() throws IOException, URISyntaxException {
        String imgElement = "<img src=\"123\" alt=\"\" />";

        setBodyResource(imgElement);
        writeHtmlResource();
        openTestPage();

        List<WebElement> filterElements = (List) altMissingFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(1));
        assertThat(filterElements.get(0).getTagName(), is("img"));
        assertThat(filterElements.get(0).toString(), is(imgElement));
    }

    @Test
    public void shouldFilterMapAltEmptyElements() throws IOException, URISyntaxException {
        String imgElement = "<map><area alt=\"\" /><area alt=\"\" src=\"123\" /><map>";

        setBodyResource(imgElement);
        writeHtmlResource();
        openTestPage();

        List<WebElement> filterElements = (List) altMissingFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(2));
        assertThat(filterElements.get(0).getTagName(), is("area"));
        assertThat(filterElements.get(0).toString(), is("<area alt=\"\" />"));
        assertThat(filterElements.get(1).toString(), is("<area alt=\"\" src=\"123\" />"));
    }

    @Test
    public void shouldFilterMapAltMissingElements() throws IOException, URISyntaxException {
        String imgElement = "<map><area /><area src=\"123\" /><map>";

        setBodyResource(imgElement);
        writeHtmlResource();
        openTestPage();

        List<WebElement> filterElements = (List) altMissingFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(2));
        assertThat(filterElements.get(0).getTagName(), is("area"));
        assertThat(filterElements.get(0).toString(), is("<area />"));
        assertThat(filterElements.get(1).toString(), is("<area src=\"123\" />"));
    }

    @Test
    public void shouldAcceptAltElements() throws IOException, URISyntaxException {
        String imgElement = "<img src=\"123\" alt=\"info\" />";

        setBodyResource(imgElement);
        writeHtmlResource();
        openTestPage();

        List<WebElement> filterElements = (List) altMissingFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(0));
    }

    @Test
    public void shouldAcceptMapAltElements() throws IOException, URISyntaxException {
        String imgElement = "<map>><area alt=\"test\" src=\"123\" /><map>";

        setBodyResource(imgElement);
        writeHtmlResource();
        openTestPage();

        List<WebElement> filterElements = (List) altMissingFilter.filter(getCheckablePage());
        assertThat(filterElements.size(), is(0));
    }
}
