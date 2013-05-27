package org.raoxunrong.check.spellcheck.dic;

import org.raoxunrong.check.spellcheck.dic.CustomisedDictionary;

public class DefaultCustomisedDictionary implements CustomisedDictionary {
    @Override
    public boolean isWord(String word) {
        return false;
    }

    @Override
    public void addNewWord(String word) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
