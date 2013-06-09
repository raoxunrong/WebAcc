package org.raoxunrong.profile;


import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.IOException;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FirefoxProfileHandleTest {

    @Test
    public void shouldInitWAVEPlugin() throws IOException {
        FirefoxProfile firefoxProfile = mock(FirefoxProfile.class);

        FirefoxProfileHandle firefoxProfileHandle = new FirefoxProfileHandle();
        firefoxProfileHandle.installWAVE(firefoxProfile);

        verify(firefoxProfile).addExtension(eq(FirefoxProfileHandle.class), eq("/wave_toolbar-1_1_6-fx.xpi"));
    }

    @Test
    public void shouldInitSpellCheckerPlugin() throws IOException {
        FirefoxProfile firefoxProfile = mock(FirefoxProfile.class);

        FirefoxProfileHandle firefoxProfileHandle = new FirefoxProfileHandle();
        firefoxProfileHandle.installSpellChecker(firefoxProfile);

        verify(firefoxProfile).addExtension(eq(FirefoxProfileHandle.class), eq("/spell_checker_improved.xpi"));
    }

}
