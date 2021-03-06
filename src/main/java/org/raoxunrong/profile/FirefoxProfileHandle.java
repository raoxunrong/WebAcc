package org.raoxunrong.profile;

import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.IOException;

public class FirefoxProfileHandle {

    private static final String WAVE_PLUGIN_NAME = "wave_toolbar-1_1_6-fx.xpi";
    private static final String SPELL_CHECKER_PLUGIN_NAME = "spell_checker_improved.xpi";

    public FirefoxProfile installWAVE(FirefoxProfile firefoxProfile) throws IOException {
        firefoxProfile.addExtension(FirefoxProfileHandle.class, "/" + WAVE_PLUGIN_NAME);
        return firefoxProfile;
    }

    public FirefoxProfile installSpellChecker(FirefoxProfile firefoxProfile) throws IOException {
        firefoxProfile.addExtension(FirefoxProfileHandle.class, "/" + SPELL_CHECKER_PLUGIN_NAME);
        return firefoxProfile;
    }
}
