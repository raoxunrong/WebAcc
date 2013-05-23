package org.raoxunrong.check;

import org.junit.Test;

import static org.apache.commons.lang3.StringUtils.contains;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.raoxunrong.check.JavascriptGenerator.generateDispatchEventScript;

public class JavascriptGeneratorTest {

    @Test
    public void shouldGenerateDispatchEventScript() {
        String dispatchEventScript = generateDispatchEventScript("elementId", "eventId");

        assertThat(contains(dispatchEventScript, "var evt = document.createEvent(\"Events\");"), is(true));
        assertThat(contains(dispatchEventScript, "evt.initEvent(\"eventId\", true, false);"), is(true));
        assertThat(contains(dispatchEventScript, "var element = document.getElementById(\"elementId\");"), is(true));
        assertThat(contains(dispatchEventScript, "element.dispatchEvent(evt);"), is(true));
    }




}
