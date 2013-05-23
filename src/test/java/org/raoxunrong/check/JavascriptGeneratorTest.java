package org.raoxunrong.check;

import org.junit.Test;

import static org.raoxunrong.check.JavascriptGenerator.generateDispatchEventScript;

public class JavascriptGeneratorTest {

    @Test
    public void shouldGenerateDispatchEventScript() {
        String dispatchEventScript = generateDispatchEventScript("elementId", "eventId");


    }

}
